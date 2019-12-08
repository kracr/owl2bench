# OWL2Bench
An OWL 2 benchmark that can generate ABox and TBox axioms to test the scalability and performance of OWL 2 Reasoners.

Project consists of four different TBox for all the OWL 2 Profiles (OWL 2 EL, OWL 2 QL, OWL 2 RL, OWL 2 DL) and java code for generating ABox for the selected profile. 
TBox Details:
OWL 2 DL : UNIV-BENCH-OWL2DL.owl
OWL 2 RL : UNIV-BENCH-OWL2RL.owl
OWL 2 QL : UNIV-BENCH-OWL2QL.owl
OWL 2 EL : UNIV-BENCH-OWL2EL.owl
We have provided an executable jar file where user can specify the <number of universities> <seed> <profile>. 
For eg. : java -jar OWL2Bench.jar 1 950 DL 
           
