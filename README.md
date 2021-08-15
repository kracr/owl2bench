# Ontology Hardness
The main aim is to find the ranges of features in all 9 categories

Actual_files contains a sample zip file i.e. 'split_2.zip'.
Uzip the file. This file contains the 300 ontologies and some files realted to Konclude reasoner

Konclude folder contains the Konclude files contain one file kon_resoner_new.sh.
Run the file **./kon_resoner_new.sh** to generate the consistency, classification and realisation time and memory consumption of these mentioned tasks in a csv file In the sample zip file we have this script 'kon_reasoner_new.sh'. Just run the script to generate the csv file as 'file_2.csv' .

r2o2-star-0.0.1-SNAPSHOT-jar-with-dependencies.jar ,this is a jar file of R2O2* component which is used to generate the metrics of an onotology. Just place the jar file inside the sample folder 'split_2' Run the below command:- </br>
**java -jar r2o2-star-0.0.1-SNAPSHOT-jar-with-dependencies.jar   directory_of_split_2    directory_of_split_2/results_2.csv**
                
