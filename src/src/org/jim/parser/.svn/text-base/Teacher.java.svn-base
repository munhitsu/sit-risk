package org.jim.parser;

import java.io.*;
import java.util.*;
import org.apache.log4j.*;
import org.dom4j.Document;
import org.jim.*;
import org.jim.parser.util.*;
import org.joone.net.*;

/**
 * Class implements the
 * 
 * @author Mateusz Lapsa
 */
public class Teacher {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    static Logger logger = Logger.getLogger(Teacher.class.getName());

    /**
     * File storing the log4j properties.
     */
    public static final String LOG_PROPERTIES_FILE = "log4j-debug.properties";

    /**
     * File storing the execution properties.
     */
    public static final String JIM_PROPERTIES_FILE = "jim.properties";

    /**
     * Constructor covers all aspects of creating the network and saving it in
     * Config.P_SERIALIZED_NETWORK.
     */
    public Teacher() {
        logger.info("Teacher()");
        NetworkTeacher eng;
        //		String learningSet = "(@podzial = 1) and ((position() mod 3) != 1)";
        //		String learningSet = "(wywiad/grupa_pacjentow = 1) and ((position()
        // mod 5) != 1)";
        //		String learningSet = "(position() <= 32) and ((position() mod 5) !=
        // 1)";
        //		String validatingSet = "(@podzial = 1) and ((position() mod 3) = 1 or
        // (position() <= 3))";
        //		String validatingSet = "(wywiad/grupa_pacjentow = 1) and ((position()
        // mod 5) = 1)";
        //		String validatingSet = "(position() <= 32) and ((position() mod 5) =
        // 1)";
        //		String inputXPathB = "*[self::wywiad or self::w1]/*";
        //		String samplesXPathB = "computed/*";
        //		String samplesXPathB = "computed/stosunek_pogorszen";
        //		String samplesXPathB = "*[self::w2 or self::w3 or self::w4 or
        // self::w5 or self::w6 or self::w7]/*[position() = last()]";
        String inputFile = Config.getProperty(Config.P_DATA_INPUT_XML);
        String networkFile = Config.getProperty(Config.P_SERIALIZED_NETWORK);
        String normalizeFile = Config.getProperty(Config.P_POLA_NORMALIZATION);
        NeuralNet bestNet = null;
        eng = new NetworkTeacher(inputFile);
        try {
            Document preprocessDocument = DocumentTools.parse(normalizeFile);
            //			eng.setPreprocessDocument(preprocessDocument);
        } catch (Exception e) {
            logger.info("error parsing normalization file");
        }

        eng.setInput(Config.getProperty(Config.P_LEARNIG_XPATH), Config
                .getProperty(Config.P_VALIDATING_XPATH), Config
                .getProperty(Config.P_INPUT_XPATH));

        eng.setSamples(Config.getProperty(Config.P_LEARNIG_XPATH), Config
                .getProperty(Config.P_VALIDATING_XPATH), Config
                .getProperty(Config.P_OUTPUT_XPATH));

        //		String layersString = Config.getProperty()

        int[] layers = { 48, 48, 48 };
        //		boolean[] enabledInputs = {false,false,false,false,true,false,false};
        //		{false,false,false,false,true};
        //		eng.setEnabledInputs(enabledInputs);
        eng.setHiddenLayersSizes(layers);
        eng.setLayerType(NetworkTeacher.LAYER_TANH);
        //		eng.setHiddenCount(3);
        //		eng.setHiddenWidthMul(1.5);
        eng.setLearningRate(0.15);
        eng.setMomentum(0.1);
        eng.setTotCicles(10000);
        bestNet = eng.goSync();
        logger.info("nn(" + bestNet.getParam("cycle") + ").elected");

        try {
            logger.info("nn(" + bestNet.getParam("cycle") + ").saving");
            bestNet.removeAllListeners(); // na wszelki wypadek
            FileOutputStream stream = new FileOutputStream(networkFile);
            ObjectOutputStream storageStream = new ObjectOutputStream(stream);
            storageStream.writeObject(bestNet);
            storageStream.close();
            stream.close();
            logger.info("nn(" + bestNet.getParam("cycle") + ").saved");
            logger.info("rmse: " + eng.getBestRMSE());
            logger.info("vrmse: " + eng.getBestVRMSE());
        } catch (Exception e) {
            logger.error("while initiating output stream", e);
        }
    }

    /**
     * The Executor
     * 
     * @throws Exception
     * 
     * @param args
     *            not used
     */
    public static void main(String[] args) throws Exception {
        Date startDate = new Date();
        System.out.println("main(): startDate: " + startDate);
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
        //		if (args.length != 3) {
        //			System.err.println("Usage <dane.xml> <network.ser>");
        //			return 1;
        //		}
        Config.load(JIM_PROPERTIES_FILE);
        Teacher t = new Teacher();
        Date endDate = new Date();
        System.out.println("main(): endDate: " + endDate);
        System.out.println("main(): total execution time: "
                + (endDate.getTime() - startDate.getTime()) + "ms");
        Config.store(JIM_PROPERTIES_FILE);
    }
}