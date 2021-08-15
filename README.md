# Ontology Hardness
The main aim is to find the ranges of features in all 9 categories

**Actual_files** folder contains a sample zip file i.e. **split_2.zip**.
Uzip the file , we get the folder **split_2**. This file contains the 300 ontologies and some files realted to Konclude reasoner

**Konclude** folder contains the Konclude files contain one file kon_resoner_new.sh.
Run the file **./kon_resoner_new.sh** to generate the consistency, classification and realisation (reasoning tasks) time and memory consumption of these mentioned tasks in a csv file In the sample zip file we have this script 'kon_reasoner_new.sh'. Just run the script to generate the csv file as 'file_2.csv' .

**r2o2-star-0.0.1-SNAPSHOT-jar-with-dependencies.jar** ,this is a jar file of R2O2* component which is used to generate the metrics of an onotology. Just place the jar file inside the sample folder 'split_2' Run the below command:- </br>
**java &ensp; -jar &ensp; r2o2-star-0.0.1-SNAPSHOT-jar-with-dependencies.jar &ensp;  directory_of_split_2 &ensp; directory_of_split_2/results_2.csv**. </br>

**dataset** folder contains 59 csv files. In each csv file we have group of ontologies for which reasoning tasks and memory consumption are caluated ans stored
**csv_features_108** contains again 59 files. Each csv file contains the same set of ontologies. For each ontology, metrics are calculated and stored.

**full_processed_csv** folder contains two csv files :-
Both of these contains merging of all the ontologies present in all csv files in **dataset** folder with all csv files in **csv_features_108** folder
- a_new_more.csv  - contains all features along with some non numeric feature values like Expressivity, EL , DL and more
- final_new_more.csv - contains same as a_new_more.csv but excluding non numeric features. This files is mainly used for further processing.



                
