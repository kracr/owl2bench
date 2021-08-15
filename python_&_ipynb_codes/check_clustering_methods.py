#!/usr/bin/env python
# coding: utf-8

# In[40]:


import numpy as np
import pandas as pd
import os 
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import MinMaxScaler
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
get_ipython().run_line_magic('matplotlib', 'inline')

#*********this section is for the checking of efficient clustering methods **********************
#*************All these clustering method are explained in the report *********************


# In[41]:


dir_list=sorted(os.listdir("/home/ritam/ontology_hardness/dataset"))
                            # give the path of the directory where all files with computed, reasoning tasks time
                            # and memory consumption
                            # this 'dir_list' contains file names in this directory but some files are in the 
                            # format like '.~lock.file_58.csv#' which needs to be get rid off

print(dir_list) 


# In[42]:


l=0
final_df=pd.DataFrame(columns=['size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])

full_dataframe = pd.DataFrame(columns=['file','size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])


for i in range(len(dir_list)):
    if dir_list[i][0]=='f': # considering files int the correct format
        x=dir_list[i]
        
        path="/home/ritam/ontology_hardness/dataset/"+x
        z=pd.read_csv(path)
        z_drop=z.drop(['file'],axis=1)
        final_df=pd.concat([final_df,z_drop])
        
        full_dataframe=pd.concat([full_dataframe,z])
        
print("total ontologies with reasoning tasks , time and memory consumption")
print(full_dataframe.shape) # contains all the ontologies (only consistent) with file name, size and 6 
                            # feature values
print(final_df.shape) # same as full_dataframe but donot contains the file name , rest are same
full_dataframe


# In[43]:


#use of minmax scalar to scale the dataframe 'final_df' , so that every feature values comes in 0 to 1
#this makes every feature to be in same scale
final_np=np.array(final_df)
scaler = MinMaxScaler()
scaler.fit(final_df)
final_df_scaled=scaler.transform(final_df)
#print(final_df_scaled)
df_MinMax = pd.DataFrame(data=final_df_scaled, columns=['size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])
df_MinMax  # this dataframe contains the scaled version of 'final_df'


# In[44]:


# We have taken a subset of 16433 ontologies to work with. This is an option provided to take any subset 
# to work with, further downwards

#'partial_dataframe' contains the subset of 'full_dataframe'. This 'partial_dataframe' is used in further 
# processing 

partial_dataframe=full_dataframe.iloc[0:16433,:]  # 'partial_dataframe' contains non scaled values
print(partial_dataframe.shape)

t=df_MinMax.head(16433)  # dataframe 't' contains scaled values and a subset of 'df_MinMax'
print(t.shape)

# this subset value should be same for both cases


# In[45]:


# K-means clustering based on time into easy, medium and hard category , we get 3 categories
# this is the first clustering.

#*****  checking  the first clustering method**************

km = KMeans(n_clusters=3,random_state=0)
print(km)

y_predicted=km.fit_predict(t[["consistency_time_milsecs"]]) # first clustered on 'consistency_time_milsecs'
print(y_predicted,np.sum(y_predicted)) # contains the predicted classes
t['cluster']=y_predicted

t1=t[t['cluster']==0] # contains the class 0
t2=t[t['cluster']==1] # contains the class 1
t3=t[t['cluster']==2] # contains the class 2 

plt.scatter(t1["consistency_time_milsecs"],t1["consistency_size_kb"],c='black') # class 0 is labelled as black
plt.scatter(t2["consistency_time_milsecs"],t2["consistency_size_kb"],c='green') # class 1 is labelled as green
plt.scatter(t3["consistency_time_milsecs"],t3["consistency_size_kb"],c='red')   # class 2 is labelled as red
plt.xlabel('time_in_milisecs')
plt.ylabel('size_in_kb')
plt.show()


# In[46]:


label_col=[]
for i in range((y_predicted.shape[0])):
    if y_predicted[i]==0:
        label_col.append('easy') # black label
    elif y_predicted[i]==1:
        label_col.append('hard') # green label
    else:
        label_col.append('medium') # red label
        
partial_dataframe['label_time']=label_col #partial dataframe has label for each of the ontology


# In[47]:


# this section tells after K-means clustering how many ontologies are in easy, medium and hard category

# collect the easy , medium and hard ones based on time

df_easy=partial_dataframe[partial_dataframe['label_time']=='easy']
df_medium=partial_dataframe[partial_dataframe['label_time']=='medium']
df_hard=partial_dataframe[partial_dataframe['label_time']=='hard']
print("Maximum value of consistency time in easy time category= ",df_easy['consistency_time_milsecs'].max(),"Minimum value of consistency time in easy time category= ",df_easy['consistency_time_milsecs'].min())
print("Shape of easy time category= ",df_easy.shape)

print("Maximum value of consistency time in medium time category= ",df_medium['consistency_time_milsecs'].max(),"Minimum value of consistency time in medium time category= ",df_medium['consistency_time_milsecs'].min())
print("Shape of medium time category= ",df_medium.shape)

print("Maximum value of consistency time in hard time category= ",df_hard['consistency_time_milsecs'].max(),"Minimum value of consistency time in hard time category= ",df_hard['consistency_time_milsecs'].min())
print("Shape of hard time category= ",df_hard.shape)

#********************** end of K means clustering *******************************************


# In[48]:


# ********** now checking the second clustering method **********************#
#**********************BIRCH clustering ***********************************

partial_dataframe=full_dataframe.iloc[0:16433,:]  # contains non scaled values
print(partial_dataframe.shape)
t=df_MinMax.head(16433)  # contains scaled values
print(t.shape)


# In[49]:


# cluster based on time  into easy, medium and hard

from sklearn.cluster import Birch

brc = Birch(branching_factor=50, n_clusters=3, threshold=10) 

y_predicted=brc.fit_predict(t[["consistency_time_milsecs"]])
print(y_predicted)
t['cluster']=y_predicted
t1=t[t['cluster']==0] 
t2=t[t['cluster']==1] 
t3=t[t['cluster']==2]  

plt.scatter(t1["consistency_time_milsecs"],t1["consistency_size_kb"],c='black')
plt.scatter(t2["consistency_time_milsecs"],t2["consistency_size_kb"],c='green')
plt.scatter(t3["consistency_time_milsecs"],t3["consistency_size_kb"],c='red')
plt.xlabel('time_in_milisecs')
plt.ylabel('size_in_kb')
plt.show()


# In[50]:


# we can see there is no proper clustering happening for different parameters , just representing one class

label_col=[]
for i in range((y_predicted.shape[0])):
    if y_predicted[i]==0:
        label_col.append('easy') 
    elif y_predicted[i]==1:                           # we cannot group them into different classes, lets assume
        label_col.append('hard')                      # all are in easy time category
    else:
        label_col.append('medium')
        
partial_dataframe['label_time']=label_col


# In[51]:


# this section tells after BIRCH clustering how many ontologies are in easy, medium and hard category

# collect the easy , medium and hard ones based on time
# we can see there are no ontologies in medium and hard category , since I assumed all ontologies to be one class
# because BIRCH clustering doesnot give proper grouping results

df_easy=partial_dataframe[partial_dataframe['label_time']=='easy']
df_medium=partial_dataframe[partial_dataframe['label_time']=='medium']
df_hard=partial_dataframe[partial_dataframe['label_time']=='hard']
print("Maximum value of consistency time in easy time category= ",df_easy['consistency_time_milsecs'].max(),"Minimum value of consistency time in easy time category= ",df_easy['consistency_time_milsecs'].min())
print("Shape of easy time category= ",df_easy.shape)

print("Maximum value of consistency time in medium time category= ",df_medium['consistency_time_milsecs'].max(),"Minimum value of consistency time in medium time category= ",df_medium['consistency_time_milsecs'].min())
print("Shape of medium time category= ",df_medium.shape)

print("Maximum value of consistency time in hard time category= ",df_hard['consistency_time_milsecs'].max(),"Minimum value of consistency time in hard time category= ",df_hard['consistency_time_milsecs'].min())
print("Shape of hard time category= ",df_hard.shape)

#************this ends BIRCH CLUSTERING *************************


# In[52]:


#************* now checking the third clustering method ********************

# *********************** DBSCAN clustering method**********

partial_dataframe=full_dataframe.iloc[0:16433,:]  # contains non scaled values
print(partial_dataframe.shape)
t=df_MinMax.head(16433)  # contains scaled values
print(t.shape)


# In[53]:


# cluster based on time  into easy, medium and hard

from sklearn.cluster import DBSCAN # density based clustering

brc = DBSCAN(eps = 0.02, min_samples = 5)
y_predicted=brc.fit_predict(t[["consistency_time_milsecs"]])
print(y_predicted, np.sum(y_predicted))
t['cluster']=y_predicted

t1=t[t['cluster']==0] # contains class 0
t2=t[t['cluster']==1] # contains class 1
t3=t[t['cluster']==2] # class 2 is not present 

plt.scatter(t1["consistency_time_milsecs"],t1["consistency_size_kb"],c='black',label='easy_time')
plt.scatter(t2["consistency_time_milsecs"],t2["consistency_size_kb"],c='green',label='hard_time')
plt.scatter(t3["consistency_time_milsecs"],t3["consistency_size_kb"],c='red')
plt.xlabel('time_in_milisecs')
plt.ylabel('size_in_kb')
plt.legend()
plt.show()


# In[54]:


# this only gives two classes after clustering, which is easy and hard. There is no medium class. So this 
# is not a proper clustering method since we dont get 3 clustering classes

label_col=[]
for i in range((y_predicted.shape[0])):
    if y_predicted[i]==0:
        label_col.append('easy')  # contains easy class           # we consider two classes are easy and hard time 
    elif y_predicted[i]==1:
        label_col.append('hard')  # contains hard class
    else:
        label_col.append('medium') # no medium class 
        
partial_dataframe['label_time']=label_col


# In[55]:


# this section tells after DBSCAN clustering how many ontologies are in easy, medium and hard category

# collect the easy , medium and hard ones based on time

df_easy=partial_dataframe[partial_dataframe['label_time']=='easy']
df_medium=partial_dataframe[partial_dataframe['label_time']=='medium']
df_hard=partial_dataframe[partial_dataframe['label_time']=='hard']
print("Maximum value of consistency time in easy time category= ",df_easy['consistency_time_milsecs'].max(),"Minimum value of consistency time in easy time category= ",df_easy['consistency_time_milsecs'].min())
print("Shape of easy time category= ",df_easy.shape)

print("Maximum value of consistency time in medium time category= ",df_medium['consistency_time_milsecs'].max(),"Minimum value of consistency time in medium time category= ",df_medium['consistency_time_milsecs'].min())
print("Shape of medium time category= ",df_medium.shape)

print("Maximum value of consistency time in hard time category= ",df_hard['consistency_time_milsecs'].max(),"Minimum value of consistency time in hard time category= ",df_hard['consistency_time_milsecs'].min())
print("Shape of hard time category= ",df_hard.shape)

#**************** this ends DBSCAN clustering algorithm*********************


# In[56]:


#************** now checking the fourth clustering ********************

#*********  Agglomerative Clustering Method ******************

partial_dataframe=full_dataframe.iloc[0:16433,:]  # contains non scaled values
print(partial_dataframe.shape)
t=df_MinMax.head(16433)  # contains scaled values
print(t.shape)


# In[57]:


# cluster based on time  into easy, medium and hard

from sklearn.cluster import AgglomerativeClustering # hierachical based clustering


agm = AgglomerativeClustering(n_clusters=3,affinity="euclidean",linkage="ward")
y_predicted=agm.fit_predict(t[["consistency_time_milsecs"]])
print(y_predicted, np.sum(y_predicted))
t['cluster']=y_predicted

t1=t[t['cluster']==0]  # contains class 0
t2=t[t['cluster']==1]  # contains class 1
t3=t[t['cluster']==2]  # contains class 2

plt.scatter(t1["consistency_time_milsecs"],t1["consistency_size_kb"],c='black',label='easy_time')
plt.scatter(t2["consistency_time_milsecs"],t2["consistency_size_kb"],c='green',label='hard_time')
plt.scatter(t3["consistency_time_milsecs"],t3["consistency_size_kb"],c='red',label='medium_time')
plt.xlabel('time_in_milisecs')
plt.ylabel('size_in_kb')
plt.legend()
plt.show()


# In[58]:


# here we get 3 desired clusters easy, medium and hard time , same as K-means clustering 

label_col=[]
for i in range((y_predicted.shape[0])):
    if y_predicted[i]==0:
        label_col.append('easy')   # easy class
    elif y_predicted[i]==1:
        label_col.append('hard')   # hard class
    else:
        label_col.append('medium') # medium class
        
partial_dataframe['label_time']=label_col


# In[59]:


# this section tells after Agglomerative clustering how many ontologies are in easy, medium and hard category

# collect the easy , medium and hard ones based on time

df_easy=partial_dataframe[partial_dataframe['label_time']=='easy']
df_medium=partial_dataframe[partial_dataframe['label_time']=='medium']
df_hard=partial_dataframe[partial_dataframe['label_time']=='hard']
print("Maximum value of consistency time in easy time category= ",df_easy['consistency_time_milsecs'].max(),"Minimum value of consistency time in easy time category= ",df_easy['consistency_time_milsecs'].min())
print("Shape of easy time category= ",df_easy.shape)

print("Maximum value of consistency time in medium time category= ",df_medium['consistency_time_milsecs'].max(),"Minimum value of consistency time in medium time category= ",df_medium['consistency_time_milsecs'].min())
print("Shape of medium time category= ",df_medium.shape)

print("Maximum value of consistency time in hard time category= ",df_hard['consistency_time_milsecs'].max(),"Minimum value of consistency time in hard time category= ",df_hard['consistency_time_milsecs'].min())
print("Shape of hard time category= ",df_hard.shape)
 
# ************ this ends Agglomerative clustering method ********************


# In[60]:


# so we have K means clustering and Agglomerative clustering working good. But we choose K means to work with 
# because Agglomerative donot works good for larger dataset, also processing is slow.


# In[ ]:




