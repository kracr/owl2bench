#!/bin/bash
# all reasoner commands

#Run: dos2unix script.sh, chmod +x script.sh, ./script.sh to run the scripts

#This is the main script that can be used to run all the experiments.

#Steps to follow to run this script:

#Create a 'New Directory' and copy all the 8 scripts given in this 'Experiments' folder.

#Add all the runnable jar files provided inside java runnable jar files (hermit.jar, jfact.jar, openllet.jar, pellet.jar, convert.jar).

#For the other reasoners (ELK and Konclude) and SPARQL query engines (Stardog and GraphDB), download them from the links provided. 

#Direct links for zip files of ELK and Konclude has been provided. Download it and extract it in the same directory 'New Directory'. For elk, elk-distribution-0.4.3-standalone-executable directory would be created and you can find 'elk-standalone.jar' inside it. For Konclude, 

#While for Stardog and GraphDB, login details are needed, so follow the steps provided in the links (https://www.stardog.com/docs/#_getting_stardog), (https://www.ontotext.com/products/graphdb/graphdb-free/). Download them and extract in the same folder. 

#Don't forget to add OWL2Bench.jar (from the main branch of our repository), all the Tboxes (UNIV-BENCH-OWL2DL.owl, UNIV-BENCH-OWL2EL.owl, UNIV-BENCH-OWL2QL.owl, UNIV-BENCH-OWL2RL.owl), RandomNames.xlsx in the same directory (New Directory) as all the scripts.

#Now, from the terminal, change to this 'New Directory' and run this main script. 





############################################
#script for dataset generation
./dataset.sh >dataset.log


#Note that the size of ABox and different type of TBox axioms are given separately in the table. While on executing the code, the line on the output console gives 'Total Logical Axiom Count= '.

grep -B 2 -i "Finished Writing to file" dataset.log >count.log

#For the counts given in Table 3 of the paper, see log count.log

############################################


#for reasoning tasks on hermit pellet openllet and jfact
./reasoningTasks1.sh >hermitpelletopenlletjfact.log

grep -B 1 -i "Finished Reasoner:" hermitpelletopenlletjfact.log >reasoningTimeHermiTPelletOpenlletJFact.log

############################################



#We will now convert all the files into OWL functional syntax for ELK and Konclude. 

./convert.sh >convert.log

############################################

#for reasoning tasks on elk
./reasoningTasks2.sh >elk.log

grep -B 24 -i "Finished Reasoner:" elk.log >reasoningTimeELK.log

#sum up all the other timings as well. For example while calculating the classification time: add loading/preprocessing time and consistency time as well.





############################################




#for reasoning tasks on konclude
./reasoningTasks3.sh >konclude.log

grep -B 14 -i "Finished Reasoner:" konclude.log >reasoningTimeKonclude.log

#sum up all the other timings as well. For example while calculating the time taken for reasoning, add loading/preprocessing time as well.



./convertback.sh >convert.log

############################################


#For finding the results given in Table 4 and 5 of the paper use logs reasoningTimeHermiTPelletOpenlletJFact.log , reasoningTimeELK.log , reasoningTimeKonclude.log

############################################


#for loading time on graphdb

./LoadingGraphDB.sh >loadingGraphDB.log

grep -B 11 -i "Finished File:" loadingGraphDB.log >loadingTimeGraphDB.log 


############################################

#for stardog, extract the folder in the 'New Directory', start stardog, it will ask for license, set it up the. I haven't set the STARDOG_HOME to a separate directory, so the databases would be stored in the user.dir (our working directory again)

#for loading time on stardog

./LoadingStardog.sh >loadingStardog.log

grep -B 2 -i "Successfully created database" loadingStardog.log >loadingTimeStardog.log 

############################################

#For finding the results given in Table 6 of the paper use logs loadingTimeGraphDB.log, loadingTimeStardog.log 

############################################

#for query response time on stardog

./QueryResponse.sh >queryStardog.log

grep -A 1 -i "Query returned" queryStardog.log >queryResponseTimeStardog.log

#use queryResponseTimeStardog.log for time-taken by stardog
##############################################

#for query response time on graphdb, queries were executed using workbench 'http://graphdb.ontotext.com/documentation/free/run-stand-alone-server.html'
#steps
#Start the GraphDB Server (graphdb -Xms24g -Xmx24g) 
$(pwd)/graphdb-free-9.0.0/bin/graphdb -Xms24g -Xmx24g
#and Workbench interface by executing the graphdb startup script located in the $graphdb_home/bin folder: A message appears in the console telling you that GraphDB has been started in Workbench mode. To access the Workbench, open http://localhost:7200/ in your browser.

