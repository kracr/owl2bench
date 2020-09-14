#!/bin/bash

#Stardog database creation. First we need to run the server using 
/stardog-7.0.2/bin/stardog-admin server stop
/stardog-7.0.2/bin/stardog-admin server start

for profile in OWL2EL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			file_path=$(pwd)
			file_name="$(pwd)/$profile-$univ.owl"
			$(pwd)/stardog-7.0.2/bin/stardog-admin db drop $profile$univ
			$(pwd)/stardog-7.0.2/bin/stardog-admin db create -n $profile$univ -o reasoning.type="EL" query.timeout='600s' -- $file_name
		done
	done
done

for profile in OWL2QL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			file_path=$(pwd)
			file_name="$(pwd)/$profile-$univ.owl"
			$(pwd)/stardog-7.0.2/bin/stardog-admin db drop $profile$univ
			$(pwd)/stardog-7.0.2/bin/stardog-admin db create -n $profile$univ -o reasoning.type="QL" query.timeout='600s' -- $file_name
		done
	done
done

for profile in OWL2RL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			file_path=$(pwd)
			file_name="$(pwd)/$profile-$univ.owl"
			$(pwd)/stardog-7.0.2/bin/stardog-admin db drop $profile$univ
			$(pwd)/stardog-7.0.2/bin/stardog-admin db create -n $profile$univ -o reasoning.type="RL" query.timeout='600s' -- $file_name
		done
	done
done

for profile in OWL2DL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			file_path=$(pwd)
			file_name="$(pwd)/profile-$univ.owl"
			$(pwd)/stardog-7.0.2/bin/stardog-admin db drop $profile$univ
			$(pwd)/stardog-7.0.2/bin/stardog-admin db create -n $profile$univ -o reasoning.type="DL" query.timeout='600s' -- $file_name
		done
	done
done
