package org.jim.parser.util;

import java.util.*;
import org.apache.log4j.*;
import org.dom4j.*;
import org.joone.engine.*;
import org.joone.engine.learning.*;
import org.joone.net.*;
import org.joone.util.*;

/**
 * The class to create, teach and run network.
 * 
 * @author Mateusz Lapsa
 */
public class NetworkTeacher extends NetworkExecutor {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    static Logger logger = Logger.getLogger(NetworkTeacher.class.getClass()
            .getName());

    /**
     * Initial learning rate (before it is modified with extra plugins)
     */
    double learningRate = 0.2;

    /**
     * Initial inertia
     */
    double momentum = 0.1;

    /**
     * Table hidden layers neurons count
     */
    int[] hiddenLayersSizes;

    /**
     * The xpath selecting patients (teaching set)
     */
    String inputXPathA;

    /**
     * The xpath selecting patients (validating set)
     */
    String inputValXPathA;

    /**
     * The xpath selecting patients parameters
     */
    String inputXPathB;

    /**
     * The xpath selecting patients (teaching set)
     */
    String samplesXPathA;

    /**
     * The xpath selecting patients (validating set)
     */
    String samplesValXPathA;

    /**
     * The xpath selecting expected patients parameters
     */
    String samplesXPathB;

    /**
     * The file storing patients database
     */
    String inputFile;

    /**
     * The date of execution start
     */
    Date startDate;

    /**
     * The date of execution end
     */
    Date endDate;

    /**
     * Columns selector String
     */
    String columnsSelector;

    /**
     * As selected columns are kept in string format, this attribute holds
     * number of enabled columns.
     */
    int ColumnsCount;

    /**
     * Best network so far.
     */
    private NeuralNet bestNet;

    /**
     * Tool object that elects the network
     */
    NetworkElector netElector;

    /**
     * The document with normalization information
     */
    private Document preprocessDocument = null;

    /**
     * Used layer type. Default is LAYER_SIGMOID
     */
    private int layerType = LAYER_SIGMOID;

    /**
     * y = 1/(1+exp(-x))
     */
    public static final int LAYER_SIGMOID = 0;

    /**
     * y = log(1+x) ;/ x>=0 y = log(1-x) ;/ x <0
     */
    public static final int LAYER_LOGARITHMIC = 1;

    /**
     * y = (exp(x)-exp(-x))/(exp(x)+exp(-x))
     */
    public static final int LAYER_TANH = 2;

    /**
     * Saved best network RMSE of teaching set
     */
    private double bestRMSE;

    /**
     * Saved best network RMSE of validating set
     */
    private double bestVRMSE;

    /**
     * How many learning cicles should engine perform
     */
    private int totCicles = 2000;

    /**
     * Network to teach
     */
    private NeuralNet nnet;

    /**
     * Constructor initializing inputFile and startDate
     * 
     * @param inputFile
     */
    public NetworkTeacher(String inputFile) {
        super();
        startDate = new Date();
        this.inputFile = inputFile;
    }

    /**
     * Sets network learning set
     * 
     * @param inputXPathA
     * @param inputValXPathA
     * @param inputXPathB
     */
    public void setInput(String inputXPathA, String inputValXPathA,
            String inputXPathB) {
        this.inputXPathA = inputXPathA;
        this.inputValXPathA = inputValXPathA;
        this.inputXPathB = inputXPathB;
    }

    /**
     * Sets network validating set
     * 
     * @param samplesXPathA
     * @param samplesValXPathA
     * @param samplesXPathB
     */
    public void setSamples(String samplesXPathA, String samplesValXPathA,
            String samplesXPathB) {
        this.samplesXPathA = samplesXPathA;
        this.samplesValXPathA = samplesValXPathA;
        this.samplesXPathB = samplesXPathB;
    }

    /**
     * @param hiddenLayersSizes
     *            updates table hiddenLayersSizes
     */
    public void setHiddenLayersSizes(int[] hiddenLayersSizes) {
        this.hiddenLayersSizes = hiddenLayersSizes;
    }

    /**
     * Async executor. Loads datafile. Creates the network, and starts the
     * execution.
     */
    public void go() {
        logger.debug("go() - start");
        state = STATE_STARTED;
        Document doc;
        try {
            doc = DocumentTools.parse(inputFile);
        } catch (DocumentException e) {
            logger.error(e);
            return;
        }
        if (preprocessDocument != null) {
            DocumentTools.normalize(doc, preprocessDocument);
        }
        XMLInputSynapse inputStream;
        //		= new XMLInputSynapse(doc, inputXPathA, inputXPathB);
        XMLInputSynapse inputValStream;
        //		= new XMLInputSynapse(doc, inputValXPathA, inputXPathB);
        XMLInputSynapse samplesStream;
        //		= new XMLInputSynapse(doc, samplesXPathA, samplesXPathB);
        XMLInputSynapse samplesValStream;
        //		= new XMLInputSynapse(doc, samplesValXPathA, samplesXPathB);

        logger.debug("inputXPathA:" + inputXPathA);
        logger.debug("inputXPathB:" + inputXPathB);
        logger.debug("inputValXPathA:" + inputXPathB);
        logger.debug("samplesXPathA:" + samplesXPathA);
        logger.debug("samplesValXPathA:" + samplesValXPathA);
        logger.debug("samplesXPathB:" + samplesXPathB);

        //        if (enabledInputs == null) {
        inputStream = new XMLInputSynapse(doc, inputXPathA, inputXPathB);
        inputValStream = new XMLInputSynapse(doc, inputValXPathA, inputXPathB);
        //        } else {
        //            inputStream = new XMLInputSynapse(doc, inputXPathA, inputXPathB,
        //                    enabledInputs);
        //            inputValStream = new XMLInputSynapse(doc, inputValXPathA,
        //                    inputXPathB, enabledInputs);
        //        }
        if (columnsSelector != null) {
            logger.debug("inputStream.setAdvancedColumnSelector("
                    + columnsSelector + ")");
            inputStream.setAdvancedColumnSelector(columnsSelector);
            inputStream.check();
            logger.debug("inputValStream.setAdvancedColumnSelector("
                    + columnsSelector + ")");
            inputValStream.setAdvancedColumnSelector(columnsSelector);
            inputValStream.check();
        }

        samplesStream = new XMLInputSynapse(doc, samplesXPathA, samplesXPathB);
        samplesValStream = new XMLInputSynapse(doc, samplesValXPathA,
                samplesXPathB);
        inputStream.setName("inputStream");
        inputValStream.setName("inputValStream");
        samplesStream.setName("samplesStream");
        samplesValStream.setName("samplesValStream");

        int inputWidth = inputStream.getInitialInputWidth();
        int trainingPatterns = inputStream.getInputHeight();
        int validationPatterns = inputValStream.getInputHeight();
        int samplesWidth = samplesStream.getInitialInputWidth();
        //        logger.debug("inputWidth: " + inputWidth);
        //        logger.debug("samplesWidth: " + samplesWidth);
        logger.debug("trainingPatterns: " + trainingPatterns);
        logger.debug("validationPatterns: " + validationPatterns);

        //		int hiddenWidth = (int)((double)inputWidth*hiddenWidthMul);

        //		logger.debug("hiddenWidth: "+hiddenWidth);
        logger.debug("hiddenLayersSizes.length: " + hiddenLayersSizes.length);

        Vector hiddenLayers = new Vector();
        for (int i = 0; i < hiddenLayersSizes.length; i++) {
            logger.debug("hiddenLayersSizes[" + i + "] = "
                    + hiddenLayersSizes[i]);
            SimpleLayer hx = (SimpleLayer) newLayer();
            hiddenLayers.add(hx);
            hx.setRows(hiddenLayersSizes[i]);
            if (i > 0)
                connectLayers((SimpleLayer) hiddenLayers.elementAt(i - 1), hx,
                        new FullSynapse());
        }

        LearningSwitch inSwitch = new LearningSwitch();
        SimpleLayer in = (SimpleLayer) newLayer();
        if (getColumnsCount() == 0) {
            logger
                    .warn("getColumnsCount() == 0 => assuming inputsWidth = initialInputsWidth() = "
                            + inputWidth);
        } else {
            inputWidth = getColumnsCount();
        }
        logger.debug("in.setRows(" + inputWidth + ")");
        in.setRows(inputWidth);
        SimpleLayer out = (SimpleLayer) newLayer();
        out.setRows(samplesWidth);
        LearningSwitch outSwitch = new LearningSwitch();

        connectLayers(in, (SimpleLayer) hiddenLayers.firstElement(),
                new FullSynapse());
        connectLayers((SimpleLayer) hiddenLayers.lastElement(), out,
                new FullSynapse());
        inSwitch.addTrainingSet(inputStream);
        inSwitch.addValidationSet(inputValStream);

        in.addInputSynapse(inSwitch);
        TeachingSynapse trainer = new TeachingSynapse();
        out.addOutputSynapse(trainer);

        outSwitch.addTrainingSet(samplesStream);
        outSwitch.addValidationSet(samplesValStream);

        trainer.setDesired(outSwitch);

        nnet = new NeuralNet();
        nnet.addLayer(in, NeuralNet.INPUT_LAYER);
        for (int i = 0; i < hiddenLayersSizes.length; i++) {
            nnet.addLayer((SimpleLayer) hiddenLayers.get(i),
                    NeuralNet.HIDDEN_LAYER);

        }
        nnet.addLayer(out, NeuralNet.OUTPUT_LAYER);
        nnet.setTeacher(trainer);
        DynamicAnnealing da = new DynamicAnnealing();
        //		da.setName("dynamic");
        nnet.addNeuralNetListener(da);

        //		nnet.addNeuralNetListener(new LearningStop(1));
        //		nnet.addNoise(1);
        //		nnet.randomize(1);
        netElector = new NetworkElector(nnet); //config data to add
        //		nnet.addNeuralNetListener(netElector);
        Monitor monitor = nnet.getMonitor();
        monitor.addNeuralNetListener(this);
        monitor.addNeuralNetListener(netElector);
        monitor.setLearningRate(learningRate);
        monitor.setMomentum(momentum);
        //		monitor.setPatterns(4); /* # of rows contained in the input file */
        monitor.setTotCicles(getTotCicles());
        /* How many times the net must be trained */

        monitor.getLearners().add(0, "org.joone.engine.BasicLearner"); // On-line
        monitor.getLearners().add(1, "org.joone.engine.BatchLearner"); // Batch
        monitor.getLearners().add(2, "org.joone.engine.RpropLearner"); // RPROP
        monitor.setLearningMode(0); // #TODO consider as chromosome parameter

        monitor.setLearning(true); /* The net must be trained */
        monitor.setTrainingPatterns(trainingPatterns);
        monitor.setValidationPatterns(validationPatterns);
        monitor.setValidation(false);

        nnet.start();
        nnet.getMonitor().Go();
        logger.debug("go() - stop");
    }

    /**
     * Not used
     * 
     * @param e
     */
    public void cicleTerminated(NeuralNetEvent e) {
    }

    /**
     * For debuging purpose. Simple function translating time in long into human
     * readable String
     * 
     * @return
     * @param time
     */
    String getTimeString(long time) {
        String output = "";
        long h = time / 3600000;
        time = time - (h * 3600000);
        long m = time / 60000;
        time = time - (m * 60000);
        long s = time / 1000;
        time = time - (s * 1000);
        if (h > 0)
            output += h + "h ";
        if (m > 0 || (output.length() > 0))
            output += m + "m ";
        if (s > 0 || (output.length() > 0))
            output += s + "s ";
        if (time > 0 || (output.length() > 0))
            output += time + "ms";
        return output;
    }

    /**
     * It is used to display how long does teaching takes
     * 
     * @param e
     */
    public void netStopped(NeuralNetEvent e) {
        super.netStopped(e);
        logger.info("Training finished");
        endDate = new Date();
        long diff = endDate.getTime() - startDate.getTime();
        logger.info("Training time: " + getTimeString(diff));

    }

    /**
     * Not used
     * 
     * @param e
     */
    public void netStarted(NeuralNetEvent e) {
        super.netStarted(e);
        logger.info("Training...");
    }

    /**
     * Used to display what was the reason to fail teaching
     * 
     * @param e
     * @param error
     */
    public void netStoppedError(NeuralNetEvent e, String error) {
        logger.error("netStoppedError(" + error + ")");
    }

    /**
     * @param d
     *            updates the learningRate
     */
    public void setLearningRate(double d) {
        learningRate = d;
    }

    /**
     * @param d
     *            updates the momentum
     */
    public void setMomentum(double d) {
        momentum = d;
    }

    /**
     * Sync wrapper for go();
     * 
     * @return
     */
    public NeuralNet goSync() {
        super.goSync();
        setBestRMSE(netElector.getBestRMSE());
        setBestVRMSE(netElector.getBestVRMSE());
        return netElector.getBestNet();
    }

    /**
     * @param bestNet
     *            The bestNet to set.
     */
    void setBestNet(NeuralNet bestNet) {
        this.bestNet = bestNet;
    }

    /**
     * @return Returns the bestNet.
     */
    NeuralNet getBestNet() {
        return bestNet;
    }

    /**
     * @param preprocessDocument
     *            The preprocessDocument to set.
     */
    public void setPreprocessDocument(Document preprocessDocument) {
        this.preprocessDocument = preprocessDocument;
    }

    /**
     * @return Returns the preprocessDocument.
     */
    public Document getPreprocessDocument() {
        return preprocessDocument;
    }

    /**
     * @param layerType
     *            The layerType to set.
     */
    public void setLayerType(int layerType) {
        this.layerType = layerType;
    }

    /**
     * @return Returns the layerType.
     */
    public int getLayerType() {
        return layerType;
    }

    /**
     * 
     * @return Returns new layer with type specified in layerType
     */
    LearnableLayer newLayer() {
        if (layerType == LAYER_SIGMOID)
            return new SigmoidLayer();
        if (layerType == LAYER_LOGARITHMIC)
            return new LogarithmicLayer();
        if (layerType == LAYER_TANH)
            return new TanhLayer();
        return null;
    }

    /**
     * @param bestRMSE
     *            The bestRMSE to set.
     */
    public void setBestRMSE(double bestRMSE) {
        this.bestRMSE = bestRMSE;
    }

    /**
     * @return Returns the bestRMSE.
     */
    public double getBestRMSE() {
        return bestRMSE;
    }

    /**
     * @param bestVRMSE
     *            The bestVRMSE to set.
     */
    public void setBestVRMSE(double bestVRMSE) {
        this.bestVRMSE = bestVRMSE;
    }

    /**
     * @return Returns the bestVRMSE.
     */
    public double getBestVRMSE() {
        return bestVRMSE;
    }

    /**
     * @param totCicles
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
     * @return Returns the columnsSelector.
     */
    public String getColumnsSelector() {
        return columnsSelector;
    }

    /**
     * @param columnsSelector
     *            The columnsSelector to set.
     */
    public void setColumnsSelector(String columnsSelector) {
        this.columnsSelector = columnsSelector;
    }

    /**
     * @return Returns the columnsCount.
     */
    public int getColumnsCount() {
        return ColumnsCount;
    }

    /**
     * @param columnsCount
     *            The columnsCount to set.
     */
    public void setColumnsCount(int columnsCount) {
        ColumnsCount = columnsCount;
    }
}