# Introduction to artificial neural networks [Chapter 3]
Artificial neural networks are algorithmic structures modeled after the biological structure of the brain. They are an effective tool for predicting, classifying and approximating of data. They are also a way of understanding the essence of the human mind and its self-awareness. Cognitive science is a field devoted to investigating the mind in a broader scope compared to psychiatry, psychology, neurology and philosophy [][#Duch:2004:NaukiKognitywne]. 
The following chapter discusses the problem of simulating the mind and its structure at the level of singular cells. 

## Structure of the brain
The brain is a wrinkled structure, in which two cerebral hemispheres, medulla and brainstem can be distinguished [][#Jura:Krzanowska:1992]. The cells that the brain matter is comprised of are nerve cells and glial cells. There are 10 glial cells for each nerve cell. 
Cross section of the brain reveals grey and white matters. White matter, found inside the brain, is comprised of nerve fibers and glial cells. Grey matter (cerebral cortex), constituting the external layer of the brain, is comprised of nerve cells and also glial cells. White matter transmits data, grey matter processes it.  

### Nerve cells
The basic building block of the brain is the neuron. A human brain is comprised of about 100 billion nerve cells (<< 10^11 >>) [][#Greenfield:1999], and about 100 trilliard (<< 10^15 >>) connections between them [][#Veggeberg:1996]. 

Following are the known elements of a neuron:

* Cell body (soma)
* Nucleus
* Axon
* Dendrites
* Synapses

Nerve cells are diploidic and adapted to transmitting and modifying electrical impulses. They have nuclei and typical organelle suspended in cytoplasm. They do not, however, possess centrioles responsible for cell replication. Axons are long, fibrous projections of neurons, which transmit output signals to other cells. Axons are shielded by myelin, which acts as an insulator. Synapses are attached to axons’ ends. They are points of contact between nerve cells and other electro active cells (nerve or muscle cells). Synapses carry impulses with the help of synaptic mediators or as an electrical impulse [][#Jura:Krzanowska:1992].  
Dendrites are extensions of nerve cells which receive signals from other neurons. Their structure is usually tree-shaped, but there exist cells with dendrites similar to axons. Depending on type, a dendrite is capable of suppressing or amplifying signals. Signals are temporarily stored in the nucleus. When their number is higher than the threshold capacity of a given cell, an output signal is generated. Neurons are capable of transmitting up to 500 signals per second. 

### Glial cells
General types of glial cells include astrocytes, oligodendrocytes, Schwann cells and microglia. It is believed that glial cells support nerve cells. 
Their known function is to maintain a proper composition of the fluid filling the intracellular spaces in the nerve tissue. Too high concentration of K+ ions, excreted by neurons, could lead to blocking of the cell. Astrocytes have a known function of absorbing those ions. Besides that, the glial cells nutrify neurons. They also play an important part during the development of the organism. Then, they create a type of protein paths, which lead neural extensions to other cells [][#Szarski:1976], e.g. nerve or muscle cells. Oligodendrocytes create a myelin sheath around neural extensions, which speeds up the transmission of impulses. Schwann cells are part of axons’ myelin sheaths.  
In recent years, research has shown the role of glutamate in the functioning of the nervous system. There is a hypothesis that through the emission of glutamate, the astrocytes can communicate with neurons using neurotransmitters and modify their functions. The frequency of their emission is 5Hz. If we keep in mind that astrocytes have branched-out structure, it becomes apparent that a single cell can interact with around 140 thousand synapses [][#Bezzi:Gundersen:2004].   

## Brain simulation
Full simulation of the brain is based on the following assumptions:

* Complete mechanisms of a nerve cell activity is known
* Mechanisms of mind development and external connections are known
* It is possible to simulate a complete network
* Simulated network will be fed data similar to that received by a human brain during a lifespan of an individual

A network of connected neurons is a known, simplified model of a brain, if we assume that one modulation of an input signal is an “x” of floating-point computations
 Furthermore, calculating the output value is “y” floating-point operations. Computing power needed for simulating neurons equals 500 actions per second * (<<x*10^15>> inter-cell connections + <<y*10^11>> cells). If we assume that <<x=y=1>>, then a machine capable of << 500 *(10^15 + 10^11) ~~ 5 >> PFlops is needed. For comparison, the computing power of the currently fastest computing cluster, BlueGene/L, equals 135 TFlops. An installation of 350 TFlops capable BlueGene/L is scheduled for 2005 [][#IBM:2005:Bluegene].
For the estimations we’ve assumed that the cell model is simple, so that a dendrite modulates a signal in one operation, and a cell processes it also as a single operation. This assumption is appropriate with the basic model of perceptron [][#Camargo:1990]. Note that if one want to model the whole brain, and not just some characteristics of its part, than a complete model of possibly higher complexity would be required.

## Nerve cell simulation
Research on neuron structure resulted in a general behaviour model of this type of cell. Main guidelines are contained in the perceptron model [][#Tadeusiewicz:1993]. Its structure consists of:

![Figure 1](fig1.pdf)
Figure 1 Nerve cell model

* Dendrites  
    Data inputs. They initially process a single input signal (<<x_i>>). That means amplifying or suppressing it, depending on its weight (<<w_i>>). 
* Nucleus  
    Computational center. Adds modulated data coming in from the dendrites and then transmits the signal to an axon depending on the defined activation function (<<F_a>>). J.Mulawka defines an auxiliary function <<F_i>> [][#Mulawka:1996], which initially processes input sum: <<  sum_(i=0)^n X_iW_i >>
* Axon  
    Singular data output. It connects to the dendrites of other cells.

<< y=F_a(F_i(sum_(i=0)^n X_iW_i)) >>

Following variants are known:

**Linear model**

Activation and initial processing functions are expressed as y=x, so the neuron is:

<< y=sum_(i=0)^n X_iW_i >>

Function value graph for two equivalent inputs:

![Figure 2](fig2.pdf)
Figure 2 Linear model

**Nonlinear discrete model**

Activation function is defined as:

<< F_a(x)={(1, x > Theta), (0, x <= Theta):} >>

Initial processing function as:

<< F_i(x)=x >>

Where << Theta >> is defined as neuron’s threshold value. It is known as TLU (Threshold Logic Unit). The graph of the activation function for two equivalent inputs (threshold: << Theta=1 >>):

![Figure 3](fig3.pdf)
Figure 3 Discrete model

**Nonlinear sigmoidal model**

Activation function is expressed as:

<< F_a(x)=1/(1+e^(Theta-x)) >>

Initial processing function as:

<< F_i(x)=x >>

Where << Theta >> is the neuron’s threshold value.
Graph of the activation function for two equivalent inputs (threshold: << Theta =1 >>):

![Figure 4](fig4.pdf)
Figure 4 Sigmoidal model

After the critical input signal energy of a given cell is exceeded, the cell emits an impulse. The closest model of that behaviour was the TLU model. Regrettably, because the derivative is not continuous, it is not easy to train. However, the sigmoidal model is very promising, because it is structurally similar to the discrete model and there exists a simple derivative for it

<< (dF_a)/x=F_a(x)(1-F_a(x)) >>

Additionally, the slope curvature, used as a component, has proven to be very usable for modeling of complex functions.

### Geometric interpretation
When we look at a neuron with a single input, we can see that functions as a classifier. Depending on the activation function employed its response will be as follows.

For discrete nonlinear:

![Figure 5](fig5.pdf)
Figure 5 Discrete activation function

For sigmoidal:

![Figure 6](fig6.pdf)
Figure 6 Sigmoidal activation function

For a single value we have one-dimensional area, divided in two parts by the threshold[^foot-threshold]. If we increase the number of inputs to 2 we get the situation depicted on [Figure 4][]. 3-dimensional space will be divided by a plane. This interpretation can be extended with further dimensions [][#Camaro:1990]. In effect, a neuron functions as a linear classifier, or, put differently, a “linear separator” [][#Podolak:2003:Perceptron]. 
Let “X” be the inputs vector, and “W” the weights vector. Then the sum of products of inputs and their weights is a dot product of two vectors:

<< X ** W = sum_(i=0)^n (X_i W_i) >>

This product is compared to threshold value << Theta >>. So the threshold function is:

<< Theta = sum_(i=0)^n (X_i W_i) >>

Alternatively:

<< sum_(i=0)^n (X_i W_i) -1*Theta = 0 >>

Increasing the number of inputs by “-1” allows for interpreting the threshold value as a weight:

<< sum_(i=0)^(n+1) (X_i W_i)  = 0 >>

Going further: neuron’s value is a scalar product of the extended input vector and the extended weights vector compared to 0. Alternatively:

<< X**W=||X||xx||W||cos alpha >>

Where << alpha >> is the angle of the vectors. From the neuron’s perspective, it being a classifier, the only important factor is whether the value higher or equal than 0, or lower than 0. << cos(alpha) >> gives the answer to this question.  
The value of a neuron, then, depends on the angle between the weight vector and inputs vector.

<< alpha in <0,pi):  >>

<< alpha <= pi/2->1 >>

<< alpha > pi/2->0 >>

###	The Delta rule
Delta rule, developed by Widrow and Hoff [][#Widrow:Hoff:1960], known also as the Least Mean Square (LMS) method, is the most common algorithm for training neurons. 

The starting point is the definition of an error. Squared difference between the output and the expected output values is used. For a single neuron:

<< E_p=1/2(t-y)^2>>

Increasing the number of neurons:

<< E = sum_p E_p = sum_p(1/2(t-y)^2) >>

The method consists of a gradual modification of weights, so that the error function’s minimum can be found:

<< w_i^(j+1)=w_i^j+Deltaw_ij>>

Level of change depends on the error level, hence the gradient descent method. 

<< Deltaw_i = -eta(delE_p)/(delw_i) >>

<< -eta >> is the learning rate. Expanding the derivative we obtain:

<< (delE_p)/(delw_i)=(delE_p)/(dely)*(dely)/(delw_i)=(delE_p)/(dely)*(dely)/(dele)*(dele)/(delw_i)>>

Calculating:

<< (delE_p)/(dely)=-(t-y)>>

<< (dele)/(delw_i)=x_i>>

A sigmoid is used as the activation function, thanks to its derivative and a simple way to calculate the “e” argument:

<< y(x)=1/(1+x^(-betae))>>

<<(dely)/(dele)=y(1-y)>>

Summing up:

<< Delta w_i=-eta(delE_p)/(dely)*(dely)/(dele)*x_i>>

<< Delta w_i = eta(t-y)(dely)/(dele)*x_i = eta(t-y)(1-y)x_iy>>

We obtained a change of the weight as functions of: desired value, network response and current neuron’s input. 

## Glial cell simulation
Simulating glial cells is not a well explored area and is certainly worth of further investigation. An interesting addition to the SSN model would be to expand it with slower cells with much higher number of connections, which would simulate the glial cells. 

## Types of networks   
Despite its severe limitations (or maybe thanks to them!), the perceptron turned out to be worthy of further examination. It has many interesting features: linearity, intriguing assumption of convergence, paradigmatic simplicity as the method of parallel computing. There are no reasons to think that any of those qualities will translate to its multi-layer form. However, we still think that serious research is needed to accept (or dismiss) our thesis that expanding to multilayer systems is futile, [][#Minsky:Papert:1969]

### Multilayer perceptron
Multi-layered model is the typical example of the topology. A single layer is composed of independent neurons and acts as a filter. Neurons of subsequent layers are connected on an “each to each” principle. Every layer can contain a different number of neurons. In the input layer, a single neuron is associated with every single piece of data (input stream, picture point). Frequently, input data is preliminarily processed. Symbolic data are assigned either a numeric value, or a separate logic input for individual symbols. Normalizing of numeric data is recommended. Subsequent layers are referred to as “hidden layers”. Depending on the complexity of a problem, two or three of those are used. Employing a larger number significantly increases the length of network’s training without improving its quality. Depending on the number of neurons in hidden layers and the length of learning, the network can either generalize or learn the input data set. The last layer is the output layer. In classifying networks, it consists of as many outputs as the number of expected classes.

![Figure 7](fig7.pdf)
Figure 7 Multilayer perceptron

### Backpropagation
Training of a network consists of simultaneously training individual neurons employing the delta method. Training of the output layer is relatively simple. For each of its neurons, we know the expected output, and the current input and output (see 5.28).  
To train the neurons of hidden layers, we need a definition of an error. We know the global output layer error. 

<< E=sum_pE_p=sum_p1/2(t-y)^2>>

Delta rule is still valid, however according to it, the error will be a weighted sum of subsequent layer errors. The layer is indicated in the upper index. Therefore:

<< Delta w_i^n = -eta(delE_p^n)/(delw_i^n)>>

<< Delta w_i^n = -eta(delE_p^n)/(dely^n)*(dely^n)/(dele)x_i>>

Simplifying:

<< Delta w_i^n = -etadelta^nx_i>>

<< delta^n = (dely^n)/(dele)sum(delta^mw_n^m)>>

Depending on the learning rate << eta >> the network can quickly find local minimum. Therefore, additional algorithms are often employed, to manipulate << eta >> so that the result is as close to the minimum as possible

### Conjugate Gradient Algorithm
Conjugate gradient algorithm is a variation of the backpropagation algorithm. It cumulatively modifies the weights at the end of every training cycle, and not (like the backpropagation model does), after the presentation of every element of the training set.
 
### Other algorithms
Completely different approach to training a network using a teacher is possible. In genetic algorithm methods, the network undergoes subsequent mutations. The algorithm is interesting, but not very efficient. 

### Hopfield network as an example of a recurrent network

![Figure 8](fig8.pdf)
Figure 8 Recurrent network

Hopfield network is a type of associative memory. It is comprised of neurons connected to every other one, except themselves. Comparing it to the multilayer model, it is a looped single layered network, in which the neurons’ connections with themselves are removed. Every neuron is both the input and the output. Network completes its task if after two iterations the output does not change, which means that the network gives out a stable output. It is used for identifying partially damaged pictures.

### Probabilistic neural networks
Bayes’ theorem:

<< P(T|X)=(P(T)P(X|T))/P(X)>>

Probabilistic Neural Networks (PNN) are comprised of four layers:

* Input
* Patterns
* Summation
* Output

In contrast with the models presented above, in PNN every node of the training set is associated to a separate neuron of the second layer. 
Data classification is a typical application of PNN, since the output given by the network is a probability that the verified data belongs to a class. Training of these networks is quick, thanks to Bayes’ theorem on dependent probability, but they are also very sensitive to the quality of data. The training set should be representative and quantitatively larger than the single element of the set (number of parameters specifying the element).  

