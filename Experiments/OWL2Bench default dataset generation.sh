#!/bin/bash
# all SPARQL query commands

#export STARDOG_SERVER_JAVA_ARGS="-Xms24g -Xmx24g -XX:MaxDirectMemorySize=64g"

#/home/gunjans/stardog-7.2.0/bin/stardog-admin server start

for i in 1 2 3 4 5
do
	echo $i
	echo "Iteration"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el1 "SELECT DISTINCT ?y ?x WHERE { ?y :knows ?x.}"
	echo "Done Query1 for el1"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el5 "SELECT DISTINCT ?y ?x WHERE { ?y :knows ?x.}"
	echo "Done Query1 for el5"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el10 "SELECT DISTINCT ?y ?x WHERE { ?y :knows ?x.}"
	echo "Done Query1 for el10"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r ql1 "SELECT DISTINCT ?y ?x WHERE { ?y :knows ?x.}"
	echo "Done Query1 for ql1"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r ql5 "SELECT DISTINCT ?y ?x WHERE { ?y :knows ?x.}"
	echo "Done Query1 for ql5"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r ql10 "SELECT DISTINCT ?y ?x WHERE { ?y :knows ?x.}"
	echo "Done Query1 for ql10"

	/home/gunjans/stardog-7.2.0/bin/stardog query -r el1 "SELECT DISTINCT ?y ?x WHERE { ?y :isMemberOf ?x.}"
	echo "Done Query2 for el1"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el5 "SELECT DISTINCT ?y ?x WHERE { ?y :isMemberOf ?x.}"
	echo "Done Query2 for el5"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el10 "SELECT DISTINCT ?y ?x WHERE { ?y :isMemberOf ?x.}"
	echo "Done Query2 for el10"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r rl1 "SELECT DISTINCT ?y ?x WHERE { ?y :isMemberOf ?x.}"
	echo "Done Query2 for rl1"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r rl5 "SELECT DISTINCT ?y ?x WHERE { ?y :isMemberOf ?x.}"
	echo "Done Query2 for rl5"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r rl10 "SELECT DISTINCT ?y ?x WHERE { ?y :isMemberOf ?x.}"
	echo "Done Query2 for rl10"


	/home/gunjans/stardog-7.2.0/bin/stardog query -r el1 "SELECT DISTINCT ?y ?x WHERE { ?y :isPartOf ?x.}"
	echo "Done Query3 for el1"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el5 "SELECT DISTINCT ?y ?x WHERE { ?y :isPartOf ?x.}"
	echo "Done Query3 for el5"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el10 "SELECT DISTINCT ?y ?x WHERE { ?y :isPartOf ?x.}"
	echo "Done Query3 for el10"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r rl1 "SELECT DISTINCT ?y ?x WHERE { ?y :isPartOf ?x.}"	
	echo "Done Query3 for rl1"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r rl5 "SELECT DISTINCT ?y ?x WHERE { ?y :isPartOf ?x.}"
	echo "Done Query3 for rl5"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r rl10 "SELECT DISTINCT ?y ?x WHERE { ?y :isPartOf ?x.}"
	echo "Done Query3 for rl10"


	/home/gunjans/stardog-7.2.0/bin/stardog query -r el1 "SELECT DISTINCT ?y ?x WHERE { ?y :hasAge ?x.}"
	echo "Done Query4 for el1"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el5 "SELECT DISTINCT ?y ?x WHERE { ?y :hasAge ?x.}"
	echo "Done Query4 for el5"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el10 "SELECT DISTINCT ?y ?x WHERE { ?y :hasAge ?x.}"
	echo "Done Query4 for el10"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r rl1 "SELECT DISTINCT ?y ?x WHERE { ?y :hasAge ?x.}"
	echo "Done Query4 for rl1"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r rl5 "SELECT DISTINCT ?y ?x WHERE { ?y :hasAge ?x.}"
	echo "Done Query4 for rl5"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r rl10 "SELECT DISTINCT ?y ?x WHERE { ?y :hasAge ?x.}"
	echo "Done Query4 for rl10"


	/home/gunjans/stardog-7.2.0/bin/stardog query -r el1 "SELECT DISTINCT ?y WHERE { ?y rdf:type :T20CricketFan.}"
	echo "Done Query5 for el1"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el5 "SELECT DISTINCT ?y WHERE { ?y rdf:type :T20CricketFan.}"
	echo "Done Query5 for el5"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el10 "SELECT DISTINCT ?y WHERE { ?y rdf:type :T20CricketFan.}"
	echo "Done Query5 for el10"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r rl1 "SELECT DISTINCT ?y WHERE { ?y rdf:type :T20CricketFan.}"
	echo "Done Query5 for rl1"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r rl5 "SELECT DISTINCT ?y WHERE { ?y rdf:type :T20CricketFan.}"
	echo "Done Query5 for rl5"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r rl10 "SELECT DISTINCT ?y WHERE { ?y rdf:type :T20CricketFan.}"
	echo "Done Query5 for rl10"

	/home/gunjans/stardog-7.2.0/bin/stardog query -r el1 "SELECT DISTINCT ?y WHERE { ?y rdf:type :SelfAware.}"
	echo "Done Query6 for el1"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el5 "SELECT DISTINCT ?y WHERE { ?y rdf:type :SelfAware.}"
	echo "Done Query6 for el5"
	/home/gunjans/stardog-7.2.0/bin/stardog query -r el10 "SELECT DISTINCT ?y WHERE { ?y rdf:type :SelfAware.}"
	echo "Done Query6 for el10"


done
