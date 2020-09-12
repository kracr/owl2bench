#!/bin/bash
# Reasoner commands
# the reported time was an average of results obtained in 5 iterations. Since, the results could be Time-outs, memory errors or other errors, the average was performed manually.

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
					file_path=
					file_name="$file_path$profile-$univ.owl"



					
					timeout 5400 java -jar $reasoner.jar $file_name $task

					echo "Finished Reasoner: $reasoner File: $profile-$univ.owl Task: $task University: $univ Iteration: $i"
				done
			done
		done
	done
done




