# Concluding remarks [Chapter 7]
The aim of the project was to extract knowledge from existing data on immunotherapy, in order to develop a system supporting doctors in decisions concerning recommending patients for this costly treatment. Conducted researched demonstrated that the number of patients, on whom data was available, was too small for effective neural network utilization. However, the network built does provide a measure of support for doctors, albeit smaller than it was initially planned. 

The JIM system developed combines “open source” components and allows for heuristic analysis, visualizations and inference based on treatment data. After adding more patients to the data set, it will be capable of quick analysis of prognoses. 

Thanks to its flexible structure it is possible to adapt it to different problems requiring an optimization of the network’s structure. 
Two universal components were developed for the needs of the system, allowing for flexible utilization of the xml data as inputs to neural networks based on JOONE library. They will be included as integral parts of the next version of this library. 

Learning about immunology and the defense mechanisms of living organisms proved to be very engaging and stimulating for the author. It is fascinating how little does medicine know about the immunotherapy’s mechanisms. Another challenging aspect of the research was sorting the flows of data and results to proceed from the experimental stage to sequential processing. The most interesting part, however, was the network selection process. It was difficult to reconcile the limitations of the genetic algorithms of the simulating library with the large amount of parameters defining a network. It was also difficult to define the “stopping condition” of network training, to allow for calculating the adaptation function in real time. Yet overcoming these obstacles proved very satisfying.   

In case of obtaining more representative data set, e.g. data on few hundred patients undergoing desensitization, the existing solutions are worth developing further by:

* in respect to the neural network:
    * implementing training algorithms alternative to backpropagation
    * increasing the time of network evolution
    * monitoring the process of population evolution
    * proceeding from initiating the network with random data to initial training by using a genetic algorithm [Yao, 1993],
    * neural network could be stored in portable Predictive Model Markup Language format
* adding further inference engines
    * not limiting the analysis only to neural networks
    * ideally, at least one engine synthesizing conclusions of other engines should be developed
        * possible implementation would consist of several parallel engines, which would present a combined conclusion to users
* developing a graphical user interface
    * it would improve data entry and allow for managing patients’ database
* expanding the system with the HL7[^foot-hl7] interface
    * it would allow o integrate the system with other existing medical solutions


# JIM Requirements specification [Appendix A]
## Meta-requirements
### [JIM-HRS-0001]
System’s objective is to support an immunologist when recommending patients for SIT therapy

### [JIM-HRS-0002]
Inference of the system can be based on any heuristic embedded as an exchangeable plug-in

### [JIM-HRS-0003]
System should be based on free software and not require any license fees. Suggested licenses are:
	a)	GNU GPL
	b)	GNU LGPL
	c)	BSD
	d)	Apache

### [JIM-HRS-0004]
The system should have full functionality at the command line level, so it can be easily embedded in existing solutions

### [JIM-HRS-0005]
Optionally, the system should have a graphical user interface

### Specification of the software requirements

### [JIM-SRS-0100]
Software will be based on open standards

### [JIM-SRS-0200]
Java will be the main language used; parts of logic could be implemented in XSL 2.0. When using other languages, attention should be placed to their portability. Languages used are defined in the following sub-requirements:

### [JIM-SRS-0201]
SAXON library will be used for XSL 2.0 transformations

### [JIM-SRS-0300]
Heuristic based on neural networks will be implemented

### [JIM-SRS-0301]
JOONE library will be used for the neural computations

### [JIM-SRS-0400]
In case of modular design, parts of code should be shared

### [JIM-SRS-0500]
All data will be stored in XML format. This is defined in following sub-requirements:

### [JIM-SRS-0501]
Input and output data will be stored in XML format

### [JIM-SRS-0502]
Temporary data and neural networks will be stored in XML format. 

### [JIM-SRS-0503]
XML based formats used will have DTD and XSD defined syntax

### [JIM-SRS-0504]
For presenting XML data in Java code the dom4j library will be used

