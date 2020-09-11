#!/bin/bash

#GraphDB loading of axioms using loadRDF tool. It does offline loading

for profile in OWL2QL OWL2RL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			file_path=/mnt/home/
			file_name="$file_path$profile-$univ.owl"
			graphdb_loadrdf=/graphdbfree-9.0.0/bin/loadrdf
			  
			$graphdb_loadrdf -f -i $profile$univ-iter$iteration -m parallel $file_name
		done
	done
done

#Stardog database creation. First we need to run the server using /stardog-7.2.0/bin/stardog-admin server start

for profile in OWL2EL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			file_path=/mnt/home/
			file_name="$file_path$profile-$univ.owl"
			/stardog-7.2.0/bin/stardog-admin db drop $profile$univ
			/stardog-7.2.0/bin/stardog-admin db create -n $profile$univ -o reasoning.type="EL" query.timeout='600s' -- $file_name
		done
	done
done

for profile in OWL2QL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			file_path=/mnt/home/
			file_name="$file_path$profile-$univ.owl"
			/stardog-7.2.0/bin/stardog-admin db drop $profile$univ
			/stardog-7.2.0/bin/stardog-admin db create -n $profile$univ -o reasoning.type="QL" query.timeout='600s' -- $file_name
		done
	done
done

for profile in OWL2RL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			file_path=/mnt/home/
			file_name="$file_path$profile-$univ.owl"
			/stardog-7.2.0/bin/stardog-admin db drop $profile$univ
			/stardog-7.2.0/bin/stardog-admin db create -n $profile$univ -o reasoning.type="RL" query.timeout='600s' -- $file_name
		done
	done
done

for profile in OWL2DL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			file_path=/mnt/home/
			file_name="$file_path$profile-$univ.owl"
			/stardog-7.2.0/bin/stardog-admin db drop $profile$univ
			/stardog-7.2.0/bin/stardog-admin db create -n $profile$univ -o reasoning.type="DL" query.timeout='600s' -- $file_name
		done
	done
done
