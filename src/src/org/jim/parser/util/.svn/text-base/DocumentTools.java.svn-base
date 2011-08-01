package org.jim.parser.util;

import java.util.*;
import org.dom4j.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * The helper class defining few methods simpllifying Document manipulaiton
 * 
 * @author Mateusz Lapsa
 */
public class DocumentTools {

    /**
     * The minimum value of data after the normalization
     */
    static final double NORMALIZED_MIN = 0.05;

    /**
     * The maximum value of data after the normalization
     */
    static final double NORMALIZED_MAX = 0.95;

    /**
     * Document parser
     * 
     * @throws DocumentException
     * 
     * @return parsed Document
     * @param url
     *            to parse
     */
    public static Document parse(String url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }

    /**
     * Normalize the Document dane using ranges found in Document pola
     * 
     * @param dane
     *            patients database
     * @param pola
     *            normalization definition
     */
    public static void normalize(Document dane, Document pola) {
        Element daneRoot = dane.getRootElement();
        Element polaRoot = pola.getRootElement();
        List polaList = polaRoot.elements("pole");
        Hashtable ranges = new Hashtable();
        for (Iterator i = polaList.iterator(); i.hasNext();) {
            Element poleElement = (Element) i.next();
            String codename = poleElement.attribute("codename").getText();
            Attribute typeAttribute = poleElement.attribute("type");
            if (typeAttribute != null) {
                String type = typeAttribute.getText();
                double min = Double.valueOf(
                        poleElement.attribute("min").getText()).doubleValue();
                double max = Double.valueOf(
                        poleElement.attribute("max").getText()).doubleValue();
                int t = 0;
                if (type == "int")
                    t = Range.TYPE_INT;
                else if (type == "double")
                    t = Range.TYPE_DOUBLE;
                else if (type == "code")
                    t = Range.TYPE_CODE;
                ranges.put(codename, new Range(t, min, max));
            }
        }
        List wartosciList = daneRoot.selectNodes("/pacjenci/pacjent/*/*");
        for (Iterator i = wartosciList.iterator(); i.hasNext();) {
            Element wartoscElement = (Element) i.next();
            String codename = wartoscElement.getName();
            try {
                double v = Double.valueOf(wartoscElement.getText())
                        .doubleValue();
                if (ranges.containsKey(codename)) {
                    Range r = (Range) ranges.get(codename);
                    //					if (v == r.min) {
                    //						v = NORMALIZED_MIN;
                    //					}
                    //					else {
                    //						v = ((NORMALIZED_MAX - NORMALIZED_MIN) * (r.max - r.min)
                    // / (v - r.min))
                    //							+ NORMALIZED_MIN;
                    v = ((v - r.min) * (NORMALIZED_MAX - NORMALIZED_MIN) / (r.max - r.min))
                            + NORMALIZED_MIN;
                    //					}
                    wartoscElement.setText(Double.toString(v));
                }
            } catch (Exception e) {
            }
        }
    }
}