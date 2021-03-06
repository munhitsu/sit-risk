package org.jim.election;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.jgap.*;
import org.jgap.impl.*;
import org.jim.Config;
import org.jim.election.genes.*;
import org.jim.parser.util.*;
import org.joone.net.NeuralNet;

/**
 * Class implementing the Fitness Function for Neural Network Chromosome. It
 * extends the standard FitnessFunction. When asked for Chromosome evaluation it
 * creates the network as specified in Chromosome and performs a quick learning.
 * <p>
 * Chromosome format: <table>
 * <tr>
 * <td>0</td>
 * <td>IntegerGene</td>
 * <td>inputs usage</td>
 * </tr>
 * <tr>
 * <td>1</td>
 * <td>IntegerGene(0,2)</td>
 * <td>layerType</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>IntegerGene(1,32)</td>
 * <td>layerWidthH1</td>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>IntegerGene(1,32)</td>
 * <td>layerWidthH2</td>
 * </tr>
 * <tr>
 * <td>4</td>
 * <td>IntegerGene(1,32)</td>
 * <td>layerWidthH3</td>
 * </tr>
 * <tr>
 * <td>5</td>
 * <td>DoubleGene()</td>
 * <td>layerMomentun</td>
 * </tr>
 * <tr>
 * <td>6</td>
 * <td>DoubleGene()</td>
 * <td>layerLearningRate</td>
 * </tr>
 * </table>
 * </p>
 * 
 * @author Mateusz Lapsa
 */
public class NetworkArchitectureRMSEFitnessFunction extends FitnessFunction {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    static Logger logger = Logger
            .getLogger(NetworkArchitectureRMSEFitnessFunction.class.getClass()
                    .getName());

    /**
     * Assumed cound of Alleles that specify the Chromosome
     */
    public static final int GENES_LENGTH = 7;

    /**
     * Cache for evaluated VRMSE
     */
    private double evaluatedVRMSE;

    /**
     * Cache for evaluated InputsCount
     */
    private int evaluatedInputsCount;

    /**
     * Cache for evaluated HiddenNeuronsCount
     */
    private int evaluatedHiddenNeuronsCount;

    /**
     * Specifies maximum length ot network study period in cicles
     */
    private int totCicles = 1000;

    /**
     * Store for evaluatedNetwork if it was better then all before
     */
    private NeuralNet evaluatedBestNet = null;

    private QualityEvalSet[] evaluatedBestNetQuality = null;

    /**
     * Fitness function is coded into int format. First is reserved - filled
     * with 0s. Second octet is the opposition of evaluated VRMSE. Third is the
     * opposition of evaluated network inputs count. Fourth is the opposition of
     * evaluated network hidden neurons count. Sho the larger is the evaluated
     * value the better is the network.
     * 
     * @return
     * @param c
     * @see org.jgap.FitnessFunction#evaluate(org.jgap.Chromosome)
     */
    protected int evaluate(Chromosome c) {
        //		TODO add interpretation of quality (first octet), quality can be
        // evaluated for three different sets (earning, validating, test)
        if (c.getGenes().length == GENES_LENGTH) {
            logger.info("evaluating: " + ChromosomeToString(c));
            int fitness;
            try {
                evaluateChromosome(c);
                double penalty = 1;
                //                to omit sensless situations when validating quality is higner
                // than test quality
                if (getEvaluatedNetTestQuality() < getEvaluatedNetValidatingQuality()) {
                    penalty = 2;
                }
                logger.info("<evaluaded " + " testQuality="
                        + getEvaluatedNetTestQuality() + " vrmse="
                        + getEvaluatedNetVRMSE() + " inputs="
                        + getEvaluatedNetInputsCount() + " hidden="
                        + getEvaluatedNetHiddenNeuronsCount() + "/>");
                fitness = Math.min(
                        (int) (8 / (getEvaluatedNetTestQuality() * penalty)),
                        127);
                fitness <<= 8;
                fitness += (int) (1 / getEvaluatedNetVRMSE());
                fitness <<= 8;
                fitness += (256 / getEvaluatedNetInputsCount());
                fitness <<= 8;
                fitness += (256 / (getEvaluatedNetHiddenNeuronsCount()));
            } catch (Exception e) {
                logger.error("Trying to survive (removing network)", e);
                fitness = 0;
            }
            System.gc();
            logger.debug("fitness=" + fitness);
            return fitness;
        } else {
            logger.error("requested to evaluate unknown chromosome type");
            return 0;
        }
    }

