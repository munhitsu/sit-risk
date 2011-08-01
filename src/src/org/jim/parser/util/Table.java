package org.jim.parser.util;

import java.util.*;
import org.apache.log4j.Logger;
import org.dom4j.*;

/**
 * Helper class holding methods to work with data tables.
 * 
 * @author Mateusz Lapsa
 */
public class Table {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    private static final Logger logger = Logger.getLogger(Table.class
            .getClass().getName());

    /**
     * Reads a Document doc and queries it for a data table
     * 
     * @return
     * @param doc
     *            input Document
     * @param rows
     *            xpath query selecting table rows
     * @param elements
     *            xpath query selecting row elements
     */
    public static double[][] getXMLData(Document doc, String rows,
            String elements) {
        logger.debug("getXMLData - start");
        double[][] wynik;
        List list = doc.selectNodes(rows);
        wynik = new double[list.size()][];
        int x = 0;
        int y = 0;
        for (Iterator i = list.iterator(); i.hasNext();) {
            Element el = (Element) i.next();
            List row = el.selectNodes(elements);
            wynik[x] = new double[row.size()];
            y = 0;
            for (Iterator i2 = row.iterator(); i2.hasNext();) {
                Node n = (Node) i2.next();
                String text = n.getText();
                double d;
                try {
                    d = Double.parseDouble(text);
                } catch (NumberFormatException e) {
                    d = -1; //TODO nie mam pomyslu jak inaczej reprezentowac
                    // puste dane
                }
                wynik[x][y] = d;
                y++;
            }
            x++;
        }
        logger.debug("getXMLData - stop");
        return wynik;
    }

    /**
     * Prints a table to a String
     * 
     * @return String representation of table
     * @param tab
     */
    public static String printTable(double[][] tab) {
        String out = "<table c='" + tab.length + "'>\n";
        for (int i = 0; i < tab.length; i++) {
            out += "<row c='" + tab[i].length + "'> ";
            for (int j = 0; j < tab[i].length; j++) {
                out += tab[i][j] + " ";
            }
            out += "</row>\n";
        }
        out += "</table>";
        return out;
    }

    /**
     * Converts table into a xhtml table
     * 
     * @return xml representation of table
     * @param tab
     */
    public static String toXHTML(double[][] tab) {
        String out = "<table>\n";
        for (int i = 0; i < tab.length; i++) {
            out += "  <tr>\n";
            for (int j = 0; j < tab[i].length; j++) {
                out += "<td>" + tab[i][j] + "</td>\n";
            }
            out += "  </tr>\n";
        }
        out += "</table>";
        return out;
    }

