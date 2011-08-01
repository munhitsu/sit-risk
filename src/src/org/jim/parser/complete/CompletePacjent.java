/*
 * Created on 2004-03-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.jim.parser.complete;

import org.dom4j.*;

/**
 * Completion interface used by org.jim.parser.Completer
 * 
 * @author Mateusz Lapsa
 */
public interface CompletePacjent {
    /**
     * Starting this method over pacjentNode should fill or evaluate all
     * required data as defined in specific Class
     * 
     * @param pacjentNode
     *            the dom4j starting the pacjent nodes tree
     */
    public void complete(Element pacjentNode);
}

