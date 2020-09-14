#!/bin/bash
# all reasoner commands

#the files created from OWL2Bench.jar were in RDF/XML format, we are now converting them into OWL/Functional syntax. We will generate these files into konclude directory.


for profile in EL QL RL DL
do
	for univ in 1 2 5 10 20 50 100 200
	do

		java -jar convert.jar ofn OWL2$profile-$univ.owl
	done
done