    /**
     * Returns lenghth of the shortest table row
     * 
     * @return
     * @param tab
     */
    public static int getTableWidth(double[][] tab) {
        if (tab.length == 0)
            return 0;
        int shortest = tab[0].length;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].length < shortest)
                shortest = tab[i].length;
        }
        return shortest;
    }

    /**
     * Returns row count
     * 
     * @return
     * @param tab
     */
    public static int getTableHeight(double[][] tab) {
        return tab.length;
    }

    /**
     * Removes specific columns from table
     * 
     * @return reduced table
     * @param inputTable
     *            table to deal with
     * @param enabledColumns
     *            boolean array that specifies which columns to keep
     */
    public static double[][] removeColumnsOld(double[][] inputTable,
            boolean[] enabledColumns) {
        logger.debug("removeColumns - start");
        int enabledCount = 0;
        for (int i = 0; (i < enabledColumns.length)
                && (i < inputTable[0].length); i++)
            if (enabledColumns[i])
                enabledCount++;
        logger.debug("enabledCount = " + enabledCount);
        double[][] outputTable = new double[inputTable.length][];
        //		initlialize data structure
        for (int i = 0; i < inputTable.length; i++) {
            outputTable[i] = new double[enabledCount];
        }
        int k = 0;
        for (int j = 0; k < enabledCount; j++) {
            if (enabledColumns[j]) {
                for (int i = 0; i < inputTable.length; i++) {
                    outputTable[i][k] = inputTable[i][j];
                }
                k++;
            }
        }
        logger.debug(Table.printTable(inputTable));
        logger.debug(Table.printTable(outputTable));
        logger.debug("removeColumns - stop");
        return outputTable;
    }

    /**
     * Creates a new table beeing a copy of inputTableA extended with new
     * columns form inputTableB
     * 
     * @param inputTableA
     * @param inputTableB
     * @return
     */
    public static double[][] addColumns(double[][] inputTableA,
            double[][] inputTableB) {
        double[][] outputTable = new double[Math.min(inputTableA.length,
                inputTableB.length)][];
        for (int i = 0; i < outputTable.length; i++) {
            outputTable[i] = new double[inputTableA[i].length
                    + inputTableB[i].length];
            int j = 0;
            for (; j < inputTableA[i].length; j++) {
                outputTable[i][j] = inputTableA[i][j];
            }
            for (; j < inputTableA[i].length + inputTableB[i].length; j++) {
                outputTable[i][j] = inputTableB[i][j - inputTableA[i].length];
            }
        }
        return outputTable;
    }

    /**
     * picks a first column from the table and counts its standard deviation
     * 
     * @param table2d
     * @return
     */
    public static double standardDeviation(double[][] table2d) {
        logger.debug("variation(double[][])");
        double[] table = new double[table2d.length];
        for (int i = 0; i < table2d.length; i++) {
            table[i] = table2d[i][0];
        }
        return standardDeviation(table);
    }

    /**
     * Calculates the standard deviation of input vector
     * 
     * @param table
     * @return
     */
    public static double standardDeviation(double[] table) {
        logger.debug("variation(double[])");
        if (table.length == 0) {
            return 0;
        } else {
            double elementsSum = 0;
            double elementsAvg;
            double elementsCount = table.length;
            for (int i = 0; i < elementsCount; i++) {
                elementsSum += table[i];
            }
            elementsAvg = elementsSum / (double) table.length;
            double squareDiffsSum = 0;
            for (int i = 0; i < elementsCount; i++) {
                squareDiffsSum += (table[i] - elementsAvg)
                        * (table[i] - elementsAvg);
            }
            double variation = Math.sqrt(squareDiffsSum / elementsCount
                    * (elementsCount - 1));
            return variation;
        }
    }

    /**
     * Calculates the difference of two vectors
     * 
     * @param tableA
     * @param tableB
     * @return
     */
    public static double[] diffTables(double[] tableA, double[] tableB) {
        logger.debug("diffTables(double[], double[])");
        int minLength = Math.min(tableA.length, tableB.length);
        double[] outputTable = new double[minLength];
        for (int i = 0; i < minLength; i++) {
            outputTable[i] = tableA[i] - tableB[i];
        }
        return outputTable;
    }

    /**
     * Calculates the difference of two tables
     * 
     * @param tableA
     * @param tableB
     * @return
     */
    public static double[][] diffTables(double[][] tableA, double[][] tableB) {
        logger.debug("diffTables(double[][], double[][])");
        int minLengthY = Math.min(tableA.length, tableB.length);

        double[][] outputTable = new double[minLengthY][];
        for (int i = 0; i < minLengthY; i++) {
            int minLengthX = Math.min(tableA[i].length, tableB[i].length);
            outputTable[i] = new double[minLengthX];
            for (int j = 0; j < minLengthX; j++) {
                outputTable[i][j] = tableA[i][j] - tableB[i][j];
            }
        }
        return outputTable;
    }

    /**
     * Calculates the round mean square of input table
     * 
     * @param inputTable
     * @return
     */
    public static double rms(double[][] inputTable) {
        double rs = 0;
        int count = 0;
        for (int i = 0; i < inputTable.length; i++) {
            for (int j = 0; j < inputTable[i].length; j++) {
                count++;
                rs += inputTable[i][j] * inputTable[i][j] / (double) 2;
            }
        }
        return Math.sqrt(rs / (double) count);
    }
}