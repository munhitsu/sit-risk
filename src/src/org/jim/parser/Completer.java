package org.jim.parser;

import java.io.*;
import java.util.*;
import org.apache.log4j.*;
import org.dom4j.*;
import org.dom4j.io.*;
import org.jim.parser.complete.*;

/**
 * Completes input pacjent document using cubic splines It depends on classes
 * from org.jim.parser.complete.
 * 
 * @author Mateusz Lapsa
 */
public class Completer {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    static Logger logger = Logger.getLogger(Completer.class.getName());

    /**
     * File storing the log4j properties.
     */
    public static final String LOG_PROPERTIES_FILE = "log4j.properties";

    /**
     * Spline level used in the process of data completion It is passed to
     * CompleteUsingSplines class
     */
    public static final int SPLINE_LEVEL = 2;

    /**
     * Following group is used to store computed parameters
     */
    public static final String COMPUTED_GROUP = "computed";

    /**
     * First series per each patient
     */
    String[] seriaW = { "w1", "w2", "w3", "w4", "w5", "w6", "w7" };

    /**
     * Second series per each patient
     */
    String[] seriaTesty = { "testy_1", "testy_2", "testy_3" };

    /**
     * xpath selecting parameters for CompleteAdding class
     */
    String pathSumaObjawow = "*[self::w1 or self::w2 or self::w3 or self::w4 or self::w5 or self::w6 or self::w7]/w_suma_objawow";

    /**
     * Method transforming input data in xml patient format into Completed data
     * file in the same file format Input file is transformed into dom4j tree,
     * updated and than stored
     * 
     * @param fileNameInput
     * @param fileNameOutput
     */
    public Completer(String fileNameInput, String fileNameOutput) {
        Document document;
        try {
            SAXReader reader = new SAXReader();
            document = reader.read(fileNameInput);
        } catch (DocumentException e) {
            logger.error("error parsing input file: " + e.toString());
            return;
        }
        Element root = document.getRootElement();

        Vector completers = new Vector();
        completers.add(new CompleteUsingSplines(seriaW, SPLINE_LEVEL));
        completers.add(new CompleteUsingSplines(seriaTesty, SPLINE_LEVEL));
        completers.add(new CompleteAdding(COMPUTED_GROUP, pathSumaObjawow));

        List pacjenci = root.elements("pacjent");
        for (Iterator i = pacjenci.iterator(); i.hasNext();) {
            Element pacjent = (Element) i.next();

            for (Iterator c = completers.iterator(); c.hasNext();) {
                CompletePacjent completer = (CompletePacjent) c.next();
                completer.complete(pacjent);
            }
        }

        try {
            XMLWriter writer = new XMLWriter(new FileWriter(fileNameOutput));
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            logger.error("error saving xml", e);
        }
    }

    /**
     * The executor. First program argument is input file. Second is the output
     * file.
     * 
     * @param args
     *            inputFile outputFile
     */
    public static void main(String[] args) {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
        if (args.length != 2) {
            System.err.println("usage: Completer <inputFile> <outputFile>");
            return;
        }
        Completer completer = new Completer(args[0], args[1]);
    }
}