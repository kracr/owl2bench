# OWL2Bench
An OWL 2 benchmark that can generate ABox axioms for fixed TBox to test the scalability and performance of OWL 2 Reasoners.

**Project repository consists of:**

Four different TBox for all the OWL 2 Profiles: UNIV-BENCH-OWL2EL.owl, UNIV-BENCH-OWL2QL.owl, UNIV-BENCH-OWL2RL.owl, UNIV-BENCH-OWL2DL.owl, all the java files for generating ABox for the selected profile (Generator.java is the main class), one excel file (RandomNames.xlsx for real like names of universities and person), 2 empty sample output files (OWL2EL-1-output.owl, OWL2EL-5-output.owl), maven pom.xml, and an executable jar file (OWL2Bench.jar). 

## TBox Details 

OWL 2 DL : UNIV-BENCH-OWL2DL.owl

OWL 2 RL : UNIV-BENCH-OWL2RL.owl

OWL 2 QL : UNIV-BENCH-OWL2QL.owl

OWL 2 EL : UNIV-BENCH-OWL2EL.owl

## Usage 1

We have provided an executable jar file (with configurations that were used to perform the experiments) where user can specify the *Number of Universities, Seed and Required OWL 2 Profile* (in the same order). 
           
Number of universities makes the ABox scalable. For 1 university number varies from approx 400K triples to 800K triples depending on the seed value. OWl 2 Profile could be any one of EL, QL, RL and DL.         

For eg. : 

java -jar OWL2Bench.jar 1 950 DL (where 1 is the number of universities, 950 is the seed value and DL is OWL 2 profile)

java -jar OWL2Bench.jar 5 69 QL

java -jar OWL2Bench.jar 10 67 EL

java -jar OWL2Bench.jar 100 950 RL

To execute OWL2Bench.jar we do not need to enter the full path for the required files as argument, that is, TBox for all profiles (UNIV-BENCH-OWL2EL.owl,UNIV-BENCH-OWL2QL.owl,UNIV-BENCH-OWL2RL.owl,UNIV-BENCH-OWL2DL.owl), RandomNames.xlsx and pre-created output files should be in the same directory as the jar file. So, for now , I have kept it in the project directory itself. If user wants to execute it from some where else, make sure all the TBoxes, RandomNames.xlsx and pre-created output files are present in the same directory as OWL2Bench.jar.

## Usage 2

We are also providing the java code (if user wants to change the configurations/density of each node). User can download the project repository. After downloading user just need to import this maven project and then user can change the min-max variables in ConfigFile.java and run Generator.java with arguments : *Number of Universities, Seed and Required OWL 2 Profile* (same as above). Required files are already present in the project directory. 
           
**Note:** 

The output files are stored in files "OWL2"+ profile + "-" + univNum + "-output.owl" . 

eg. OWL2EL-1-output.owl, OWL2EL-5-output.owl . (Sample files already present in the directory)

In the case of File Not Found exception, please create owl files (not directory) of type *OWL2DL-1-output.owl* (let's say you run it for 1 university and DL profile) in the same folder.


           
