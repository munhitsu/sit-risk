package org.jim.election;

import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jgap.*;
import org.jgap.impl.*;
import org.jim.Config;

/**
 * Class is the user executable for electing the network using gnetic algorithms
 * 
 * @author Mateusz Lapsa
 */
public class NetworkArchitectureElector {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    static Logger logger = Logger.getLogger(NetworkArchitectureElector.class
            .getName());

    /**
     * File storing the log4j properties.
     */
    public static final String LOG_PROPERTIES_FILE = "log4j-debug.properties";

    /**
     * File storing the execution properties.
     */
    public static final String JIM_PROPERTIES_FILE = "jim.properties";

    /**
     * The limit of generations
     */
    static final int MAX_ALLOWED_EVOLUTIONS = 10;

    /**
     * Size of each population/generation
     */
    static final int POPULATION_SIZE = 180;

    /**
     * Simple constructor. Actually it does nothing.
     */
    public NetworkArchitectureElector() {
    }

    /**
     * Function used to simply translate time in long into String representation
     * 
     * @return String representation
     * @param time
     */
    static String getTimeString(long time) {
        String output = "";
        long h = time / 3600000;
        time = time - (h * 3600000);
        long m = time / 60000;
        time = time - (m * 60000);
        long s = time / 1000;
        time = time - (s * 1000);
        if (h > 0)
            output += h + "h ";
        if (m > 0 || (output.length() > 0))
            output += m + "m ";
        if (s > 0 || (output.length() > 0))
            output += s + "s ";
        if (time > 0 || (output.length() > 0))
            output += time + "ms";
        return output;
    }

    /**
     * Main method. It measures execution time. Loads configurations and evolves
     * the network using electNetwork();
     * 
     * @throws Exception
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Date startDate = new Date();
        System.out.println("main(): startDate: " + startDate);
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
        Config.load(JIM_PROPERTIES_FILE);
        NetworkArchitectureElector nae = new NetworkArchitectureElector();
        nae.electNetwork();
        Date endDate = new Date();
        long diff = endDate.getTime() - startDate.getTime();
        System.out.println("main(): endDate: " + endDate);
        System.out.println("main(): total execution time: "
                + getTimeString(diff));
    }

    /**
     * Evolves the network using JGAP. It limits the evolution to
     * MAX_ALLOWED_EVOLUTIONS generations. Each generation has POPULATION_SIZE
     * members. The best solution/network is only displayed.
     * 
     * @throws InvalidConfigurationException
     */
    public void electNetwork() throws InvalidConfigurationException {
        Configuration conf = new DefaultConfiguration();
        FitnessFunction nnArchFitnessFunc = new NetworkArchitectureRMSEFitnessFunction();
        conf.setFitnessFunction(nnArchFitnessFunc);
        Gene[] sampleGenes = NetworkArchitectureRMSEFitnessFunction
                .getSampleGenes();

        Chromosome sampleChromosome = new Chromosome(sampleGenes);
        conf.setSampleChromosome(sampleChromosome);
        conf.setPopulationSize(POPULATION_SIZE);
        Genotype population = Genotype.randomInitialGenotype(conf);
        for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
            population.evolve();
        }
        Chromosome bestSolutionSoFar = population.getFittestChromosome();
        System.out.println("The best solution: ");
        System.out.println(bestSolutionSoFar);
    }
}