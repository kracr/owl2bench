#!/bin/bash
# Reasoner commands
# the reported time was an average of results obtained in 5 iterations and that was performed manually.

for reasoner in hermit pellet openllet jfact
do

	for profile in OWL2EL OWL2QL OWL2RL OWL2DL
	do
		for task in consistency realisation classification
		do
			for univ in 1 2 5 10 20 50 100 200
			do
				for i in 1 2 3 4 5
				do
					sleep 5
					file_path=/mnt/home/
					file_name="$file_path$profile-$univ.owl"



					echo "******Reasoner: $reasoner Profile: $profile Task: $task University: $univ Iteration: $i*************"
					timeout 5400 java -Xms24g -Xmx24g -jar $reasoner.jar $file_name $task

				done
			done
		done
	done
done

#ontologies must be in OWL/Functional syntax for both ELK and Konclude. Either convert them by changing the configuration file of OWL2Bench or simply use Convert.java (that is present in the directory).

for reasoner in konclude
do

	for profile in OWL2EL OWL2QL OWL2RL OWL2DL
	do
		for task in consistency realisation classification
		do
			for univ in 1 2 5 10 20 50 100 200
			do
				for i in 1 2 3 4 5
				do
					sleep 5
					file_path=/mnt/home/
					file_name="$file_path$profile-$univ.owl"



					echo "******Reasoner: $reasoner Profile: $profile Task: $task University: $univ Iteration: $i*************"
					./Konclude $task -i $file_name
				done
			done
		done
	done
done


for reasoner in elk
do
	for profile in OWL2EL
	do
		for task in s c r
		do
			for univ in 1 2 5 10 20 50 100 200
			do
				for i in 1 2 3 4 5
				do
					sleep 5
					file_path=/mnt/home/
					file_name="$file_path$profile-$univ.owl"



					echo "******Reasoner: $reasoner Profile: $profile Task: $task University: $univ Iteration: $i*************"
					timeout 5400 java -Xms24g -Xmx24g -jar elk-standalone.jar $file_name -$task

				done
			done
		done
	done
done




