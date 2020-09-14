#!/bin/bash
# Reasoner commands
# the reported time was an average of results obtained in 5 iterations and that was performed manually.


#ontologies must be in OWL/Functional syntax for both ELK and Konclude. You can convert them using Protege or generate in the required format by changing the configuration file of OWL2Bench. Or, simply use convert.jar, which is provided in the directory. Command would be: java -jar convert.jar ofn <input OWL file>

# Download elk-standalone jar from https://github.com/liveontologies/elk-reasoner/releases/download/v0.4.3/elk-distribution-0.4.3-standalone-executable.zip 

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
					file_path=$(pwd)
					file_name="$(pwd)/$profile-$univ.owl"

					timeout 5400 java -Xms24g -Xmx24g -jar $file_path/elk-distribution-0.4.3-standalone-executable/elk-standalone.jar -i $file_name -$task
					echo "Finished Reasoner: $reasoner FileName: $profile-$univ Task: $task University: $univ Iteration: $i"

				done
			done
		done
	done
done




