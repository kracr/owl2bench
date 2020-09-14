#!/bin/bash
# all SPARQL query commands

$(pwd)/stardog-7.0.2/bin/stardog-admin server stop

export STARDOG_SERVER_JAVA_ARGS="-Xms24g -Xmx24g -XX:MaxDirectMemorySize=64g"

$(pwd)/stardog-7.0.2/bin/stardog-admin server start

for profile in OWL2EL OWL2QL OWL2DL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y ?x WHERE { ?y :knows ?x.}"
			echo "Done Query1 for $profile$univ"
		done
	done
done

for profile in OWL2EL OWL2RL OWL2DL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y ?x WHERE { ?y :isMemberOf ?x.}"
			echo "Done Query2 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y ?x WHERE { ?y :isPartOf ?x.}"
			echo "Done Query3 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y ?x WHERE { ?y :hasAge ?x.}"
			echo "Done Query4 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y WHERE { ?y rdf:type :T20CricketFan.}"
			echo "Done Query5 for $profile$univ"

		done
	done
done


for profile in OWL2EL OWL2DL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y WHERE { ?y rdf:type :SelfAware.}"
			echo "Done Query6 for $profile$univ"
		done
	done
done

for profile in OWL2QL OWL2RL OWL2DL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y ?x WHERE { ?y :hasAlumnus ?x.}"
			echo "Done Query7 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y ?x WHERE { ?y :isAffiliatedOrganizationOf ?x.}"
			echo "Done Query8 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?x WHERE {?x :hasCollegeDiscipline :NonScience.}"
			echo "Done Query9 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y ?x WHERE {?y :hasCollaborationWith ?x.}"
			echo "Done Query10 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y ?x WHERE { ?y :isAdvisedBy ?x.}"
			echo "Done Query11 for $profile$univ"

		done
	done
done

for profile in OWL2RL OWL2DL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?x WHERE { ?x rdf:type :Person.}"
			echo "Done Query12 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT  DISTINCT  ?x  WHERE { ?x  rdf:type  :WomanCollege}"
			echo "Done Query13 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT  DISTINCT  ?x  WHERE { ?x  rdf:type  :LeisureStudent}"
			echo "Done Query14 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y ?x where { ?y :isHeadOf ?x. }"
			echo "Done Query15 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y ?x where { ?y :hasHead ?x. }"
			echo "Done Query16 for $profile$univ"

		done
	done
done

for profile in OWL2DL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT  DISTINCT  ?x  WHERE {?x  rdf:type  :UGStudent}"
			echo "Done Query17 for $profile$univ"
			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?x WHERE { ?x rdf:type :PeopleWithManyHobbies}"
			echo "Done Query18 for $profile$univ"

		done
	done
done

for profile in OWL2EL OWL2QL OWL2RL OWL2DL
do
	for univ in 1 5 10
	do 
		for iteration in 1 2 3 4 5
		do
			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?x WHERE { ?x rdf:type :Faculty.}"
			echo "Done Query19 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?y ?x WHERE { ?y :hasSameHomeTownWith ?x.}"
			echo "Done Query20 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?x where { ?x rdf:type :Student.  ?x :isStudentOf ?y. ?y :isPartOf ?z. ?z :hasCollegeDiscipline :Engineering  }"

			echo "Done Query21 for $profile$univ"

			$(pwd)/stardog-7.0.2/bin/stardog query -r $profile$univ "SELECT DISTINCT ?s ?c WHERE { ?x rdf:type :Organization. ?x :hasDean ?z. ?z :teachesCourse ?c. ?s :takesCourse ?c }"
			echo "Done Query22 for $profile$univ"

		done
	done
done
