package org.jim.election;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jgap.*;
import org.jgap.impl.*;
import org.jim.Config;
import org.jim.election.genes.*;
import org.joone.net.NeuralNet;

/**
 * Class simplifying the process of teaching the network specified by
 * Chromosome. It is used as a stand alone application.
 * 
 * @author Mateusz Lapsa
 */
public class ChromosomeTeacher {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    static Logger logger = Logger.getLogger(ChromosomeTeacher.class.getName());

    /**
     * File storing the log4j properties.
     */
    public static final String LOG_PROPERTIES_FILE = "log4j-debug.properties";

    /**
     * File storing the execution properties.
     */
    public static final String JIM_PROPERTIES_FILE = "jim.properties";

    /**
     * The executor. Application executes a hardcoded Chromosome.
     * 
     * @param args
     *            are not used
     */
    public static void main(String[] args) {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
        Config.load(JIM_PROPERTIES_FILE);

        Gene[] genes = new Gene[NetworkArchitectureRMSEFitnessFunction.GENES_LENGTH];
        int i = 0;
        //        129951495, 1, 26, 11, 16, 0.3969476086165147, 0.5667604188174811
        //        1614170496, 2, 10, 26, 29, 0.08618879750182118, 0.06645338200521234
        //        37457123, 2, 6, 18, 2, 0.14816038185060398, 0.8494237937467063 -
        // great testQuality <evaluaded testQuality=0.6436202099007554
        // vrmse=0.23382891496138122 inputs=14 hidden=26/>
        //        298347225, 2, 18, 7, 13, 0.8022968860132272, 0.23859594170281484
        genes[i] = new IntegerGene();
        genes[i].setAllele(new Integer(298347225));
        genes[++i] = new IntegerGene();
        genes[i].setAllele(new Integer(2));
        genes[++i] = new IntegerGene();
        genes[i].setAllele(new Integer(18));
        genes[++i] = new IntegerGene();
        genes[i].setAllele(new Integer(7));
        genes[++i] = new IntegerGene();
        genes[i].setAllele(new Integer(13));
        genes[++i] = new DoubleGene();
        genes[i].setAllele(new Double(0.8022968860132272));
        genes[++i] = new DoubleGene();
        genes[i].setAllele(new Double(0.23859594170281484));
        Chromosome chromosome = new Chromosome(genes);
        NetworkArchitectureRMSEFitnessFunction fitness = new NetworkArchitectureRMSEFitnessFunction();
        fitness.setTotCicles(5000);
        fitness.evaluate(chromosome);
        NeuralNet bestNet = fitness.getEvaluatedBestNet();

        String networkFile = Config.getProperty(Config.P_SERIALIZED_NETWORK);
        try {
            logger.info("nn(" + bestNet.getParam("cycle") + ").saving");
            bestNet.removeAllListeners(); // na wszelki wypadek
            FileOutputStream stream = new FileOutputStream(networkFile);
            ObjectOutputStream storageStream = new ObjectOutputStream(stream);
            storageStream.writeObject(bestNet);
            storageStream.close();
            stream.close();
            logger.info("nn(" + bestNet.getParam("cycle") + ").saved");
        } catch (Exception e) {
            logger.error("while initiating output stream", e);
        }
    }
}