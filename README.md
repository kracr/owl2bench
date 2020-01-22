# OWL2Bench
An OWL 2 benchmark that can generate ABox axioms for fixed TBox to test the scalability and performance of OWL 2 Reasoners.

Project consists of four different TBox for all the OWL 2 Profiles (OWL 2 EL, OWL 2 QL, OWL 2 RL, OWL 2 DL) and java code for generating ABox for the selected profile. 

# TBox Details

OWL 2 DL : UNIV-BENCH-OWL2DL.owl

OWL 2 RL : UNIV-BENCH-OWL2RL.owl

OWL 2 QL : UNIV-BENCH-OWL2QL.owl

OWL 2 EL : UNIV-BENCH-OWL2EL.owl

# Usage

We have provided an executable jar file where user can specify the <Number of Universities> <Seed> <Required OWL 2 Profile>. 
           
Number of universities makes the ABox scalable. For 1 university number varies from approx 400K triples to 800K triples depending on the seed value. OWl 2 Profile could be any one of EL, QL, RL and DL.         

For eg. : 

java -jar OWL2Bench.jar 1 950 DL 

java -jar OWL2Bench.jar 5 69 QL

java -jar OWL2Bench.jar 10 67 EL

java -jar OWL2Bench.jar 100 950 RL

Also, to execute OWL2Bench.jar TBox for all profiles (UNIV-BENCH-OWL2EL.owl,UNIV-BENCH-OWL2QL.owl,UNIV-BENCH-OWL2RL.owl,UNIV-BENCH-OWL2DL.owl) and RandomNames.xlxs should be in the same folder as the jar file.
           
