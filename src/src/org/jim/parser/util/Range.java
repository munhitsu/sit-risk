package org.jim.parser.util;

/**
 * Storage class to combine data type, minimum and maximum values.
 * 
 * @author Mateusz Lapsa
 */
public class Range {

    /**
     * Constructor initializing the fields
     * 
     * @param type
     * @param min
     * @param max
     */
    Range(int type, double min, double max) {
        this.min = min;
        this.max = max;
        this.type = type;
    }

    /**
     * parameter type double
     */
    public static final int TYPE_DOUBLE = 1;

    /**
     * parameter type int
     */
    public static final int TYPE_INT = 2;

    /**
     * parameter type coded (int with only a few values)
     */
    public static final int TYPE_CODE = 3;

    /**
     * minimum
     */
    double min;

    /**
     * maximum
     */
    double max;

    /**
     * parameter type
     */
    int type;
}