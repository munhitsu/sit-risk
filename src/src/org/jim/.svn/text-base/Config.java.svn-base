package org.jim;

import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * Class is encapsulating the configuration file. It provides methods to store
 * and load it. Actually is wraps the Properties class.
 * 
 * @author Mateusz Lapsa
 */
public class Config {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    static Logger logger = Logger.getLogger(Config.class.getName());

    /**
     * File storing the log4j properties.
     */
    public static final String LOG_PROPERTIES_FILE = "log4j.properties";

    /**
     * Object to store properties during the life of system.
     */
    static Properties prop = new Properties();

    /**
     * Properties key holding the filename of input xml.
     */
    public static final String P_DATA_INPUT_XML = "data_input_xml";

    /**
     * Properties key holding the filename of neural nerwork in serialized form.
     * It is used for saving and loading the network.
     */
    public static final String P_SERIALIZED_NETWORK = "serialized_network";

    /**
     * Properties key holding the xpath query selecting patients whose
     * parameters are used as a learning set.
     */
    public static final String P_LEARNIG_XPATH = "learning_xpath";

    /**
     * Properties key holding the xpath query selecting patients whose
     * parameters are used as a validating set
     */
    public static final String P_VALIDATING_XPATH = "validating_xpath";

    /**
     * Properties key holding the xpath query selecting patients whose
     * parameters are used as a testing set
     */
    public static final String P_TESTING_XPATH = "testing_xpath";

    /**
     * Properties key holding the xpath query selecting patient parameters that
     * are a network input
     */
    public static final String P_INPUT_XPATH = "input_xpath";

    /**
     * Properties key holding the xpath query selecting patient parameters that
     * are a network output
     */
    public static final String P_OUTPUT_XPATH = "output_xpath";

    /**
     * Properties key holding the filename of fields ranges used to normalize
     * the data.
     */
    public static final String P_POLA_NORMALIZATION = "pola_normalization";

    /**
     * Properties key holding the filename of html file where network output
     * will be stored.
     */
    public static final String P_EXECUTION_OUTPUT_HTML = "execution_output_html";

    //	example values
    //	String learningSet = "(position() <= 32) and ((position() mod 5) != 1)";
    //	String validatingSet = "(position() <= 32) and ((position() mod 5) = 1)";
    //	String inputXPathB = "*[self::wywiad or self::w1]/*";
    //	String samplesXPathB = "computed/*";

    /**
     * Associates a key with its value
     * 
     * @param key
     *            the index key
     * @param value
     *            string to be stored
     */
    public static void setProperty(String key, String value) {
        prop.setProperty(key, value);
    }

    /**
     * Reads a value stored under the key
     * 
     * @return the stored value
     * @param key
     *            index key
     */
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    /**
     * Method used to load configuration from specific file
     * 
     * @param propertiesFile
     *            configuration file
     */
    public static void load(String propertiesFile) {
        try {
            FileInputStream cfgInStream = new FileInputStream(propertiesFile);
            prop.load(cfgInStream);
        } catch (Exception e) {
            logger.debug("error opening propertiesFile: " + propertiesFile, e);
        }
    }

    /**
     * Method used to save the configuration in a specific file
     * 
     * @param propertiesFile
     *            configuration file
     */
    public static void store(String propertiesFile) {
        try {
            FileOutputStream cfgOutStream = new FileOutputStream(propertiesFile);
            prop.store(cfgOutStream, "jim configuration file");
        } catch (Exception e) {
            logger.error("error opening propertiesFile: " + propertiesFile, e);
        }
    }
}