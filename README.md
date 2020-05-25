# OWL2Bench Documentation
This document provides documentation for the first version of our benchmark OWL2Bench. OWL2Bench can be used to benchmark three aspects of the reasoners - supportfor OWL 2 language constructs, scalability in terms of ABox size, and the query performance.

# Table of Contents
1. [ Introduction. ](#intro)

2. [ About the Repository. ](#repo)

3. [ TBox Details. ](#tbox)

4. [ ABox Details. ](#abox)

5. [ SPARQL Query Details. ](#sparql)

6. [ Usage. ](#usage)

   4.1 [ Direct execution using executable jar (with default configurations). ](#exe)
   
   4.2 [ Using Source Code (with or without default configurations). ](#code)
   
7. [ Future Work. ](#future)
8. [ References. ](#references)
<a name="intro"></a>
## 1. Introduction
OWl 2 is gaining popularity in a variety of domains because of its high level of expressivity. OWL 2 has several profiles such as OWL 2 EL, OWl 2 QL, OWL 2 RL, and OWL 2 DL that vary in terms of their expressivity and reasoning performance. There are several OWL 2 reasoners (such as Hermit, JFact, Openllet, Pellet, Konclude and ELK) and some SPARQL query engines (such as Stardog and GraphDB) that are backed by OWL 2 Reasoners so as to help answer queries that involve reasoning. OWL2Bench is our first step towards a standard benchmark for all the OWL 2 profiles. Our benchmark is an extension of well known University Ontology Benchmark (UOBM). OWL2Bench includes TBox for each profile covering the set of constructs supported by that profile, generation of synthetic data scalable to arbitrary size and a separate set of SPARQL queries for each profile to be executed over generated data for performance evaluation of several reasoners and SPARQL query engines.

The hierarchy among some of the classes, including the relations between them, is shown in the figure below. All the four TBoxes of OWL2Bench consist of classes such as University, College, CollegeDiscipline, Department, Person, Program, and Course. They are related to each other through relationships such as enrollFor, teachesCourse, and offerCourse.

![OWL2Bench](https://github.com/kracr/owl2bench/blob/master/Images/OWL2Bench.JPG)

<a name="repo"></a>
## 2. About the Repository
Repository consists of 2 directories: **OWL2Bench** and **Experiments**. OWL2Bench is a java source code directory, Experiments consists of java codes used to evaluate different reasoners (HermiT, Openllet, JFact, Pellet, Konclude and ELK) and a text file **OWL2Bench_SPARQL_Queries**, available at https://doi.org/10.5281/zenodo.3838735, consisting of twenty-two SPARQL queries for all the profiles used to evaluate Stardog and GraphDB, four different **TBox** for OWL 2 Profiles: **UNIV-BENCH-OWL2EL.owl**, **UNIV-BENCH-OWL2QL.owl**, **UNIV-BENCH-OWL2RL.owl**, **UNIV-BENCH-OWL2DL.owl**, one excel file **RandomNames.xlsx** used to give real like names of University and Person instances, and an executable jar file : **OWL2Bench.jar**. 

<a name="tbox"></a>

## 3. TBox Details 

OWL 2 DL : UNIV-BENCH-OWL2DL.owl

OWL 2 RL : UNIV-BENCH-OWL2RL.owl

OWL 2 QL : UNIV-BENCH-OWL2QL.owl

OWL 2 EL : UNIV-BENCH-OWL2EL.owl

<a name="abox"></a>
## 4. ABox Details 

<a name="sparql"></a>
## 5. SPARQL Query Details

<a name="usage"></a>
## 6. Usage
<a name="exe"></a>
## 6.1. Direct execution using executable jar (with default configurations) :

We have provided an executable jar file (with configurations that were used to perform the experiments) where user can specify the *Number of Universities, Required OWL 2 Profile and Seed* (in the same order). 
           
Number of universities makes the ABox scalable. For 1 university number varies from approx 400K triples to 800K triples depending on the seed value. OWl 2 Profile could be any one of EL, QL, RL and DL.         

For eg. : 

java -jar OWL2Bench.jar 1 DL 1 (where 1 is the number of universities,  DL is OWL 2 profile and 1 is the seed value)


To execute **OWL2Bench.jar** make sure the TBox for all profiles (UNIV-BENCH-OWL2EL.owl,UNIV-BENCH-OWL2QL.owl,UNIV-BENCH-OWL2RL.owl,UNIV-BENCH-OWL2DL.owl) and excel file for random names RandomNames.xlsx is present in the same directory as jar file. 
<a name="code"></a>
## 6.2. Using Source Code (with or without default configurations) :

We are also providing the java code (if user wants to change the configurations/density of each node) for ABox generation. User can download the project repository OWL2Bench. After downloading user just need to import this maven project and then user can change the min-max variables in ConfigFile.java and run Generator.java with arguments : *Number of Universities, Required OWL 2 Profile and Seed* (same as above). Required files are already present in the project directory. 
           
**Note:** 

The output files are stored in files with names such as "OWL2"+ Profile + "-" + Number of Universities + ".owl" . 

For example. On executing using the arguments given in examples above, output files would be OWL2DL-1.owl, OWL2QL-1.owl, OWL2EL-10.owl, OWL2RL-100.owl. 

The datasets used for the experiments (in RDF/XML Format) are available at https://drive.google.com/drive/u/3/folders/1HYURRLaQkLK8cQwV-UBNKK4_Zur2nU68 .

<a name="future"></a>
## 7. Future Work

For the next version of OWL2Bench, we plan to be able to customize the TBox for each profile rather than having a fixed TBox. We also plan to extend this by providing an option to the users to choose the desired hardness level (easy, medium, and hard) of the ontology with respect to the reasoning time and OWL2Bench will then generate such an ontology.
 
<a name="references"></a> 
## 8. References
1. Bail, S., B., Sattler, U.: JustBench: A Framework for OWL Benchmarking. In: The Semantic  Web  -  ISWC  2010  -  9th  International  Semantic  Web  Conference,  ISWC2010,  November  7-11,  2010,  Revised  Selected  Papers,  PartI.  Lecture  Notes  in  Computer  Science,  vol.  6496,  pp.  32–47.  Springer  (2010).
2. Glimm,   B.,   Horrocks,   I.,   Motik,   B.,   S.,   G.,   Wang,   Z.:   Hermit:   An   owl2  reasoner.  Journal  of  Automated  Reasoning53(3),  245—-269  (Oct  2014).
3. Guo,   Y.,   Pan,   Z.,   Heflin,   J.:   Lubm:   A   benchmark   for   owl   knowledgebase   systems.   Journal   of   Web   Semantics3(2-3),   158–182   (Oct   2005). 
4. Hitzler,  P.,  Kr ̈otzsch,  M.,  Parsia,  B.,  F.  Patel-Schneider,  P.,  Rudolph,  S.:  OWL2 Web Ontology Language Primer (Second Edition) (2012).
5. Kazakov, Y., Kr ̈otzsch, M., Simanˇc ́ık, F.: The incredible elk. Journal of AutomatedReasoning53(1), 1–61 (Jun 2014). 
6. Link,  V.,  Lohmann,  S.,  F.,  H.:  Ontobench:  Generating  custom  owl  2  bench-mark ontologies. In: International Semantic Web Conference. pp. 122–130 (2016).
7. Ma,  L.,  Yang,  Y.,  Qiu,  Z  .and  Xie,  G.,  Pan,  Y.,  Liu,  S.:  Towards  a  complete  owl  ontology  benchmark.  In:  The  Semantic  Web:  Research  and  Appli-cations.  pp.  125–139.  Springer  Berlin  Heidelberg,  Berlin,  Heidelberg  (2006).  
8. Parsia,   B.,   Matentzoglu,   N.,   Gon ̧calves,   R.S.,   Glimm,   B.,   Steigmiller,   A.:The  owl  reasoner  evaluation  (ore)  2015  resources.  In:  The  Semantic  Web  –ISWC   2016.   pp.   159–167.   Springer   International   Publishing,   Cham   (2016).
9. Parsia, B., Matentzoglu, N., Gon ̧calves, R.S., Glimm, B., Steigmiller, A.: The owlreasoner evaluation (ore) 2015 competition report. Journal of Automated Reasoning, 59(4), 455––482 (Dec 2017).
10. Sakr, S., Wylot, M., Mutharaju, R., Le Phuoc, D., Fundulaki, I.: Linked Data -Storing, Querying, and Reasoning. Springer (2018).
11. Sirin,   E.,   Parsia,   B.,   Cuenca   Grau,   B.,   Kalyanpur,   A.,   Katz,   Y.:   Pellet:A  practical  owl-dl  reasoner.  Journal  of  Web  Semantics5(2),  51–53  (2007).
12. Steigmiller,A.,Liebig,T.,Glimm,B.:Konclude:System Description.Journal of Web Semantics. 27-28, 78–85(2014).
13. Tsarkov,  D.,  Horrocks,  I.:  Fact++  description  logic  reasoner:  System  descrip-tion.   In:   Third   International   Joint   Conference   on   Automated   Reasoning   ,IJCAR.  pp.  292–297.  Springer  Berlin  Heidelberg,  Berlin,  Heidelberg  (2006).



