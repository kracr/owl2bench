# Experiments

Note: The file contains details of the experiments and results that were reported in our paper. 

All our evaluations ran on a server with an AMD Ryzen Threadripper 2990WX 32-Core Processor and 128GB of RAM. The server ran on a 5.3.0-46-generic \#38~18.04.1-Ubuntu SMP operating system. We use Java 1.8 and OWL API 5.1.11 in the experiments. For the performance evaluation we fixed 24GB of RAM. We reported average time taken for 5 independent runs. 

We ran our benchmark on:

1. [ ELK 0.4.3 ](https://github.com/liveontologies/elk-reasoner/releases/tag/v0.4.3) 

2. [ HermiT 1.3.8.1 ](https://github.com/kracr/owl2bench/tree/master/Experiments/hermit)

3. [ JFact 5.0.0 ](https://github.com/kracr/owl2bench/tree/master/Experiments/jfact)

4. [ Konclude 0.6.2 ](https://www.derivo.de/fileadmin/externe_websites/ext.derivo/KoncludeReleases/v0.6.2-544/Konclude-v0.6.2-544-Linux-x64-GCC4.3.2-Static-Qt4.8.5.zip)

5. [ Openllet 2.6.4 ](https://github.com/kracr/owl2bench/tree/master/Experiments/openllet)

6. [ Pellet 2.3.6 ](https://github.com/kracr/owl2bench/tree/master/Experiments/pellet2)

7. [ GraphDB 9.0.0 ](https://www.ontotext.com/products/graphdb/graphdb-free/)

8. [ Stardog 7.0.2 ](https://www.stardog.com/)


We first generated the datasets using OWL2Bench for the performance evaluation of reasoners (details for dataset generation given in the [ OWL2Bench's README ](https://github.com/kracr/owl2bench/blob/master/README.md#usage)). The generated datasets are based on the number of universities. So we generated datasets for 1, 2, 5, 10, 20, 50, 100, 200 universities. The size varies from approximately 50,000 axioms (for 1 university) to 14 million axioms(for 200 universities). Few lines from the output are given below:

---------------------------------------------

...

...

U0 University of Meadow Woods

Total Axiom Count=50755

Total Logical Axiom Count=50450

Saved Ontology Format is RDF/XML

Finished Writing to file /path/OWL2EL-1.owl

---------------------------------------------

**Table 3** in the paper reports the size of generated axioms. Note that the size of ABox and TBox axioms are given separately in the table. While when you execute the code, the line on the output console 'Total Logical Axiom Count=50485' is the combined TBox + ABox axiom count.



The datasets generated (mentioned-above) using OWL2Bench was then used to evaluate the performance of:

1. Six ontology reasoners (ELK, HermiT, JFact, Openllet, Pellet, Konclude) with respect to the time taken for performing three major reasoning tasks, namely, **consistency checking**, **classification**, and **realisation** (**Table 4 and 5** in the paper). 

2. Two SPARQL query engines in terms of their loading time (**Table 6**) and query response time (**Table 7**).


## Details for Table 4 and 5 :

The time-out for each of these experiments was set to 90 minutes. This directory already consists of the java code for **HermiT**, **Openllet**, **Pellet**, and **JFact** that were implemented using the OWL API. Two arguments need to be passed: *Input File name (path)* and *Reasoning Task*. The Reasoning Tasks could be C (for Consistency Checking), R (for Realisation), CT (for Classification Time). For example: To run each reasoner task from command line, 

mvn compile

mvn install

mvn exec:java -Dexec.mainClass=debug.hermit.Hermit -Dexec.args="input_filepath/OWL2EL.owl C"

For the other two reasoners **Konclude** (https://www.derivo.de/fileadmin/externe_websites/ext.derivo/KoncludeReleases/v0.6.2-544/Konclude-v0.6.2-544-Linux-x64-GCC4.3.2-Static-Qt4.8.5.zip) and **ELK** (https://github.com/liveontologies/elk-reasoner/releases/tag/v0.4.3) standalone executable files were used. Also, for ELK and Konclude OWL Functional syntax was used. The RDF/XML syntax was converted to OWL/Functional using the file *convert.java* (already present in the directory). For example: ./Konclude classification -i OWL2DL-1.owl, java -jar elk-standalone.jar -i OWL2EL-100.owl --classify


## Details for Table 6 and 7 : 

We compared GraphDB and Stardog in terms of their loading time (Table 6) and query response time (Table 7). We did not put limits on RAM usage while Loading of axioms for both the query engines. However, for Query Response time, we kept a time-out of 10minutes and RAM limit 24GB.

----------------------------

Stardog commands:

----------------------------

/path/stardog-7.2.0/bin/stardog-admin server start

**Loading:** /path/stardog-7.2.0/bin/stardog-admin db create -n jar1DL1 -o reasoning.type="DL" query.timeout='600s' -- /path/OWL2DL-1-output.owl

**Query Execution:** /path/stardog-7.2.0/bin/stardog query -r jar1DL1 "SELECT DISTINCT ?y ?x WHERE { ?x rdf:type :Person. ?y :hasSameHomeTownWith ?x }"
 
----------------------------

GraphDB commands:

----------------------------

graphdb -Xms24g -Xmx24g

**Loading:** We used its LoadRDF tool that does offline loading and is mainly used for loading large files. <graphdb-dist>/bin/loadrdf -f -i <repo-name> -m parallel < data file(s)>

**Query Execution:** SPARQL queries were executed from GraphDB workbench.


If required, the datasets that were used for the experiments (in RDF/XML Format) are available at https://drive.google.com/drive/u/3/folders/1HYURRLaQkLK8cQwV-UBNKK4_Zur2nU68. The datasets were generated using default settings and seed value of 1. 



           
