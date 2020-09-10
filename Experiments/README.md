# Experiments
OWL2Bench was used to evaluate 6 OWL 2 Reasoners (ELK, HermiT, Openllet, Pellet, Konclude and JFact) and 2 SPARQL Query Engines (Stardog and GraphDB). 

This directory consists of the java code for HermiT, Openllet, Pellet, and JFact that were implemented using the OWL API. For the other two reasoners Konclude (https://www.derivo.de/fileadmin/externe_websites/ext.derivo/KoncludeReleases/v0.6.2-544/Konclude-v0.6.2-544-Linux-x64-GCC4.3.2-Static-Qt4.8.5.zip) and ELK(https://github.com/liveontologies/elk-reasoner/releases/tag/v0.4.3) standalone executable files were used. We compare the six ontology reasoners with respect to the time taken for performing three major reasoning tasks, namely, consistency checking, classification, and realisation. 

We use GraphDB 9.0.08 and Stardog 7.0.27 for running the SPARQL queries. GraphDB supports SPARQL
queries and OWL 2 QL, OWL 2 RL profiles (but not OWL 2 EL and OWL 2 DL). Stardog's underlying reasoner is Pellet and hence it supports ontologies of
all the profiles including OWL 2 DL. For the GraphDB we used its LoadRDF tool that does offline loading and is mainly used for loading large files. We compare these two systems in terms of their loading time and query response time.

The datasets used for the experiments (in RDF/XML Format) are available at https://drive.google.com/drive/u/3/folders/1HYURRLaQkLK8cQwV-UBNKK4_Zur2nU68 . The datasets were generated using default seed value of 1.



           
