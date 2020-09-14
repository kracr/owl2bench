#!/bin/bash
# Reasoner commands
# the reported time was an average of results obtained in 5 iterations and that was performed manually.


#ontologies must be in OWL/Functional syntax for both ELK and Konclude. Either convert them by changing the configuration file of OWL2Bench or simply use Convert.java (that is present in the directory).

#download konclude from https://www.derivo.de/fileadmin/externe_websites/ext.derivo/KoncludeReleases/v0.6.2-544/Konclude-v0.6.2-544-Linux-x64-GCC4.3.2-Static-Qt4.8.5.zip

#RAM should be 24GB (although konclude is much faster, it uses too much of memory and thus could not perform msot of the experiments)
file_path=$(pwd)
#change to konclude directory
cd Konclude-v0.6.2-544-Linux-x64-GCC4.3.2-Static-Qt4.8.5

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
					
					file_name="$file_path/$profile-$univ.owl"

					timeout 5400 ./Konclude $task -i $file_name
					echo "Finished Reasoner: $reasoner FileName: $profile-$univ Task: $task University: $univ Iteration: $i"

				done
			done
		done
	done
done


cd ../



