['excel-tab', 'excel']
| Name/Description | Choice | Rationale | Remarks |  |  |  |  |  |  |  |
| --- |
| Stage 1: Converting textual data into xml | perl | Language optimized for textual data processing |  |  |  |  |  |  |  |  |
| Stage 2: Filtering data based on the expert advice | xslt | Language optimized for xml->xml conversion | After expert’s conclusions were written up, an xml file was created  |  |  |  |  |  |  |  |
| Stage 3: Data gap-filling | java, xslt | Part of data processing was convenient in xslt. Interpolation using secondary splines. Existing java numerical libraries were used  |  |  |  |  |  |  |  |  |
| Stage 4: Knowledge extraction from data | java | Large number of libraries, transferability | Neural network library selected: joone
Network architecure selection library chosen: jgap |  |  |  |  |  |  |  |
| Stage 5: Infering | java | Infering means transoforming the xml patients’ data into output data (conlusions), by using an engine and knowledge. Stage 5 shares the code with Stage 4.  |  |  |  |  |  |  |  |  |
| Transformation coordination tool | ant | Native xslt, java support (compiling, method developing) |  |  |  |  |  |  |  |  |
| Network library  | Joone | Open, well documented. Capacity for distributed computing. Easy implementation of custom input/output layers | Library has been modified by expanding with xml input data support |  |  |  |  |  |  |  |
| Data imaging | xslt, svg | svg is an xml expression of graphical objects. Transformation of xml data in svg by using xslt is natural.  |  |  |  |  |  |  |  |  |
| Transformation | mainly xslt 2.0 | Well defined standard. Capable of converting xml into any variation of xml.  | Objective was to create some appendixes and figures as xml->pdf conversion |  |  |  |  |  |  |  |
| Xslt transformation | saxon | Closest implementation of xslt 2.0 (working draft). The author collaborates on defining the standard |  |  |  |  |  |  |  |  |
| Data format | xml | Univarsal format. Large number of libraries and editors interpreting and checking the syntax compatibility.  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
|  |  |  |  |  |  |  |  |  |  |  |
