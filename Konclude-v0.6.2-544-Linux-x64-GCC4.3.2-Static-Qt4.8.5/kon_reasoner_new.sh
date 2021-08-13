#!/bin/bash
# Reasoner commands
# the reported time was an average of results obtained in 5 iterations and that was performed manually.


#ontologies must be in OWL/Functional syntax for both ELK and Konclude. Either convert them by changing the configuration file of OWL2Bench or simply use Convert.java (that is present in the directory).

#download konclude from https://www.derivo.de/fileadmin/externe_websites/ext.derivo/KoncludeReleases/v0.6.2-544/Konclude-v0.6.2-544-Linux-x64-GCC4.3.2-Static-Qt4.8.5.zip

#RAM should be 24GB (although konclude is much faster, it uses too much of memory and thus could not perform msot of the experiments)
file_path=$(pwd)
#change to konclude directory
echo $file_path

owl_files=$(ls *.owl)

total=0
printf '%s\n' "file" "size" "consistency_time_milsecs" "consistency_size_kb" "realisation_time_milsecs" "realisation_size_kb" "classification_time_milsecs" "classification_size_kb" | paste -sd ',' >> file.csv

for i in $owl_files
do
	echo $i
	java -jar convert.jar ofn $i
	size=$( ls -l $i | awk '{print $5} ' )
	size=`expr $size / 1000`
	echo $size
		
	arr=(-1 -1 -1 -1 -1 -1)
	k=0
	for task in consistency realisation classification
	do
		echo $task
		#start=$SECONDS
		#mem=5
		s=`mktemp`
		start=$(date +%s%N)
		#echo "start : $start"
		
		#/usr/bin/time -f "%M" -o "$s" timeout 21600 ./Konclude $task -i $i
		z=$(/usr/bin/time -f "%M" -o "$s" timeout 10 ./Konclude $task -i $i | tail -n 1 | cut -d' ' -f 7)
		ELAPSED_TIME=$((($(date +%s%N) - $start)/1000000)) ;
		echo "Time taken: $ELAPSED_TIME milliseconds"
		
		#z=$(/usr/bin/time -f "%M" -o "$s" timeout 21600 ./Konclude consistency -i 00a47ea4-c236-4894-b8fb-a15d3d66c110_onto.rdf_functional.owl | tail -n 1 | cut -d' ' -f 7)
		mem=$(cat "$s")
		#echo "Memory consumed: $mem"
	      	echo $z
		#l=$(echo $z)
		#echo $l
		#echo ${l:0:12}
		#b="uytuo\r"
		#r=${ $b | cut -c2-6 }
		#echo $l | wc -c
		#r=$(echo $l| cut -d'_' -f 2)
		#echo $b
		#echo ${#z}

		if [ $(echo ${z:0:12}) = "inconsistent" ];
		then
			#echo "ritam"
			break
		fi
		#ELAPSED_TIME=$(($SECONDS - $start))
		#echo "Script Execution Time : $ELAPSED_TIME"

		arr[$k]=$ELAPSED_TIME
		k=`expr $k + 1`
		arr[$k]=$mem
		k=`expr $k + 1`
		SECONDS=0
	done
	
	if [ $k = 6 ];
	then
		printf '%s\n' $i $size ${arr[0]} ${arr[1]} ${arr[2]} ${arr[3]} ${arr[4]} ${arr[5]} | paste -sd ',' >> file.csv
	fi
	
	
	#break
done


