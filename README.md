# OWL2Bench
An OWL 2 benchmark that can generate ABox axioms for fixed TBox to test the scalability and performance of OWL 2 Reasoners.

Project consists of four different TBox for all the OWL 2 Profiles (OWL 2 EL, OWL 2 QL, OWL 2 RL, OWL 2 DL) and java code for generating ABox for the selected profile. 

# TBox Details

OWL 2 DL : UNIV-BENCH-OWL2DL.owl

OWL 2 RL : UNIV-BENCH-OWL2RL.owl

OWL 2 QL : UNIV-BENCH-OWL2QL.owl

OWL 2 EL : UNIV-BENCH-OWL2EL.owl

# Usage

We have provided an executable jar file (with configurations that were used to perform the experiments) where user can specify the <Number of Universities> <Seed> <Required OWL 2 Profile>. 
           
Number of universities makes the ABox scalable. For 1 university number varies from approx 400K triples to 800K triples depending on the seed value. OWl 2 Profile could be any one of EL, QL, RL and DL.         

To execute OWL2Bench.jar TBox for all profiles (UNIV-BENCH-OWL2EL.owl,UNIV-BENCH-OWL2QL.owl,UNIV-BENCH-OWL2RL.owl,UNIV-BENCH-OWL2DL.owl) and RandomNames.xlsx should be in the same folder as the jar file.

For eg. : 

java -jar OWL2Bench.jar 1 950 DL 

java -jar OWL2Bench.jar 5 69 QL

java -jar OWL2Bench.jar 10 67 EL

java -jar OWL2Bench.jar 100 950 RL

We are also providing the code (if user wants to change the configurations/density of each node). User can change the min-max variables in ConfigFile.java and executing Generator.java (with arguments <Number of Universities> <Seed> <Required OWL 2 Profile>)
           
Note: The output files are stored in files "OWL2"+ profile + "-" + univNum + "-output.owl" . eg. OWL2DL-1-output.owl. In case File Not Found exception, please create owl files of type OWL2DL-1-output.owl in the same folder.


           
