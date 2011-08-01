package org.jim.election.genes;

import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.RandomGenerator;
import org.jgap.UnsupportedRepresentationException;

/**
 * This Gene is used to model individual weights (or bias') in the neural
 * network.
 * <P>
 * It can take any <CODE>double</CODE> value, and in all respects is very
 * similar to a <CODE>DoubleGene</CODE>, except that it is initialised with a
 * uniform random distribution ranging over [-0.5, 0.5]
 */
public class DoubleGene implements Gene {
    //	used by setToRandomValue

    /**
     * Minimum value of gene
     */
    private double minValue = 0;

    /**
     * Maximum value of gene
     */
    private double maxValue = 1;

    /**
     * References the internal double value (allele) of this Gene.
     */
    protected Double m_value = null;

    /**
     * The current active configuration that is in use.
     */
    protected transient Configuration m_activeConfiguration = null;

    /**
     * Constructs a new DoubleGene with default settings. No bounds will be put
     * into effect for values (alleles) of this Gene instance, other than the
     * standard range of double values.
     */
    public DoubleGene() {
        // your code here
    }

    /**
     * Initializes the DoubleGene with specific limits
     * 
     * @param minValue
     * @param maxValue
     */
    public DoubleGene(double minValue, double maxValue) {
        setMinValue(minValue);
        setMaxValue(maxValue);
    }

    /**
     * Constructs a new DoubleGene according to the given active configuration.
     * 
     * @param a_activeConfiguration
     *            The current active configuration.
     */
    public DoubleGene(Configuration a_activeConfiguration) {
        m_activeConfiguration = a_activeConfiguration;
    }

    /**
     * @return a new gene based on provided configuration
     * @param a_activeConfiguration
     */
    public Gene newGene(Configuration a_activeConfiguration) {
        return new DoubleGene(a_activeConfiguration);
    }

    /**
     * Sets the value of allele.
     * 
     * @param a_newValue
     */
    public void setAllele(Object a_newValue) {
        m_value = (Double) a_newValue;
    }

    /**
     * Provides the persistent representation for serialization purpose.
     * 
     * @throws UnsupportedOperationException
     * @return
     */
    public String getPersistentRepresentation()
            throws UnsupportedOperationException {
        return toString();
    }

    /**
     * Sets Allele value based on loaded Persistent representation for
     * deserialization purpose.
     * 
     * @throws UnsupportedRepresentationException
     * @param a_representation
     */
    public void setValueFromPersistentRepresentation(String a_representation)
            throws UnsupportedRepresentationException {
        if (a_representation != null) {
            m_value = new Double(Double.parseDouble(a_representation));
        }
    }

    /**
     * Gets stored value
     * 
     * @return
     */
    public Object getAllele() {
        return m_value;
    }

    /**
     * Sets the gene to random value (mutate)
     * 
     * @param a_numberGenerator
     *            random number generator to use
     */
    public void setToRandomValue(RandomGenerator a_numberGenerator) {
        //TODO allow to modify distribution or at least range
        // Uniform distribution ranging over [0, 1]
        double dValue = a_numberGenerator.nextDouble() * maxValue + minValue;
        m_value = new Double(dValue);
    }

    /**
     * Implementation of coparator
     * 
     * @return
     * @param other
     */
    public int compareTo(Object other) {
        DoubleGene otherDoubleGene = (DoubleGene) other;
        if (otherDoubleGene == null) {
            return 1;
        } else if (otherDoubleGene.m_value == null) {
            return m_value == null ? 0 : 1;
        } else {
            try {
                return m_value.compareTo(otherDoubleGene.m_value);
            } catch (ClassCastException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    /**
     * Implementation of equality operator
     * 
     * @return
     * @param other
     */
    public boolean equals(Object other) {
        try {
            return compareTo(other) == 0;
        } catch (ClassCastException e) {
            return false;
        }
    }

    /**
     * Returns hashCode of allele
     * 
     * @return
     */
    public int hashCode() {
        if (m_value == null) {
            return 0;
        } else {
            return m_value.hashCode();
        }
    }

    /**
     * String representation
     * 
     * @return
     */
    public String toString() {
        if (m_value == null) {
            return "null";
        } else {
            return m_value.toString();
        }
    }

    /**
     * Cleanup of Gene. Not necessary for DoubleGene
     */
    public void cleanup() {

    }

    /**
     * @param minValue
     *            The minValue to set.
     */
    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    /**
     * @return Returns the minValue.
     */
    public double getMinValue() {
        return minValue;
    }

    /**
     * @param maxValue
     *            The maxValue to set.
     */
    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * @return Returns the maxValue.
     */
    public double getMaxValue() {
        return maxValue;
    }
}