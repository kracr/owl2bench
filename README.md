# OWL2Bench Documentation
This document provides documentation for the first version of our benchmark OWL2Bench. OWL2Benchv1.0 can generate ABox axioms for fixed TBox to test the scalability and performance of OWL 2 Reasoners and SPARQL Query Engines.

# Table of Contents
1. [ Introduction. ](#intro)

2. [ About the Repository. ](#repo)

3. [ TBox Details. ](#tbox)

4. [ Usage. ](#usage)

   4.1 [ Direct execution using executable jar (with default configurations). ](#exe)
   
   4.2 [ Using Source Code (with or without default configurations). ](#code)
   
5. [ Future Work. ](#future)
<a name="intro"></a>
## 1. Introduction
OWL 2 is gaining popularity in a variety of domains because of its high level of expressivity. OWL 2 has several profiles such as OWL2 EL, OWL 2 QL, OWL 2 RL, and OWL 2 DL that vary in terms of their expressivity and reasoning performance. There are several reasoners such as HermiT, Openllet, JFact, ELK that  support  different  OWL  2  profiles. Some of the querying engines such as Stardog, GraphDB, Virtuoso etc are also backed by the reasoners so as to help answer the queries that involve reasoning. But there is no benchmark that can test reasoners for all OWL 2 profiles in terms of coverage of supported constructs, scalability and reasoning performance. In this work,  we  describe  our  ongoing  efforts  to  address  this  shortcoming  by developing a standard benchmark for complete evaluation of reasoners that support OWL 2 profiles. Our benchmark is an extension of the well-known University Ontology Benchmark (UOBM). It includes an ontology for each profile covering the set of constructs supported by that profile,generation of synthetic data scalable to arbitrary size and a separate setof SPARQL queries for each profile. We use our benchmark to evaluatethe performance of several reasoners and SPARQL query engines. 

The images below represent a subset of TBox and ABox. First image represents the TBox i.e classes and relationships that exist between them. Relationships written in red represent added OWL 2 constructs. Second image represents the ABox (generated instances) red dashed edges in the ABox represent the interlinks across Universities.

![Sample TBox](https://github.com/GunjanSingh1/owl2dl-benchmark/blob/master/Images/Sample%20TBox.png)
![Sample ABox](https://github.com/GunjanSingh1/owl2dl-benchmark/blob/master/Images/Sample%20ABox.png)



<a name="repo"></a>

## 2. About the Repository
Repository consists of 2 directories: **OWL2Bench** and **Experiments**. OWL2Bench is a java source code directory, Experiments consists of java codes used to evaluate different reasoners (HermiT, Openllet, JFact and ELK) and a pdf file consisting of SPARQL queries for all the profiles used to evaluate Stardog and GraphDB, four different **TBox** for OWL 2 Profiles: **UNIV-BENCH-OWL2EL.owl**, **UNIV-BENCH-OWL2QL.owl**, **UNIV-BENCH-OWL2RL.owl**, **UNIV-BENCH-OWL2DL.owl**, one excel file **RandomNames.xlsx** used to give real like names of University and Person instances, and an executable jar file : **OWL2Bench.jar**. 

<a name="tbox"></a>

## 3. TBox Details 

OWL 2 DL : UNIV-BENCH-OWL2DL.owl

OWL 2 RL : UNIV-BENCH-OWL2RL.owl

OWL 2 QL : UNIV-BENCH-OWL2QL.owl

OWL 2 EL : UNIV-BENCH-OWL2EL.owl

<a name="usage"></a>
## 4. Usage
<a name="exe"></a>
## 4.1. Direct execution using executable jar (with default configurations) :

We have provided an executable jar file (with configurations that were used to perform the experiments) where user can specify the *Number of Universities, Seed and Required OWL 2 Profile* (in the same order). 
           
Number of universities makes the ABox scalable. For 1 university number varies from approx 400K triples to 800K triples depending on the seed value. OWl 2 Profile could be any one of EL, QL, RL and DL.         

For eg. : 

java -jar OWL2Bench.jar 1 950 DL (where 1 is the number of universities, 950 is the seed value and DL is OWL 2 profile)

java -jar OWL2Bench.jar 5 69 QL

java -jar OWL2Bench.jar 10 67 EL

java -jar OWL2Bench.jar 100 950 RL

To execute **OWL2Bench.jar** make sure the TBox for all profiles (UNIV-BENCH-OWL2EL.owl,UNIV-BENCH-OWL2QL.owl,UNIV-BENCH-OWL2RL.owl,UNIV-BENCH-OWL2DL.owl) and excel file for random names RandomNames.xlsx is present in the same directory as jar file. 
<a name="code"></a>
## 4.2 Using Source Code (with or without default configurations) :

We are also providing the java code (if user wants to change the configurations/density of each node) for ABox generation. User can download the project repository OWL2Bench. After downloading user just need to import this maven project and then user can change the min-max variables in ConfigFile.java and run Generator.java with arguments : *Number of Universities, Seed and Required OWL 2 Profile* (same as above). Required files are already present in the project directory. 
           
**Note:** 

The output files are stored in files with names such as "OWL2"+ Profile + "-" + Number of Universities + "-output.owl" . 

For example. On executing using the arguments given in examples above, output files would be OWL2DL-1-output.owl, OWL2QL-1-output.owl, OWL2EL-10-output.owl, OWL2RL-100-output.owl. 

<a name="future"></a>
## Future Work

For the next version of OWL2Bench, we plan to be able to customize the TBox for each profile rather than having a fixed TBox.
           
