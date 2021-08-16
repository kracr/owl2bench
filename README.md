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
- final_new_more.csv - contains same as a_new_more.csv but excluding non numeric features. This files is mainly used for further processing.<\br>

**python_&_ipynb_code** folder 4 python files:-</br>
-check_clustering_methods.py this is used for finding the efficient clustering method for our experiment.</br>
-merging_90_features_&_reasoning_tasks.py  - these generates the file **a_new_more.csv**  and **final_new_more.csv**. </br>
-ontology_hardness.py : this is the main file. Run this for generating the ranges.</br>
-plot_generation.py - this is used for generating the zoomed plots for common features in easy, medium and hard time category. Zoomed plots are present in
  **plots_for_comparison_with_all_category** folder. The plots are can be used for analysis of a feature among 9 categories.
  
**normal_curve_&_skewness** this folder contains the plots for all 9 categories. Each subfolder represents 1 category. A single plot is respect to one feature with its **approximately normal curve and skewness**. Depending on the skewness value two different formulas are applied for calculating range of a feature. Explained in the report , follow there.

**ranges_result** this folder contains mainly the range of features for all 9 categories.

**pickles_file**
There are 9 pickle files corresponding to each of the 9 category:-
- easy_time_easy_memory :  contain set of of ontologies in easy_time_easy_memory category
- easy_time_medium_memory : contain set of of ontologies in easy_time_medium_memory category
- easy_time_hard_memory : contain set of of ontologies in easy_time_hard_memory category
- medium_time_easy_memory : contain set of of ontologies in medium_time_easy_memory category
- medium_time_medium_memory : contain set of of ontologies in medium_time_medium_memory category
- medium_time_hard_memory : contain set of of ontologies in medium_time_hard_memory category
- hard_time_easy_memory : contain set of of ontologies in hard_time_easy_memory category
- hard_time_medium_memory : contain set of of ontologies in hard_time_medium_memory category
- hard_time_hard_memory : contain set of of ontologies in hard_time_easy_memory category
- rows : it contains the common features of these 9 categories

**How to run the main file ontology_hardness.py**:
1. Make a folder by any name, suppose **ontology hardness**. 
2. Place the folder **directory** and **csv_features_108** inside this folder.
3. 
