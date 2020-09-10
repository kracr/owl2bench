# Experiments

Note: The file contains details of the experiments and results that were reported in our paper. 

All our evaluations ran on a server with an AMD Ryzen Threadripper 2990WX 32-Core Processor and 128GB of RAM. The server ran on a 5.3.0-46-generic \#38~18.04.1-Ubuntu SMP operating system. We use Java 1.8 and OWL API 5.1.11 in the experiments. For the performance evaluation we used 24GB of RAM. We reported average time taken for 5 independent runs. 

We ran our benchmark on:

1. ELK 0.4.3 
2. HermiT 1.3.8.1 
3. JFact 5.0.0 
4. Konclude 0.6.2 
5. Openllet 2.6.4 
6. Pellet 2.3.6 
7. GraphDB 9.0.0 
8. Stardog 7.0.2

We first generated the datasets using OWL2Bench for the performance evaluation of reasoners (details for dataset generation given in the OWL2Bench's readme). The generated datasets are based on the number of universities. So we generated datasets for 1, 2, 5, 10, 20, 50, 100, 200 universities. The size varies from approximately 50,000 axioms (for 1 university) to 14 million axioms(for 200 universities). We compared the six ontology reasoners (ELK, HermiT, JFact, Openllet, Pellet, Konclude) with respect to the time taken for performing three major reasoning tasks, namely, **consistency checking**, **classification**, and **realisation**. The time-out for each of these experiments was set to 90 minutes (Table 4 and 5 in the paper). 

This directory already consists of the java code for **HermiT**, **Openllet**, **Pellet**, and **JFact** that were implemented using the OWL API. Two arguments need to be passed: *Input File name (path)* and *Reasoning Task*. The Reasoning Tasks could be C (for Consistency Checking), R (for Realisation), CT (for Classification Time). For example: To run each reasoner task from command line, 

mvn compile

mvn install

mvn exec:java -Dexec.mainClass=debug.hermit.Hermit -Dexec.args="input_filepath/OWL2EL.owl C"

For the other two reasoners **Konclude** (https://www.derivo.de/fileadmin/externe_websites/ext.derivo/KoncludeReleases/v0.6.2-544/Konclude-v0.6.2-544-Linux-x64-GCC4.3.2-Static-Qt4.8.5.zip) and **ELK** (https://github.com/liveontologies/elk-reasoner/releases/tag/v0.4.3) standalone executable files were used. Also, for ELK and Konclude OWL Functional syntax was used. The RDF/XML syntax was converted to OWL/Functional using the file *convert.java* (already present in the directory). For example: ./Konclude classification -i OWL2DL-1.owl, java -jar elk-standalone.jar -i OWL2EL-100.owl --classify

We compared GraphDB and Stardog in terms of their loading time (Table 6) and query response time (Table 7). We did not put limits on RAM usage while Loading of axioms for both the query engines. However, for Query Response time, we kept a time-out of 10minutes and RAM limit 24GB.

Example of Stardog commands:

export STARDOG_SERVER_JAVA_ARGS="-Xms24g -Xmx24g -XX:MaxDirectMemorySize=64g"

/path/stardog-7.2.0/bin/stardog-admin server start

Loading: /path/stardog-7.2.0/bin/stardog-admin db create -n jar1DL1 -o reasoning.type="DL" query.timeout='600s' -- /path/OWL2DL-1-output.owl

Query Execution: /path/stardog-7.2.0/bin/stardog query -r jar1DL1 "SELECT DISTINCT ?y ?x WHERE { ?x rdf:type :Person. ?y :hasSameHomeTownWith ?x }"
 
GraphDB supports SPARQL queries and OWL 2 QL, OWL 2 RL profiles (but not OWL 2 EL and OWL 2 DL). For the GraphDB we used its LoadRDF tool that does offline loading and is mainly used for loading large files. Queries were executed from GraphDB workbench.

server : graphdb -Xms24g -Xmx24g

Loading: <graphdb-dist>/bin/loadrdf -f -i <repo-name> -m parallel <RDF data file(s)>



If required, the datasets that were used for the experiments (in RDF/XML Format) are available at https://drive.google.com/drive/u/3/folders/1HYURRLaQkLK8cQwV-UBNKK4_Zur2nU68. The datasets were generated using default settings and seed value of 1. 



           
