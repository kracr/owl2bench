#!/bin/bash

#GraphDB loading of axioms using loadRDF tool. It does offline loading

for profile in OWL2QL OWL2RL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			file_path=
			file_name="$file_path$profile-$univ.owl"
			graphdb_loadrdf=/graphdbfree-9.0.0/bin/loadrdf
			  
			$graphdb_loadrdf -f -i $profile$univ-iter$iteration -m parallel $file_name
			echo "Finished File: $profile$univ"
		done
	done
done