    /**
     * Initializes a random Chromosome compatibile wirh
     * NetworkArchitectureRMSEFitnessFunction
     * 
     * @return Gene array
     */
    public static final Gene[] getSampleGenes() {
        Gene[] genes = new Gene[GENES_LENGTH];
        int i = 0;
        genes[i] = new IntegerGene(0, Integer.MAX_VALUE); //inputs
        genes[++i] = new IntegerGene(0, 2); //layerType
        genes[++i] = new IntegerGene(1, 32); //layerWidthH1
        genes[++i] = new IntegerGene(1, 32); //layerWidthH2
        genes[++i] = new IntegerGene(1, 32); //layerWidthH3
        //		TODO think of limits
        genes[++i] = new DoubleGene(); //layerMomentun
        genes[++i] = new DoubleGene(); //layerLearningRate
        return genes;
    }

    /**
     * Provides the function to display the Chromosome. It assumes that
     * Chromosome is a valid Naural Network specification. Usefull for
     * debugging.
     * 
     * @return the string representation
     * @param c
     *            the Chromosome used to specify the Network.
     */
    public static String ChromosomeToString(Chromosome c) {
        String str = "<chromosome ";
        Integer Inputs = (Integer) (c.getGene(0).getAllele());

        str += "inputs='" + Inputs.intValue() + "' ";
        str += "inputs='" + getGeneInputsString(c) + "' ";
        str += "layerType='" + getGeneLayerType(c) + "' ";
        str += "h1='" + getGeneWidthH1(c) + "' ";
        str += "h2='" + getGeneWidthH2(c) + "' ";
        str += "h3='" + getGeneWidthH3(c) + "' ";
        str += "learningRate='" + getGeneLearningRate(c) + "' ";
        str += "momentum='" + getGeneMomentum(c) + "'/>";

        return str;
    }

    /**
     * Takes out the first Allele from Chromosome and changes it from int into a
     * table of boolean that represents which inputs were used
     * 
     * @return the inputs usage table
     * @param c
     *            the Chromosome
     */
    public static boolean[] getGeneInputs(Chromosome c) {
        Integer alleleInputs = (Integer) (c.getGene(0).getAllele());
        int m_geneInputsInt = alleleInputs.intValue();
        boolean[] m_geneInputs = new boolean[32];
        String debug = "";
        for (int i = 0; i < 32; i++) {
            if ((m_geneInputsInt & 1) == 1) {
                m_geneInputs[i] = true;
                debug += "1";
            } else {
                m_geneInputs[i] = false;
                debug += "0";
            }
            m_geneInputsInt = m_geneInputsInt >> 1;
        }
        logger.debug("inputs:" + debug);
        return m_geneInputs;
    }

    /**
     * Takes out the first Allele from Chromosome and changes it from int into a
     * string of 0 or 1
     * 
     * @return the inputs usage String
     * @param c
     *            the Chromosome
     */
    public static String getGeneInputsString(Chromosome c) {
        Integer alleleInputs = (Integer) (c.getGene(0).getAllele());
        int m_geneInputsInt = alleleInputs.intValue();
        boolean[] m_geneInputs = new boolean[32];
        String debug = "";
        for (int i = 0; i < 32; i++) {
            if ((m_geneInputsInt & 1) == 1) {
                m_geneInputs[i] = true;
                debug += "1";
            } else {
                m_geneInputs[i] = false;
                debug += "0";
            }
            m_geneInputsInt = m_geneInputsInt >> 1;
        }
        return debug;
    }

