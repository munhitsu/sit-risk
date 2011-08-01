package org.jim.parser.util;

import org.apache.log4j.*;
import org.dom4j.*;
import org.joone.io.MemoryInputSynapse;

/**
 * Class extending MemoryInputSynapse to read data from xml file. Rows are
 * specified in one xml query. Columns are specified in a separate xml query.
 * 
 * @author Mateusz Lapsa
 */
public class XMLInputSynapse extends MemoryInputSynapse {

    /**
     * id required for serialization
     */
    static final long serialVersionUID = 2141321L;

    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    private static final Logger logger = Logger.getLogger(XMLInputSynapse.class
            .getClass().getName());

    /**
     * dom4j input document
     */
    Document inputDocument;

    /**
     * input xpath to select rows
     */
    String inputXPathA;

    /**
     * input xpath to select columns
     */
    String inputXPathB;

    /**
     * holds input height (number of rows)
     */
    int inputHeight;

    /**
     * holds initial input width (columns) - before overwriten by
     * advancedColumnsSelector
     */
    int initialInputWidth;

    /**
     * Simple constructor
     */
    public XMLInputSynapse() {
        super();
    }

    /**
     * Simple constructor initializing the input document and table.
     * 
     * @param inputFile
     * @param inputXPathA
     * @param inputXPathB
     */
    public XMLInputSynapse(String inputFile, String inputXPathA,
            String inputXPathB) {
        super();
        try {
            inputDocument = DocumentTools.parse(inputFile);
        } catch (DocumentException e) {
            logger.error(e);
            //			TODO throw some exception
            return;
        }
        setInputXML(inputDocument, inputXPathA, inputXPathB);
    }

    /**
     * Simple constructor initializing the input table.
     * 
     * @param inputDocument
     * @param inputXPathA
     * @param inputXPathB
     */
    public XMLInputSynapse(Document inputDocument, String inputXPathA,
            String inputXPathB) {
        super();
        setInputXML(inputDocument, inputXPathA, inputXPathB);
    }

    /**
     * Updates the input table with data from inputDocument specified by two
     * xpaths
     * 
     * @param inputDocument
     * @param inputXPathA
     * @param inputXPathB
     */
    public void setInputXML(Document inputDocument, String inputXPathA,
            String inputXPathB) {
        logger.debug("setInputXML - start");
        this.inputDocument = inputDocument;
        this.inputXPathA = inputXPathA;
        this.inputXPathB = inputXPathB;
        double[][] inputTable = Table.getXMLData(inputDocument, inputXPathA,
                inputXPathB);
        //        if (getEnabledInputs() != null) {
        //            logger.debug(Table.printTable(inputTable));
        //// inputTable = Table.removeColumns(inputTable, getEnabledInputs());
        //            logger.debug("inputTable was reduced");
        //        }
        logger.debug(Table.printTable(inputTable));
        initialInputWidth = Table.getTableWidth(inputTable);
        inputHeight = Table.getTableHeight(inputTable);

        setInputArray(inputTable);
        logger.debug("setAdvancedColumnSelector(1-" + initialInputWidth
                + ") - default value");
        this.setAdvancedColumnSelector("1-" + initialInputWidth);
        logger.debug("setInputXML - stop");
        //        inputWidth = super.getInputDimension();
        //		setFirstCol(1);
        //		setLastCol(inputWidth);
        //TODO change to advanced column selector
    }

    /**
     * @return initialInputWidth
     */
    public int getInitialInputWidth() {
        return initialInputWidth;
    }

    /**
     * @return inputHeight
     */
    public int getInputHeight() {
        return inputHeight;
    }
}