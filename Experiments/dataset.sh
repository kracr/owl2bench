#!/bin/bash
# all reasoner commands

#the OWL2Bench.jar was created from the source code given in the github repository.

#make sure the other files: TBoxes and RandomName.xlsx are present in the same directory as OWL2Bench.jar 


for profile in EL QL RL DL
do
	for univ in 1 2 5 10 20 50 100 200
	do

		java -jar OWL2Bench.jar $univ $profile 1
	done
done