    /**
     * Takes out the first Allele from Chromosome - the enabled inputs array.
     * 
     * @param c
     *            Chromosome
     * @return the number of enabled inputs
     */
    public static int getGeneInputsCount(Chromosome c) {
        Integer alleleInputs = (Integer) (c.getGene(0).getAllele());
        int m_geneInputsInt = alleleInputs.intValue();
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((m_geneInputsInt & 1) == 1) {
                count++;
            }
            m_geneInputsInt = m_geneInputsInt >> 1;
        }
        return count;
    }

    /**
     * Takes out the first Allele from Chromosome - the enabled inputs array.
     * Than generates the selection string in format: "10,11,14,20". This format
     * is nativelly used by joone.
     * 
     * @param c
     *            the Chromosome
     * @return selection string
     */
    public static String getGeneInputsSelectionString(Chromosome c) {
        Integer alleleInputs = (Integer) (c.getGene(0).getAllele());
        int m_geneInputsInt = alleleInputs.intValue();
        String selection = null;
        for (int i = 0; i < 32; i++) {
            if ((m_geneInputsInt & 1) == 1) {
                if (selection == null) {
                    selection = String.valueOf(i + 1);
                } else
                    selection += "," + String.valueOf(i + 1);
            }
            m_geneInputsInt = m_geneInputsInt >> 1;
        }
        return selection;
    }

    /**
     * Takes out of the Chromosome the Gene responsible of LayerType
     * 
     * @return
     * @param c
     *            the Chromosome
     * @returns int value of layerType
     */
    public static int getGeneLayerType(Chromosome c) {
        Integer alleleLayerType = (Integer) (c.getGene(1).getAllele());
        int m_geneLayerType = alleleLayerType.intValue();
        return m_geneLayerType;
    }

    /**
     * Takes out of the Chromosome the Gene responsible of first hidden layer
     * width
     * 
     * @return
     * @param c
     *            the Chromosome
     * @returns width of the first hidden layer
     */
    public static int getGeneWidthH1(Chromosome c) {
        Integer alleleWidthH1 = (Integer) (c.getGene(2).getAllele());
        int m_geneWidthH1 = alleleWidthH1.intValue();
        return m_geneWidthH1;
    }

    /**
     * Takes out of the Chromosome the Gene responsible of second hidden layer
     * width
     * 
     * @return
     * @param c
     *            the Chromosome
     * @returns width of the second hidden layer
     */
    public static int getGeneWidthH2(Chromosome c) {
        Integer alleleWidthH2 = (Integer) (c.getGene(3).getAllele());
        int m_geneWidthH2 = alleleWidthH2.intValue();
        return m_geneWidthH2;
    }

    /**
     * Takes out of the Chromosome the Gene responsible of third hidden layer
     * width
     * 
     * @return
     * @param c
     *            the Chromosome
     * @returns width of third hidden layer
     */
    public static int getGeneWidthH3(Chromosome c) {
        Integer alleleWidthH3 = (Integer) (c.getGene(4).getAllele());
        int m_geneWidthH3 = alleleWidthH3.intValue();
        return m_geneWidthH3;
    }

    /**
     * Takes out of the Chromosome the Gene responsible of Momentum
     * 
     * @return
     * @param c
     *            the Chromosome
     * @returns double value of Momentum
     */
    public static double getGeneMomentum(Chromosome c) {
        Double alleleMomentum = (Double) (c.getGene(5).getAllele());
        double m_geneMomentum = alleleMomentum.doubleValue();
        return m_geneMomentum;
    }

    /**
     * Takes out of the Chromosome the Gene responsible of LearningRate
     * 
     * @return
     * @param c
     *            the Chromosome
     * @returns double value of LearningRate
     */
    public static double getGeneLearningRate(Chromosome c) {
        Double alleleLearningRate = (Double) (c.getGene(6).getAllele());
        double m_geneLearningRate = alleleLearningRate.doubleValue();
        return m_geneLearningRate;
    }

    /**
     * The interior of evalate(Chromosome c) Function takes care of creating and
     * runing the network
     * 
     * @param c
     */
    private void evaluateChromosome(Chromosome c) {
        String inputFile = Config.getProperty(Config.P_DATA_INPUT_XML);
        //		String networkFile = Config.getProperty(Config.P_SERIALIZED_NETWORK);
        String normalizeFile = Config.getProperty(Config.P_POLA_NORMALIZATION);
        NetworkTeacher eng = new NetworkTeacher(inputFile);

        eng.setInput(Config.getProperty(Config.P_LEARNIG_XPATH), Config
                .getProperty(Config.P_VALIDATING_XPATH), Config
                .getProperty(Config.P_INPUT_XPATH));

        eng.setSamples(Config.getProperty(Config.P_LEARNIG_XPATH), Config
                .getProperty(Config.P_VALIDATING_XPATH), Config
                .getProperty(Config.P_OUTPUT_XPATH));
        Document preprocessDocument = null;
        try {
            preprocessDocument = DocumentTools.parse(normalizeFile);
            eng.setPreprocessDocument(preprocessDocument);
        } catch (Exception e) {
            logger.info("error parsing normalization file");
        }

        //		String layersString = Config.getProperty()

        int[] layers = { getGeneWidthH1(c), getGeneWidthH2(c),
                getGeneWidthH3(c) };
        //		boolean[] enabledInputs = getGeneInputs(c);
        String enabledInputsSelectionString = getGeneInputsSelectionString(c);
        eng.setColumnsSelector(enabledInputsSelectionString);
        eng.setColumnsCount(getGeneInputsCount(c));
        eng.setHiddenLayersSizes(layers);
        eng.setLayerType(getGeneLayerType(c));
        eng.setLearningRate(getGeneLearningRate(c));
        eng.setMomentum(getGeneMomentum(c));
        eng.setTotCicles(getTotCicles());

        setEvaluatedBestNet(eng.goSync());
        logger.debug("nn(" + getEvaluatedBestNet().getParam("cycle")
                + ").elected");
        evaluatedVRMSE = eng.getBestVRMSE();

        evaluatedInputsCount = getEvaluatedBestNet().getInputLayer()
                .getDimension();
        //		for (int i = 0; i < enabledInputs.length; i++)
        //			if (enabledInputs[i]) evaluatedInputsCount++;
        evaluatedHiddenNeuronsCount = layers[0] + layers[1] + layers[2];
        logger.debug("evaluatedInputsCount:" + evaluatedInputsCount);
        logger.debug("evaluatedHiddenNeuronsCount:"
                + evaluatedHiddenNeuronsCount);
        //		bestNet.setParam("evaluationVRMSE",new Double(eng.getBestVRMSE()));
        //		bestNet.setParam("evaluationRMSE",new Double(eng.getBestRMSE()));

        Document inputDocument;
        try {
            inputDocument = DocumentTools.parse(inputFile);
        } catch (DocumentException e) {
            logger.error(e);
            return;
        }
        if (preprocessDocument != null) {
            DocumentTools.normalize(inputDocument, preprocessDocument);
        }

        evaluatedBestNetQuality = evaluateNetworkQuality(getEvaluatedBestNet()
                .cloneNet(), c, inputDocument);
    }

    /**
     * Internal class that encapsulates: String: name of evaluation set Tables:
     * inputTable, outputTable, expectedTable, errorsTable. <br>
     * Scalars: standard deviation of all tables except inputTable, quality of
     * output and round mean square error
     * 
     * @author Mateusz Lapsa
     */
    class QualityEvalSet {
        double[][] inputTable;

        double[][] outputTable;

        double[][] expectedTable;

        double[][] errorsTable;

        String name;

        double sdExpected;

        double sdErrors;

        double sdOutput;

        double quality;

        double rmse;

        /**
         * Constructor
         * 
         * @param name
         *            of evaluation set
         * @param inputTable
         *            the table of network inputs
         * @param expectedTable
         *            the table of expected network outputs
         */
        public QualityEvalSet(String name, double[][] inputTable,
                double[][] expectedTable) {
            this.inputTable = inputTable;
            this.expectedTable = expectedTable;
            this.name = name;
        }

        /**
         * Constructor
         * 
         * @param name
         *            of evaluation set
         * @param inputDocument
         *            the Document object of xml input data file
         * @param xPathA
         *            the xpath selecting which patients to use
         * @param inputXPathB
         *            the xpath selecting what parameters to use as network
         *            input
         * @param expectedXPathB
         *            the xpath selecting what parameters to use as network
         *            expected output
         */
        public QualityEvalSet(String name, Document inputDocument,
                String xPathA, String inputXPathB, String expectedXPathB) {
            inputTable = Table.getXMLData(inputDocument, xPathA, inputXPathB);
            expectedTable = Table.getXMLData(inputDocument, xPathA,
                    expectedXPathB);
            this.name = name;
        }

        /**
         * Assuming that inputTable, expectedTable and outputTable are filled it
         * evaluates the: standard deviations, quality and round mean square error
         */
        public void evaluate() {
            errorsTable = Table.diffTables(outputTable, expectedTable);
            logger.debug("expectedTable,outputTable,errorsTable:"
                    + Table.printTable(Table.addColumns(Table.addColumns(
                            expectedTable, outputTable), errorsTable)));
            sdExpected = Table.standardDeviation(expectedTable);
            sdErrors = Table.standardDeviation(errorsTable);
            sdOutput = Table.standardDeviation(outputTable);
            quality = sdErrors / sdExpected;
            rmse = Table.rms(errorsTable);
        }
    }

    /**
     * Function is responsible for evaluating outputs quality for all three sets
     * (learning, validatin, testing)
     * 
     * @param nNet
     *            neural network to evaluate
     * @param c
     *            Chromosome of network architecture (so far only enabled inputs
     *            allele is used)
     * @param inputDocument
     *            the Document object of xml patients database
     * @return the array of three QualityEvalSet objects each for separate set
     */
    private QualityEvalSet[] evaluateNetworkQuality(NeuralNet nNet,
            Chromosome c, Document inputDocument) {
        logger.info("evaluateNetworkQuality - start");
        NetworkExecutor netExec = new NetworkExecutor(nNet);
        String enabledInputsSelectionString = getGeneInputsSelectionString(c);
        logger.debug("enabledInputsSelectionString = "
                + enabledInputsSelectionString);
        //        boolean[] enabledInputs = getGeneInputs(c);

        //      xpathA
        String xpathInput = Config.getProperty(Config.P_INPUT_XPATH);
        String xpathOutput = Config.getProperty(Config.P_OUTPUT_XPATH);

        //      xpathB
        String xpathLearning = Config.getProperty(Config.P_LEARNIG_XPATH);
        String xpathValidating = Config.getProperty(Config.P_VALIDATING_XPATH);
        String xpathTesting = Config.getProperty(Config.P_TESTING_XPATH);

        QualityEvalSet[] qess = new QualityEvalSet[3];

        qess[0] = new QualityEvalSet("training", inputDocument, xpathLearning,
                xpathInput, xpathOutput);
        qess[1] = new QualityEvalSet("validating", inputDocument,
                xpathValidating, xpathInput, xpathOutput);
        qess[2] = new QualityEvalSet("testing", inputDocument, xpathTesting,
                xpathInput, xpathOutput);

        for (int i = 0; i < qess.length; i++) {
            logger.info("evaluateNetworkQuality - prepareNetwork: "
                    + qess[i].name);
            netExec.prepareNetwork(qess[i].inputTable,
                    enabledInputsSelectionString);
            logger.info("evaluateNetworkQuality - goSync: " + qess[i].name);
            netExec.go();
            logger.info("evaluateNetworkQuality - getOutputTable: "
                    + qess[i].name);
            qess[i].outputTable = netExec.getOutputTable();
            qess[i].evaluate();
            logger.info(qess[i].name + ": vExp=" + qess[i].sdExpected
                    + " vOut=" + qess[i].sdOutput + " vErr=" + qess[i].sdErrors
                    + " q=" + qess[i].quality + " rmse=" + qess[i].rmse);
        }
        logger.info("evaluateNetworkQuality - stop");
        return qess;
    }

    /**
     * @return Returns the cached value of VRMSE
     */
    double getEvaluatedNetVRMSE() {
        return evaluatedVRMSE;
    }

    /**
     * @return Returns the cached inputs count
     */
    int getEvaluatedNetInputsCount() {
        return evaluatedInputsCount;
    }

    /**
     * @return Returns the cached neurons count
     */
    int getEvaluatedNetHiddenNeuronsCount() {
        return evaluatedHiddenNeuronsCount;
    }

    /**
     * @param totCicles
     *            The totCicles to set.
     */
    public void setTotCicles(int totCicles) {
        this.totCicles = totCicles;
    }

    /**
     * @return Returns the totCicles.
     */
    public int getTotCicles() {
        return totCicles;
    }

    /**
     * @param evaluatedBestNet
     *            The evaluatedBestNet to set.
     */
    private void setEvaluatedBestNet(NeuralNet evaluatedBestNet) {
        this.evaluatedBestNet = evaluatedBestNet;
    }

    /**
     * @return Returns the evaluatedBestNet.
     */
    public NeuralNet getEvaluatedBestNet() {
        return evaluatedBestNet;
    }

    /**
     * @return Returns the quality of network for test set
     */
    public double getEvaluatedNetTestQuality() {
        return evaluatedBestNetQuality[2].quality;
    }

    /**
     * @return Returns the quality of network for validating set
     */
    public double getEvaluatedNetValidatingQuality() {
        return evaluatedBestNetQuality[1].quality;
    }
}