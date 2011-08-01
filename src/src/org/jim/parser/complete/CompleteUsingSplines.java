/*
 * Created on 2004-03-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.jim.parser.complete;

import java.util.*;
import org.apache.log4j.*;
import org.dom4j.*;
import ru.sscc.spline.*;
import ru.sscc.spline.polynomial.*;

//import ru.sscc.util.*;

/**
 * Completion Class used in org.jim.parser.Completer. It evaluates empty
 * parameters by evaluating the spline ann all known parameters and then
 * interpolating tha value
 * 
 * @author Mateusz Lapsa
 */
public class CompleteUsingSplines implements CompletePacjent {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    static Logger logger = Logger.getLogger(CompleteUsingSplines.class
            .getName());

    /**
     * File storing the log4j properties.
     */
    public static final String LOG_PROPERTIES_FILE = "log4j.properties";

    /**
     * The spline level used for interpolation
     */
    int splineLevel;

    /**
     * Groups names creating a full series
     */
    String[] seria;

    /**
     * Initialization constructor
     * 
     * @param seria
     *            groups names creating a full series
     * @param splineLevel
     *            the spline level used for interpolation
     */
    public CompleteUsingSplines(String[] seria, int splineLevel) {
        this.splineLevel = splineLevel;
        this.seria = seria;
    }

    /**
     * @param pacjentNode
     * @see org.jim.parser.complete.CompletePacjent#Complete(org.dom4j.Element)
     */
    public void complete(Element pacjentNode) {
        Element[] seriaE = new Element[seria.length];
        for (int i = 0; i < seria.length; i++) {
            seriaE[i] = pacjentNode.element(seria[i]);
        }
        HashMap splineDefsHash = generateSplineDefsHash(pacjentNode, seriaE);
        completePacjent(pacjentNode, seriaE, splineDefsHash);
    }

    /**
     * Generates the hash of splines for all atributes
     * 
     * @return
     * @param pacjentNode
     * @param seriaE
     */
    public HashMap generateSplineDefsHash(Element pacjentNode, Element[] seriaE) {
        HashMap splinedefsHash = new HashMap();
        HashMap elementsHash = new HashMap();
        /*
         * twozy hasha wiazacego nazwy atrybutow z ich wystapieniami w kolejnych
         * latach badan
         */
        for (int i = 0; i < seriaE.length; i++) {
            List column = seriaE[i].elements();
            for (Iterator j = column.iterator(); j.hasNext();) {
                Element element = (Element) j.next();
                String name = element.getName();
                String wiarygodnosc = element.attributeValue("wiarygodnosc",
                        "oryginal"); // jesli nie jest okreslona wiarygodnosc
                // danych to sa to dane oryginalne
                if (wiarygodnosc.equalsIgnoreCase("oryginal")) { // interpoluj z
                    // danych
                    // orginalnych
                    // wylacznie
                    Element[] seria = (Element[]) elementsHash.get(name);
                    if (seria == null) {
                        seria = new Element[seriaE.length];
                        seria[i] = element;
                        elementsHash.put(name, seria);
                    } else {
                        seria[i] = element;
                    }
                }
            }
        }

        /*
         * twozy hasha definicji splinow na podstawia hasha atrybutow
         */
        for (Iterator i = elementsHash.keySet().iterator(); i.hasNext();) {
            String name = (String) i.next();
            Element[] seria = (Element[]) elementsHash.get(name);
            int count = 0;
            int min = -1;
            int max = -1;
            for (int j = 0; j < seria.length; j++)
                if (seria[j] != null) {
                    String text = seria[j].getText();
                    try {
                        double textVal = Double.valueOf(text).doubleValue();
                        count++;
                        if (min < 0) {
                            min = j;
                        }
                        max = j;
                    } catch (NumberFormatException e) {
                    }
                    ;
                }
            double mesh[] = new double[count];
            double values[] = new double[count];
            int k = 0;
            //			System.err.println(min+":"+max+":"+count);
            for (int j = 0; j < seria.length; j++) {
                if (seria[j] != null) {
                    String text = seria[j].getText();
                    try {
                        values[k] = Double.valueOf(text).doubleValue();
                        mesh[k] = j;
                        k++;
                    } catch (NumberFormatException e) {
                    }
                    ;
                }
            }
            try {
                Spline spline = POddSplineCreator.createSpline(splineLevel,
                        mesh, values);
                //				logger.debug("saving spline: "+name);
                splinedefsHash.put(name, new SplineDef(spline, min, max));
            } catch (Exception e) {
                logger.error("error counting spline: " + e.toString());
                //				System.exit(1);
            }
        }
        return splinedefsHash;
    }

    /**
     * Method started from complete(org.dom4j.Element)
     * 
     * @param pacjentNode
     * @param seriaE
     * @param splineDefsHash
     */
    public void completePacjent(Element pacjentNode, Element[] seriaE,
            HashMap splineDefsHash) {
        for (int i = 0; i < seriaE.length; i++) {
            List column = seriaE[i].elements();
            for (Iterator j = column.iterator(); j.hasNext();) {
                Element element = (Element) j.next();
                String name = element.getName();
                String wiarygodnosc = element.attributeValue("wiarygodnosc",
                        "oryginal");
                if (!wiarygodnosc.equalsIgnoreCase("oryginal")) { //przemysl
                    // pelne
                    // uzupelnienie
                    // danych
                    // nawet o te
                    // pominiete w
                    // badaniach w
                    // danym roku
                    SplineDef splineDef = (SplineDef) splineDefsHash.get(name);
                    logger
                            .info("completing: " + String.valueOf(i) + "-"
                                    + name);
                    if (splineDef != null) {
                        if (i > splineDef.min && i < splineDef.max) {
                            double value = splineDef.spline.value(i);
                            element.setText(String.valueOf(value));
                            element.addAttribute("wiarygodnosc", "spline");
                            logger.info("updated");
                        }
                    }
                }
            }
        }
    }

    /**
     * Helper class describing the spline.
     * 
     * @author Mateusz Lapsa
     */
    class SplineDef {
        /**
         * The spline itself
         */
        Spline spline;

        /**
         * The minimum of interpolation area
         */
        double min;

        /**
         * The maximum of interpolation area
         */
        double max;

        /**
         * Standard constructor
         * 
         * @param spline
         * @param min
         * @param max
         */
        SplineDef(Spline spline, double min, double max) {
            this.spline = spline;
            this.min = min;
            this.max = max;
        }
    }
}