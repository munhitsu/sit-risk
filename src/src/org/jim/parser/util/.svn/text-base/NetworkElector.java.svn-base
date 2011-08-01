package org.jim.parser.util;

import java.io.*;
import org.apache.log4j.*;
import org.joone.engine.*;
import org.joone.net.*;

/**
 * Implementation of org.joone.net.NeuralValidationListener and
 * org.joone.net.NeuralNetListener. Class provides a way to analyze network
 * while it is being tought. It stops learnind when network is starting to be
 * overteached.
 * 
 * @author Mateusz Lapsa
 */
public class NetworkElector implements NeuralNetListener,
        NeuralValidationListener {
    /**
     * Keeps the reference to Logger configured for this specific class. logger
     * is shared between class instances
     */
    static Logger logger = Logger.getLogger(NetworkElector.class.getClass()
            .getName());

    /**
     *  Executed network
     */
    NeuralNet nNet;

    /**
     *  Test rmse
     */
    double te;

    /**
     *  Validating rmse
     */
    double ve;

    /**
     *  Saved best network rmse of test set 
     */
    double lte = Double.MAX_VALUE;

    /**
     *  Saved best network rmse of validating set 
     */
    double lve = Double.MAX_VALUE;

    /**
     * The multiplication difference between rmse and vrmse that will stop the
     * training
     */
    double cmp = 10;

    /**
     * @deprecated debuging facility
     */
    boolean print = false;

    /**
     * Actual state of network validation
     */
    private int validation_state = this.STATE_VALIDATION_NOTSTARTED;

    /**
     *  Validation state - not yet started
     */
    final int STATE_VALIDATION_NOTSTARTED = 0;

    /**
     *  Validation state - started
     */
    final int STATE_VALIDATION_STARTED = 1;

    /**
     *  Validation state - validated/performed
     */
    final int STATE_VALIDATION_PERFORMED = 2;

    /**
     * Saved best network
     */
    private NeuralNet bestNet = null;

    /**
     * Saved value of best round mean square error
     */
    private double bestRMSE = Double.POSITIVE_INFINITY;

    /**
     * nNet - network to be taught bestNet - will be filled with best choosen
     * network
     * 
     * @param nNet
     */
    public NetworkElector(NeuralNet nNet) {
        super();
        this.nNet = nNet;
    }

    /**
     *  
     */
    private double bestVRMSE = Double.POSITIVE_INFINITY;

    /**
     * Triggered when validating network finishes evaluation
     * 
     * @param event
     * @see org.joone.net.NeuralValidationListener#netValidated(org.joone.net.NeuralValidationEvent)
     */
    public void netValidated(NeuralValidationEvent event) {
        synchronized (nNet) {
            if (nNet != null) {
                NeuralNet vNet = (NeuralNet) event.getSource();
                ve = vNet.getMonitor().getGlobalError();
                //				if (print) {
                String cycleString = (String) vNet.getParam("cycle");
                logger.debug("nn(" + cycleString + ").vrmse = " + ve);
                //				}
                if (netImproved()) {
                    logger.debug("nn(" + cycleString + ").save");
                    vNet.stop();
                    setBestNet(vNet);
                    setBestVRMSE(ve);
                    setBestRMSE(te);
                    //					storeNetwork(vNet);
                    //					logger.info("stored");
                }
                //porownanie rzedow dokladnosci.
                if (te * cmp < ve) {
                    logger.debug("nn(" + getBestNet().getParam("cycle")
                            + ").pick");
                    nNet.stop();
                    nNet = null;
                }
            }
            validation_state = this.STATE_VALIDATION_PERFORMED;
        }
    }

    /**
     * Not used
     * 
     * @param event
     * @see org.joone.engine.NeuralNetListener#netStarted(org.joone.engine.NeuralNetEvent)
     */
    public void netStarted(NeuralNetEvent event) {
    }

    /**
     * Once a 200 cicles network is being validated Once a 500 cicles garbage
     * collector is started
     * 
     * @param event
     * @see org.joone.engine.NeuralNetListener#cicleTerminated(org.joone.engine.NeuralNetEvent)
     */
    public void cicleTerminated(NeuralNetEvent event) {
        Monitor lMon = (Monitor) event.getSource();
        int cycle = nNet.getMonitor().getTotCicles()
                - nNet.getMonitor().getCurrentCicle() + 1;
        //		if (cycle % 500 == 0) {
        //			print = true;
        //			System.gc();
        //		} else
        //			print = false;
        if (cycle % 200 == 0) {
            te = nNet.getMonitor().getGlobalError();
            logger.debug("nn(" + cycle + ").rmse = " + te);
            // Creates a copy of the neural network
            nNet.getMonitor().setExporting(true);
            NeuralNet vNet = nNet.cloneNet();
            vNet.setParam("cycle", String.valueOf(cycle));
            nNet.getMonitor().setExporting(false);
            // Cleans the old listeners
            // This is a fundamental action to avoid that the validating net
            // calls the cicleTerminated method of this class
            vNet.removeAllListeners();
            // Set all the parameters for the validation
            NeuralNetValidator nnv = new NeuralNetValidator(vNet);
            nnv.addValidationListener(this);
            nnv.start(); // Validates the net
            validation_state = this.STATE_VALIDATION_STARTED;
        }
        while (validation_state == STATE_VALIDATION_STARTED)
            ;
        //		garbage collector is needed but executed too often fill slow down
        // learning rate
        if (cycle % 5000 == 0) {
            System.gc();
        }
    }

    /**
     * Not used
     * 
     * @param event
     * @see org.joone.engine.NeuralNetListener#netStopped(org.joone.engine.NeuralNetEvent)
     */
    public void netStopped(NeuralNetEvent event) {
    }

    /**
     * Not used
     * 
     * @param event
     * @see org.joone.engine.NeuralNetListener#errorChanged(org.joone.engine.NeuralNetEvent)
     */
    public void errorChanged(NeuralNetEvent event) {
    }

    /**
     * Not used
     * 
     * @param event
     * @param error
     * @see org.joone.engine.NeuralNetListener#netStoppedError(org.joone.engine.NeuralNetEvent,
     *      java.lang.String)
     */
    public void netStoppedError(NeuralNetEvent event, String error) {
    }

    /**
     * Store the network in hard coded filename "trial.jnn"
     * 
     * @param net
     *            NeuralNet to serialize
     */
    private void storeNetwork(NeuralNet net) {
        try {
            FileOutputStream stream = new FileOutputStream("trial.jnn");
            ObjectOutputStream out = new ObjectOutputStream(stream);
            out.writeObject(net);
            out.close();
            //			storageStream.reset();
            //			storageStream.writeObject(net);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("network stored");
    }

    /**
     * Kind of fitness function of the rmse. Used by netImproved()
     * 
     * @return
     * @param te
     *            rmse of test set
     * @param ve
     *            rmse of validating set
     */
    private double fE(double te, double ve) {
        return te + 5 * ve;
    }

    /**
     * Verification whether the network have improved over the saved one?
     * 
     * @return
     */
    private boolean netImproved() {
        if (fE(te, ve) < fE(lte, lve)) {
            lte = te;
            lve = ve;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param bestNet
     *            The bestNet to set.
     */
    public void setBestNet(NeuralNet bestNet) {
        this.bestNet = bestNet;
    }

    /**
     * @return Returns the bestNet.
     */
    public NeuralNet getBestNet() {
        return bestNet;
    }

    /**
     * @param bestRMSE
     *            The bestRMSE to set.
     */
    private void setBestRMSE(double bestRMSE) {
        this.bestRMSE = bestRMSE;
    }

    /**
     * @return Returns the bestRMSE.
     */
    public double getBestRMSE() {
        return bestRMSE;
    }

    /**
     * @param bestVRMSE
     *            The bestVRMSE to set.
     */
    private void setBestVRMSE(double bestVRMSE) {
        this.bestVRMSE = bestVRMSE;
    }

    /**
     * @return Returns the bestVRMSE.
     */
    public double getBestVRMSE() {
        return bestVRMSE;
    }
}