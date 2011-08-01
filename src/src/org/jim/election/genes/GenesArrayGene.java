package org.jim.election.genes;

import java.util.*;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.RandomGenerator;
import org.jgap.UnsupportedRepresentationException;

/**
 * Not implemented. Just an option for the future.
 * 
 * @author Mateusz Lapsa-Malawski
 */
public class GenesArrayGene implements Gene {

    /**
     *  
     */
    Collection m_genes;

    /**
     *  
     */
    protected transient Configuration m_activeConfiguration = null;

    /**
     *  
     */
    public GenesArrayGene() {
        // your code here
    }

    /**
     * 
     * @param a_activeConfiguration
     */
    public GenesArrayGene(Configuration a_activeConfiguration) {
        m_activeConfiguration = a_activeConfiguration;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jgap.Gene#newGene(org.jgap.Configuration)
     */

    /**
     * 
     * @return
     * @param a_activeConfiguration
     */
    public Gene newGene(Configuration a_activeConfiguration) {
        return new GenesArrayGene(a_activeConfiguration);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jgap.Gene#setAllele(java.lang.Object)
     */

    /**
     * 
     * @param a_newValue
     */
    public void setAllele(Object a_newValue) {
        m_genes = new Vector((Collection) a_newValue);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jgap.Gene#getAllele()
     */

    /**
     * 
     * @return
     */
    public Object getAllele() {
        return m_genes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jgap.Gene#getPersistentRepresentation()
     */

    /**
     * 
     * @throws UnsupportedOperationException
     * 
     * @return
     */
    public String getPersistentRepresentation()
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Representation TBD");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jgap.Gene#setValueFromPersistentRepresentation(java.lang.String)
     */

    /**
     * 
     * @throws UnsupportedOperationException
     * @throws UnsupportedRepresentationException
     * 
     * @param a_representation
     */
    public void setValueFromPersistentRepresentation(String a_representation)
            throws UnsupportedOperationException,
            UnsupportedRepresentationException {
        throw new UnsupportedOperationException("Representation TBD");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jgap.Gene#setToRandomValue(org.jgap.RandomGenerator)
     */

    /**
     * 
     * @param a_numberGenerator
     */
    public void setToRandomValue(RandomGenerator a_numberGenerator) {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jgap.Gene#cleanup()
     */

    /**
     *  
     */
    public void cleanup() {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */

    /**
     * 
     * @return
     * @param arg0
     */
    public int compareTo(Object arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
}