### [JIM-SRS-0800]
Basic command line interface will be available through Ant

### [JIM-SRS-0900]
Additional GUI will be implemented. It is defined in following sub-requirements

### [JIM-SRS-0901]
GUI will allow for data management

### [JIM-SRS-0902]
GUI will allow for inferring based on newly entered data

### [JIM-SRS-0903]
GUI will allow for inferring based on archival data

### [JIM-SRS-0904] GUI will allow for training of the system using archival data

### [JIM-SRS-0905]
GUI will be an Eclipse plug-in

# Contents of the CD attached [Appendix B]
Root folder:

* jim/ -  JIM system as an Eclipse environment project
* oprogramowanie/ - applications and external libraries useful for system development
* pismiennictowo/ - papers, presentations and documentations used when working on thesis

„Jim” folder contains „build.xml” script parsed by ant and config files:

* jim.properties
* log4j-debug.properties 
* log4j.properties

Additionally, following folders:

* bin/ - scripts used for data transformations
* build/ - compiled code
* data.chart/ - generated chart
* data.conf/ - files configuring transformations, expert interview
* data.document/ - generated tables included in the document
* data.nn/ - stored neural network
* data.original/ - original data provided by dr Ewa Cichocka-Jarosz
* data.preprocessed/ - data in different stages of automatic processing
* data.training/ - automatically generated training sets for the network
* dtd/ - syntax of the patients’ database
* lib/ - libraries used
* log/ - system event log
* src/ - system source
* statistica/ - folder for storing analyses conducted using Statistica Neural Networks

[#Cichocka-Jarosz:1997]: Ewa Cichocka-Jarosz, Skuteczność swoistej immunoterapii u dzieci z alergią pyłkową w ocenie klinicznej i laboratoryjnej, Collegium Medicum Uniwersytetu Jagiellońśkiego, Kraków 1997

[#Resmedica:UkladImmunologiczny]: http://www.resmedica.pl/rmart0015.html

[#Alergologia:1998]: Neils Mygind, Ronald Dahl, Søren Pedersen, Kristian Thestrup-Pedersen, Alergologia, Wydawnictwo Medyczne Urban & Partner, Wrocław 1998

[#Duch:2004:NaukiKognitywne]: http://www.phys.uni.torun.pl/~duch/cognitive.html

[#Jura:Krzanowska:1992]: Pod redakcją Czesław Jura, Halina Krzanowska, Leksykon biologiczny, Wiedza Powszechna, Warszawa 1992 

[#Greenfield:1999]: Susan Greenfield, Mózg, Wydawnictwo CiS, Warszawa 1999

[#Veggeberg:1996]: Scott K. Veggeberg, Leczenie Umysłu, Pruszyński i S-ka, Warszawa 1996

[#Szarski:1976]: [Ed.] Henryk Szarski, Anatomia porównawcza kręgowców, TWydawnictwo Naukowe PWN, Warszawa 1997 

[#Camargo:1990]: Francisco A. Camargo, Learning Algorithms in Neural Networks, 1990. Available as PostScript at http://citeseer.csail.mit.edu/camargo90learning.htm

[#Tadeusiewicz:1993]: Ryszard Tadeusiewicz, Sieci Neuronowe, AOW RM Warszawa 1993

[#Mulawka:1996]: Jan J. Mulawka, Systemy Ekspertowe, WNT Warszawa 1996

[#Widrow:Hoff:1960]: Widrow, B. and Hoff, M. E. Adaptive switching circuits. In IRE WESTCON Connection Record, 1960.

[#Minsky:Papert:1969]: Marvin Lee Minsky and Seymour Papert, Perceptrons; an introduction to computational geometry, MIT Press, 1969.

[#Joone:2005:Documentation]: Paolo Marrone, The Complete Guide - All you need to know about Joone, available as  PDF: http://sourceforge.net/projects/joone/

[Not cited][#Bevington:Robinson:1992]
[#Bevington:Robinson:1992]: Philips R. Bevington, D. Keith Robinson, Data Reduction and Error Analysis for the Physical Sciences second ed. McGraw-Hill 1992

[Not cited][#Bezzi1:Gundersen:2004]
[#Bezzi1:Gundersen:2004]: Paola Bezzi1, Vidar Gundersen, José Luis Galbete, Gerald Seifert, Christian Steinhäuser, Ethel Pilati1, Andrea Volterra, Astrocytes contain a vesicular compartment that is competent for regulated exocytosis of glutamate, Nature Neuroscience 7, 613 - 620 (2004), Published online: 23 May 2004

[Not cited][#Grevers:Rocken:2002]
[#Grevers:Rocken:2002]: Gerhard Grevers, Martin Röcken, Ilustrowany Podręcznik Chorób Alergicznych, Wydawnictwo Medyczne Urban & Partner, Wrocław 2002

[Not cited][#IBM:2005:Bluegene]
[#IBM:2005:Bluegene]: http://www.research.ibm.com/bluegene/

[Not cited][#Immunologia:2002]
[#Immunologia:2002]: Pod redakcją Gołąb J., Jakóbisiak M., Lasek W., Immunologia, Wydawnictwo Naukowe PWN, Warszawa 2002

[Not cited][#Lula:1998]
[#Lula:1998]: Paweł Lula, Jednokierunkowe sieci neuronowe w modelowaniu zjawisk społeczno – gospodarczych, Akademia Ekonomiczna w Krakowie, Kraków 1998

[Not cited][#Podolak:2003:Perceptron]
[#Podolak:2003:Perceptron]: Igor T. Podolak, Sieci Neuronowe – Perceptron, available as PDF: http://www.ii.uj.edu.pl/%7Epodolak/pub/sn1/sn1foils-perceptron.pdf

[Not cited][#Rutkowska:Pilinski:Rutkowski:1999]
[#Rutkowska:Pilinski:Rutkowski:1999]: Danuta Rutkowska, Maciej Piliński, Leszek Rutkowski, Sieci neuronowe, algorytmy genetyczne i systemy rozmyte, Wydawnictwo Naukowe PWN, Warszawa 1997

[Not cited][#Statistica:2004]
[#Statistica:2004]: StatSoft, Inc. (2004). STATISTICA (data analysis software system), version 6. www.statsoft.com.

[Not cited][#StatSoft:2001]
[#StatSoft:2001]: StatSoft, Wprowadzenie do sieci neuronowych. StatSoft Kraków 2001 

[Not cited][#Yao:1993]
[#Yao:1993]: Xin Yao, A review of evolutionary artificial neural networks, International Journal of Intelligent Systems,  8(4):539-567. Available as PostScript: ftp://www.cs.adfa.edu.au/pub/xin/ijis.ps.Z


[^foot-threshold]: Two sets of points A and B on n-dimensional input space are linearly separatable if there exists n+1 of  << w_1,...,w_(n+1) >>, such that every point << x in A >> satisfies << sum_(i=1)^n(W_i X_i) >= W_(n+1) >> , and every << y in B >> satisfies << sum_(i=1)^n(W_i Y_i) >= W_(n+1) >>

[^foot-deltarule]: Precise definition of the error can be found in Chapter “3.3.2. Delta Rule”

[^foot-statsoft]: http://www.statsoft.com/

[^foot-snns]: http://www-ra.informatik.uni-tuebingen.de/SNNS/

[^foot-cilib]: http://cilib.sourceforge.net/

[^foot-jaga]: http://www.jaga.org/

[^foot-jgap]: http://jgap.sourceforge.net/

[^foot-osi]: http://opensource.org/docs/certification_mark.php

[^foot-joone]: http://www.jooneworld.com/

[^foot-saxon]: http://saxon.sourceforge.net/

[^foot-applib]: http://www.sscc.ru/matso/rozhenko/applib/

[^foot-eclipse]: http://www.eclipse.org/

[^foot-hl7]: http://www.hl7.org/

