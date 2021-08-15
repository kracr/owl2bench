#!/usr/bin/env python
# coding: utf-8

# In[223]:


import numpy as np
import pandas as pd                                             #load the libraries
import os 
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import MinMaxScaler
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
get_ipython().run_line_magic('matplotlib', 'inline')


# In[224]:


dir_list=sorted(os.listdir("/home/ritam/ontology_hardness/dataset"))
                            # give the path of the directory where all files with computed, reasoning tasks time
                            # and memory consumption
                            # this 'dir_list' contains file names in this directory but some files are in the 
                            # format like '.~lock.file_58.csv#' which needs to be get rid off

print(dir_list)


# In[225]:


l=0
final_df=pd.DataFrame(columns=['size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])

full_dataframe = pd.DataFrame(columns=['file','size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])


for i in range(len(dir_list)):
    if dir_list[i][0]=='f':  # considering files int the correct format
        x=dir_list[i]
        path="/home/ritam/ontology_hardness/dataset/"+x
        z=pd.read_csv(path)
        z_drop=z.drop(['file'],axis=1)
        final_df=pd.concat([final_df,z_drop])
        
        full_dataframe=pd.concat([full_dataframe,z])
        
print("total consistent ontologies with reasoning tasks , time and memory consumption")
print(full_dataframe.shape) # contains all the ontologies (only consistent) with file name, size and 6 
                            # feature values
print(final_df.shape) # same as full_dataframe but donot contains the file name , rest are same
full_dataframe
#final_df
# total consistent + inconsistent  ontologies = 16637
# total consistent ontologies = 16434


# In[226]:


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


# In[227]:


# We have taken a subset of 16433 ontologies to work with. This is an option provided to take any subset 
# to work with, further downwards

#'partial_dataframe' contains the subset of 'full_dataframe'. This 'partial_dataframe' is used in further 
# processing 

partial_dataframe=full_dataframe.iloc[0:16433,:]  # 'partial_dataframe' contains non scaled values
print(partial_dataframe.shape)

t=df_MinMax.head(16433)  # dataframe 't' contains scaled values and a subset of 'df_MinMax'
print(t.shape)

# this subset value should be same for both cases


# In[228]:


# K-means clustering based on time into easy, medium and hard category , we get 3 categories
# this is the first clustering.
            
    
km = KMeans(n_clusters=3,random_state=0)
print(km)

y_predicted=km.fit_predict(t[["consistency_time_milsecs"]]) # first clustered on 'consistency_time_milsecs'
print(y_predicted) # contains the predicted classes
print(type(t))
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


# In[229]:


label_col=[]
for i in range((y_predicted.shape[0])):
    if y_predicted[i]==0:
        label_col.append('easy') # black label
    elif y_predicted[i]==1:
        label_col.append('hard') # green label
    else:
        label_col.append('medium') # red label
        
partial_dataframe['label_time']=label_col #partial dataframe has label for each of the ontology


# In[230]:


partial_dataframe # contains only time and memeory


# In[231]:


# collect the easy ones based on time from partial dataframe 

df_easy=partial_dataframe[partial_dataframe['label_time']=='easy']
df_easy_drop=df_easy # contains based on time and also non scaled values
df_easy_drop


# In[232]:


print(df_easy['consistency_time_milsecs'].max(),df_easy['consistency_time_milsecs'].min())
print(df_easy.shape) # shape of the easy time category dataframe, no of easy time ontologies = 16371


# In[233]:


# now each of the 3 category will be clustered on memory
# hence , now easy time ontologies are clustered on memory - 'consistency_size_kb'

t=df_easy_drop.drop(['file','label_time'],axis=1) # contains non scaled values

#again MinMax scalar is used for scaling , since dataframe 't' contains non scaled values 
scaler = MinMaxScaler()
scaler.fit(t)
t_df_scaled=scaler.transform(t)

t_MinMax = pd.DataFrame(data=t_df_scaled, columns=['size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])
t=t_MinMax

print(t.shape)  

km_1 = KMeans(n_clusters=3,random_state=0)
y_predicted_1=km_1.fit_predict(t[["consistency_size_kb"]]) # now clustered on 'consistency_size_kb' to get 3 more
                                # categories under easy time ontology, they are easy time easy memory, easy time 
                                # medium memory and easy time hard memory

t['cluster']=y_predicted_1 # contians the predicted class
t1=t[t['cluster']==0] # contains class 0
t2=t[t['cluster']==1] # contains class 1
t3=t[t['cluster']==2] # contains class 2

plt.scatter(t1["consistency_time_milsecs"],t1["consistency_size_kb"],c='black') #class 0 is labelled as black
plt.scatter(t2["consistency_time_milsecs"],t2["consistency_size_kb"],c='green') # class 1 is labelled as green
plt.scatter(t3["consistency_time_milsecs"],t3["consistency_size_kb"],c='red') # class 2 is labelled as red
plt.xlabel('time_in_milisecs')
plt.ylabel('size_in_kb')
plt.show()


# In[234]:


label_col=[]
for i in range((y_predicted_1.shape[0])):
    if y_predicted_1[i]==0:
        label_col.append('easy') #black label
    elif y_predicted_1[i]==1:
        label_col.append('hard') #green label
    else:
        label_col.append('medium') # red label
        
df_easy_drop['label_mem']=label_col
df_easy_time_mem=df_easy_drop # contains easy time ontologies clustered on memory and labelled too, also 
                                # contians only 6 reasoning feature values
df_easy_time_mem


# In[235]:


# this section is for combining 91 feature with 6 reasoning features
# 'final_new_more.csv' this inlcudes all ontologies(consistent and inconsitent) with 91 metric values 
# and 6 reasoning feature values. But we are interested for consistent ontologies. Hence we extract all the 91 
# values corresponding to each of the ontology which are in easy time category and then clustered on memory

total_df= pd.read_csv('final_new_more.csv') # for features collection 
print("size of final_new_more= ",total_df.shape)


df_easy_time_mem_feature=[]
for i in range(df_easy_time_mem.shape[0]):
    ont=df_easy_time_mem.iloc[i,:]['file']
    lab=df_easy_time_mem.iloc[i,:]['label_mem']
    o=(np.array(total_df[total_df['file']==ont]))
    if o.shape[0]!=0:
    
        u=list(np.array(total_df[total_df['file']==ont])[0])
        u.append(str(lab))
        df_easy_time_mem_feature.append(u)

    
total_df_cols=list(total_df.columns)
total_df_cols.append('label_mem')
df_easy_time_mem_feature_df=pd.DataFrame(data=df_easy_time_mem_feature,columns=total_df_cols)
df_easy_time_mem_feature_df  # total easy time consistent ontologies clustered on memory and labelled with easy,
                             # medium , hard memory . Also contains 91 features + 6 resoning task values

#total easy_time consistent ontologies clustered on memory with all features = 16370


# In[236]:


# easy time with easy mem, medium mem and hard mem
# this section shows the mean , min ,max of each of the feature category wise for easy time ontologies category

l=df_easy_time_mem_feature_df.drop('file',axis=1)
x=l.groupby(l['label_mem']).agg(['min', 'max','mean'])
#x.to_csv('easy_time_feature_details.csv',index_label=True)
x_easy=x
x_easy


# In[237]:


# this section is for finding feature importance of each feature in easy time category 
#this is done using feature importance metric of RandomForest Classifier

from sklearn import preprocessing
from sklearn.ensemble import RandomForestClassifier
import operator

le = preprocessing.LabelEncoder()
p=df_easy_time_mem_feature_df
le.fit(p['label_mem'])
g=le.transform(p['label_mem']) 
x_train_np=p.drop(['file','label_mem'],axis=1)
y_train_np=np.array(g)


model = RandomForestClassifier(n_estimators = 100, random_state = 2)
model.feature_names = list(x_train_np.columns.values)
model.fit(x_train_np, y_train_np)
ft=list(model.feature_names)
importance = model.feature_importances_
print(model.n_features_)

dict1_imp={} #contains each feature with its importance value
c=0
for i,v in enumerate(importance):
    #print('Feature: %0d, Score: %.5f' % (i,v))
    val= round(v,5)
    dict1_imp[ft[c]]=val
    c+=1
dict1_imp_sort=dict(sorted(dict1_imp.items(), key=operator.itemgetter(1),reverse=True)) 
                #maintains the sorted  version of the 'dict1_imp'
    
for key,val in dict1_imp_sort.items():
    print(key,val)
plt.bar([x for x in range(len(importance))], importance)
plt.show()

easy_feature_sort_dic=dict1_imp_sort # this contains the sorted importance of features for easy time category


top_n_1=(list(easy_feature_sort_dic.keys())[:50]) # I have used top 50 features in this category, and hence 
                                                # ranges are calculated for top 50 features for this category


# In[238]:


# this section is for finding the features with zero importance and then exluding them for this category
d= dict(x_easy)
easy_zero_features =[]
for key, value in dict1_imp_sort.items():
    if value == 0.0:
        if ( sum(list(d[(key, 'mean')]))/3 )== 0.0 :
            #print(key,list(d[(key, 'mean')])) # these zero importance feature also has their mean values for easy,
                                              # medium , hard category as zero after final clustering on memory. 
                                              # This condition is also considered here for claiming them as useless
                                              # feature
            easy_zero_features.append(key)
print(easy_zero_features) # contains the feature with zero importance

print(top_n_1) # top n features 


# In[239]:


#proccessing easy time with easy memory with 108 features

cols_list=list(df_easy_time_mem_feature_df.columns)
print(cols_list,len(cols_list))


# In[240]:


# now the range calculation begins for easy time ontology category clustered on memory

#after easy time and memory clusering ,
#collect the easy ones

from matplotlib import pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')
#%matplotlib qt 
import scipy
from scipy.stats import norm
from statsmodels.graphics.gofplots import qqplot
import statistics
from scipy.stats import shapiro
from scipy.stats import normaltest
import math


#   this below function calculates the interquratile range for features whose skewness value is not in (-2,2) 
#                      ****     full - algorithm is explianed in the report *****
def cal(sov_sort):
    
    p=0
    l=len(sov_sort)
    left=0
    right=0
    while  p < 90 :
        if p==0:
            if l % 2 ==0:
                left=( l /2)                                     #left and right variable is incrementally shifted  
                right=( l / 2) + 1                               # each time by 50 % of the remaining length on either
                p= round((((right -left +1) / l) * 100),2)       # side
                
            else:
                left=math.floor( l /2)
                right=math.ceil(l/2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
        else:
            if left % 2 !=0:
                left = math.ceil(left/2)
            else:
                left = left /2
            
            dist = l - right +1
            if dist % 2 !=0:
                right = right + math.ceil(dist/2)
            else:
                right = right + (dist /2)
                
            p= round((((right -left +1) / l) * 100),2)
             
            
        
    return left-1,right-1 #return the index of array within which more than 90% of the values are present for
                            # that particular feature



print(df_easy_time_mem_feature_df.shape) 
easy_time_mem_easy = df_easy_time_mem_feature_df[df_easy_time_mem_feature_df['label_mem']== 'easy']
print(easy_time_mem_easy.shape) # contains the easy time easy memory ontology

prior_easy= round(easy_time_mem_easy.shape[0] / df_easy_time_mem_feature_df.shape[0] ,5)
                            #fraction of easy time easy memory ontology in easy time catgeory
    
main_dict_etme={}           # this contains the features whose skewness value is in (-2,2) and in this section
                            # only initialization is done, like  min ,max, lw_i, rw_i, min_val_w, etc
                            # the calculation for the ranges of features in this category is shown in next section
                            # this is done using effective range

main_dict_etme_iqr={}       # this contains the range values and all other parameters of those features whose
                            # skewness value not in (-2,2)

for i in range(len(cols_list)):
    if cols_list[i] in top_n_1:
        tmp_dict={}
        tmp_dict_iqr={}
        
        feature = cols_list[i]
        print("feature name= ",feature)
        
        sov=np.array(easy_time_mem_easy[feature])
        sov_1=(np.sort(sov))
        #print("feature values= ",sov)
        print("hii")
        skew=scipy.stats.skew(sov) # calculate the skewness value of a feature
        
        tmp_dict["skew"]=round(skew,4)    
        tmp_dict_iqr["skew"]=round(skew,4)
        
        
        # section for intialization of those feature which are approximately normally distributed and will use 
        # effective range formula
        
        if -2 < skew and skew < 2:
        
            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            tmp_dict["min"]=round(np.min(sov),9) #min value of feature
            tmp_dict["max"]=round(np.max(sov),9) #max value of feature
            min_val=np.min(sov)
            max_val=np.max(sov)

            mean = np.mean(sov) #mean value of feature
            std = np.std(sov)   #standard deviation of feature
            
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict["mean"]=round(mean,9)
            tmp_dict["std"]=round(std,9)
            
            # ---  effective range formula originally ---
            
            #gamma=2
            #rx= mean - ((1- prior_easy) *gamma * std)    #explianed in the report 
            #ry= mean + ((1- prior_easy) *gamma * std)
            
            # ----------------------------------------------
            l_weight=0.5  #initial left weight
            r_weight=0.5  #initial right weight
            rx= mean - (l_weight* std)   #intial left range 
            ry= mean + (r_weight* std)   #initial right range 
            
            print("left range= ",rx,"right range= ", ry)
            tmp_dict["lw_i"]=round(l_weight,9)
            tmp_dict["rw_i"]=round(r_weight,9)
            tmp_dict["lr"]=round(rx,9)
            tmp_dict["rr"]=round(ry,9)

            min_val_w= round(((mean - min_val) /std ),9)  # fraction of std by which minimum value of the feature
                                                          # away from mean 
            max_val_w= round(((max_val - mean) /std ),9)  # similaly maximum value of feature away from mean
            print(min_val_w,max_val_w)
            
            tmp_dict["min_val_w"]=round(min_val_w,9)
            tmp_dict["max_val_w"]=round(max_val_w,9)


            val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_easy.shape[0] 
            v =  round((val * 100 ),2) #percentage of data is covered within initial range
            print("data covered within this range= ",v,"%")

            tmp_dict["percent_i"]=round(v,9)  #storing the initial percentage of data covered within that range 
            tmp_dict["percent_change"]=round(v,9) # this will contain the final percentage of data covered 
                                                    # after the final range caluclation

            val=(((min_val <= sov) & (sov <= mean)).sum()) / easy_time_mem_easy.shape[0]
            percent_l =  round((val * 100 ),2) # contain the percentage of data covered on the left side the mean

            val=(((mean <= sov) & (sov <= max_val)).sum()) / easy_time_mem_easy.shape[0]
            percent_r =  round((val * 100 ),2) # contain the percentage of data covered on the right side the mean

            print("percent left and right= ",percent_l,percent_r)
            tmp_dict["percent_l"]=percent_l
            tmp_dict["percent_r"]=percent_r
            
            main_dict_etme[feature]=tmp_dict
            
        else:
            # section for calculating the range of features whose skewness value is not in (-2,2)
            
            left_index,right_index = cal(sov_1) #to find interquartile range by using the above function
            #print(left_index,right_index)
            rx=sov_1[int(left_index)] # we get left range 
            ry=sov_1[int(right_index)] # we get right range 
 
            # rest parameters defination is same as above
    
            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            
            tmp_dict_iqr["min"]=round(np.min(sov),9)
            tmp_dict_iqr["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)
            
            
            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict_iqr["mean"]=round(mean,9)
            tmp_dict_iqr["std"]=round(std,9)
            
            print("left range= ",rx,"right range= ", ry)
            tmp_dict_iqr["lw_i"]="null"  # no need in this case, hence null value is placed
            tmp_dict_iqr["rw_i"]="null"  # no need in this case, hence null value is placed
            tmp_dict_iqr["lr"]=round(rx,9)
            tmp_dict_iqr["rr"]=round(ry,9)
            
            
            tmp_dict_iqr["min_val_w"]="null" # no need in this case, hence null value is placed
            tmp_dict_iqr["max_val_w"]="null" # no need in this case, hence null value is placed
            tmp_dict_iqr["percent_i"]="null" # no need in this case, hence null value is placed
            
            val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            
            print("data covered within this range= ",v,"%")
            tmp_dict_iqr["percent_change"]=round(v,9)
            
            val=(((min_val <= sov) & (sov <= mean)).sum()) / easy_time_mem_easy.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / easy_time_mem_easy.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            
            tmp_dict_iqr["percent_l"]=percent_l
            tmp_dict_iqr["percent_r"]=percent_r
            
            main_dict_etme_iqr[feature]=tmp_dict_iqr

            
            # 68–95–99.7 rule, also known as the empirical rule for normal distribution
            # checking for how much data is covered with (1,2,3) times standard deviation
            
            # for 1*std
            left = mean - std
            right = mean + std
            val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 1 standard deviation= ",v,"%")
            
            # for 2*std
            left = mean - (2*std)
            right = mean + (2*std)
            val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 2 standard deviation= ",v,"%")
            


            # for 3*std
            left = mean - (3*std)
            right = mean + (3*std)
            val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 3 standard deviation= ",v,"%")
            
            
        print("\n\n")
        
        # section for normality tests begin like kurtosis value, skewness value, Quantile Quantile plot, Shapiro
        # wilk test
        
        #normality test
        print("normality test :----\n")
        
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        print("skewness value= ",skew)
        
        #plotting graph for to see the skewess
        sov_1=np.sort(sov)
        fig, ax1 = plt.subplots(1, 1, figsize=(8,5))
        ax1.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$, $ skew = \\approx {round(skew,3)}$')
        ax1.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        ax1.set_title("Normal Fit")
        ax1.set_xlabel(feature)
        ax1.set_ylabel("pdf")
        ax1.legend()
        #plt.savefig('/home/ritam/ontology_hardness/normal_curve_&_skewness/easy_time_easy_memory/'+str(feature)+'.jpeg')
        plt.show()
        
        # Quantile-Quantile Plot Nomrality check
        print("\nQQ plot \n")
        qqplot(sov_1, line='s')
        plt.show()
        
        #Statistical Normality Tests
        # Shapiro-Wilk Test
        print("\n Shapiro-Wilk Test \n ")
        #stat, p = shapiro(sov_1[0:4998])
        stat, p = normaltest(sov_1)
        print('Statistics=%.3f, p=%f' % (stat, p))
        
        alpha = 0.05
        if p > alpha:
            print('Sample looks Gaussian (fail to reject H0)')
        else:
            print('Sample does not look Gaussian (reject H0)')
            
        print("ends normality test ------------------------------------------------\n")
        
        


# In[241]:


# this section does the calculation of effective range formula for features whose skewness value is in (-2,2)
# the features which will use this formula are considered as approximately normal curve

            # **************   FULL algorithm is explained in the report **********************  #
import copy

main_dict_cal_etme={} # this contains the final calculated range of the approximately normal features 

#incr_rate=[(0.2,0.3),(0.5,0.8),(0.6,0.7),(0.9,0.9)]
incr_rate=[(0.9,0.9)]
for i in range(len(incr_rate)): #for this one incr_rate, observe how many features having more than 90% coverage
 
    main_dict_cal_etme=copy.deepcopy(main_dict_etme)
    lower_r=incr_rate[i][0]  # apply this on the side where data ponits are more dense
    high_r=incr_rate[i][1]   # apply this on the side where data ponits are less dense
                             # here we kept the threhsold as 50% (it is changeable), if left part of the mean 
                             # has more than 50% data, then increment the fraction at slow rate and vice versa.
                             # Similarly for the right part
                
    for j in range (100): # iterated 100 times and slowly incrementing the right and left weightage 
        c=0                                  # to increase the range till it crosses 90 % coverage 
        for key,value in main_dict_cal_etme.items():
            
            if value["percent_change"] < 90:
                
                lw_i_temp=value["lw_i"]
                rw_i_temp=value["rw_i"]
                if value["percent_l"] >= 50:                         
                    diff=value["min_val_w"] - lw_i_temp # it is the difference between the current left weightage
                                                        # and weightage of the minimum value of that frature
                    frac = round(lower_r * diff, 9)     # take fraction of the difference
                    
                    lw_i_temp= round(lw_i_temp + frac , 9) # increment the left weightage by the fraction
                    main_dict_cal_etme[key]["lw_i"]=lw_i_temp
                else:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(high_r * diff, 9)  
                    
                    lw_i_temp= round(lw_i_temp + frac ,9 )
                    main_dict_cal_etme[key]["lw_i"]=lw_i_temp

                if value["percent_r"] >= 50:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(lower_r * diff, 9)  
                    
                    rw_i_temp= round(rw_i_temp + frac , 9 )
                    main_dict_cal_etme[key]["rw_i"]=rw_i_temp
                else:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(high_r * diff, 9)  
                    
                    rw_i_temp= round(rw_i_temp + frac ,9 )
                    main_dict_cal_etme[key]["rw_i"]=rw_i_temp

                sov=np.array(easy_time_mem_easy[key])
                rx= round(value["mean"] - (lw_i_temp* value["std"]) ,9) #find left range using new left weightage
                ry= round(value["mean"] + (rw_i_temp* value["std"]),9) #find right range using new right weightage
                
                main_dict_cal_etme[key]["lr"]=round(rx,9)
                main_dict_cal_etme[key]["rr"]=round(ry,9)
                
                val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_easy.shape[0]
                v =  round((val * 100 ),2)
                main_dict_cal_etme[key]["percent_change"]=v # check the percent coverage for this new range, then
                                                             # iterate again
                if v >=85:
                    c=c+1
                
            else:
                c=c+1
        p=round(c / len(list(main_dict_cal_etme.keys())),2)
        print ("percent in loop ",j , "covers ", p , "%" )
        


# In[242]:


main_dict_cal_etme # contians the final calculated ranges for approximately normal features 
print(len(main_dict_cal_etme),len(main_dict_etme_iqr))
etme_pd=pd.DataFrame(main_dict_cal_etme)
etme_iqr_pd=pd.DataFrame(main_dict_etme_iqr)

frames = [etme_pd,etme_iqr_pd]
result = pd.concat(frames,axis=1)
result  
#result.to_csv("etme_ranges.csv") # csv file for easy time easy memory range calculation for all 50 features 
                                  # (approximately normal and non normal both)


# In[243]:


easy_time_mem_easy # total 16348 ontologies in this category


# In[244]:


#after easy time and memory clusering ,
#collect the medium ones 

     #**** follow the same notation as explained above in 'easy_tim_easy_memory' category wherever required*******
    
from matplotlib import pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')
#%matplotlib qt 
import scipy
from scipy.stats import norm
from statsmodels.graphics.gofplots import qqplot
import statistics
from scipy.stats import shapiro
from scipy.stats import normaltest
import math

#interquratile range 
def cal(sov_sort):
    p=0
    l=len(sov_sort)
    print(l)
    left=0
    right=0
    while  p < 90 :
        if p==0:
            if l % 2 ==0:
                left=( l /2)
                right=( l / 2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
            else:
                left=math.floor( l /2)
                right=math.ceil(l/2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
        else:
            if left % 2 !=0:
                left = math.ceil(left/2)
            else:
                left = left /2
            
            dist = l - right +1
            if dist % 2 !=0:
                right = right + math.ceil(dist/2)
            else:
                right = right + (dist /2)
                
            p= round((((right -left +1) / l) * 100),2)
             
        
    return left-1,right-1





print(df_easy_time_mem_feature_df.shape)
easy_time_mem_medium = df_easy_time_mem_feature_df[df_easy_time_mem_feature_df['label_mem']== 'medium']
print(easy_time_mem_medium.shape)  # contains the easy time medium memory ontology
prior_medium= round(easy_time_mem_medium.shape[0] / df_easy_time_mem_feature_df.shape[0] ,5)
                # fraction of easy time medium memory ontologies in easy time category
main_dict_etmm={}
main_dict_etmm_iqr={}

for i in range(len(cols_list)):
    if cols_list[i] in top_n_1:
        tmp_dict={}
        tmp_dict_iqr={}
        
        feature = cols_list[i]
        print("feature name= ",feature)
        
        sov=np.array(easy_time_mem_medium[feature])
        sov_1=(np.sort(sov))
        skew=scipy.stats.skew(sov)
        
        tmp_dict["skew"]=round(skew,4)
        tmp_dict_iqr["skew"]=round(skew,4)
        
        if -2 < skew and skew < 2:
        
            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            tmp_dict["min"]=round(np.min(sov),9)
            tmp_dict["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)

            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict["mean"]=round(mean,9)
            tmp_dict["std"]=round(std,9)
            #gamma=2
            #rx= mean - ((1- prior_medium) *gamma * std) 
            #ry= mean + ((1- prior_medium) *gamma * std)
            l_weight=0.5
            r_weight=0.5
            rx= mean - (l_weight* std) 
            ry= mean + (r_weight* std)
            print("left range= ",rx,"right range= ", ry)
            tmp_dict["lw_i"]=round(l_weight,9)
            tmp_dict["rw_i"]=round(r_weight,9)
            tmp_dict["lr"]=round(rx,9)
            tmp_dict["rr"]=round(ry,9)

            min_val_w= round(((mean - min_val) /std ),9)
            max_val_w= round(((max_val - mean) /std ),9)
            print(min_val_w,max_val_w)
            tmp_dict["min_val_w"]=round(min_val_w,9)
            tmp_dict["max_val_w"]=round(max_val_w,9)


            val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")

            tmp_dict["percent_i"]=round(v,9)
            tmp_dict["percent_change"]=round(v,9)

            val=(((min_val <= sov) & (sov <= mean)).sum()) / easy_time_mem_medium.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / easy_time_mem_medium.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            tmp_dict["percent_l"]=percent_l
            tmp_dict["percent_r"]=percent_r
            
            main_dict_etmm[feature]=tmp_dict
            
        else:
            
            left_index,right_index = cal(sov_1)
            #print(left_index,right_index)
            rx=sov_1[int(left_index)]
            ry=sov_1[int(right_index)]

            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            
            tmp_dict_iqr["min"]=round(np.min(sov),9)
            tmp_dict_iqr["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)
            
            
            
            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict_iqr["mean"]=round(mean,9)
            tmp_dict_iqr["std"]=round(std,9)
            
            print("left range= ",rx,"right range= ", ry)
            tmp_dict_iqr["lw_i"]="null"
            tmp_dict_iqr["rw_i"]="null"
            tmp_dict_iqr["lr"]=round(rx,9)
            tmp_dict_iqr["rr"]=round(ry,9)
            
            
            tmp_dict_iqr["min_val_w"]="null"
            tmp_dict_iqr["max_val_w"]="null"
            tmp_dict_iqr["percent_i"]="null"
            val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")
            tmp_dict_iqr["percent_change"]=round(v,9)
            
            val=(((min_val <= sov) & (sov <= mean)).sum()) / easy_time_mem_medium.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / easy_time_mem_medium.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            
            tmp_dict_iqr["percent_l"]=percent_l
            tmp_dict_iqr["percent_r"]=percent_r
            
            main_dict_etmm_iqr[feature]=tmp_dict_iqr

            
            # 68–95–99.7 rule, also known as the empirical rule

            # for 1*std
            left = mean - std
            right = mean + std
            val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 1 standard deviation= ",v,"%")
            
            # for 2*std
            left = mean - (2*std)
            right = mean + (2*std)
            val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 2 standard deviation= ",v,"%")
            


            # for 3*std
            left = mean - (3*std)
            right = mean + (3*std)
            val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 3 standard deviation= ",v,"%")
            
            
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        
        print("skewness value= ",skew)
        
        
        
        #plotting graph for to see the skewess
        sov_1=np.sort(sov)
        fig, ax1 = plt.subplots(1, 1, figsize=(8,5))
        ax1.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$, $ skew = \\approx {round(skew,3)}$')
        ax1.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        ax1.set_title("Normal Fit")
        ax1.set_xlabel(feature)
        ax1.set_ylabel("pdf")
        ax1.legend()
        #plt.savefig('/home/ritam/ontology_hardness/normal_curve_&_skewness/easy_time_medium_memory/'+str(feature)+'.jpeg')
        plt.show()
        
        # Quantile-Quantile Plot Nomrality check
        print("\nQQ plot \n")
        qqplot(sov_1, line='s')
        plt.show()
        
        #Statistical Normality Tests
        # Shapiro-Wilk Test
        print("\n Shapiro-Wilk Test \n ")
        #stat, p = shapiro(sov_1[0:4998])
        stat, p = normaltest(sov_1)
        print('Statistics=%.3f, p=%f' % (stat, p))
        
        alpha = 0.05
        if p > alpha:
            print('Sample looks Gaussian (fail to reject H0)')
        else:
            print('Sample does not look Gaussian (reject H0)')
            
        print("ends normality test ------------------------------------------------\n")
        
        


# In[245]:


print(len(main_dict_etmm))
print(len(main_dict_etmm_iqr))
main_dict_etmm_iqr


# In[246]:


#**** follow the same notation as explained above in 'easy_tim_easy_memory' category wherever required*******#
import copy

main_dict_cal_etmm={}
dec_rate=[(0.9,0.9)]
for i in range(len(dec_rate)):
 
    main_dict_cal_etmm=copy.deepcopy(main_dict_etmm)
    lower_r=dec_rate[i][0]
    high_r=dec_rate[i][1]
    for j in range (100):
        c=0
        for key,value in main_dict_cal_etmm.items():
            
            if value["percent_change"] < 90:
                
                lw_i_temp=value["lw_i"]
                rw_i_temp=value["rw_i"]
                if value["percent_l"] >= 50:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(lower_r * diff, 9)  
                    
                    lw_i_temp= round(lw_i_temp + frac , 9)
                    main_dict_cal_etmm[key]["lw_i"]=lw_i_temp
                else:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(high_r * diff, 9)  
                    
                    lw_i_temp= round(lw_i_temp + frac ,9 )
                    main_dict_cal_etmm[key]["lw_i"]=lw_i_temp

                if value["percent_r"] >= 50:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(lower_r * diff, 9)  
                    
                    rw_i_temp= round(rw_i_temp + frac , 9 )
                    main_dict_cal_etmm[key]["rw_i"]=rw_i_temp
                else:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(high_r * diff, 9)  
                    
                    rw_i_temp= round(rw_i_temp + frac ,9 )
                    main_dict_cal_etmm[key]["rw_i"]=rw_i_temp

                sov=np.array(easy_time_mem_medium[key])
                rx= round(value["mean"] - (lw_i_temp* value["std"]) ,9)
                ry= round(value["mean"] + (rw_i_temp* value["std"]),9)
                main_dict_cal_etmm[key]["lr"]=round(rx,9)
                main_dict_cal_etmm[key]["rr"]=round(ry,9)
                
                val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_medium.shape[0]
                v =  round((val * 100 ),2)
                main_dict_cal_etmm[key]["percent_change"]=v

                if v >=85:
                    c=c+1
                #break
            else:
                c=c+1
        p=round(c / len(list(main_dict_cal_etmm.keys())),2)
        print ("percent in loop ",j , "covers ", p , "%" )
        


# In[247]:


#**** follow the same notation as explained above in 'easy_tim_easy_memory' category wherever required*******   
main_dict_cal_etmm
#print(len(main_dict_cal_etmm))
etmm_pd=pd.DataFrame(main_dict_cal_etmm)
etmm_iqr_pd=pd.DataFrame(main_dict_etmm_iqr)

frames = [etmm_pd,etmm_iqr_pd]
result = pd.concat(frames,axis=1)
result
#result.to_csv("etmm_ranges.csv")


# In[248]:


# this section shows a graph among 44 features (approximately normal) how many are above 90% coverage
from matplotlib import pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')

val_sort=np.sort(list(etmm_pd.loc['percent_change',:]))
print(val_sort)
x_axis=np.arange(1,len(val_sort)+1)
print(x_axis,len(x_axis),len(val_sort))
plt.plot(x_axis,val_sort,label="percentage",marker='o', color='b')
plt.xlabel("total 44 features")
plt.ylabel("percentage")
plt.legend()
plt.show()


# In[249]:


easy_time_mem_medium # total 11 onotlogies in this category


# In[250]:


#after easy time and memory clusering ,
#collect the hard ones
#**** follow the same notation as explained above in 'easy_tim_easy_memory' category wherever required*******#
                        
from matplotlib import pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')
#%matplotlib qt 
import scipy
from scipy.stats import norm
from statsmodels.graphics.gofplots import qqplot
import statistics
from scipy.stats import shapiro
from scipy.stats import normaltest
import math

#interquratile range 
def cal(sov_sort):
    p=0
    l=len(sov_sort)
    left=0
    right=0
    while  p < 90 :
        if p==0:
            if l % 2 ==0:
                left=( l /2)
                right=( l / 2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
            else:
                left=math.floor( l /2)
                right=math.ceil(l/2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
        else:
            if left % 2 !=0:
                left = math.ceil(left/2)
            else:
                left = left /2
            
            dist = l - right +1
            if dist % 2 !=0:
                right = right + math.ceil(dist/2)
            else:
                right = right + (dist /2)
                
            p= round((((right -left +1) / l) * 100),2)
             
        
    return left-1,right-1





print(df_easy_time_mem_feature_df.shape)
easy_time_mem_hard = df_easy_time_mem_feature_df[df_easy_time_mem_feature_df['label_mem']== 'hard']
print(easy_time_mem_hard.shape) # contain easy time hard memory ontologies
prior_hard= round(easy_time_mem_hard.shape[0] / df_easy_time_mem_feature_df.shape[0] ,5)
                #fraction of easy time hard memory ontologies in the easy time category
main_dict_etmh={}
main_dict_etmh_iqr={}

for i in range(len(cols_list)):
    if cols_list[i] in top_n_1:
        tmp_dict={}
        tmp_dict_iqr={}
        
        feature = cols_list[i]
        print("feature name= ",feature)
        
        sov=np.array(easy_time_mem_hard[feature])
        sov_1=(np.sort(sov))
        skew=scipy.stats.skew(sov)
        
        tmp_dict["skew"]=round(skew,4)
        tmp_dict_iqr["skew"]=round(skew,4)
        
        if -2 < skew and skew < 2:
        
            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            tmp_dict["min"]=round(np.min(sov),9)
            tmp_dict["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)

            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict["mean"]=round(mean,9)
            tmp_dict["std"]=round(std,9)
            #gamma=2
            #rx= mean - ((1- prior_hard) *gamma * std) 
            #ry= mean + ((1- prior_hard) *gamma * std)
            
            l_weight=0.5
            r_weight=0.5
            rx= mean - (l_weight* std) 
            ry= mean + (r_weight* std)
            print("left range= ",rx,"right range= ", ry)
            tmp_dict["lw_i"]=round(l_weight,9)
            tmp_dict["rw_i"]=round(r_weight,9)
            tmp_dict["lr"]=round(rx,9)
            tmp_dict["rr"]=round(ry,9)

            min_val_w= round(((mean - min_val) /std ),9)
            max_val_w= round(((max_val - mean) /std ),9)
            print(min_val_w,max_val_w)
            tmp_dict["min_val_w"]=round(min_val_w,9)
            tmp_dict["max_val_w"]=round(max_val_w,9)


            val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")

            tmp_dict["percent_i"]=round(v,9)
            tmp_dict["percent_change"]=round(v,9)

            val=(((min_val <= sov) & (sov <= mean)).sum()) / easy_time_mem_hard.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / easy_time_mem_hard.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            tmp_dict["percent_l"]=percent_l
            tmp_dict["percent_r"]=percent_r
            
            main_dict_etmh[feature]=tmp_dict
            
        else:
            
            left_index,right_index = cal(sov_1)
            rx=sov_1[int(left_index)]
            ry=sov_1[int(right_index)]

            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            
            tmp_dict_iqr["min"]=round(np.min(sov),9)
            tmp_dict_iqr["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)
            
            
            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict_iqr["mean"]=round(mean,9)
            tmp_dict_iqr["std"]=round(std,9)
            
            print("left range= ",rx,"right range= ", ry)
            tmp_dict_iqr["lw_i"]="null"
            tmp_dict_iqr["rw_i"]="null"
            tmp_dict_iqr["lr"]=round(rx,9)
            tmp_dict_iqr["rr"]=round(ry,9)
            
            
            tmp_dict_iqr["min_val_w"]="null"
            tmp_dict_iqr["max_val_w"]="null"
            tmp_dict_iqr["percent_i"]="null"
            val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")
            tmp_dict_iqr["percent_change"]=round(v,9)
            
            val=(((min_val <= sov) & (sov <= mean)).sum()) / easy_time_mem_hard.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / easy_time_mem_hard.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            
            tmp_dict_iqr["percent_l"]=percent_l
            tmp_dict_iqr["percent_r"]=percent_r
            
            main_dict_etmh_iqr[feature]=tmp_dict_iqr

            
            # 68–95–99.7 rule, also known as the empirical rule

            # for 1*std
            left = mean - std
            right = mean + std
            val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 1 standard deviation= ",v,"%")
            
            # for 2*std
            left = mean - (2*std)
            right = mean + (2*std)
            val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 2 standard deviation= ",v,"%")
            


            # for 3*std
            left = mean - (3*std)
            right = mean + (3*std)
            val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 3 standard deviation= ",v,"%")
            
            
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        
        print("skewness value= ",skew)
        
        
        
        #plotting graph for to see the skewess
        sov_1=np.sort(sov)
        fig, ax1 = plt.subplots(1, 1, figsize=(8,5))
        ax1.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$, $ skew = \\approx {round(skew,3)}$')
        ax1.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        ax1.set_title("Normal Fit")
        ax1.set_xlabel(feature)
        ax1.set_ylabel("pdf")
        ax1.legend()
        #plt.savefig('/home/ritam/ontology_hardness/normal_curve_&_skewness/easy_time_hard_memory/'+str(feature)+'.jpeg')
        plt.show()
        
        # Quantile-Quantile Plot Nomrality check
        print("\nQQ plot \n")
        qqplot(sov_1, line='s')
        plt.show()
        
        #Statistical Normality Tests
        # Shapiro-Wilk Test
        print("\n Shapiro-Wilk Test \n ")
        #stat, p = shapiro(sov_1[0:4998])
        stat, p = normaltest(sov_1)
        print('Statistics=%.3f, p=%f' % (stat, p))
        
        alpha = 0.05
        if p > alpha:
            print('Sample looks Gaussian (fail to reject H0)')
        else:
            print('Sample does not look Gaussian (reject H0)')
            
        print("ends normality test ------------------------------------------------\n")
        
        


# In[251]:


print(len(main_dict_etmh),len(main_dict_etmh_iqr))


# In[252]:


#**** follow the same notation as explained above in 'easy_tim_easy_memory' category wherever required*******#


import copy

main_dict_cal_etmh={}
dec_rate=[(0.9,0.9)]
for i in range(len(dec_rate)):
 
    main_dict_cal_etmh=copy.deepcopy(main_dict_etmh)
    lower_r=dec_rate[i][0]
    high_r=dec_rate[i][1]
    for j in range (100):
        c=0
        for key,value in main_dict_cal_etmh.items():
            
            if value["percent_change"] < 90:
                
                lw_i_temp=value["lw_i"]
                rw_i_temp=value["rw_i"]
                if value["percent_l"] >= 50:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(lower_r * diff, 9)  
                
                    lw_i_temp= round(lw_i_temp + frac , 9)
                    main_dict_cal_etmh[key]["lw_i"]=lw_i_temp
                else:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(high_r * diff, 9)  
                
                    lw_i_temp= round(lw_i_temp + frac ,9 )
                    main_dict_cal_etmh[key]["lw_i"]=lw_i_temp

                if value["percent_r"] >= 50:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(lower_r * diff, 9)  
                
                    rw_i_temp= round(rw_i_temp + frac , 9 )
                    main_dict_cal_etmh[key]["rw_i"]=rw_i_temp
                else:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(high_r * diff, 9)  
                
                    rw_i_temp= round(rw_i_temp + frac ,9 )
                    main_dict_cal_etmh[key]["rw_i"]=rw_i_temp

                sov=np.array(easy_time_mem_hard[key])
                rx= round(value["mean"] - (lw_i_temp* value["std"]) ,9)
                ry= round(value["mean"] + (rw_i_temp* value["std"]),9)
                main_dict_cal_etmh[key]["lr"]=round(rx,9)
                main_dict_cal_etmh[key]["rr"]=round(ry,9)
                
                val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_hard.shape[0]
                v =  round((val * 100 ),2)
                main_dict_cal_etmh[key]["percent_change"]=v

                if v >=85:
                    c=c+1
                
            else:
                c=c+1
        p=round(c / len(list(main_dict_cal_etmh.keys())),2)
        print ("percent in loop ",j , "covers ", p , "%" )
        


# In[253]:


#**** follow the same notation as explained above in 'easy_tim_easy_memory' category wherever required*******#

main_dict_cal_etmh
#print(len(main_dict_cal_etmh))
etmh_pd=pd.DataFrame(main_dict_cal_etmh)
etmh_iqr_pd=pd.DataFrame(main_dict_etmh_iqr)

frames = [etmh_pd,etmh_iqr_pd]
result = pd.concat(frames,axis=1)
result
#result.to_csv("etmh_ranges.csv")


# In[254]:


easy_time_mem_hard # total 11 otologies in this category


# In[255]:


#**** follow the same notation as explained above wherever required, just names are modified*******#


# collect the medium ones
df_medium=partial_dataframe[partial_dataframe['label_time']=='medium']
df_medium_drop=df_medium # contains based on time
df_medium_drop


# In[256]:


# now medium time ontologies clustered on memory
print(df_medium_drop.shape)
t=df_medium_drop.drop(['file','label_time'],axis=1)
scaler = MinMaxScaler()
scaler.fit(t)
t_df_scaled=scaler.transform(t)

t_MinMax = pd.DataFrame(data=t_df_scaled, columns=['size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])
t=t_MinMax

km_2 = KMeans(n_clusters=3,random_state=0)
y_predicted_2=km_2.fit_predict(t[["consistency_size_kb"]])
print(y_predicted_2)
t['cluster']=y_predicted_2

t1=t[t['cluster']==0]
t2=t[t['cluster']==1]
t3=t[t['cluster']==2]

plt.scatter(t1["consistency_time_milsecs"],t1["consistency_size_kb"],c='black')
plt.scatter(t2["consistency_time_milsecs"],t2["consistency_size_kb"],c='green')
plt.scatter(t3["consistency_time_milsecs"],t3["consistency_size_kb"],c='red')
plt.xlabel('time_in_milisecs')
plt.ylabel('size_in_kb')
plt.show()


# In[257]:


label_col=[]
for i in range((y_predicted_2.shape[0])):
    if y_predicted_2[i]==0:
        label_col.append('medium')
    elif y_predicted_2[i]==1:
        label_col.append('easy')
    else:
        label_col.append('hard')
        
df_medium_drop['label_mem']=label_col
df_medium_time_mem=df_medium_drop # contains first based on time and now on memory
df_medium_time_mem


# In[258]:


#**** follow the same notation as explained above wherever required*******#

total_df= pd.read_csv('final_new_more.csv') # for features collection 
print("size of final_new_more= ",total_df.shape)

df_medium_time_mem_feature=[]
for i in range(df_medium_time_mem.shape[0]):
    ont=df_medium_time_mem.iloc[i,:]['file']
    lab=df_medium_time_mem.iloc[i,:]['label_mem']
    o=(np.array(total_df[total_df['file']==ont]))
    if o.shape[0]!=0:
    
        u=list(np.array(total_df[total_df['file']==ont])[0])
        u.append(str(lab))
        df_medium_time_mem_feature.append(u)

    
total_df_cols=list(total_df.columns)
total_df_cols.append('label_mem')
df_medium_time_mem_feature_df=pd.DataFrame(data=df_medium_time_mem_feature,columns=total_df_cols)

df_medium_time_mem_feature_df # medium time ontologies are 56 in total


# In[259]:


#**** follow the same notation as explained above wherever required*******#


# medium time with easy mem, medium mem and hard mem
l=df_medium_time_mem_feature_df.drop('file',axis=1)
x=l.groupby(l['label_mem']).agg(['min', 'max','mean'])
#x.to_csv('medium_time_feature_details.csv',index_label=True)
x_medium=x
x_medium


# In[260]:


#**** follow the same notation as explained above wherever required*******#

from sklearn import preprocessing
from sklearn.ensemble import RandomForestClassifier
import operator
le = preprocessing.LabelEncoder()
p=df_medium_time_mem_feature_df
le.fit(p['label_mem'])
g=le.transform(p['label_mem']) 

x_train_np=p.drop(['file','label_mem'],axis=1)
y_train_np=np.array(g)


model = RandomForestClassifier(n_estimators = 100, random_state = 2)
model.feature_names = list(x_train_np.columns.values)
model.fit(x_train_np, y_train_np)
ft=list(model.feature_names)
importance = model.feature_importances_
print(model.n_features_)

dict1_imp={}
c=0
for i,v in enumerate(importance):
    val= round(v,5)
    dict1_imp[ft[c]]=val
    c+=1
dict1_imp_sort=dict(sorted(dict1_imp.items(), key=operator.itemgetter(1),reverse=True))

for key,val in dict1_imp_sort.items():
    print(key,val)
plt.bar([x for x in range(len(importance))], importance)
plt.show()


medium_feature_sort_dic=dict1_imp_sort

top_n_2=(list(medium_feature_sort_dic.keys())[:50])


# In[261]:


#**** follow the same notation as explained above wherever required*******#

d= dict(x_medium)
medium_zero_features =[]
for key, value in dict1_imp_sort.items():
    if value == 0.0:
        if ( sum(list(d[(key, 'mean')]))/3 )== 0.0 :
            #print(key,list(d[(key, 'mean')]))
            medium_zero_features.append(key)
print(medium_zero_features)

print(top_n_2)


# In[262]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

#after medium time and memory clusering ,
#collect the easy ones

from matplotlib import pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')
#%matplotlib qt 
import scipy
from scipy.stats import norm
from statsmodels.graphics.gofplots import qqplot
import statistics
from scipy.stats import shapiro
from scipy.stats import normaltest
import math
#interquratile range 

def cal(sov_sort):
    p=0
    l=len(sov_sort)
    left=0
    right=0
    while  p < 90 :
        if p==0:
            if l % 2 ==0:
                left=( l /2)
                right=( l / 2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
            else:
                left=math.floor( l /2)
                right=math.ceil(l/2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
        else:
            if left % 2 !=0:
                left = math.ceil(left/2)
            else:
                left = left /2
            
            dist = l - right +1
            if dist % 2 !=0:
                right = right + math.ceil(dist/2)
            else:
                right = right + (dist /2)
                
            p= round((((right -left +1) / l) * 100),2)
             
    return left-1,right-1





print(df_medium_time_mem_feature_df.shape)
medium_time_mem_easy = df_medium_time_mem_feature_df[df_medium_time_mem_feature_df['label_mem']== 'easy']
print(medium_time_mem_easy.shape)
prior_easy= round(medium_time_mem_easy.shape[0] / df_medium_time_mem_feature_df.shape[0] ,5)

main_dict_mtme={}
main_dict_mtme_iqr={}

for i in range(len(cols_list)):
    if cols_list[i] in top_n_2:
        tmp_dict={}
        tmp_dict_iqr={}
        
        feature = cols_list[i]
        print("feature name= ",feature)
        
        sov=np.array(medium_time_mem_easy[feature])
        sov_1=(np.sort(sov))
        skew=scipy.stats.skew(sov)
        
        tmp_dict["skew"]=round(skew,4)
        tmp_dict_iqr["skew"]=round(skew,4)
        
        if -2 < skew and skew < 2:
        
            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            tmp_dict["min"]=round(np.min(sov),9)
            tmp_dict["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)

            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict["mean"]=round(mean,9)
            tmp_dict["std"]=round(std,9)
            #gamma=2
            #rx= mean - ((1- prior_easy) *gamma * std) 
            #ry= mean + ((1- prior_easy) *gamma * std)
            l_weight=0.5
            r_weight=0.5
            rx= mean - (l_weight* std) 
            ry= mean + (r_weight* std)
            print("left range= ",rx,"right range= ", ry)
            tmp_dict["lw_i"]=round(l_weight,9)
            tmp_dict["rw_i"]=round(r_weight,9)
            tmp_dict["lr"]=round(rx,9)
            tmp_dict["rr"]=round(ry,9)

            min_val_w= round(((mean - min_val) /std ),9)
            max_val_w= round(((max_val - mean) /std ),9)
            tmp_dict["min_val_w"]=round(min_val_w,9)
            tmp_dict["max_val_w"]=round(max_val_w,9)


            val=(((rx <= sov) & (sov <= ry)).sum()) / medium_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")

            tmp_dict["percent_i"]=round(v,9)
            tmp_dict["percent_change"]=round(v,9)

            val=(((min_val <= sov) & (sov <= mean)).sum()) / medium_time_mem_easy.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / medium_time_mem_easy.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            tmp_dict["percent_l"]=percent_l
            tmp_dict["percent_r"]=percent_r
            
            main_dict_mtme[feature]=tmp_dict
            
        else:
            
            left_index,right_index = cal(sov_1)
            rx=sov_1[int(left_index)]
            ry=sov_1[int(right_index)]

            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            
            tmp_dict_iqr["min"]=round(np.min(sov),9)
            tmp_dict_iqr["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)
            
            
            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict_iqr["mean"]=round(mean,9)
            tmp_dict_iqr["std"]=round(std,9)
            
            print("left range= ",rx,"right range= ", ry)
            tmp_dict_iqr["lw_i"]="null"
            tmp_dict_iqr["rw_i"]="null"
            tmp_dict_iqr["lr"]=round(rx,9)
            tmp_dict_iqr["rr"]=round(ry,9)
            
            
            tmp_dict_iqr["min_val_w"]="null"
            tmp_dict_iqr["max_val_w"]="null"
            tmp_dict_iqr["percent_i"]="null"
            val=(((rx <= sov) & (sov <= ry)).sum()) / medium_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")
            tmp_dict_iqr["percent_change"]=round(v,9)
            
            val=(((min_val <= sov) & (sov <= mean)).sum()) / medium_time_mem_easy.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / medium_time_mem_easy.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            
            tmp_dict_iqr["percent_l"]=percent_l
            tmp_dict_iqr["percent_r"]=percent_r
            
            main_dict_mtme_iqr[feature]=tmp_dict_iqr

            
            # 68–95–99.7 rule, also known as the empirical rule

            # for 1*std
            left = mean - std
            right = mean + std
            val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 1 standard deviation= ",v,"%")
            
            # for 2*std
            left = mean - (2*std)
            right = mean + (2*std)
            val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 2 standard deviation= ",v,"%")
            


            # for 3*std
            left = mean - (3*std)
            right = mean + (3*std)
            val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 3 standard deviation= ",v,"%")
            
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        
        print("skewness value= ",skew)
        
        
        #plotting graph for to see the skewess
        sov_1=np.sort(sov)
        fig, ax1 = plt.subplots(1, 1, figsize=(8,5))
        ax1.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$, $ skew = \\approx {round(skew,3)}$')
        ax1.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        ax1.set_title("Normal Fit")
        ax1.set_xlabel(feature)
        ax1.set_ylabel("pdf")
        ax1.legend()
        #plt.savefig('/home/ritam/ontology_hardness/normal_curve_&_skewness/medium_time_easy_memory/'+str(feature)+'.jpeg')
        plt.show()
        
        # Quantile-Quantile Plot Nomrality check
        print("\nQQ plot \n")
        qqplot(sov_1, line='s')
        plt.show()
        
        #Statistical Normality Tests
        # Shapiro-Wilk Test
        print("\n Shapiro-Wilk Test \n ")
        #stat, p = shapiro(sov_1[0:4998])
        stat, p = normaltest(sov_1)
        print('Statistics=%.3f, p=%f' % (stat, p))
        
        alpha = 0.05
        if p > alpha:
            print('Sample looks Gaussian (fail to reject H0)')
        else:
            print('Sample does not look Gaussian (reject H0)')
            
        print("ends normality test ------------------------------------------------\n")
        
        


# In[263]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

import copy

main_dict_cal_mtme={}
dec_rate=[(0.9,0.9)]
for i in range(len(dec_rate)):
 
    main_dict_cal_mtme=copy.deepcopy(main_dict_mtme)
    lower_r=dec_rate[i][0]
    high_r=dec_rate[i][1]
    for j in range (100):
        c=0
        for key,value in main_dict_cal_mtme.items():
            
            if value["percent_change"] < 90:
                
                lw_i_temp=value["lw_i"]
                rw_i_temp=value["rw_i"]
                if value["percent_l"] >= 50:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(lower_r * diff, 9)  
                    
                    lw_i_temp= round(lw_i_temp + frac , 9)
                    main_dict_cal_mtme[key]["lw_i"]=lw_i_temp
                else:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(high_r * diff, 9)  
                    
                    lw_i_temp= round(lw_i_temp + frac ,9 )
                    main_dict_cal_mtme[key]["lw_i"]=lw_i_temp

                if value["percent_r"] >= 50:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(lower_r * diff, 9)  
                    
                    rw_i_temp= round(rw_i_temp + frac , 9 )
                    main_dict_cal_mtme[key]["rw_i"]=rw_i_temp
                else:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(high_r * diff, 9)  
                    
                    rw_i_temp= round(rw_i_temp + frac ,9 )
                    main_dict_cal_mtme[key]["rw_i"]=rw_i_temp

                sov=np.array(medium_time_mem_easy[key])
                rx= round(value["mean"] - (lw_i_temp* value["std"]) ,9)
                ry= round(value["mean"] + (rw_i_temp* value["std"]),9)
                main_dict_cal_mtme[key]["lr"]=round(rx,9)
                main_dict_cal_mtme[key]["rr"]=round(ry,9)
                
                val=(((rx <= sov) & (sov <= ry)).sum()) / medium_time_mem_easy.shape[0]
                v =  round((val * 100 ),2)
                main_dict_cal_mtme[key]["percent_change"]=v

                if v >=85:
                    c=c+1
                
            else:
                c=c+1
        p=round(c / len(list(main_dict_cal_mtme.keys())),2)
        print ("percent in loop ",j , "covers ", p , "%" )
        


# In[264]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

main_dict_cal_mtme
print(len(main_dict_cal_mtme),len(main_dict_mtme_iqr))
mtme_pd=pd.DataFrame(main_dict_cal_mtme)
mtme_iqr_pd=pd.DataFrame(main_dict_mtme_iqr)

frames = [mtme_pd,mtme_iqr_pd]
result = pd.concat(frames,axis=1)
result
#result.to_csv("mtme_ranges.csv")


# In[265]:


medium_time_mem_easy # contians 14 ontologies in this category


# In[266]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#


#after medium time and memory clusering ,
#collect the medium ones

from matplotlib import pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')
#%matplotlib qt 
import scipy
from scipy.stats import norm
from statsmodels.graphics.gofplots import qqplot
import statistics
from scipy.stats import shapiro
from scipy.stats import normaltest
import math
#interquratile range 
def cal(sov_sort):
    p=0
    l=len(sov_sort)
    print(l)
    left=0
    right=0
    while  p < 90 :
        if p==0:
            if l % 2 ==0:
                left=( l /2)
                right=( l / 2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
            else:
                left=math.floor( l /2)
                right=math.ceil(l/2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
        else:
            if left % 2 !=0:
                left = math.ceil(left/2)
            else:
                left = left /2
            
            dist = l - right +1
            if dist % 2 !=0:
                right = right + math.ceil(dist/2)
            else:
                right = right + (dist /2)
                
            p= round((((right -left +1) / l) * 100),2)
             
        
    return left-1,right-1



print(df_medium_time_mem_feature_df.shape)
medium_time_mem_medium = df_medium_time_mem_feature_df[df_medium_time_mem_feature_df['label_mem']== 'medium']
print(medium_time_mem_medium.shape)
prior_medium= round(medium_time_mem_medium.shape[0] / df_medium_time_mem_feature_df.shape[0] ,5)

main_dict_mtmm={}
main_dict_mtmm_iqr={}

for i in range(len(cols_list)):
    if cols_list[i] in top_n_2:
        tmp_dict={}
        tmp_dict_iqr={}
        
        feature = cols_list[i]
        print("feature name= ",feature)
        
        sov=np.array(medium_time_mem_medium[feature])
        sov_1=(np.sort(sov))
        skew=scipy.stats.skew(sov)
        
        tmp_dict["skew"]=round(skew,4)
        tmp_dict_iqr["skew"]=round(skew,4)
        
        if -2 < skew and skew < 2:
        
            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            tmp_dict["min"]=round(np.min(sov),9)
            tmp_dict["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)

            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict["mean"]=round(mean,9)
            tmp_dict["std"]=round(std,9)
            #gamma=2
            #rx= mean - ((1- prior_medium) *gamma * std) 
            #ry= mean + ((1- prior_medium) *gamma * std)
            
            l_weight=0.5
            r_weight=0.5
            rx= mean - (l_weight* std) 
            ry= mean + (r_weight* std)
            print("left range= ",rx,"right range= ", ry)
            tmp_dict["lw_i"]=round(l_weight,9)
            tmp_dict["rw_i"]=round(r_weight,9)
            tmp_dict["lr"]=round(rx,9)
            tmp_dict["rr"]=round(ry,9)

            min_val_w= round(((mean - min_val) /std ),9)
            max_val_w= round(((max_val - mean) /std ),9)
            tmp_dict["min_val_w"]=round(min_val_w,9)
            tmp_dict["max_val_w"]=round(max_val_w,9)


            val=(((rx <= sov) & (sov <= ry)).sum()) / medium_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")

            tmp_dict["percent_i"]=round(v,9)
            tmp_dict["percent_change"]=round(v,9)

            val=(((min_val <= sov) & (sov <= mean)).sum()) / medium_time_mem_medium.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / medium_time_mem_medium.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            tmp_dict["percent_l"]=percent_l
            tmp_dict["percent_r"]=percent_r
            
            main_dict_mtmm[feature]=tmp_dict
            
        else:
            
            left_index,right_index = cal(sov_1)
            rx=sov_1[int(left_index)]
            ry=sov_1[int(right_index)]

            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            
            tmp_dict_iqr["min"]=round(np.min(sov),9)
            tmp_dict_iqr["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)
            
            
            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict_iqr["mean"]=round(mean,9)
            tmp_dict_iqr["std"]=round(std,9)
            
            print("left range= ",rx,"right range= ", ry)
            tmp_dict_iqr["lw_i"]="null"
            tmp_dict_iqr["rw_i"]="null"
            tmp_dict_iqr["lr"]=round(rx,9)
            tmp_dict_iqr["rr"]=round(ry,9)
            
            
            tmp_dict_iqr["min_val_w"]="null"
            tmp_dict_iqr["max_val_w"]="null"
            tmp_dict_iqr["percent_i"]="null"
            val=(((rx <= sov) & (sov <= ry)).sum()) / medium_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")
            tmp_dict_iqr["percent_change"]=round(v,9)
            
            val=(((min_val <= sov) & (sov <= mean)).sum()) / medium_time_mem_medium.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / medium_time_mem_medium.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
        
            
            tmp_dict_iqr["percent_l"]=percent_l
            tmp_dict_iqr["percent_r"]=percent_r
            
            main_dict_mtmm_iqr[feature]=tmp_dict_iqr

            
            # 68–95–99.7 rule, also known as the empirical rule

            # for 1*std
            left = mean - std
            right = mean + std
            val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 1 standard deviation= ",v,"%")
            
            # for 2*std
            left = mean - (2*std)
            right = mean + (2*std)
            val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 2 standard deviation= ",v,"%")
            


            # for 3*std
            left = mean - (3*std)
            right = mean + (3*std)
            val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 3 standard deviation= ",v,"%")
            
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        
        print("skewness value= ",skew)
        
        
        
        #plotting graph for to see the skewess
        sov_1=np.sort(sov)
        fig, ax1 = plt.subplots(1, 1, figsize=(8,5))
        ax1.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$, $ skew = \\approx {round(skew,3)}$')
        ax1.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        ax1.set_title("Normal Fit")
        ax1.set_xlabel(feature)
        ax1.set_ylabel("pdf")
        ax1.legend()
        #plt.savefig('/home/ritam/ontology_hardness/normal_curve_&_skewness/medium_time_medium_memory/'+str(feature)+'.jpeg')
        plt.show()
        
        # Quantile-Quantile Plot Nomrality check
        print("\nQQ plot \n")
        qqplot(sov_1, line='s')
        plt.show()
        
        #Statistical Normality Tests
        # Shapiro-Wilk Test
        print("\n Shapiro-Wilk Test \n ")
        #stat, p = shapiro(sov_1[0:4998])
        stat, p = normaltest(sov_1)
        print('Statistics=%.3f, p=%f' % (stat, p))
        
        alpha = 0.05
        if p > alpha:
            print('Sample looks Gaussian (fail to reject H0)')
        else:
            print('Sample does not look Gaussian (reject H0)')
            
        print("ends normality test ------------------------------------------------\n")
        
        


# In[267]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

import copy

main_dict_cal_mtmm={}
dec_rate=[(0.9,0.9)]
for i in range(len(dec_rate)):
 
    main_dict_cal_mtmm=copy.deepcopy(main_dict_mtmm)
    lower_r=dec_rate[i][0]
    high_r=dec_rate[i][1]
    for j in range (100):
        c=0
        for key,value in main_dict_cal_mtmm.items():
            
            if value["percent_change"] < 90:
                
                lw_i_temp=value["lw_i"]
                rw_i_temp=value["rw_i"]
                if value["percent_l"] >= 50:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(lower_r * diff, 9)  
                    
                    lw_i_temp= round(lw_i_temp + frac , 9)
                    main_dict_cal_mtmm[key]["lw_i"]=lw_i_temp
                else:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(high_r * diff, 9)  
                    
                    lw_i_temp= round(lw_i_temp + frac ,9 )
                    main_dict_cal_mtmm[key]["lw_i"]=lw_i_temp

                if value["percent_r"] >= 50:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(lower_r * diff, 9)  
                    
                    rw_i_temp= round(rw_i_temp + frac , 9 )
                    main_dict_cal_mtmm[key]["rw_i"]=rw_i_temp
                else:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(high_r * diff, 9)  
                    
                    rw_i_temp= round(rw_i_temp + frac ,9 )
                    main_dict_cal_mtmm[key]["rw_i"]=rw_i_temp

                sov=np.array(medium_time_mem_medium[key])
                rx= round(value["mean"] - (lw_i_temp* value["std"]) ,9)
                ry= round(value["mean"] + (rw_i_temp* value["std"]),9)
                main_dict_cal_mtmm[key]["lr"]=round(rx,9)
                main_dict_cal_mtmm[key]["rr"]=round(ry,9)
                #print(rx,ry)

                val=(((rx <= sov) & (sov <= ry)).sum()) / medium_time_mem_medium.shape[0]
                v =  round((val * 100 ),2)
                main_dict_cal_mtmm[key]["percent_change"]=v

                if v >=85:
                    c=c+1
                
            else:
                c=c+1
        p=round(c / len(list(main_dict_cal_mtmm.keys())),2)
        print ("percent in loop ",j , "covers ", p , "%" )
        


# In[268]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

main_dict_cal_mtmm
print(len(main_dict_cal_mtmm),len(main_dict_mtmm_iqr))
mtmm_pd=pd.DataFrame(main_dict_cal_mtmm)
mtmm_iqr_pd=pd.DataFrame(main_dict_mtmm_iqr)

frames = [mtmm_pd,mtmm_iqr_pd]
result = pd.concat(frames,axis=1)
result
#result.to_csv("mtmm_ranges.csv")


# In[269]:


medium_time_mem_medium  #  contains 38 ontoogies in this category


# In[270]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#


#after medium time and memory clusering ,
#collect the hard ones

from matplotlib import pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')
#%matplotlib qt 
import scipy
from scipy.stats import norm
from statsmodels.graphics.gofplots import qqplot
import statistics
from scipy.stats import shapiro
from scipy.stats import normaltest
import math
#interquratile range 
def cal(sov_sort):
    p=0
    l=len(sov_sort)
    left=0
    right=0
    while  p < 90 :
        if p==0:
            if l % 2 ==0:
                left=( l /2)
                right=( l / 2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
            else:
                left=math.floor( l /2)
                right=math.ceil(l/2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
        else:
            if left % 2 !=0:
                left = math.ceil(left/2)
            else:
                left = left /2
            
            dist = l - right +1
            if dist % 2 !=0:
                right = right + math.ceil(dist/2)
            else:
                right = right + (dist /2)
                
            p= round((((right -left +1) / l) * 100),2)
             
        
    return left-1,right-1





medium_time_mem_hard = df_medium_time_mem_feature_df[df_medium_time_mem_feature_df['label_mem']== 'hard']
print(medium_time_mem_hard.shape)
prior_hard= round(medium_time_mem_hard.shape[0] / df_medium_time_mem_feature_df.shape[0] ,5)

main_dict_mtmh={}
main_dict_mtmh_iqr={}

for i in range(len(cols_list)):
    if cols_list[i] in top_n_2:
        tmp_dict={}
        tmp_dict_iqr={}
        
        feature = cols_list[i]
        print("feature name= ",feature)
        
        sov=np.array(medium_time_mem_hard[feature])
        sov_1=(np.sort(sov))
        print("feature values= ",sov)
        skew=scipy.stats.skew(sov)
        
        tmp_dict["skew"]=round(skew,4)
        tmp_dict_iqr["skew"]=round(skew,4)
        
        if -2 < skew and skew < 2:
        
            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            tmp_dict["min"]=round(np.min(sov),9)
            tmp_dict["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)

            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict["mean"]=round(mean,9)
            tmp_dict["std"]=round(std,9)
            #gamma=2
            #rx= mean - ((1- prior_hard) *gamma * std) 
            #ry= mean + ((1- prior_hard) *gamma * std)
            
            l_weight=0.5
            r_weight=0.5
            rx= mean - (l_weight* std) 
            ry= mean + (r_weight* std)
            print("left range= ",rx,"right range= ", ry)
            tmp_dict["lw_i"]=round(l_weight,9)
            tmp_dict["rw_i"]=round(r_weight,9)
            tmp_dict["lr"]=round(rx,9)
            tmp_dict["rr"]=round(ry,9)

            min_val_w= round(((mean - min_val) /std ),9)
            max_val_w= round(((max_val - mean) /std ),9)
            print(min_val_w,max_val_w)
            tmp_dict["min_val_w"]=round(min_val_w,9)
            tmp_dict["max_val_w"]=round(max_val_w,9)


            val=(((rx <= sov) & (sov <= ry)).sum()) / medium_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")

            tmp_dict["percent_i"]=round(v,9)
            tmp_dict["percent_change"]=round(v,9)

            val=(((min_val <= sov) & (sov <= mean)).sum()) / medium_time_mem_hard.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / medium_time_mem_hard.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            tmp_dict["percent_l"]=percent_l
            tmp_dict["percent_r"]=percent_r
            
            main_dict_mtmh[feature]=tmp_dict
            
        else:
            
            left_index,right_index = cal(sov_1)
            rx=sov_1[int(left_index)]
            ry=sov_1[int(right_index)]

            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            
            tmp_dict_iqr["min"]=round(np.min(sov),9)
            tmp_dict_iqr["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)
            
            
            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict_iqr["mean"]=round(mean,9)
            tmp_dict_iqr["std"]=round(std,9)
            
            print("left range= ",rx,"right range= ", ry)
            tmp_dict_iqr["lw_i"]="null"
            tmp_dict_iqr["rw_i"]="null"
            tmp_dict_iqr["lr"]=round(rx,9)
            tmp_dict_iqr["rr"]=round(ry,9)
            
            
            tmp_dict_iqr["min_val_w"]="null"
            tmp_dict_iqr["max_val_w"]="null"
            tmp_dict_iqr["percent_i"]="null"
            val=(((rx <= sov) & (sov <= ry)).sum()) / medium_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")
            tmp_dict_iqr["percent_change"]=round(v,9)
            
            val=(((min_val <= sov) & (sov <= mean)).sum()) / medium_time_mem_hard.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / medium_time_mem_hard.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            
            tmp_dict_iqr["percent_l"]=percent_l
            tmp_dict_iqr["percent_r"]=percent_r
            
            main_dict_mtmh_iqr[feature]=tmp_dict_iqr

            
            # 68–95–99.7 rule, also known as the empirical rule

            # for 1*std
            left = mean - std
            right = mean + std
            val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 1 standard deviation= ",v,"%")
            
            # for 2*std
            left = mean - (2*std)
            right = mean + (2*std)
            val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 2 standard deviation= ",v,"%")
            


            # for 3*std
            left = mean - (3*std)
            right = mean + (3*std)
            val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 3 standard deviation= ",v,"%")
            
            
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        
        print("skewness value= ",skew)
        
        
        
        #plotting graph for to see the skewess
        sov_1=np.sort(sov)
        fig, ax1 = plt.subplots(1, 1, figsize=(8,5))
        ax1.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$, $ skew = \\approx {round(skew,3)}$')
        ax1.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        ax1.set_title("Normal Fit")
        ax1.set_xlabel(feature)
        ax1.set_ylabel("pdf")
        ax1.legend()
        #plt.savefig('/home/ritam/ontology_hardness/normal_curve_&_skewness/medium_time_hard_memory/'+str(feature)+'.jpeg')
        plt.show()
        
        # Quantile-Quantile Plot Nomrality check
        print("\nQQ plot \n")
        qqplot(sov_1, line='s')
        plt.show()
        
        #try the below test when you have more than 8 ontologies
        ''''
        #Statistical Normality Tests
        # Shapiro-Wilk Test
        print("\n Shapiro-Wilk Test \n ")
        #stat, p = shapiro(sov_1[0:4998])
        stat, p = normaltest(sov_1)
        print('Statistics=%.3f, p=%f' % (stat, p))
        
        alpha = 0.05
        if p > alpha:
            print('Sample looks Gaussian (fail to reject H0)')
        else:
            print('Sample does not look Gaussian (reject H0)')
        '''   
        print("ends normality test ------------------------------------------------\n")
        
        


# In[271]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

import copy

main_dict_cal_mtmh={}
dec_rate=[(0.9,0.9)]
for i in range(len(dec_rate)):
 
    main_dict_cal_mtmh=copy.deepcopy(main_dict_mtmh)
    lower_r=dec_rate[i][0]
    high_r=dec_rate[i][1]
    for j in range (100):
        c=0
        for key,value in main_dict_cal_mtmh.items():
            
            if value["percent_change"] < 90:
                
                lw_i_temp=value["lw_i"]
                rw_i_temp=value["rw_i"]
                if value["percent_l"] >= 50:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(lower_r * diff, 9)  
                    
                    lw_i_temp= round(lw_i_temp + frac , 9)
                    main_dict_cal_mtmh[key]["lw_i"]=lw_i_temp
                else:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(high_r * diff, 9)  
                    
                    lw_i_temp= round(lw_i_temp + frac ,9 )
                    main_dict_cal_mtmh[key]["lw_i"]=lw_i_temp

                if value["percent_r"] >= 50:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(lower_r * diff, 9)  
                    
                    rw_i_temp= round(rw_i_temp + frac , 9 )
                    main_dict_cal_mtmh[key]["rw_i"]=rw_i_temp
                else:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(high_r * diff, 9)  
                    
                    rw_i_temp= round(rw_i_temp + frac ,9 )
                    main_dict_cal_mtmh[key]["rw_i"]=rw_i_temp

                sov=np.array(medium_time_mem_hard[key])
                rx= round(value["mean"] - (lw_i_temp* value["std"]) ,9)
                ry= round(value["mean"] + (rw_i_temp* value["std"]),9)
                main_dict_cal_mtmh[key]["lr"]=round(rx,9)
                main_dict_cal_mtmh[key]["rr"]=round(ry,9)
                
                val=(((rx <= sov) & (sov <= ry)).sum()) / medium_time_mem_hard.shape[0]
                v =  round((val * 100 ),2)
                main_dict_cal_mtmh[key]["percent_change"]=v

                if v >=85:
                    c=c+1
                
            else:
                c=c+1
        p=round(c / len(list(main_dict_cal_mtmh.keys())),2)
        print ("percent in loop ",j , "covers ", p , "%" )
        


# In[272]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

main_dict_cal_mtmh
print(len(main_dict_cal_mtmh),len(main_dict_mtmh_iqr))
mtmh_pd=pd.DataFrame(main_dict_cal_mtmh)
mtmh_iqr_pd=pd.DataFrame(main_dict_mtmh_iqr)

frames = [mtmh_pd,mtmh_iqr_pd]
result = pd.concat(frames,axis=1)
result
#result.to_csv("mtmh_ranges.csv")


# In[273]:


medium_time_mem_hard # contains 4 ontologies in this category


# In[274]:


#**** follow the same notation as explained above in  wherever required*******#

# collect the hard ones
df_hard=partial_dataframe[partial_dataframe['label_time']=='hard']
df_hard_drop=df_hard # contains based on time
df_hard_drop


# In[275]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

# now hard time ontologies clustered on memory
t=df_hard_drop.drop(['file','label_time'],axis=1)
scaler = MinMaxScaler()
scaler.fit(t)
t_df_scaled=scaler.transform(t)

t_MinMax = pd.DataFrame(data=t_df_scaled, columns=['size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])
t=t_MinMax

print(t.shape)
km_3 = KMeans(n_clusters=3,random_state=0)
y_predicted_3=km_3.fit_predict(t[["consistency_size_kb"]])
print(y_predicted_3)
t['cluster']=y_predicted_3

t1=t[t['cluster']==0]
t2=t[t['cluster']==1]
t3=t[t['cluster']==2]

plt.scatter(t1["consistency_time_milsecs"],t1["consistency_size_kb"],c='black')
plt.scatter(t2["consistency_time_milsecs"],t2["consistency_size_kb"],c='green')
plt.scatter(t3["consistency_time_milsecs"],t3["consistency_size_kb"],c='red')
plt.xlabel('time_in_milisecs')
plt.ylabel('size_in_kb')
plt.show()


# In[276]:


#**** follow the same notation as explained above in  wherever required*******#


label_col=[]
for i in range((y_predicted_3.shape[0])):
    if y_predicted_3[i]==0:
        label_col.append('medium')
    elif y_predicted_3[i]==1:
        label_col.append('easy')
    else:
        label_col.append('hard')
        
df_hard_drop['label_mem']=label_col
df_hard_time_mem=df_hard_drop # contains first based on time and now on memory
df_hard_time_mem


# In[277]:


#**** follow the same notation as explained above  wherever required*******#


total_df= pd.read_csv('final_new_more.csv') # for features collection 
print("size of final_new_more= ",total_df.shape)

df_hard_time_mem_feature=[]
for i in range(df_hard_time_mem.shape[0]):
    ont=df_hard_time_mem.iloc[i,:]['file']
    lab=df_hard_time_mem.iloc[i,:]['label_mem']
    o=(np.array(total_df[total_df['file']==ont]))
    if o.shape[0]!=0:
    
        u=list(np.array(total_df[total_df['file']==ont])[0])
        u.append(str(lab))
        df_hard_time_mem_feature.append(u)

    
total_df_cols=list(total_df.columns)
total_df_cols.append('label_mem')
df_hard_time_mem_feature_df=pd.DataFrame(data=df_hard_time_mem_feature,columns=total_df_cols)
df_hard_time_mem_feature_df  # contains 6 ontologies in total in hard time category


# In[278]:


#**** follow the same notation as explained above  wherever required*******#

# hard time with easy mem, medium mem and hard mem
l=df_hard_time_mem_feature_df.drop('file',axis=1)
x=l.groupby(l['label_mem']).agg(['min', 'max','mean'])
#x.to_csv('hard_time_feature_details.csv',index_label=True)
x_hard = x
x_hard


# In[279]:


#**** follow the same notation as explained above wherever required*******#


from sklearn import preprocessing
from sklearn.ensemble import RandomForestClassifier
import operator
le = preprocessing.LabelEncoder()
p=df_hard_time_mem_feature_df
le.fit(p['label_mem'])
g=le.transform(p['label_mem']) 
x_train_np=p.drop(['file','label_mem'],axis=1)
y_train_np=np.array(g)


model = RandomForestClassifier(n_estimators = 100, random_state = 2)
model.feature_names = list(x_train_np.columns.values)
model.fit(x_train_np, y_train_np)
ft=list(model.feature_names)
importance = model.feature_importances_
print(model.n_features_)

dict1_imp={}
c=0
for i,v in enumerate(importance):
    val= round(v,5)
    dict1_imp[ft[c]]=val
    c+=1
dict1_imp_sort=dict(sorted(dict1_imp.items(), key=operator.itemgetter(1),reverse=True))

for key,val in dict1_imp_sort.items():
    print(key,val)
plt.bar([x for x in range(len(importance))], importance)
plt.show()


hard_feature_sort_dic=dict1_imp_sort

top_n_3=(list(hard_feature_sort_dic.keys())[:50])


# In[280]:


d= dict(x_hard)
hard_zero_features =[]
for key, value in dict1_imp_sort.items():
    if value == 0.0:
        if ( sum(list(d[(key, 'mean')]))/3 )== 0.0 :
            #print(key,list(d[(key, 'mean')]))
            hard_zero_features.append(key)
print(hard_zero_features)
print(top_n_3)


# In[281]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

#after hard time and memory clusering ,
#collect the easy ones

from matplotlib import pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')
#%matplotlib qt 
import scipy
from scipy.stats import norm
from statsmodels.graphics.gofplots import qqplot
import statistics
from scipy.stats import shapiro
from scipy.stats import normaltest
import math
#interquratile range 
def cal(sov_sort):
    p=0
    l=len(sov_sort)
    left=0
    right=0
    while  p < 90 :
        if p==0:
            if l % 2 ==0:
                left=( l /2)
                right=( l / 2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
            else:
                left=math.floor( l /2)
                right=math.ceil(l/2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
        else:
            if left % 2 !=0:
                left = math.ceil(left/2)
            else:
                left = left /2
            
            dist = l - right +1
            if dist % 2 !=0:
                right = right + math.ceil(dist/2)
            else:
                right = right + (dist /2)
                
            p= round((((right -left +1) / l) * 100),2)
             
        
    return left-1,right-1





print(df_hard_time_mem_feature_df.shape)
hard_time_mem_easy = df_hard_time_mem_feature_df[df_hard_time_mem_feature_df['label_mem']== 'easy']
print(hard_time_mem_easy.shape)
prior_easy= round(hard_time_mem_easy.shape[0] / df_hard_time_mem_feature_df.shape[0] ,5)

main_dict_htme={}
main_dict_htme_iqr={}

for i in range(len(cols_list)):
    if cols_list[i] in top_n_3:
        tmp_dict={}
        tmp_dict_iqr={}
        
        feature = cols_list[i]
        print("feature name= ",feature)
        
        sov=np.array(hard_time_mem_easy[feature])
        sov_1=(np.sort(sov))
        skew=scipy.stats.skew(sov)
        
        tmp_dict["skew"]=round(skew,4)
        tmp_dict_iqr["skew"]=round(skew,4)
        
        if -2 < skew and skew < 2:
        
            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            tmp_dict["min"]=round(np.min(sov),9)
            tmp_dict["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)

            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict["mean"]=round(mean,9)
            tmp_dict["std"]=round(std,9)
            #gamma=2
            #rx= mean - ((1- prior_easy) *gamma * std) 
            #ry= mean + ((1- prior_easy) *gamma * std)
            l_weight=0.5
            r_weight=0.5
            rx= mean - (l_weight* std) 
            ry= mean + (r_weight* std)
            print("left range= ",rx,"right range= ", ry)
            tmp_dict["lw_i"]=round(l_weight,9)
            tmp_dict["rw_i"]=round(r_weight,9)
            tmp_dict["lr"]=round(rx,9)
            tmp_dict["rr"]=round(ry,9)

            min_val_w= round(((mean - min_val) /std ),9)
            max_val_w= round(((max_val - mean) /std ),9)
            print(min_val_w,max_val_w)
            if math.isnan(min_val_w):
                    tmp_dict["min_val_w"]="null"
            else:
                    tmp_dict["min_val_w"]=round(min_val_w,9)
            
            if math.isnan(max_val_w):
                    tmp_dict["max_val_w"]="null"
            else:
                tmp_dict["max_val_w"]=round(max_val_w,9)


            val=(((rx <= sov) & (sov <= ry)).sum()) / hard_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")

            tmp_dict["percent_i"]=round(v,9)
            tmp_dict["percent_change"]=round(v,9)

            val=(((min_val <= sov) & (sov <= mean)).sum()) / hard_time_mem_easy.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / hard_time_mem_easy.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            tmp_dict["percent_l"]=percent_l
            tmp_dict["percent_r"]=percent_r
            
            main_dict_htme[feature]=tmp_dict
            
        else:
            
            left_index,right_index = cal(sov_1)
            rx=sov_1[int(left_index)]
            ry=sov_1[int(right_index)]

            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            
            tmp_dict_iqr["min"]=round(np.min(sov),9)
            tmp_dict_iqr["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)
            
            
            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict_iqr["mean"]=round(mean,9)
            tmp_dict_iqr["std"]=round(std,9)
            
            print("left range= ",rx,"right range= ", ry)
            tmp_dict_iqr["lw_i"]="null"
            tmp_dict_iqr["rw_i"]="null"
            tmp_dict_iqr["lr"]=round(rx,9)
            tmp_dict_iqr["rr"]=round(ry,9)
            
            
            tmp_dict_iqr["min_val_w"]="null"
            tmp_dict_iqr["max_val_w"]="null"
            tmp_dict_iqr["percent_i"]="null"
            val=(((rx <= sov) & (sov <= ry)).sum()) / hard_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")
            tmp_dict_iqr["percent_change"]=round(v,9)
            
            val=(((min_val <= sov) & (sov <= mean)).sum()) / hard_time_mem_easy.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / hard_time_mem_easy.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            
            tmp_dict_iqr["percent_l"]=percent_l
            tmp_dict_iqr["percent_r"]=percent_r
            
            main_dict_htme_iqr[feature]=tmp_dict_iqr

            
            # 68–95–99.7 rule, also known as the empirical rule

            # for 1*std
            left = mean - std
            right = mean + std
            val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 1 standard deviation= ",v,"%")
            
            # for 2*std
            left = mean - (2*std)
            right = mean + (2*std)
            val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 2 standard deviation= ",v,"%")
            


            # for 3*std
            left = mean - (3*std)
            right = mean + (3*std)
            val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_easy.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 3 standard deviation= ",v,"%")
            
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        
        print("skewness value= ",skew)
        
        
        
        #plotting graph for to see the skewess
        sov_1=np.sort(sov)
        fig, ax1 = plt.subplots(1, 1, figsize=(8,5))
        ax1.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$, $ skew = \\approx {round(skew,3)}$')
        ax1.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        ax1.set_title("Normal Fit")
        ax1.set_xlabel(feature)
        ax1.set_ylabel("pdf")
        ax1.legend()
        #plt.savefig('/home/ritam/ontology_hardness/normal_curve_&_skewness/hard_time_easy_memory/'+str(feature)+'.jpeg')
        plt.show()
        
        # Quantile-Quantile Plot Nomrality check
        print("\nQQ plot \n")
        qqplot(sov_1, line='s')
        plt.show()
        ''''
        #Statistical Normality Tests
        # Shapiro-Wilk Test
        print("\n Shapiro-Wilk Test \n ")
        #stat, p = shapiro(sov_1[0:4998])
        stat, p = normaltest(sov_1)
        print('Statistics=%.3f, p=%f' % (stat, p))
        
        alpha = 0.05
        if p > alpha:
            print('Sample looks Gaussian (fail to reject H0)')
        else:
            print('Sample does not look Gaussian (reject H0)')
        '''   
        print("ends normality test ------------------------------------------------\n")
        
        


# In[282]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#


import copy

main_dict_cal_htme={}
dec_rate=[(0.9,0.9)]
for i in range(len(dec_rate)):
 
    main_dict_cal_htme=copy.deepcopy(main_dict_htme)
    lower_r=dec_rate[i][0]
    high_r=dec_rate[i][1]
    for j in range (100):
        c=0
        for key,value in main_dict_cal_htme.items():
            
            if value["percent_change"] < 90:
                
                lw_i_temp=value["lw_i"]
                rw_i_temp=value["rw_i"]
                if value["percent_l"] >= 50:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(lower_r * diff, 9)  
                    
                    lw_i_temp= round(lw_i_temp + frac , 9)
                    main_dict_cal_htme[key]["lw_i"]=lw_i_temp
                else:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(high_r * diff, 9)  
                    
                    lw_i_temp= round(lw_i_temp + frac ,9 )
                    main_dict_cal_htme[key]["lw_i"]=lw_i_temp

                if value["percent_r"] >= 50:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(lower_r * diff, 9)  
                    
                    rw_i_temp= round(rw_i_temp + frac , 9 )
                    main_dict_cal_htme[key]["rw_i"]=rw_i_temp
                else:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(high_r * diff, 9)  
                    
                    rw_i_temp= round(rw_i_temp + frac ,9 )
                    main_dict_cal_htme[key]["rw_i"]=rw_i_temp

                sov=np.array(hard_time_mem_easy[key])
                rx= round(value["mean"] - (lw_i_temp* value["std"]) ,9)
                ry= round(value["mean"] + (rw_i_temp* value["std"]),9)
                main_dict_cal_htme[key]["lr"]=round(rx,9)
                main_dict_cal_htme[key]["rr"]=round(ry,9)
                
                val=(((rx <= sov) & (sov <= ry)).sum()) / hard_time_mem_easy.shape[0]
                v =  round((val * 100 ),2)
                main_dict_cal_htme[key]["percent_change"]=v

                if v >=85:
                    c=c+1
                #break
            else:
                c=c+1
        p=round(c / len(list(main_dict_cal_htme.keys())),2)
        print ("percent in loop ",j , "covers ", p , "%" )
        


# In[283]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

main_dict_cal_htme
print(len(main_dict_cal_htme),len(main_dict_htme_iqr))
htme_pd=pd.DataFrame(main_dict_cal_htme)
htme_iqr_pd=pd.DataFrame(main_dict_htme_iqr)

frames = [htme_pd,htme_iqr_pd]
result = pd.concat(frames,axis=1)
result
#result.to_csv("htme_ranges.csv")


# In[284]:


hard_time_mem_easy  # contains 4 ontologies in this category


# In[285]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#


#after hard time and memory clusering ,
#collect the medium ones

from matplotlib import pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')
#%matplotlib qt 
import scipy
from scipy.stats import norm
from statsmodels.graphics.gofplots import qqplot
import statistics
from scipy.stats import shapiro
from scipy.stats import normaltest
import math
#interquratile range 
def cal(sov_sort):
    p=0
    l=len(sov_sort)
    left=0
    right=0
    while  p < 90 :
        if p==0:
            if l % 2 ==0:
                left=( l /2)
                right=( l / 2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
            else:
                left=math.floor( l /2)
                right=math.ceil(l/2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
        else:
            if left % 2 !=0:
                left = math.ceil(left/2)
            else:
                left = left /2
            
            dist = l - right +1
            if dist % 2 !=0:
                right = right + math.ceil(dist/2)
            else:
                right = right + (dist /2)
                
            p= round((((right -left +1) / l) * 100),2)
             
        
    return left-1,right-1





print(df_hard_time_mem_feature_df.shape)
hard_time_mem_medium= df_hard_time_mem_feature_df[df_hard_time_mem_feature_df['label_mem']== 'medium']
print(hard_time_mem_medium.shape)
prior_medium= round(hard_time_mem_medium.shape[0] / df_hard_time_mem_feature_df.shape[0] ,5)

main_dict_htmm={}
main_dict_htmm_iqr={}

for i in range(len(cols_list)):
    if cols_list[i] in top_n_3:
        tmp_dict={}
        tmp_dict_iqr={}
        
        feature = cols_list[i]
        print("feature name= ",feature)
        
        sov=np.array(hard_time_mem_medium[feature])
        sov_1=(np.sort(sov))
        skew=scipy.stats.skew(sov)
        
        tmp_dict["skew"]=round(skew,4)
        tmp_dict_iqr["skew"]=round(skew,4)
        
        if -2 < skew and skew < 2:
        
            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            tmp_dict["min"]=round(np.min(sov),9)
            tmp_dict["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)

            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict["mean"]=round(mean,9)
            tmp_dict["std"]=round(std,9)
            #gamma=2
            #rx= mean - ((1- prior_medium) *gamma * std) 
            #ry= mean + ((1- prior_medium) *gamma * std)
            l_weight=0.5
            r_weight=0.5
            rx= mean - (l_weight* std) 
            ry= mean + (r_weight* std)
            print("left range= ",rx,"right range= ", ry)
            tmp_dict["lw_i"]=round(l_weight,9)
            tmp_dict["rw_i"]=round(r_weight,9)
            tmp_dict["lr"]=round(rx,9)
            tmp_dict["rr"]=round(ry,9)

            min_val_w= round(((mean - min_val) /std ),9)
            max_val_w= round(((max_val - mean) /std ),9)
            print(min_val_w,max_val_w)
            if math.isnan(min_val_w):
                    tmp_dict["min_val_w"]="null"
            else:
                    tmp_dict["min_val_w"]=round(min_val_w,9)
            
            if math.isnan(max_val_w):
                    tmp_dict["max_val_w"]="null"
            else:
                tmp_dict["max_val_w"]=round(max_val_w,9)


            val=(((rx <= sov) & (sov <= ry)).sum()) / hard_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")

            tmp_dict["percent_i"]=round(v,9)
            tmp_dict["percent_change"]=round(v,9)

            val=(((min_val <= sov) & (sov <= mean)).sum()) / hard_time_mem_medium.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / hard_time_mem_medium.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            tmp_dict["percent_l"]=percent_l
            tmp_dict["percent_r"]=percent_r
            
            main_dict_htmm[feature]=tmp_dict
            
        else:
            
            left_index,right_index = cal(sov_1)
            rx=sov_1[int(left_index)]
            ry=sov_1[int(right_index)]

            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            
            tmp_dict_iqr["min"]=round(np.min(sov),9)
            tmp_dict_iqr["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)
            
            
            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict_iqr["mean"]=round(mean,9)
            tmp_dict_iqr["std"]=round(std,9)
            
            print("left range= ",rx,"right range= ", ry)
            tmp_dict_iqr["lw_i"]="null"
            tmp_dict_iqr["rw_i"]="null"
            tmp_dict_iqr["lr"]=round(rx,9)
            tmp_dict_iqr["rr"]=round(ry,9)
            
            
            tmp_dict_iqr["min_val_w"]="null"
            tmp_dict_iqr["max_val_w"]="null"
            tmp_dict_iqr["percent_i"]="null"
            val=(((rx <= sov) & (sov <= ry)).sum()) / hard_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")
            tmp_dict_iqr["percent_change"]=round(v,9)
            
            val=(((min_val <= sov) & (sov <= mean)).sum()) / hard_time_mem_medium.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / hard_time_mem_medium.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            
            tmp_dict_iqr["percent_l"]=percent_l
            tmp_dict_iqr["percent_r"]=percent_r
            
            main_dict_htmm_iqr[feature]=tmp_dict_iqr

            
            # 68–95–99.7 rule, also known as the empirical rule

            # for 1*std
            left = mean - std
            right = mean + std
            val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 1 standard deviation= ",v,"%")
            
            # for 2*std
            left = mean - (2*std)
            right = mean + (2*std)
            val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 2 standard deviation= ",v,"%")
            


            # for 3*std
            left = mean - (3*std)
            right = mean + (3*std)
            val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_medium.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 3 standard deviation= ",v,"%")
            
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        
        print("skewness value= ",skew)
        
        
        
        #plotting graph for to see the skewess
        sov_1=np.sort(sov)
        fig, ax1 = plt.subplots(1, 1, figsize=(8,5))
        ax1.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$, $ skew = \\approx {round(skew,3)}$')
        ax1.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        ax1.set_title("Normal Fit")
        ax1.set_xlabel(feature)
        ax1.set_ylabel("pdf")
        ax1.legend()
        #plt.savefig('/home/ritam/ontology_hardness/normal_curve_&_skewness/hard_time_medium_memory/'+str(feature)+'.jpeg')
        plt.show()
        
        # Quantile-Quantile Plot Nomrality check
        print("\nQQ plot \n")
        qqplot(sov_1, line='s')
        plt.show()
        ''''
        #Statistical Normality Tests
        # Shapiro-Wilk Test
        print("\n Shapiro-Wilk Test \n ")
        #stat, p = shapiro(sov_1[0:4998])
        stat, p = normaltest(sov_1)
        print('Statistics=%.3f, p=%f' % (stat, p))
        
        alpha = 0.05
        if p > alpha:
            print('Sample looks Gaussian (fail to reject H0)')
        else:
            print('Sample does not look Gaussian (reject H0)')
        '''   
        print("ends normality test ------------------------------------------------\n")
        
        


# In[286]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

import copy

main_dict_cal_htmm={}
dec_rate=[(0.9,0.9)]
for i in range(len(dec_rate)):
 
    main_dict_cal_htmm=copy.deepcopy(main_dict_htmm)
    lower_r=dec_rate[i][0]
    high_r=dec_rate[i][1]
    for j in range (100):
        c=0
        for key,value in main_dict_cal_htmm.items():
            
            if value["percent_change"] < 90:

                lw_i_temp=value["lw_i"]
                rw_i_temp=value["rw_i"]
                if value["percent_l"] >= 50:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(lower_r * diff, 9)  

                    lw_i_temp= round(lw_i_temp + frac , 9)
                    main_dict_cal_htmm[key]["lw_i"]=lw_i_temp
                else:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(high_r * diff, 9)  

                    lw_i_temp= round(lw_i_temp + frac ,9 )
                    main_dict_cal_htmm[key]["lw_i"]=lw_i_temp

                if value["percent_r"] >= 50:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(lower_r * diff, 9)  

                    rw_i_temp= round(rw_i_temp + frac , 9 )
                    main_dict_cal_htmm[key]["rw_i"]=rw_i_temp
                else:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(high_r * diff, 9)  

                    rw_i_temp= round(rw_i_temp + frac ,9 )
                    main_dict_cal_htmm[key]["rw_i"]=rw_i_temp

                sov=np.array(hard_time_mem_medium[key])
                rx= round(value["mean"] - (lw_i_temp* value["std"]) ,9)
                ry= round(value["mean"] + (rw_i_temp* value["std"]),9)
                main_dict_cal_htmm[key]["lr"]=round(rx,9)
                main_dict_cal_htmm[key]["rr"]=round(ry,9)

                val=(((rx <= sov) & (sov <= ry)).sum()) / hard_time_mem_medium.shape[0]
                v =  round((val * 100 ),2)
                main_dict_cal_htmm[key]["percent_change"]=v

                if v >=85:
                    c=c+1
            else:
                c=c+1
        p=round(c / len(list(main_dict_cal_htmm.keys())),2)
        print ("percent in loop ",j , "covers ", p , "%" )


# In[287]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

main_dict_cal_htmm
print(len(main_dict_cal_htmm),len(main_dict_htmm_iqr))
htmm_pd=pd.DataFrame(main_dict_cal_htmm)
htmm_iqr_pd=pd.DataFrame(main_dict_htmm_iqr)

frames = [htmm_pd,htmm_iqr_pd]
result = pd.concat(frames,axis=1)
result
#result.to_csv("htmm_ranges.csv")


# In[288]:


hard_time_mem_medium  # contains 1 ontology in this category


# In[289]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#


#after hard time and memory clusering ,
#collect the hard ones

from matplotlib import pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')
#%matplotlib qt 
import scipy
from scipy.stats import norm
from statsmodels.graphics.gofplots import qqplot
import statistics
from scipy.stats import shapiro
from scipy.stats import normaltest
import math
#interquratile range 
def cal(sov_sort):
    p=0
    l=len(sov_sort)
    left=0
    right=0
    while  p < 90 :
        if p==0:
            if l % 2 ==0:
                left=( l /2)
                right=( l / 2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
            else:
                left=math.floor( l /2)
                right=math.ceil(l/2) + 1
                p= round((((right -left +1) / l) * 100),2)
                
        else:
            if left % 2 !=0:
                left = math.ceil(left/2)
            else:
                left = left /2
            
            dist = l - right +1
            if dist % 2 !=0:
                right = right + math.ceil(dist/2)
            else:
                right = right + (dist /2)
                
            p= round((((right -left +1) / l) * 100),2)
        
    return left-1,right-1





print(df_hard_time_mem_feature_df.shape)
hard_time_mem_hard= df_hard_time_mem_feature_df[df_hard_time_mem_feature_df['label_mem']== 'hard']
print(hard_time_mem_hard.shape)
prior_hard= round(hard_time_mem_hard.shape[0] / df_hard_time_mem_feature_df.shape[0] ,5)

main_dict_htmh={}
main_dict_htmh_iqr={}

for i in range(len(cols_list)):
    if cols_list[i] in top_n_3:
        tmp_dict={}
        tmp_dict_iqr={}
        
        feature = cols_list[i]
        print("feature name= ",feature)
        
        sov=np.array(hard_time_mem_hard[feature])
        sov_1=(np.sort(sov))
        skew=scipy.stats.skew(sov)
        
        tmp_dict["skew"]=round(skew,4)
        tmp_dict_iqr["skew"]=round(skew,4)
        
        if -2 < skew and skew < 2:
        
            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            tmp_dict["min"]=round(np.min(sov),9)
            tmp_dict["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)

            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict["mean"]=round(mean,9)
            tmp_dict["std"]=round(std,9)
            #gamma=2
            #rx= mean - ((1- prior_hard) *gamma * std) 
            #ry= mean + ((1- prior_hard) *gamma * std)
            l_weight=0.5
            r_weight=0.5
            rx= mean - (l_weight* std) 
            ry= mean + (r_weight* std)
            print("left range= ",rx,"right range= ", ry)
            tmp_dict["lw_i"]=round(l_weight,9)
            tmp_dict["rw_i"]=round(r_weight,9)
            tmp_dict["lr"]=round(rx,9)
            tmp_dict["rr"]=round(ry,9)

            min_val_w= round(((mean - min_val) /std ),9)
            max_val_w= round(((max_val - mean) /std ),9)
            print(min_val_w,max_val_w)
            if math.isnan(min_val_w):
                    tmp_dict["min_val_w"]="null"
            else:
                    tmp_dict["min_val_w"]=round(min_val_w,9)
            
            if math.isnan(max_val_w):
                    tmp_dict["max_val_w"]="null"
            else:
                tmp_dict["max_val_w"]=round(max_val_w,9)


            val=(((rx <= sov) & (sov <= ry)).sum()) / hard_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")

            tmp_dict["percent_i"]=round(v,9)
            tmp_dict["percent_change"]=round(v,9)

            val=(((min_val <= sov) & (sov <= mean)).sum()) / hard_time_mem_hard.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / hard_time_mem_hard.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            tmp_dict["percent_l"]=percent_l
            tmp_dict["percent_r"]=percent_r
            
            main_dict_htmh[feature]=tmp_dict
            
        else:
            
            left_index,right_index = cal(sov_1)
            rx=sov_1[int(left_index)]
            ry=sov_1[int(right_index)]

            print("max value = ",np.max(sov),"min value = ",np.min(sov))
            
            tmp_dict_iqr["min"]=round(np.min(sov),9)
            tmp_dict_iqr["max"]=round(np.max(sov),9)
            min_val=np.min(sov)
            max_val=np.max(sov)
            
            
            mean = np.mean(sov)
            std = np.std(sov)
            print("mean= ",mean,"standard deviation= ",std)
            tmp_dict_iqr["mean"]=round(mean,9)
            tmp_dict_iqr["std"]=round(std,9)
            
            print("left range= ",rx,"right range= ", ry)
            tmp_dict_iqr["lw_i"]="null"
            tmp_dict_iqr["rw_i"]="null"
            tmp_dict_iqr["lr"]=round(rx,9)
            tmp_dict_iqr["rr"]=round(ry,9)
            
            
            tmp_dict_iqr["min_val_w"]="null"
            tmp_dict_iqr["max_val_w"]="null"
            tmp_dict_iqr["percent_i"]="null"
            val=(((rx <= sov) & (sov <= ry)).sum()) / hard_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within this range= ",v,"%")
            tmp_dict_iqr["percent_change"]=round(v,9)
            
            val=(((min_val <= sov) & (sov <= mean)).sum()) / hard_time_mem_hard.shape[0]
            percent_l =  round((val * 100 ),2)

            val=(((mean <= sov) & (sov <= max_val)).sum()) / hard_time_mem_hard.shape[0]
            percent_r =  round((val * 100 ),2)

            print("percent left and right= ",percent_l,percent_r)
            
            tmp_dict_iqr["percent_l"]=percent_l
            tmp_dict_iqr["percent_r"]=percent_r
            
            main_dict_htmh_iqr[feature]=tmp_dict_iqr


            # 68–95–99.7 rule, also known as the empirical rule

            # for 1*std
            left = mean - std
            right = mean + std
            val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 1 standard deviation= ",v,"%")

            # for 2*std
            left = mean - (2*std)
            right = mean + (2*std)
            val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 2 standard deviation= ",v,"%")



            # for 3*std
            left = mean - (3*std)
            right = mean + (3*std)
            val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_hard.shape[0]
            v =  round((val * 100 ),2)
            print("data covered within 3 standard deviation= ",v,"%")
  
            
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        
        print("skewness value= ",skew)
        
        
        
        #plotting graph for to see the skewess
        sov_1=np.sort(sov)
        fig, ax1 = plt.subplots(1, 1, figsize=(8,5))
        ax1.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$, $ skew = \\approx {round(skew,3)}$')
        ax1.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        ax1.set_title("Normal Fit")
        ax1.set_xlabel(feature)
        ax1.set_ylabel("pdf")
        ax1.legend()
        #plt.savefig('/home/ritam/ontology_hardness/normal_curve_&_skewness/hard_time_hard_memory/'+str(feature)+'.jpeg')
        plt.show()
        
        # Quantile-Quantile Plot Nomrality check
        print("\nQQ plot \n")
        qqplot(sov_1, line='s')
        plt.show()
        ''''
        #Statistical Normality Tests
        # Shapiro-Wilk Test
        print("\n Shapiro-Wilk Test \n ")
        #stat, p = shapiro(sov_1[0:4998])
        stat, p = normaltest(sov_1)
        print('Statistics=%.3f, p=%f' % (stat, p))
        
        alpha = 0.05
        if p > alpha:
            print('Sample looks Gaussian (fail to reject H0)')
        else:
            print('Sample does not look Gaussian (reject H0)')
        '''   
        print("ends normality test ------------------------------------------------\n")
        
        


# In[290]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

import copy

main_dict_cal_htmh={}
dec_rate=[(0.9,0.9)]
for i in range(len(dec_rate)):
 
    main_dict_cal_htmh=copy.deepcopy(main_dict_htmh)
    lower_r=dec_rate[i][0]
    high_r=dec_rate[i][1]
    for j in range (100):
        c=0
        for key,value in main_dict_cal_htmh.items():
            
            if value["percent_change"] < 90:

                lw_i_temp=value["lw_i"]
                rw_i_temp=value["rw_i"]
                if value["percent_l"] >= 50:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(lower_r * diff, 9)  

                    lw_i_temp= round(lw_i_temp + frac , 9)
                    main_dict_cal_htmh[key]["lw_i"]=lw_i_temp
                else:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(high_r * diff, 9)  

                    lw_i_temp= round(lw_i_temp + frac ,9 )
                    main_dict_cal_htmh[key]["lw_i"]=lw_i_temp

                if value["percent_r"] >= 50:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(lower_r * diff, 9)  

                    rw_i_temp= round(rw_i_temp + frac , 9 )
                    main_dict_cal_htmh[key]["rw_i"]=rw_i_temp
                else:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(high_r * diff, 9)  

                    rw_i_temp= round(rw_i_temp + frac ,9 )
                    main_dict_cal_htmh[key]["rw_i"]=rw_i_temp

                sov=np.array(hard_time_mem_hard[key])
                rx= round(value["mean"] - (lw_i_temp* value["std"]) ,9)
                ry= round(value["mean"] + (rw_i_temp* value["std"]),9)
                main_dict_cal_htmh[key]["lr"]=round(rx,9)
                main_dict_cal_htmh[key]["rr"]=round(ry,9)

                val=(((rx <= sov) & (sov <= ry)).sum()) / hard_time_mem_hard.shape[0]
                v =  round((val * 100 ),2)
                main_dict_cal_htmh[key]["percent_change"]=v

                if v >=85:
                    c=c+1
            else:
                c=c+1
        p=round(c / len(list(main_dict_cal_htmh.keys())),2)
        print ("percent in loop ",j , "covers ", p , "%" )


# In[291]:


#**** follow the same notation as explained above in 'easy time easy memory' category wherever required*******#

main_dict_cal_htmh
print(len(main_dict_cal_htmh),len(main_dict_htmh_iqr))
htmh_pd=pd.DataFrame(main_dict_cal_htmh)
htmh_iqr_pd=pd.DataFrame(main_dict_htmh_iqr)

frames = [htmh_pd,htmh_iqr_pd]
result = pd.concat(frames,axis=1)
result
#result.to_csv("htmh_ranges.csv")


# In[292]:


hard_time_mem_hard #contains 1 ontolgy in this category


# In[293]:


#--------------this ends the range calculation ------------------------------


# In[294]:


x_easy


# In[295]:


#(easy_feature_sort_dic)
#medium_feature_sort_dic
#hard_feature_sort_dic
# =====   this section finds the intersection of top n features from easy time ,medium time and hard time category

n=50

easy_top_n_features=dict(list(easy_feature_sort_dic.items())[:n])  # top n features of easy time ontologies with 
                                                                   # their importance value
medium_top_n_features=dict(list(medium_feature_sort_dic.items())[:n]) # top n features of medium time ontologies
hard_top_n_features=dict(list(hard_feature_sort_dic.items())[:n]) # top n features of hard time ontologies

easy_key=list(easy_top_n_features.keys())
medium_key=list(medium_top_n_features.keys())
hard_key=list(hard_top_n_features.keys())

def intersection(lst1, lst2):
    return list(set(lst1) & set(lst2))
  
inter1 = (intersection(easy_key, medium_key))
print(len(inter1))

inter2 = (intersection(inter1, hard_key))
print("No of common features of first 3 categories= ",len(inter2))


main=[]
rows=[]
for i in range(len(inter2)):
    f=inter2[i]
    rows.append(f)
    tmp=[]
    l1=list(x_easy[f]['mean'])
    for j in range(len(l1)):
        tmp.append(round(l1[j],4))
    
    l2=list(x_medium[f]['mean'])
    for j in range(len(l2)):
        tmp.append(round(l2[j],4))
    
    l3=list(x_hard[f]['mean'])
    for j in range(len(l3)):
        tmp.append(round(l3[j],4))
    
    main.append(tmp)
    


# In[296]:


print(len(main))
cols=['easy_time_easy_mem','easy_time_hard_mem','easy_time_medium_mem','medium_time_easy_mem','medium_time_hard_mem',
      'medium_time_medium_mem','hard_time_easy_mem','hard_time_hard_mem','hard_time_medium_mem']
top_n_mean_feature=pd.DataFrame(data=main,columns=cols,index=rows)
top_n_mean_feature   # this contains common features with their mean values in each of the 9 category
#top_n_mean_feature.to_csv("common_features.csv") 


# In[297]:


''''
import pickle
p1 = open("easy_time_mem_easy","wb")
pickle.dump(easy_time_mem_easy, p1)
p1.close()

p2 = open("easy_time_mem_medium","wb")
pickle.dump(easy_time_mem_medium, p2)
p2.close()

p3 = open("easy_time_mem_hard","wb")
pickle.dump(easy_time_mem_hard, p3)
p3.close()

p4 = open("medium_time_mem_easy","wb")
pickle.dump(medium_time_mem_easy, p4)
p4.close()

p5 = open("medium_time_mem_medium","wb")
pickle.dump(medium_time_mem_medium, p5)
p5.close()

p6 = open("medium_time_mem_hard","wb")
pickle.dump(medium_time_mem_hard, p6)
p6.close()

p7 = open("hard_time_mem_easy","wb")
pickle.dump(hard_time_mem_easy, p7)
p7.close()

p8 = open("hard_time_mem_medium","wb")
pickle.dump(hard_time_mem_medium, p8)
p8.close()

p9 = open("hard_time_mem_hard","wb")
pickle.dump(hard_time_mem_hard, p9)
p9.close()

p10 = open("rows","wb")
pickle.dump(rows, p10)
p10.close()
 
'''


# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:




