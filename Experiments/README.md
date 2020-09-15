# Experiments

Note: This file contains details of the experiments and results that were reported in our paper.

-------------------------------------------------------------------------------------

Directory structure:

1. README

2. Scripts for Evaluations: steps to follow (dos2unix script.sh, chmod +x script.sh, ./script.sh)

	2.1 main.sh : Main script that runs all the below given scripts. Steps to follow are provided as 'comments' on the top in main.sh

	2.2 dataset.sh: Script for generating exact same Dataset used in the paper (Table 3).

	2.3 reasoningTask1.sh: Script for 3 tasks consistency, realisation, classification on HermiT, Openllet, Pellet, JFact

	2.4 reasoningTask2.sh: Script for 3 tasks consistency, realisation, classification on ELK

	2.5 reasoningTask3.sh: Script for 3 tasks consistency, realisation, classification on Konclude

	2.6 LoadingGraphDB.sh: Script for loading datasets on GraphDB using its loadRDF tool.

	2.7 LoadingStardog.sh: Script for loading datasets on Stardog.

	2.8 QueryResponse.sh: Script for executing SPARQL queries on Stardog.

3. java runnable jar files: additional jar files (HermiT, Openllet, Pellet, JFact, convert) that were generated using Eclipse IDE from their source codes. Source code files are also provided in the directory.

-----------------------------------------------------------------------------------

All our evaluations ran on a server with an AMD Ryzen Threadripper 2990WX 32-Core Processor and 128GB of RAM. The server ran on a 5.3.0-46-generic \#38~18.04.1-Ubuntu SMP operating system. **We used Java 1.8 and OWL API 5. For the performance evaluation we fixed 24GB of RAM**. We reported average time taken for 5 independent runs. 


Our benchmark, OWL2Bench, generates varying size datasets for ontology reasoner evaluations. So, in order to demonstrate the utility of OWL2Bench we ran the generated datasets on 6 OWL2Reasoners and 2 SPARQL Query Engines. The list is given below. The version used along with their links are also specified.

1. [ ELK 0.4.3 ](https://github.com/liveontologies/elk-reasoner/releases/download/v0.4.3/elk-distribution-0.4.3-standalone-executable.zip)(https://github.com/liveontologies/elk-reasoner/releases/download/v0.4.3/elk-distribution-0.4.3-standalone-executable.zip) 

2. [ HermiT 1.3.8.1 ](https://jar-download.com/artifacts/com.hermit-reasoner/org.semanticweb.hermit/1.3.8.1/source-code)(https://jar-download.com/artifacts/com.hermit-reasoner/org.semanticweb.hermit/1.3.8.1/source-code)

3. [ JFact 5.0.0 ](https://jar-download.com/artifacts/net.sourceforge.owlapi/jfact/5.0.0/source-code)(https://jar-download.com/artifacts/net.sourceforge.owlapi/jfact/5.0.0/source-code)

4. [ Konclude 0.6.2 ](https://www.derivo.de/fileadmin/externe_websites/ext.derivo/KoncludeReleases/v0.6.2-544/Konclude-v0.6.2-544-Linux-x64-GCC4.3.2-Static-Qt4.8.5.zip)(https://www.derivo.de/fileadmin/externe_websites/ext.derivo/KoncludeReleases/v0.6.2-544/Konclude-v0.6.2-544-Linux-x64-GCC4.3.2-Static-Qt4.8.5.zip)

5. [ Openllet 2.6.4 ](https://jar-download.com/artifacts/com.github.galigator.openllet/openllet-owlapi/2.6.4/source-code)(https://jar-download.com/artifacts/com.github.galigator.openllet/openllet-owlapi/2.6.4/source-code)

6. [ Pellet 2.3.6 ](https://jar-download.com/artifacts/com.github.ansell.pellet/pellet-core/2.3.6-ansell/source-code)(https://jar-download.com/artifacts/com.github.ansell.pellet/pellet-core/2.3.6-ansell/source-code)

7. [ GraphDB 9.0.0 ](https://www.ontotext.com/products/graphdb/graphdb-free/)(http://download.ontotext.com/owlim/cc4fadf4-e3ae-11e9-9439-42843b1b6b38/graphdb-free-9.0.0-dist.zip)

8. [ Stardog 7.0.2 ](https://www.stardog.com/get-started/)(https://www.stardog.com/get-started/)

Requirements: Java 1.8 and Maven, 24GB RAM for evaluations. Run the main.sh script for all the experiments except the experiments for GraphDB's Query Performance.

We first generated the datasets using OWL2Bench. In order to generate the datasets used in the paper, follow the steps given in the [ OWL2Bench's README ](https://github.com/kracr/owl2bench/blob/master/README.md#usage)) using default seed value '1'. Or, run the **[script](https://github.com/kracr/owl2bench/blob/master/Experiments)** datasets.sh. The generated datasets are based on the number of universities. So we generated datasets for 1, 2, 5, 10, 20, 50, 100, 200 universities. The size varies from approximately 50,000 axioms (for 1 university) to 14 million axioms(for 200 universities). **Table 3** in the paper reports the size of generated axioms. Few lines from the output look like this:

---------------------------------------------

...

...

U0 University of Meadow Woods

Total Axiom Count=50755

Total Logical Axiom Count=50450

Saved Ontology Format is RDF/XML

Finished Writing to file /path/OWL2EL-1.owl

---------------------------------------------

Note that the size of ABox and TBox axioms are given separately in the table. While on executing the code, the line on the output console 'Total Logical Axiom Count=50485' is the combined TBox + ABox axiom count.


The datasets generated (mentioned-above) using OWL2Bench were then used to evaluate the performance of:

1. Six ontology reasoners (ELK, HermiT, JFact, Openllet, Pellet, Konclude) with respect to the time taken for performing three major reasoning tasks, namely, **consistency checking**, **classification**, and **realisation** (**Table 4 and 5** in the paper). 

2. Two SPARQL query engines in terms of their loading time (**Table 6**) and query response time (**Table 7**).


## Details for Table 4 and 5 :

The time-out for each of these experiments was set to 90 minutes. This directory already consists of the java source code and [executable jar files](https://github.com/kracr/owl2bench/tree/master/Experiments/java%20runnable%20jar%20files)  for **HermiT**, **Openllet**, **Pellet**, and **JFact** that were implemented using the OWL API. Two arguments need to be passed: *Input File name (path)* and *Reasoning Task*. The Reasoning Task argument could be *consistency* (for Consistency Checking), *realisation* (for Realisation Task), *classification* (for Classification Task). However, we used the java runnable jar files in these scripts..

For the other two reasoners **Konclude** (https://www.derivo.de/fileadmin/externe_websites/ext.derivo/KoncludeReleases/v0.6.2-544/Konclude-v0.6.2-544-Linux-x64-GCC4.3.2-Static-Qt4.8.5.zip) and **ELK** (https://github.com/liveontologies/elk-reasoner/releases/tag/v0.4.3) standalone executable files were used. For example: ./Konclude classification -i OWL2DL-1.owl, java -jar elk-standalone.jar -i OWL2EL-100.owl -c

Also, for ELK and Konclude OWL Functional syntax was used. The dataset could be directly generated in OWL-Functional syntax by changing the configuration parameter in OWL2Bench source code or can be converted using Protege. We converted RDF/XML to OWL/Functional using the file *[convert.jar](https://github.com/kracr/owl2bench/tree/master/Experiments/java%20runnable%20jar%20files)* (already present in the directory). 

**For reasoning tasks run the [ script ](https://github.com/kracr/owl2bench/blob/master/Experiments)** reasoningTasks1.sh, reasoningTasks2.sh, reasoningTasks3.sh,

## Details for Table 6 and 7 : 

We compared GraphDB and Stardog in terms of their loading time (Table 6) and query response time (Table 7). We did not put limits on RAM usage while loading axioms for both the query engines. However, for Query Response time, we kept a time-out of 10minutes and RAM limit 24GB. The queries are available at https://doi.org/10.5281/zenodo.3838735.

----------------------------

Stardog steps:

----------------------------
1) Download Stardog 7.0.2 from https://www.stardog.com/get-started/ and set it up following the instructions given at https://www.stardog.com/docs/#_starting_stardog.

2) To customize the environment in which stardog is run for query execution: export STARDOG_SERVER_JAVA_ARGS="-Xms24g -Xmx24g -XX:MaxDirectMemorySize=24g". You can skip this step for loading.

3) Start the server: /path/stardog-7.2.0/bin/stardog-admin server start

4) **For Loading:** /path/stardog-7.2.0/bin/stardog-admin db create -n jar1DL1 -o reasoning.type="DL" query.timeout='600s' -- /path/OWL2DL-1-output.owl. [Script](https://github.com/kracr/owl2bench/blob/master/Experiments) LoadingStardog.sh

5) **For Query Execution use commands like:** /path/stardog-7.2.0/bin/stardog query -r jar1DL1 "SELECT DISTINCT ?y ?x WHERE { ?x rdf:type :Person. ?y :hasSameHomeTownWith ?x }". [Script](https://github.com/kracr/owl2bench/blob/master/Experiments) QueryResponse.sh
 
----------------------------

GraphDB steps:

----------------------------
1) Download your GraphDB 9.0.0 distribution file from https://www.ontotext.com/products/graphdb/graphdb-free/ and unzip it.

2) **For Loading:** We used its LoadRDF tool that does offline loading and is mainly used for loading large files. <graphdb-dist>/bin/loadrdf -f -i <repo-name> -m parallel < data file(s)>. [Script](https://github.com/kracr/owl2bench/blob/master/Experiments) LoadingGraphDB.sh

3) Start the GraphDB Server and Workbench interface by executing the graphdb startup script located in the $graphdb_home/bin folder: graphdb -Xms24g -Xmx24g

4) A message appears in the console telling you that GraphDB has been started in Workbench mode. To access the Workbench, open http://localhost:7200/ in your browser.

5) **For Query Execution:** SPARQL queries were executed from GraphDB workbench. So, the queries given at https://doi.org/10.5281/zenodo.3838735 can be directly executed (http://graphdb.ontotext.com/documentation/free/querying-data.html).


Note: The scripts do not automatically find the average over 5 independent runs because there could be time-out, memory errors or other errors. So, the average of time-taken over 5 runs was computed manually. 

If required, all the datasets that were used for the experiments are available at https://drive.google.com/drive/u/3/folders/1HYURRLaQkLK8cQwV-UBNKK4_Zur2nU68. The datasets were generated using default settings and all are in RDF/XML format. 



           
