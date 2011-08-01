package org.jim.parser.util;

import java.io.*;
import java.util.*;
import org.apache.log4j.*;
import org.joone.engine.*;
import org.joone.io.*;
import org.joone.net.*;

/**
 * Layer responsible for writing network outputs into a xhtml file as a table.
 * 
 * @author Mateusz Lapsa
 */
public class XHTMLOutputSynapse extends StreamOutputSynapse {

    /**
     * id required for serialization
     */
    static final long serialVersionUID = 2066240186L;

    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    private static final Logger logger = Logger
            .getLogger(XHTMLOutputSynapse.class.getClass().getName());

    /**
     * output buffer
     */
    private String out;

    /**
     * Name of output file
     */
    private String fileName = "";

    /**
     * The default PrinterWriter
     */
    protected transient PrintWriter printer = null;

    /**
     * Should we append the file? No!
     */
    private boolean append = false;

    /**
     * Simple construnctor.
     */
    public XHTMLOutputSynapse() {
        super();
    }

    /**
     * Simple construnctor updating the output file
     * 
     * @param fileName
     */
    public XHTMLOutputSynapse(String fileName) {
        super();
        setFileName(fileName);
    }

    /**
     * Writes to the printer object.
     * 
     * @param pattern
     */
    public synchronized void write(Pattern pattern) {
        if ((printer == null) || (pattern.getCount() == 1))
            setFileName(fileName);
        if (pattern.getCount() == -1) {
            flush();
        } else {
            out += "<tr>";
            double[] array = pattern.getArray();
            for (int i = 0; i < array.length; ++i) {
                out += "<td>" + array[i] + "</td>";
                //				if (i < (array.length - 1))
                //					printer.print(getSeparator());
            }
            out += "</tr>\n";
            //printer.flush(); // Flush the output after every line, avoid
            // building any large buffers
        }
    }

    /**
     * @return value of fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets output fileName and opens it.
     * 
     * @param fn
     */
    public void setFileName(String fn) {
        fileName = fn;
        if (printer != null)
            flush();
        open(fn);
    }

    /**
     * Flushes the output, appends html stuff and closes the file.
     */
    public void flush() {
        out += "</table></body></html>\n";
        printer.print(out);
        //		printer.flush();
        printer.close();
        logger.info(out);
        printer = null;
    }

    /**
     * Open file wrapper
     * 
     * @param fn
     */
    private void open(String fn) {
        try {
            printer = new PrintWriter(new FileOutputStream(fn, append), true);
            out = "<html><body><table>\n";
        } catch (IOException ioe) {
            String error = "IOException in " + getName() + ". Message is : ";
            logger.error(error + ioe.getMessage());
            if (getMonitor() != null)
                new NetErrorManager(getMonitor(), error + ioe.getMessage());
        }
    }

    /**
     * Checks whether the output file was specified.
     * 
     * @return
     */
    public TreeSet check() {
        TreeSet checks = super.check();
        if (fileName == null || fileName.trim().equals("")) {
            checks
                    .add(new NetCheck(NetCheck.ERROR, "File Name not set.",
                            this));
        }
        return checks;
    }
}