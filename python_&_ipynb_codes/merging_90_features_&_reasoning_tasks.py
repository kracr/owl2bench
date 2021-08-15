#!/usr/bin/env python
# coding: utf-8

# import numpy as np
# import pandas as pd
# import os 
# from sklearn.preprocessing import StandardScaler
# from sklearn.preprocessing import MinMaxScaler
# import matplotlib.pyplot as plt
# from sklearn.cluster import KMeans
# %matplotlib inline

# In[1]:


import numpy as np                                          ## import all the necesseary libraries ##
import pandas as pd 
import os 
from sklearn.preprocessing import StandardScaler 
from sklearn.preprocessing import MinMaxScaler 
import matplotlib.pyplot as plt 
from sklearn.cluster import KMeans 
get_ipython().run_line_magic('matplotlib', 'inline')


# In[2]:


dir_list=sorted(os.listdir("/home/ritam/ontology_hardness/dataset"))  
                            # give the path of the directory where all files with computed, reasoning tasks time
                            # and memory consumption
                            # this 'dir_list' contains file names in this directory but some files are in the 
                            # format like '.~lock.file_58.csv#' which needs to be get rid off

temp=[]                     #this 'temp' array contains the correct format 
for i in range(len(dir_list)):
    if dir_list[i][0]=='f':        #consider only those whose name starts with 'f' in correct format
        temp.append(dir_list[i])

dir_list=temp  # contains list of all files names in that directory in the correct format
print(dir_list)


# In[3]:


l=0
final_df=pd.DataFrame(columns=['file','size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb']) # these are column names in each of the file 
                                                                    # in that directory

#final_df is the dataframe which contains all the consistent ontologies with the reasoning tasks, 
#time and memory consumption

for i in range(len(dir_list)):
    if dir_list[i][0]=='f':
        x=dir_list[i]
        path="/home/ritam/ontology_hardness/dataset/"+x #getting each file 
        z=pd.read_csv(path)
        z_drop=z
        final_df=pd.concat([final_df,z_drop]) 
        


# In[4]:


print(final_df.shape) #shape of the final dataframe


# In[5]:


l=1
dir_list_features=sorted(os.listdir("/home/ritam/ontology_hardness/new_features/csv_features_108"))
#print(dir_list_features)   # place the path contains all the files which contains the 91 metric values 
                            # calculated from R2O2* component 
                            # 'dir_list_features' contain some files not in the correct format

temp=[]
for i in range(len(dir_list_features)):
    if dir_list_features[i][0]=='r':
        temp.append(dir_list_features[i])
dir_list_features=temp  # dir_list_features now contains files in correct format
print(dir_list_features)

for i in range(len(dir_list_features)):
    if dir_list_features[i][0]=='r':
        x=dir_list_features[i]
        path="/home/ritam/ontology_hardness/new_features/csv_features_108/"+x
        z=pd.read_csv(path)


# In[6]:


# this section contains merging of the files containing reasoning time and memory with the corresponding
# files containing 91 feature values 

final_list=[]
cols=[]
#print(dir_list)
for i in range(len(dir_list)):
    if dir_list[i][0]=='f':
        if i <= 58:
            #print(i)
            x=dir_list[i]
            path_1="/home/ritam/ontology_hardness/dataset/"+x  #place the path, files with reasoning time, memory
            parent_csv=pd.read_csv(path_1)
            
            y=dir_list_features[i]
            path_2="/home/ritam/ontology_hardness/new_features/csv_features_108/"+y  #place the path,
            print(x,y)                                                               # files with 91 features
            child_csv=pd.read_csv(path_2)
            
            for j in range(parent_csv.shape[0]):
                temp=parent_csv.iloc[j,:]
                ontology_name=parent_csv.iloc[j,:]['file']
                child_features=(child_csv[child_csv['Ontology']==ontology_name])
                if child_features.shape[0]==1:
                    child_features_np=np.array(child_features)[0]
                    child_features_np=list(np.delete(child_features_np,[0,1]))
                    temp_np=list(temp)
            
                    join=temp_np+ child_features_np
                    final_list.append(join)
            if i==0:
                l1=list(parent_csv.columns)
                l2=list(child_csv.columns)
                l2=l2[2:]
                cols=l1+l2
                #break
        
final_features_df=pd.DataFrame(final_list,columns=cols) # this dataframe contains all the consistent ontologies 
                                                        # with all the 91 features + 6 reasoning task values
#print(final_features_df.shape)
#print(final_list[0])
final_features_df


# In[7]:


#final_features_df.to_csv("a_new_more.csv",index=False)  # saving this dataframe to 'a_new_more.csv'

final_features_df=final_features_df.drop(['EXPRESSIVITY','EL','QL','RL','DL','FULL'],axis=1)
final_features_df #drop the columns which are useless and contain non numeric values in files with 91 features


# In[8]:


#final_features_df.to_csv('final_new_more.csv',index=False) #saving this dataframe to final_new_more.csv which 
                                                            # will be used further in ontology hardness file


# In[ ]:




