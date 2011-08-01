package org.jim.parser;

import java.util.*;
import org.apache.log4j.*;
import org.dom4j.*;
import org.jim.Config;
import org.jim.parser.util.*;
import org.joone.engine.*;
import org.joone.net.*;

/**
 * Neural network executor
 * 
 * @author Mateusz Lapsa
 */
public class Executor {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    static Logger logger = Logger.getLogger(Executor.class.getName());

    /**
     * File storing the log4j properties.
     */
    public static final String LOG_PROPERTIES_FILE = "log4j-debug.properties";

    /**
     * File storing the execution properties.
     */
    public static final String JIM_PROPERTIES_FILE = "jim.properties";

    /**
     * filename of patients database in xml format
     */
    String inputFile = "";

    /**
     * xpath query selecting patients whose parameters are used as a learning
     * set.
     */
    String inputXPathA = "";

    /**
     * xpath query selecting patient parameters that are a network input
     */
    String inputXPathB = "";

    /**
     * File where the output (estimated parameters) will be serialized in xhtml
     * table format
     */
    String outputFile = "";

    /**
     * The NeuralNet initialized over Config.P_SERIALIZED_NETWORK parameter and
     * used to evaluate outputs
     */
    NeuralNet nNet;

    /**
     * Constructor that loads the network
     * 
     * @param netFileName
     *            the filename of network to deselialize
     */
    public Executor(String netFileName) {
        logger.info("Loading NN from " + netFileName);
        nNet = NetworkExecutor.loadNeuralNet(netFileName);
        if (nNet == null) {
            logger.error("Error loading NeuralNet");
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
        Config.load(JIM_PROPERTIES_FILE);
        try {
            Document doc = DocumentTools.parse(Config
                    .getProperty(Config.P_DATA_INPUT_XML));
            logger.info("expected results:"
                    + Table.printTable(Table.getXMLData(doc, Config
                            .getProperty(Config.P_VALIDATING_XPATH), Config
                            .getProperty(Config.P_OUTPUT_XPATH))));
        } catch (Exception e) {
            logger.error(e);
        }
        Executor exe = new Executor(Config
                .getProperty(Config.P_SERIALIZED_NETWORK));
        exe.setInputFile(Config.getProperty(Config.P_DATA_INPUT_XML));
        exe.setInputXPathA(Config.getProperty(Config.P_VALIDATING_XPATH));
        exe.setInputXPathB(Config.getProperty(Config.P_INPUT_XPATH));
        exe.setOutputFile(Config.getProperty(Config.P_EXECUTION_OUTPUT_HTML));
        //		"./data.output/store.html"
        exe.go();
    }

    /**
     * Method axtends network with input and output layers loading the
     * datafiles.
     */
    void go() {
        if (nNet != null) {
            nNet.removeAllInputs();
            Layer input = nNet.getInputLayer();
            input.removeAllInputs(); //TODO verify if necessary
            XMLInputSynapse xmlInput = new XMLInputSynapse(inputFile,
                    inputXPathA, inputXPathB);
            input.addInputSynapse(xmlInput);
            Layer output = nNet.getOutputLayer();
            XHTMLOutputSynapse xhtmlOutput = new XHTMLOutputSynapse(outputFile);
            output.addOutputSynapse(xhtmlOutput);
            //		 we run the neural network for only one cycle in recall mode
            nNet.getMonitor().setTotCicles(1);
            nNet.getMonitor().setLearning(false);
            nNet.start();
            nNet.getMonitor().Go();
            try {
                //				Synapse sIn = nNet.`
                Layer in = nNet.getInputLayer();
                Vector outputSynapses = in.getAllOutputs();
                //				System.out.println("size: " + outputSynapses.size());
                Synapse first = (Synapse) outputSynapses.get(0);
                Matrix wagi = first.getWeights();
                //				System.err.println("layer");
                //				System.err.println(in.toString());
                //				System.err.println("synapse");
                //				System.err.println(first.toString());
                boolean[][] enabled = wagi.getEnabled();
                double[][] values = wagi.getValue();
                double[] wspOut = new double[values.length];
                for (int k = 0; k < values.length; k++) {
                    wspOut[k] = 0;
                }
                for (int i = 0; i < enabled.length; i++) {
                    for (int j = 0; j < enabled[i].length; j++) {
                        //						System.err.print(enabled[i][j]+" ");
                        //						System.err.print(values[i][j]+" ");
                        double v = values[i][j];
                        double abs;
                        //						if (v > 0)
                        //							abs = v;
                        //						else
                        //							abs = -v;
                        //						if (abs < 0.001)
                        //							System.err.print("0 ");
                        //						else if (abs < 0.005)
                        //							System.err.print("1 ");
                        //						else if (abs < 0.01)
                        //							System.err.print("2 ");
                        //						else if (abs < 0.05)
                        //							System.err.print("3 ");
                        //						else if (abs < 0.1)
                        //							System.err.print("4 ");
                        //						else if (abs < 0.5)
                        //							System.err.print("5 ");
                        //						else if (abs < 1)
                        //							System.err.print("6 ");
                        //						else
                        //							System.err.print("7 ");
                        if (values[i][j] >= 0)
                            wspOut[i] += values[i][j];
                        else
                            wspOut[i] -= values[i][j];
                    }
                    //					System.err.println();
                }
                //				for (int k = 0; k < enabled.length; k++) {
                //					System.err.println(k + ": " + wspOut[k]);
                //				}
                //				in.
            } catch (Exception e) {
                System.err.println(e);
            }
            ;
        } else {
            logger.info("network not ready");
        }
    }

    /**
     * @return inputFile
     */
    public String getInputFile() {
        return inputFile;
    }

    /**
     * @return inputXPathA
     */
    public String getInputXPathA() {
        return inputXPathA;
    }

    /**
     * @return inputXPathB
     */
    public String getInputXPathB() {
        return inputXPathB;
    }

    //	/**
    //	 * @return
    //	 */
    //	public String getNetFileName() {
    //		return netFileName;
    //	}

    /**
     * @return outputFile
     */
    public String getOutputFile() {
        return outputFile;
    }

    /**
     * @param inputFile
     *            to update
     */
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * @param inputXPathA
     *            to update
     */
    public void setInputXPathA(String inputXPathA) {
        this.inputXPathA = inputXPathA;
    }

    /**
     * @param inputXPathB
     *            to update
     */
    public void setInputXPathB(String inputXPathB) {
        this.inputXPathB = inputXPathB;
    }

    //	/**
    //	 * @param string
    //	 */
    //	public void setNetFileName(String string) {
    //		netFileName = string;
    //	}

    /**
     * @param outputFile
     *            to update
     */
    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }
}