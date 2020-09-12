#!/bin/bash
# all reasoner commands


#please set the correct file path in all the referenced scripts

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


#for reasoning tasks on elk
./reasoningTasks2.sh >elk.log

grep -B 24 -i "Finished Reasoner:" elk.log >reasoningTimeELK.log

#sum up all the other timings as well. For example while calculating the classification time: add loading/preprocessing time and consistency time as well.

############################################


#for reasoning tasks on konclude
./reasoningTasks3.sh >konclude.log

grep -B 14 -i "Finished Reasoner:" konclude.log >reasoningTimeKonclude.log

#sum up all the other timings as well. For example while calculating the time taken for reasoning, add loading/preprocessing time as well.

############################################
#For finding the results given in Table 4 and 5 of the paper use logs reasoningTimeHermiTPelletOpenlletJFact.log , reasoningTimeELK.log , reasoningTimeKonclude.log

############################################


#for loading time on graphdb

./LoadingGraphDB.sh >loadingGraphDB.log

grep -B 11 -i "Finished File:" loadingGraphDB.log >loadingTimeGraphDB.log 


############################################


#for loading time on stardog

./LoadingStardog.sh >loadingStardog.log

grep -B 2 -i "Successfully created database" loadingStardog.log >loadingTimeStardog.log 

############################################

#For finding the results given in Table 6 of the paper use logs loadingTimeGraphDB.log, loadingTimeStardog.log 

############################################

#for query response time on stardog

./QueryResponse.sh >queryStardog.log

grep -A 1 -i "Query returned" queryStardog.log >queryResponseTimeStardog.log

##############################################

#for query response time on graphdb, queries were executed using workbench 'http://graphdb.ontotext.com/documentation/free/run-stand-alone-server.html'
#steps
#Start the GraphDB Server and Workbench interface by executing the graphdb startup script located in the $graphdb_home/bin folder: A message appears in the console telling you that GraphDB has been started in Workbench mode. To access the Workbench, open http://localhost:7200/ in your browser.
