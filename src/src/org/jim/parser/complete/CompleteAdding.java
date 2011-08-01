package org.jim.parser.complete;

import java.util.*;
import org.apache.log4j.*;
import org.dom4j.*;
import org.dom4j.tree.*;

/**
 * Completion Class used in org.jim.parser.Completer. It evaluates extra
 * parameters on the nodes defined in seriaXPath. "stosunek_pogorszen" and
 * "stopien_pogorszenia_max"
 * 
 * @author Mateusz Lapsa
 */
public class CompleteAdding implements CompletePacjent {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    static Logger logger = Logger.getLogger(CompleteAdding.class.getName());

    /**
     * File storing the log4j properties.
     */
    public static final String LOG_PROPERTIES_FILE = "log4j.properties";

    /**
     * The group used for output
     */
    String group;

    /**
     * xpath query to select all attributes
     */
    String seriaXPath;

    /**
     * Initialization constructor
     * 
     * @param computedGroup
     *            the group used for output
     * @param seriaXPath
     *            xpath query to select all attributes to sum
     */
    public CompleteAdding(String computedGroup, String seriaXPath) {
        this.group = computedGroup;
        this.seriaXPath = seriaXPath;
    }

    /**
     * @param pacjentNode
     * @see org.jim.parser.complete.CompletePacjent#complete(org.dom4j.Element)
     */
    public void complete(Element pacjentNode) {
        List seria = pacjentNode.selectNodes(seriaXPath);
        if (!seria.isEmpty()) {
            double lastVal = -1;
            double diff;
            double lastDiffToVal;
            double diffToVal;
            double maxDiffToVal = 0;
            int zysk = 0;
            int strata = 0;
            int counted = 0;
            for (Iterator i = seria.iterator(); i.hasNext();) {
                Node node = (Node) i.next();
                String text = node.getText();
                double val;
                try {
                    val = Double.parseDouble(text);
                    if (lastVal >= -0.5) { //czyli zostala juz wypelniona
                        diff = lastVal - val;
                        if (diff < 0)
                            strata++;
                        else
                            zysk++; //zyskiem jest brak straty
                        counted++;
                        diffToVal = diff / val;
                        lastDiffToVal = diffToVal;
                        if (maxDiffToVal > diffToVal)
                            maxDiffToVal = diffToVal;
                    }
                } catch (NumberFormatException e) {
                    val = -1; //TODO nie mam pomyslu jak inaczej reprezentowac
                    // puste dane
                }
                lastVal = val;
            }
            double wsp = (double) strata / (double) counted;
            setValue(pacjentNode, "stosunek_pogorszen", wsp);

            setValue(pacjentNode, "stopien_pogorszenia_max", maxDiffToVal
                    * (-1));
            
            if (wsp <= 0.075) {
                setValue(pacjentNode, "czy_pogorszenie_075", 0);
            } else {
                setValue(pacjentNode, "czy_pogorszenie_075", 1);
            }
            
            if (wsp <= 0.100) {
                setValue(pacjentNode, "czy_pogorszenie_100", 0);
            } else {
                setValue(pacjentNode, "czy_pogorszenie_100", 1);
            }
            
        } else {
            logger.error("no data to evaluate");
        }
    }

    /**
     * per function that creates the output value element and if necessary the
     * parameters group with using name defined in "group"
     * 
     * @param base
     *            the pacjentNode
     * @param name
     *            parameter name
     * @param value
     *            parameter value
     */
    void setValue(Element base, String name, double value) {
        Element eGroup = base.element(group);
        if (eGroup == null) {
            eGroup = new DefaultElement(group);
            base.add(eGroup);
        }
        Element eParam = eGroup.element(name);
        if (eParam == null) {
            eParam = new DefaultElement(name);
            eGroup.add(eParam);
        }
        eParam.addText(Double.toString(value));
    }
}