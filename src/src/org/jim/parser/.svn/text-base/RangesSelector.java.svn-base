package org.jim.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * This class is the program that searches the data file to select the ranges.
 * It also tries to out whether data was some numeric value (double or int) or a
 * category.
 * 
 * @author Mateusz Lapsa
 */
public class RangesSelector {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    static Logger logger = Logger.getLogger(RangesSelector.class.getName());

    /**
     * File storing the log4j properties.
     */
    public static final String LOG_PROPERTIES_FILE = "log4j-debug.properties";

    /**
     * The String defining which fields shlould be analysed
     */
    public String FILE_POLA_IN = "./data.conf/pola-edited.xml";

    /**
     * The Sting defining where output (fields associated with their type,
     * minumum and maximum values) should be stored
     */
    public String FILE_POLA_RANGES = "./data.conf/pola-ranges.xml";

    //out

    /**
     * The String defining what data file to analyze
     */
    public String FILE_DANE = "./data.preprocessed/dane.xml";

    /**
     * The threshold defining difference between selector type an int. When
     * values are found to be int it counts how many different values were used.
     * If there were less than MAX_VALUES different values the field is assumed
     * to be a categorization.
     */
    public int MAX_VALUES = 5;

    /**
     * The constructor loads data and all config files. It browses through the
     * data file to to find used values ranges. It also tries to decide whether
     * the field is double, int or category.
     * 
     * @param args
     *            copy of main arguments
     */
    public RangesSelector(String[] args) {
        if (args.length != 4) {
            System.err.println("Usage:");
            System.err
                    .println(getClass().getName()
                            + "(data file) (pola file) {output pola ranges file) (max_values)");
            return;
        }
        FILE_DANE = args[0];
        FILE_POLA_IN = args[1];
        FILE_POLA_RANGES = args[2];
        try {
            MAX_VALUES = Integer.valueOf(args[3]).intValue();
            logger.info("MAX_VALUES = " + MAX_VALUES);
        } catch (Exception e) {
            logger.info(
                    "Error parsing MAX_VALUES (args[3]) assuming MAX_VALUES = "
                            + MAX_VALUES, e);
            MAX_VALUES = 5;
        }
        SAXReader reader = new SAXReader();
        Document pola;
        Document dane;
        try {
            logger.info("opening dane: " + FILE_DANE);
            dane = reader.read(FILE_DANE);
            logger.info("opening pola: " + FILE_POLA_IN);
            pola = reader.read(FILE_POLA_IN);
        } catch (DocumentException e) {
            logger.error("error opening input files", e);
            return;
        }
        Element polaRoot = pola.getRootElement();
        Element daneRoot = dane.getRootElement();
        List polaList = polaRoot.elements("pole");
        for (Iterator i = polaList.iterator(); i.hasNext();) {
            int[] intValues = new int[MAX_VALUES + 1];
            int intValuesCount = 0;
            Element poleElement = (Element) i.next();
            String codename = poleElement.attribute("codename")
                    .getStringValue();
            logger.debug("codename = " + codename);
            List wartosciList = daneRoot
                    .selectNodes("/pacjenci/pacjent/*/*[name(.) = '" + codename
                            + "']");
            logger.debug("wartosciList.size() = " + wartosciList.size());
            double dFirst = Double.POSITIVE_INFINITY;
            double dLast = Double.NEGATIVE_INFINITY;
            boolean isDouble = false;
            boolean isCategory = true;
            for (Iterator j = wartosciList.iterator(); j.hasNext();) {
                Element wartoscElement = (Element) j.next();
                double vd;
                int vi = Integer.MIN_VALUE;
                try {
                    vi = Integer.valueOf(((Element) wartoscElement).getText())
                            .intValue();
                    boolean newValue = true;
                    for (int x = 0; x < intValuesCount; x++) {
                        if (intValues[x] == vi) {
                            newValue = false;
                            break;
                        }
                    }
                    if (isCategory && newValue) {
                        if (intValuesCount != MAX_VALUES) {
                            intValues[intValuesCount++] = vi;
                        } else {
                            isCategory = false;
                        }
                    }
                } catch (Exception e) {
                }
                try {
                    vd = Double.valueOf(((Element) wartoscElement).getText())
                            .doubleValue();
                    if (!isDouble && (vd != vi)) {
                        isDouble = true;
                        logger.debug("doubleReason : " + vd);
                    }
                    if (vd < dFirst)
                        dFirst = vd;
                    if (vd > dLast)
                        dLast = vd;
                } catch (Exception e) {
                }
            }
            logger.debug("first : " + dFirst);
            logger.debug("last : " + dLast);
            logger.debug("isDouble : " + isDouble);
            if (dFirst != Double.POSITIVE_INFINITY) {
                if (isDouble) {
                    poleElement.addAttribute("type", "double");
                    poleElement.addAttribute("min", Double.toString(dFirst));
                    poleElement.addAttribute("max", Double.toString(dLast));
                } else if (isCategory) {
                    poleElement.addAttribute("type", "category");
                    poleElement.addAttribute("min", Integer
                            .toString((int) dFirst));
                    poleElement.addAttribute("max", Integer
                            .toString((int) dLast));
                } else {
                    poleElement.addAttribute("type", "int");
                    poleElement.addAttribute("min", Integer
                            .toString((int) dFirst));
                    poleElement.addAttribute("max", Integer
                            .toString((int) dLast));
                }
            }
        }
        try {
            logger.info("saving output to : " + FILE_POLA_RANGES);
            XMLWriter writer = new XMLWriter(new FileWriter(FILE_POLA_RANGES));
            writer.write(pola);
            writer.close();
        } catch (IOException e) {
            logger.error("error saving xml", e);
        }
    }

    /**
     * All logis is implemented in RangesSelector constructos.
     * 
     * @param args
     *            (data file) (pola file) {output pola ranges file) (max_values)
     */
    public static void main(String[] args) {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
        new RangesSelector(args);
        System.gc();
    }
}