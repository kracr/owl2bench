#!/usr/bin/env python
# coding: utf-8

# In[31]:


import numpy as np
import pandas as pd
import os 
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import MinMaxScaler
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
get_ipython().run_line_magic('matplotlib', 'inline')


# In[32]:


dir_list=sorted(os.listdir("/home/ritam/ontology_hardness/dataset"))
print(dir_list)


# In[33]:


l=0
final_df=pd.DataFrame(columns=['size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])

full_dataframe = pd.DataFrame(columns=['file','size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])


for i in range(len(dir_list)):
    if dir_list[i][0]=='f':
        x=dir_list[i]
        #print(x)
        path="/home/ritam/ontology_hardness/dataset/"+x
        z=pd.read_csv(path)
        #print(z.columns)
        z_drop=z.drop(['file'],axis=1)
        #print(z_drop)
        final_df=pd.concat([final_df,z_drop])
        
        full_dataframe=pd.concat([full_dataframe,z])
        #print(final_df)
        #if l==1:
        #    break
        #l=l+1

print("total ontologies with time and memrory")
print(full_dataframe.shape) # contains the file name and all other
print(final_df.shape) #donot contains the file name and all other


# In[34]:


#print(final_df)
final_np=np.array(final_df)
#print(final_np)
scaler = MinMaxScaler()
scaler.fit(final_df)
final_df_scaled=scaler.transform(final_df)
#print(final_df_scaled)
df_MinMax = pd.DataFrame(data=final_df_scaled, columns=['size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])
df_MinMax


# In[35]:


#print(df_MinMax)
#consistency_size_kb
#t=np.max(final_df[0:10000]["consistency_size_kb"])
#print(t)
#d=np.max(final_df[0:10000]["consistency_time_milsecs"])
#print(d)
#16433

partial_dataframe=full_dataframe.iloc[0:16433,:]  # contains non scaled values
print(partial_dataframe.shape)
t=df_MinMax.head(16433)  # contains scaled values
print(t.shape)

#t=df_MinMax
#print(final_df["consistency_time_milsecs"])
#print(t)
#plt.scatter(t["consistency_time_milsecs"],t["consistency_size_kb"],c='black')
#plt.xlabel('time_in_milisecs')
#plt.ylabel('size_in_kb')
#plt.show()
#print(np.max(final_df[2065:2070])[])
#print(final_df[final_df["consistency_time_milsecs"]>=1000])


# In[36]:


# cluster based on time  into easy, medium and hard
#t_np=np.array(t)
#print(t_np)
km = KMeans(n_clusters=3,random_state=0)
print(km)
#y_predicted=km.fit_predict(t[["consistency_time_milsecs","consistency_size_kb"]])
y_predicted=km.fit_predict(t[["consistency_time_milsecs"]])
print(y_predicted)
print(type(t))
t['cluster']=y_predicted
#print(t)
t1=t[t['cluster']==0]
t2=t[t['cluster']==1]
t3=t[t['cluster']==2]

plt.scatter(t1["consistency_time_milsecs"],t1["consistency_size_kb"],c='black')
plt.scatter(t2["consistency_time_milsecs"],t2["consistency_size_kb"],c='green')
plt.scatter(t3["consistency_time_milsecs"],t3["consistency_size_kb"],c='red')
#plt.scatter(km.cluster_centers_[:,0],km.cluster_centers_[:,1],color='yellow',marker='.',label='centroid')
plt.xlabel('time_in_milisecs')
plt.ylabel('size_in_kb')
plt.show()


# In[37]:


label_col=[]
for i in range((y_predicted.shape[0])):
    if y_predicted[i]==0:
        label_col.append('easy')
    elif y_predicted[i]==1:
        label_col.append('hard')
    else:
        label_col.append('medium')
        
partial_dataframe['label_time']=label_col
#full_dataframe_hard_medium=partial_dataframe[partial_dataframe['label']!='easy']
#full_dataframe_hard_medium=full_dataframe_hard_medium[full_dataframe_hard_medium['label']!='hard']
#full_dataframe_hard_medium


# In[38]:


partial_dataframe # contains only time and memeory


# In[39]:


# collect the easy ones based on time

df_easy=partial_dataframe[partial_dataframe['label_time']=='easy']
#df_easy_drop=df_easy.drop(['label'],axis=1)
df_easy_drop=df_easy # contains based on time
df_easy_drop


# In[40]:


print(df_easy['consistency_time_milsecs'].max(),df_easy['consistency_time_milsecs'].min())
print(df_easy.shape)


# In[41]:


# now easy time ontologies clustered on memory
t=df_easy_drop.drop(['file','label_time'],axis=1)
#print(df_easy_drop.shape)
#print(t.columns)
scaler = MinMaxScaler()
scaler.fit(t)
t_df_scaled=scaler.transform(t)
#print(t_df_scaled)

t_MinMax = pd.DataFrame(data=t_df_scaled, columns=['size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])
t=t_MinMax

print(t.shape)
#t_np=np.array(t)
#print(t_np)
km_1 = KMeans(n_clusters=3,random_state=0)
print(km_1)
y_predicted_1=km_1.fit_predict(t[["consistency_size_kb"]])
print(y_predicted_1)
#print(type(t))
t['cluster']=y_predicted_1
#print(t)
t1=t[t['cluster']==0]
t2=t[t['cluster']==1]
t3=t[t['cluster']==2]

plt.scatter(t1["consistency_time_milsecs"],t1["consistency_size_kb"],c='black')
plt.scatter(t2["consistency_time_milsecs"],t2["consistency_size_kb"],c='green')
plt.scatter(t3["consistency_time_milsecs"],t3["consistency_size_kb"],c='red')
#plt.scatter(km.cluster_centers_[:,0],km.cluster_centers_[:,1],color='yellow',marker='.',label='centroid')
plt.xlabel('time_in_milisecs')
plt.ylabel('size_in_kb')
plt.show()
#t_MinMax


# In[42]:


label_col=[]
for i in range((y_predicted_1.shape[0])):
    if y_predicted_1[i]==0:
        label_col.append('easy')
    elif y_predicted_1[i]==1:
        label_col.append('hard')
    else:
        label_col.append('medium')
        
df_easy_drop['label_mem']=label_col
df_easy_time_mem=df_easy_drop # contains first based on time and now on memory
df_easy_time_mem


# In[43]:


total_df= pd.read_csv('final_new_more.csv') # for features collection 
print("size of final_new_more= ",total_df.shape)

df_easy_time_mem_feature=[]
for i in range(df_easy_time_mem.shape[0]):
    ont=df_easy_time_mem.iloc[i,:]['file']
    lab=df_easy_time_mem.iloc[i,:]['label_mem']
    #print(ont,lab)
    o=(np.array(total_df[total_df['file']==ont]))
    #print(o.shape)
    if o.shape[0]!=0:
    
        u=list(np.array(total_df[total_df['file']==ont])[0])
        u.append(str(lab))
        #print(u)
        df_easy_time_mem_feature.append(u)

    #break

total_df_cols=list(total_df.columns)
total_df_cols.append('label_mem')
#print(total_df_cols)
df_easy_time_mem_feature_df=pd.DataFrame(data=df_easy_time_mem_feature,columns=total_df_cols)
df_easy_time_mem_feature_df  # total easy time ontologies with easy , medium , hard memory and all features


# In[44]:


from scipy.integrate import quad
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

get_ipython().run_line_magic('matplotlib', 'inline')

sns.boxplot(x='label_mem', y='consistency_size_kb', data=df_easy_time_mem_feature_df)


# In[ ]:





# In[45]:


print(df_easy['consistency_time_milsecs'].max(),df_easy['consistency_time_milsecs'].min())
print(df_easy_time_mem_feature_df['consistency_time_milsecs'].max(),df_easy_time_mem_feature_df['consistency_time_milsecs'].min())
print(df_easy['consistency_size_kb'].max(),df_easy['consistency_size_kb'].min())
print(df_easy_time_mem_feature_df['consistency_size_kb'].max(),df_easy_time_mem_feature_df['consistency_size_kb'].min())
(df_easy_time_mem_feature_df[df_easy_time_mem_feature_df['consistency_size_kb']== 21437240])


# In[46]:


# easy time with easy mem, medium mem and hard mem
l=df_easy_time_mem_feature_df.drop('file',axis=1)
x=l.groupby(l['label_mem']).agg(['min', 'max','mean'])
#x.to_csv('easy_time_mem_feature_16315.csv',index_label=True)
x
x_easy=x
x_easy


# In[47]:


m=total_df.drop('file',axis=1)
y=m.agg(['min', 'max','mean'])
#y.to_csv('total_df_mean.csv',index_label=True)

y


# In[48]:



from sklearn import preprocessing
from sklearn.ensemble import RandomForestClassifier
import operator
le = preprocessing.LabelEncoder()
p=df_easy_time_mem_feature_df
le.fit(p['label_mem'])
g=le.transform(p['label_mem']) 
print(g)
x_train_np=p.drop(['file','label_mem'],axis=1)
print(x_train_np.shape)
y_train_np=np.array(g)


model = RandomForestClassifier(n_estimators = 100, random_state = 2)
model.feature_names = list(x_train_np.columns.values)
model.fit(x_train_np, y_train_np)
ft=list(model.feature_names)
importance = model.feature_importances_
print(model.n_features_)
print(ft,type(ft))

dict1_imp={}
c=0
for i,v in enumerate(importance):
    #print('Feature: %0d, Score: %.5f' % (i,v))
    val= round(v,5)
    #print(val,type(val),ft[c])
    dict1_imp[ft[c]]=val
    c+=1
#print(dict1_imp)
dict1_imp_sort=dict(sorted(dict1_imp.items(), key=operator.itemgetter(1),reverse=True))

for key,val in dict1_imp_sort.items():
    print(key,val)
plt.bar([x for x in range(len(importance))], importance)
plt.show()

easy_feature_sort_dic=dict1_imp_sort


top_n_1=(list(easy_feature_sort_dic.keys())[:30])


# In[49]:



d= dict(x_easy)
easy_zero_features =[]
for key, value in dict1_imp_sort.items():
    if value == 0.0:
        if ( sum(list(d[(key, 'mean')]))/3 )== 0.0 :
            print(key,list(d[(key, 'mean')]))
            easy_zero_features.append(key)
print(easy_zero_features)

print(top_n_1)


# In[50]:


#proccessing easy time with easy memory with 108 features

cols_list=list(df_easy_time_mem_feature_df.columns)
print(cols_list,len(cols_list))


# In[51]:


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

easy_zero_features.append('file')
easy_zero_features.append('label_mem')


#print(easy_zero_features)
print(df_easy_time_mem_feature_df.shape)
easy_time_mem_easy = df_easy_time_mem_feature_df[df_easy_time_mem_feature_df['label_mem']== 'easy']
print(easy_time_mem_easy.shape)
prior_easy= round(easy_time_mem_easy.shape[0] / df_easy_time_mem_feature_df.shape[0] ,5)

main_table=[]

for i in range(len(cols_list)):
    #if cols_list[i] not in easy_zero_features:
    if cols_list[i] in top_n_1:
        tmp_table=[]
        
        feature = cols_list[i]
        print("feature name= ",feature)
        tmp_table.append(feature)
        
        sov=np.array(easy_time_mem_easy[feature])
        print("feature values= ",sov)
        print("max value = ",np.max(sov),"min value = ",np.min(sov))
        tmp_table.append(round(np.min(sov),5))
        tmp_table.append(round(np.max(sov),5))
        
        mean = np.mean(sov)
        std = np.std(sov)
        print("mean= ",mean,"standard deviation= ",std)
        tmp_table.append(round(mean,5))
        tmp_table.append(round(std,5))
        #gamma=2
        #rx= mean - ((1- prior_easy) *gamma * std) 
        #ry= mean + ((1- prior_easy) *gamma * std)
        #rx= mean - (0.05*(gamma * std) )
        #ry= mean + (0.5*(gamma * std))
        l_weight=0.25
        r_weight=2
        rx= mean - (l_weight* std) 
        ry= mean + (r_weight* std)
        print("left range= ",rx,"right range= ", ry)
        
        val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_easy.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within this range= ",v,"%")
        
        tmp_table.append(l_weight)
        tmp_table.append(r_weight)
        tmp_table.append(rx)
        tmp_table.append(ry)
        tmp_table.append(v)
        
        # 68–95–99.7 rule, also known as the empirical rule
        
        # for 1*std
        left = mean - std
        right = mean + std
        val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_easy.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 1 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        # for 2*std
        left = mean - (2*std)
        right = mean + (2*std)
        val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_easy.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 2 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        main_table.append(tmp_table)
        
        # for 3*std
        left = mean - (3*std)
        right = mean + (3*std)
        val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_easy.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 3 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        skew=scipy.stats.skew(sov)
        print("skewness value= ",skew)
        
        
        #sov_1=(sov-mean)/std
        sov_1=np.sort(sov)
        plt.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$')
        plt.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        plt.title("Normal Fit")
        plt.xlabel(feature)
        plt.ylabel("pdf")
        plt.legend()
        #plt.savefig('/home/ritam/ontology_hardness/images/'+str(feature)+'.jpeg')
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
        
        #print((sov_1[0:4500]))
        #break


# In[52]:


cols=['f_name','min_value','max_value','mean','std','l_weight','r_weight','left_range','right_range', 'data_cover%','1_std%','2_std%','3_std%']
easy_time_easy_mem_table=pd.DataFrame(data=main_table,columns=cols)
print(easy_time_easy_mem_table.shape)
#easy_time_easy_mem_table.to_csv('easy_time_easy_mem_table.csv',index=False)
#easy_time_mem_easy.to_csv('easy_time_mem_easy.csv',index=False)


# In[53]:


easy_time_mem_easy


# In[173]:


#after easy time and memory clusering ,
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

easy_zero_features.append('file')
easy_zero_features.append('label_mem')


#print(easy_zero_features)
print(df_easy_time_mem_feature_df.shape)
easy_time_mem_medium = df_easy_time_mem_feature_df[df_easy_time_mem_feature_df['label_mem']== 'medium']
print(easy_time_mem_medium.shape)
prior_medium= round(easy_time_mem_medium.shape[0] / df_easy_time_mem_feature_df.shape[0] ,5)

main_table=[]
main_dict={}

for i in range(len(cols_list)):
    #if cols_list[i] not in easy_zero_features:
    if cols_list[i] in top_n_1:
        tmp_table=[]
        tmp_dict={}
        
        
        feature = cols_list[i]
        print("feature name= ",feature)
        tmp_table.append(feature)
        
        sov=np.array(easy_time_mem_medium[feature])
        print("feature values= ",sov)
        
        
        print("max value = ",np.max(sov),"min value = ",np.min(sov))
        tmp_table.append(round(np.min(sov),5))
        tmp_table.append(round(np.max(sov),5))
        tmp_dict["min"]=np.min(sov)
        tmp_dict["max"]=np.max(sov)
        min_val=np.min(sov)
        max_val=np.max(sov)
        
        mean = np.mean(sov)
        std = np.std(sov)
        print("mean= ",mean,"standard deviation= ",std)
        tmp_table.append(round(mean,5))
        tmp_table.append(round(std,5))
        tmp_dict["mean"]=mean
        tmp_dict["std"]=std
        #gamma=2
        #rx= mean - ((1- prior_medium) *gamma * std) 
        #ry= mean + ((1- prior_medium) *gamma * std)
        #rx= mean - (0.05*(gamma * std) )
        #ry= mean + (0.5*(gamma * std))
        l_weight=0.5
        r_weight=0.5
        rx= mean - (l_weight* std) 
        ry= mean + (r_weight* std)
        print("left range= ",rx,"right range= ", ry)
        tmp_dict["lw_i"]=l_weight
        tmp_dict["rw_i"]=r_weight
        tmp_dict["lr"]=rx
        tmp_dict["rr"]=ry
        
        min_val_w= round(((mean - min_val) /std ),4)
        max_val_w= round(((max_val - mean) /std ),4)
        print(min_val_w,max_val_w)
        tmp_dict["min_val_w"]=min_val_w
        tmp_dict["max_val_w"]=max_val_w
        
        
        val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within this range= ",v,"%")
        
        tmp_table.append(l_weight)
        tmp_table.append(r_weight)
        tmp_table.append(rx)
        tmp_table.append(ry)
        tmp_table.append(v)
        tmp_dict["percent_i"]=v
        tmp_dict["percent_change"]=v
        
        val=(((min_val <= sov) & (sov <= mean)).sum()) / easy_time_mem_medium.shape[0]
        percent_l =  round((val * 100 ),2)
        
        val=(((mean <= sov) & (sov <= max_val)).sum()) / easy_time_mem_medium.shape[0]
        percent_r =  round((val * 100 ),2)
        
        print("percent left and right= ",percent_l,percent_r)
        tmp_dict["percent_l"]=percent_l
        tmp_dict["percent_r"]=percent_r
        
        main_dict[feature]=tmp_dict
        # 68–95–99.7 rule, also known as the empirical rule
        
        # for 1*std
        left = mean - std
        right = mean + std
        val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 1 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        # for 2*std
        left = mean - (2*std)
        right = mean + (2*std)
        val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 2 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        main_table.append(tmp_table)
        
        # for 3*std
        left = mean - (3*std)
        right = mean + (3*std)
        val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 3 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        skew=scipy.stats.skew(sov)
        print("skewness value= ",skew)
        
        
        #sov_1=(sov-mean)/std
        sov_1=np.sort(sov)
        plt.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$')
        plt.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        plt.title("Normal Fit")
        plt.xlabel(feature)
        plt.ylabel("pdf")
        plt.legend()
        #plt.savefig('/home/ritam/ontology_hardness/images/'+str(feature)+'.jpeg')
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
        
        #print((sov_1[0:4500]))
        #break


# In[174]:


main_dict['RCH']


# In[175]:


#main_dict_cal=main_dict
dec_rate=[(0.2,0.3)]

for i in range(len(dec_rate)):
    
    lower_r=dec_rate[i][0]
    high_r=dec_rate[i][1]
    print(lower_r,high_r)
    
    for j in range (100):
        c=0
        for key,value in main_dict.items():
            
            if value["percent_change"] < 90:
                #print(key,value)

                lw_i_temp=value["lw_i"]
                rw_i_temp=value["rw_i"]
                #print(lw_i_temp,rw_i_temp)
                if value["percent_l"] >= 50:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(lower_r * diff, 3)  
                    #print(frac)

                    lw_i_temp= round(lw_i_temp + frac , 4)
                    #print(lw_i_temp)
                    value["lw_i"]=lw_i_temp
                else:
                    diff=value["min_val_w"] - lw_i_temp
                    frac = round(high_r * diff, 3)  
                    #print(frac)

                    lw_i_temp= round(lw_i_temp + frac ,4 )
                    #print(lw_i_temp)
                    value["lw_i"]=lw_i_temp

                if value["percent_r"] >= 50:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(lower_r * diff, 3)  
                    #print(frac)

                    rw_i_temp= round(rw_i_temp + frac , 4 )
                    #print(rw_i_temp)
                    value["rw_i"]=rw_i_temp
                else:
                    diff=value["max_val_w"] - rw_i_temp
                    frac = round(high_r * diff, 3)  
                    #print(frac)

                    rw_i_temp= round(rw_i_temp + frac ,4 )
                    #print(rw_i_temp)
                    value["rw_i"]=rw_i_temp

                sov=np.array(easy_time_mem_medium[key])
                #print("feature values= ",sov)
                #print(lw_i_temp,rw_i_temp)
                rx= value["mean"] - (lw_i_temp* value["std"]) 
                ry= value["mean"] + (rw_i_temp* value["std"])
                value["lr"]=round(rx,5)
                value["rr"]=round(ry,5)
                #print(rx,ry)

                val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_medium.shape[0]
                v =  round((val * 100 ),2)
                #print("data covered within this range= ",v,"%")
                value["percent_change"]=v

                if v >=85:
                    c=c+1
                #break
            else:
                c=c+1
        #print(main_dict_cal['size_kb'])
        #print(len(list(main_dict_cal.keys())))
        p=round(c / len(list(main_dict.keys())),2)
        print ("percent in loop ",j , "covers ", p , "%" )
        #break
    #break


# In[176]:


main_dict


# In[141]:


easy_time_mem_medium


# In[59]:


cols=['f_name','min_value','max_value','mean','std','l_weight','r_weight','left_range','right_range', 'data_cover%','1_std%','2_std%','3_std%']
easy_time_medium_mem_table=pd.DataFrame(data=main_table,columns=cols)
print(easy_time_medium_mem_table.shape)
#easy_time_medium_mem_table.to_csv('easy_time_medium_mem_table.csv',index=False)
#easy_time_mem_medium.to_csv('easy_time_mem_medium.csv',index=False)


# In[60]:


#after easy time and memory clusering ,
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

easy_zero_features.append('file')
easy_zero_features.append('label_mem')


#print(easy_zero_features)
print(df_easy_time_mem_feature_df.shape)
easy_time_mem_hard = df_easy_time_mem_feature_df[df_easy_time_mem_feature_df['label_mem']== 'hard']
print(easy_time_mem_hard.shape)
prior_hard= round(easy_time_mem_hard.shape[0] / df_easy_time_mem_feature_df.shape[0] ,5)

main_table=[]

for i in range(len(cols_list)):
    #if cols_list[i] not in easy_zero_features:
    if cols_list[i] in top_n_1:
        tmp_table=[]
        
        feature = cols_list[i]
        print("feature name= ",feature)
        tmp_table.append(feature)
        
        sov=np.array(easy_time_mem_hard[feature])
        print("feature values= ",sov)
        print("max value = ",np.max(sov),"min value = ",np.min(sov))
        tmp_table.append(round(np.min(sov),5))
        tmp_table.append(round(np.max(sov),5))
        
        mean = np.mean(sov)
        std = np.std(sov)
        print("mean= ",mean,"standard deviation= ",std)
        tmp_table.append(round(mean,5))
        tmp_table.append(round(std,5))
        #gamma=2
        #rx= mean - ((1- prior_hard) *gamma * std) 
        #ry= mean + ((1- prior_hard) *gamma * std)
        #rx= mean - (0.05*(gamma * std) )
        #ry= mean + (0.5*(gamma * std))
        l_weight=0.25
        r_weight=2
        rx= mean - (l_weight* std) 
        ry= mean + (r_weight* std)
        print("left range= ",rx,"right range= ", ry)
        
        val=(((rx <= sov) & (sov <= ry)).sum()) / easy_time_mem_hard.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within this range= ",v,"%")
        
        tmp_table.append(l_weight)
        tmp_table.append(r_weight)
        tmp_table.append(rx)
        tmp_table.append(ry)
        tmp_table.append(v)
        
        # 68–95–99.7 rule, also known as the empirical rule
        
        # for 1*std
        left = mean - std
        right = mean + std
        val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_hard.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 1 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        # for 2*std
        left = mean - (2*std)
        right = mean + (2*std)
        val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_hard.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 2 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        main_table.append(tmp_table)
        
        # for 3*std
        left = mean - (3*std)
        right = mean + (3*std)
        val=(((left <= sov) & (sov <= right)).sum()) / easy_time_mem_hard.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 3 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        skew=scipy.stats.skew(sov)
        print("skewness value= ",skew)
        
        
        #sov_1=(sov-mean)/std
        sov_1=np.sort(sov)
        plt.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$')
        plt.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        plt.title("Normal Fit")
        plt.xlabel(feature)
        plt.ylabel("pdf")
        plt.legend()
        #plt.savefig('/home/ritam/ontology_hardness/images/'+str(feature)+'.jpeg')
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
        
        #print((sov_1[0:4500]))
        #break


# In[61]:


h=[0.99708573, 0.16541353, 0.99958107, 0.99934591, 0.15789474, 0.15789474,
 0.92682927, 0.92682927, 0.92682927, 0.92682927]
h_np=np.array(h)
h_np

sov=h_np
print("feature values= ",sov)
print("max value = ",np.max(sov),"min value = ",np.min(sov))

mean = np.mean(sov)
std = np.std(sov)
print("mean= ",mean,"standard deviation= ",std)

sov_1=np.sort(sov)
print(norm.pdf(sov_1, mean, std))
plt.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$')
plt.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
plt.title("Normal Fit")
plt.xlabel(feature)
plt.ylabel("pdf")
plt.legend()
#plt.savefig('/home/ritam/ontology_hardness/images/'+str(feature)+'.jpeg')
plt.show()


# In[62]:


easy_time_mem_hard


# In[63]:


cols=['f_name','min_value','max_value','mean','std','l_weight','r_weight','left_range','right_range', 'data_cover%','1_std%','2_std%','3_std%']
easy_time_hard_mem_table=pd.DataFrame(data=main_table,columns=cols)
print(easy_time_hard_mem_table.shape)
#easy_time_hard_mem_table.to_csv('easy_time_hard_mem_table.csv',index=False)
#easy_time_mem_hard.to_csv('easy_time_mem_hard.csv',index=False)


# In[64]:


# easy part
# after time and memory , collect the  easy ones
# for calculating the effective range
''''
from matplotlib import pyplot as plt
%matplotlib inline
#%matplotlib qt 
from scipy.stats import norm
import statistics


#cols=list(x_train_np.columns.values)
print(df_easy_time_mem_feature_df.shape)
easy_time_mem_easy = df_easy_time_mem_feature_df[df_easy_time_mem_feature_df['label_mem']== 'easy']
print(easy_time_mem_easy.shape)
sov = np.array(easy_time_mem_easy['ELCLSPRT'])
print(sov,type(sov),np.max(sov),np.min(sov))

plt.hist(sov,bins=20)
#plt.xticks(sov)
#plt.xlim([50,51175])
plt.show()

#sov=np.sort(sov)
mean = statistics.mean(sov)
sd = np.std(sov)
print("sd= ",sd)
p=(sov-mean )
print(mean)
print(p)
a=p**2
print(a)
print(len(p))
s=np.sum(a) / len((p))
print(s,np.sqrt(s))
#sov_1=(sov-mean)/sd
#print(sov_1)
#sov_1=np.sort(sov)#
#print(sov_1)
#plt.plot(sov_1, norm.pdf(sov_1, mean, sd))
#plt.savefig('/home/ritam/ontology_hardness/images/first.jpeg')
#plt.show()

mean= round(np.mean(sov),2)
print("mean= ",mean)
sd=round(np.std(sov),2)
print("std=",sd)
prior_easy= round(easy_time_mem_easy.shape[0] / df_easy_time_mem_feature_df.shape[0] ,5)
print("prior= ",prior_easy)
gamma=2
print("min---",np.min(sov),"max----",np.max(sov))
#rx= mean - ((1- prior_easy) *gamma * sd) 
#ry= mean + ((1- prior_easy) *gamma * sd)
rx= mean - (0.05*(gamma * sd) )
ry= mean + (0.5*(gamma * sd))
print("range= ",rx,ry)

#easy_time_mem_easy['SOV'].hist(bins = 10)
d=int(np.max(sov) /5)
print(d)
bins=[]
for i in range(0,np.max(sov)+d+1,d):
    bins.append(i)
    #print(i)
    #i=i+d
print(bins)

#del bins[0:4]
#print(bins)
hist, edges = np.histogram( sov , bins = bins)
print(sov.shape)
print(hist)
print(edges)
#bins.remove(0)
plt.hist(sov, bins=bins,edgecolor='black')
plt.axvline(mean, color='green',label='mean')
plt.style.use('fivethirtyeight')
#plt.tight_layout()
plt.xlabel('SOV')
plt.ylabel('Freaq')
plt.show()
print(((rx < sov) & (ry <= 21437240)).sum() / easy_time_mem_easy.shape[0] )
# need to get more ontologies
'''


# In[65]:


print(df_easy['consistency_time_milsecs'].max(),df_easy['consistency_time_milsecs'].min())


# In[66]:


# collect the medium ones
df_medium=partial_dataframe[partial_dataframe['label_time']=='medium']
#df_medium_drop=df_medium.drop(['label'],axis=1)
df_medium_drop=df_medium # contains based on time
df_medium_drop


# In[67]:


# now medium time ontologies clustered on memory
t=df_medium_drop.drop(['file','label_time'],axis=1)
#print(df_medium_drop.shape)
#print(t.columns)
scaler = MinMaxScaler()
scaler.fit(t)
t_df_scaled=scaler.transform(t)
#print(t_df_scaled)

t_MinMax = pd.DataFrame(data=t_df_scaled, columns=['size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])
t=t_MinMax

print(t.shape)
#t_np=np.array(t)
#print(t_np)
km_2 = KMeans(n_clusters=3,random_state=0)
print(km_2)
y_predicted_2=km_2.fit_predict(t[["consistency_size_kb"]])
print(y_predicted_2)
#print(type(t))
t['cluster']=y_predicted_2
#print(t)
t1=t[t['cluster']==0]
t2=t[t['cluster']==1]
t3=t[t['cluster']==2]

plt.scatter(t1["consistency_time_milsecs"],t1["consistency_size_kb"],c='black')
plt.scatter(t2["consistency_time_milsecs"],t2["consistency_size_kb"],c='green')
plt.scatter(t3["consistency_time_milsecs"],t3["consistency_size_kb"],c='red')
#plt.scatter(km.cluster_centers_[:,0],km.cluster_centers_[:,1],color='yellow',marker='.',label='centroid')
plt.xlabel('time_in_milisecs')
plt.ylabel('size_in_kb')
plt.show()
#t_MinMax


# In[68]:


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


# In[69]:


total_df= pd.read_csv('final_new_more.csv') # for features collection 
print("size of final_new_more= ",total_df.shape)

df_medium_time_mem_feature=[]
for i in range(df_medium_time_mem.shape[0]):
    ont=df_medium_time_mem.iloc[i,:]['file']
    lab=df_medium_time_mem.iloc[i,:]['label_mem']
    #print(ont,lab)
    o=(np.array(total_df[total_df['file']==ont]))
    #print(o.shape)
    if o.shape[0]!=0:
    
        u=list(np.array(total_df[total_df['file']==ont])[0])
        u.append(str(lab))
        #print(u)
        df_medium_time_mem_feature.append(u)

    #break

total_df_cols=list(total_df.columns)
total_df_cols.append('label_mem')
#print(total_df_cols)
df_medium_time_mem_feature_df=pd.DataFrame(data=df_medium_time_mem_feature,columns=total_df_cols)
df_medium_time_mem_feature_df


# In[70]:


print(df_medium['consistency_time_milsecs'].max(),df_medium['consistency_time_milsecs'].min())
print(df_medium_time_mem_feature_df['consistency_time_milsecs'].max(),df_medium_time_mem_feature_df['consistency_time_milsecs'].min())
print(df_medium['consistency_size_kb'].max(),df_medium['consistency_size_kb'].min())
print(df_medium_time_mem_feature_df['consistency_size_kb'].max(),df_medium_time_mem_feature_df['consistency_size_kb'].min())


# In[71]:


# medium time with easy mem, medium mem and hard mem
l=df_medium_time_mem_feature_df.drop('file',axis=1)
x=l.groupby(l['label_mem']).agg(['min', 'max','mean'])
#x.to_csv('medium_time_mem_feature_16315.csv',index_label=True)
x
x_medium=x
x_medium


# In[72]:


from sklearn import preprocessing
from sklearn.ensemble import RandomForestClassifier
import operator
le = preprocessing.LabelEncoder()
p=df_medium_time_mem_feature_df
le.fit(p['label_mem'])
g=le.transform(p['label_mem']) 
print(g)
x_train_np=p.drop(['file','label_mem'],axis=1)
print(x_train_np.shape)
y_train_np=np.array(g)


model = RandomForestClassifier(n_estimators = 100, random_state = 2)
model.feature_names = list(x_train_np.columns.values)
model.fit(x_train_np, y_train_np)
ft=list(model.feature_names)
importance = model.feature_importances_
print(model.n_features_)
print(ft,type(ft))

dict1_imp={}
c=0
for i,v in enumerate(importance):
    #print('Feature: %0d, Score: %.5f' % (i,v))
    val= round(v,5)
    #print(val,type(val),ft[c])
    dict1_imp[ft[c]]=val
    c+=1
#print(dict1_imp)
dict1_imp_sort=dict(sorted(dict1_imp.items(), key=operator.itemgetter(1),reverse=True))

for key,val in dict1_imp_sort.items():
    print(key,val)
plt.bar([x for x in range(len(importance))], importance)
plt.show()


medium_feature_sort_dic=dict1_imp_sort

top_n_2=(list(medium_feature_sort_dic.keys())[:30])


# In[73]:


d= dict(x_medium)
medium_zero_features =[]
for key, value in dict1_imp_sort.items():
    if value == 0.0:
        if ( sum(list(d[(key, 'mean')]))/3 )== 0.0 :
            print(key,list(d[(key, 'mean')]))
            medium_zero_features.append(key)
print(medium_zero_features)

print(top_n_2)


# In[74]:


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

medium_zero_features.append('file')
medium_zero_features.append('label_mem')


#print(medium_zero_features)
print(df_medium_time_mem_feature_df.shape)
medium_time_mem_easy = df_medium_time_mem_feature_df[df_medium_time_mem_feature_df['label_mem']== 'easy']
print(medium_time_mem_easy.shape)
prior_easy= round(medium_time_mem_easy.shape[0] / df_medium_time_mem_feature_df.shape[0] ,5)

main_table=[]

for i in range(len(cols_list)):
    #if cols_list[i] not in medium_zero_features:
    if cols_list[i] in top_n_2:
        tmp_table=[]
        
        feature = cols_list[i]
        print("feature name= ",feature)
        tmp_table.append(feature)
        
        sov=np.array(medium_time_mem_easy[feature])
        print("feature values= ",sov)
        print("max value = ",np.max(sov),"min value = ",np.min(sov))
        tmp_table.append(round(np.min(sov),5))
        tmp_table.append(round(np.max(sov),5))
        
        mean = np.mean(sov)
        std = np.std(sov)
        print("mean= ",mean,"standard deviation= ",std)
        tmp_table.append(round(mean,5))
        tmp_table.append(round(std,5))
        #gamma=2
        #rx= mean - ((1- prior_easy) *gamma * std) 
        #ry= mean + ((1- prior_easy) *gamma * std)
        #rx= mean - (0.05*(gamma * std) )
        #ry= mean + (0.5*(gamma * std))
        l_weight=0.25
        r_weight=2
        rx= mean - (l_weight* std) 
        ry= mean + (r_weight* std)
        print("left range= ",rx,"right range= ", ry)
        
        val=(((rx <= sov) & (sov <= ry)).sum()) / medium_time_mem_easy.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within this range= ",v,"%")
        
        tmp_table.append(l_weight)
        tmp_table.append(r_weight)
        tmp_table.append(rx)
        tmp_table.append(ry)
        tmp_table.append(v)
        
        # 68–95–99.7 rule, also known as the empirical rule
        
        # for 1*std
        left = mean - std
        right = mean + std
        val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_easy.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 1 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        # for 2*std
        left = mean - (2*std)
        right = mean + (2*std)
        val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_easy.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 2 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        main_table.append(tmp_table)
        
        # for 3*std
        left = mean - (3*std)
        right = mean + (3*std)
        val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_easy.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 3 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        skew=scipy.stats.skew(sov)
        print("skewness value= ",skew)
        
        
        #sov_1=(sov-mean)/std
        sov_1=np.sort(sov)
        plt.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$')
        plt.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        plt.title("Normal Fit")
        plt.xlabel(feature)
        plt.ylabel("pdf")
        plt.legend()
        #plt.savefig('/home/ritam/ontology_hardness/images/'+str(feature)+'.jpeg')
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
        
        #print((sov_1[0:4500]))
        #break


# In[75]:


medium_time_mem_easy


# In[76]:


cols=['f_name','min_value','max_value','mean','std','l_weight','r_weight','left_range','right_range', 'data_cover%','1_std%','2_std%','3_std%']
medium_time_easy_mem_table=pd.DataFrame(data=main_table,columns=cols)
print(medium_time_easy_mem_table.shape)
#medium_time_easy_mem_table.to_csv('medium_time_easy_mem_table.csv',index=False)
#medium_time_mem_easy.to_csv('medium_time_mem_easy.csv',index=False)


# In[77]:


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

medium_zero_features.append('file')
medium_zero_features.append('label_mem')


#print(medium_zero_features)
print(df_medium_time_mem_feature_df.shape)
medium_time_mem_medium = df_medium_time_mem_feature_df[df_medium_time_mem_feature_df['label_mem']== 'medium']
print(medium_time_mem_medium.shape)
prior_medium= round(medium_time_mem_medium.shape[0] / df_medium_time_mem_feature_df.shape[0] ,5)

main_table=[]

for i in range(len(cols_list)):
    #if cols_list[i] not in medium_zero_features:
    if cols_list[i] in top_n_2:
        tmp_table=[]
        
        feature = cols_list[i]
        print("feature name= ",feature)
        tmp_table.append(feature)
        
        sov=np.array(medium_time_mem_medium[feature])
        print("feature values= ",sov)
        print("max value = ",np.max(sov),"min value = ",np.min(sov))
        tmp_table.append(round(np.min(sov),5))
        tmp_table.append(round(np.max(sov),5))
        
        mean = np.mean(sov)
        std = np.std(sov)
        print("mean= ",mean,"standard deviation= ",std)
        tmp_table.append(round(mean,5))
        tmp_table.append(round(std,5))
        #gamma=2
        #rx= mean - ((1- prior_medium) *gamma * std) 
        #ry= mean + ((1- prior_medium) *gamma * std)
        #rx= mean - (0.05*(gamma * std) )
        #ry= mean + (0.5*(gamma * std))
        l_weight=0.25
        r_weight=2
        rx= mean - (l_weight* std) 
        ry= mean + (r_weight* std)
        print("left range= ",rx,"right range= ", ry)
        
        val=(((rx <= sov) & (sov <= ry)).sum()) / medium_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within this range= ",v,"%")
        
        tmp_table.append(l_weight)
        tmp_table.append(r_weight)
        tmp_table.append(rx)
        tmp_table.append(ry)
        tmp_table.append(v)
        
        # 68–95–99.7 rule, also known as the empirical rule
        
        # for 1*std
        left = mean - std
        right = mean + std
        val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 1 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        # for 2*std
        left = mean - (2*std)
        right = mean + (2*std)
        val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 2 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        main_table.append(tmp_table)
        
        # for 3*std
        left = mean - (3*std)
        right = mean + (3*std)
        val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 3 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        skew=scipy.stats.skew(sov)
        print("skewness value= ",skew)
        
        
        #sov_1=(sov-mean)/std
        sov_1=np.sort(sov)
        plt.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$')
        plt.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        plt.title("Normal Fit")
        plt.xlabel(feature)
        plt.ylabel("pdf")
        plt.legend()
        #plt.savefig('/home/ritam/ontology_hardness/images/'+str(feature)+'.jpeg')
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
        
        #print((sov_1[0:4500]))
        #break


# In[78]:


medium_time_mem_medium


# In[79]:


cols=['f_name','min_value','max_value','mean','std','l_weight','r_weight','left_range','right_range', 'data_cover%','1_std%','2_std%','3_std%']
medium_time_medium_mem_table=pd.DataFrame(data=main_table,columns=cols)
print(medium_time_medium_mem_table.shape)
#medium_time_medium_mem_table.to_csv('medium_time_medium_mem_table.csv',index=False)
#medium_time_mem_medium.to_csv('medium_time_mem_medium.csv',index=False)


# In[80]:


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

medium_zero_features.append('file')
medium_zero_features.append('label_mem')


#print(medium_zero_features)
print(df_medium_time_mem_feature_df.shape)
medium_time_mem_hard = df_medium_time_mem_feature_df[df_medium_time_mem_feature_df['label_mem']== 'hard']
print(medium_time_mem_hard.shape)
prior_hard= round(medium_time_mem_hard.shape[0] / df_medium_time_mem_feature_df.shape[0] ,5)

main_table=[]

for i in range(len(cols_list)):
    #if cols_list[i] not in medium_zero_features:
    if cols_list[i] in top_n_2:
        tmp_table=[]
        
        feature = cols_list[i]
        print("feature name= ",feature)
        tmp_table.append(feature)
        
        sov=np.array(medium_time_mem_hard[feature])
        print("feature values= ",sov)
        print("max value = ",np.max(sov),"min value = ",np.min(sov))
        tmp_table.append(round(np.min(sov),5))
        tmp_table.append(round(np.max(sov),5))
        
        mean = np.mean(sov)
        std = np.std(sov)
        print("mean= ",mean,"standard deviation= ",std)
        tmp_table.append(round(mean,5))
        tmp_table.append(round(std,5))
        #gamma=2
        #rx= mean - ((1- prior_hard) *gamma * std) 
        #ry= mean + ((1- prior_hard) *gamma * std)
        #rx= mean - (0.05*(gamma * std) )
        #ry= mean + (0.5*(gamma * std))
        l_weight=0.25
        r_weight=2
        rx= mean - (l_weight* std) 
        ry= mean + (r_weight* std)
        print("left range= ",rx,"right range= ", ry)
        
        val=(((rx <= sov) & (sov <= ry)).sum()) / medium_time_mem_hard.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within this range= ",v,"%")
        
        tmp_table.append(l_weight)
        tmp_table.append(r_weight)
        tmp_table.append(rx)
        tmp_table.append(ry)
        tmp_table.append(v)
        
        # 68–95–99.7 rule, also known as the empirical rule
        
        # for 1*std
        left = mean - std
        right = mean + std
        val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_hard.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 1 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        # for 2*std
        left = mean - (2*std)
        right = mean + (2*std)
        val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 2 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        main_table.append(tmp_table)
        
        # for 3*std
        left = mean - (3*std)
        right = mean + (3*std)
        val=(((left <= sov) & (sov <= right)).sum()) / medium_time_mem_hard.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 3 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        skew=scipy.stats.skew(sov)
        print("skewness value= ",skew)
        
        
        #sov_1=(sov-mean)/std
        sov_1=np.sort(sov)
        plt.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$')
        plt.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        plt.title("Normal Fit")
        plt.xlabel(feature)
        plt.ylabel("pdf")
        plt.legend()
        #plt.savefig('/home/ritam/ontology_hardness/images/'+str(feature)+'.jpeg')
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
            
        print("ends normality test ------------------------------------------------\n")
        
        #print((sov_1[0:4500]))
        #break
        '''


# In[81]:


medium_time_mem_hard


# In[82]:


cols=['f_name','min_value','max_value','mean','std','l_weight','r_weight','left_range','right_range', 'data_cover%','1_std%','2_std%','3_std%']
medium_time_hard_mem_table=pd.DataFrame(data=main_table,columns=cols)
print(medium_time_hard_mem_table.shape)
#medium_time_hard_mem_table.to_csv('medium_time_hard_mem_table.csv',index=False)
#medium_time_mem_hard.to_csv('medium_time_mem_hard.csv',index=False)


# In[83]:


# collect the hard ones
df_hard=partial_dataframe[partial_dataframe['label_time']=='hard']
#df_medium_drop=df_medium.drop(['label'],axis=1)
df_hard_drop=df_hard # contains based on time
df_hard_drop


# In[84]:


print(df_hard['consistency_time_milsecs'].max(),df_hard['consistency_time_milsecs'].min())


# In[85]:


# now hard time ontologies clustered on memory
t=df_hard_drop.drop(['file','label_time'],axis=1)
#print(df_hard_drop.shape)
#print(t.columns)
scaler = MinMaxScaler()
scaler.fit(t)
t_df_scaled=scaler.transform(t)
#print(t_df_scaled)

t_MinMax = pd.DataFrame(data=t_df_scaled, columns=['size_kb', 'consistency_time_milsecs', 'consistency_size_kb',
       'realisation_time_milsecs', 'realisation_size_kb',
       'classification_time_milsecs', 'classification_size_kb'])
t=t_MinMax

print(t.shape)
#t_np=np.array(t)
#print(t_np)
km_3 = KMeans(n_clusters=3,random_state=0)
print(km_3)
y_predicted_3=km_3.fit_predict(t[["consistency_size_kb"]])
print(y_predicted_3)
#print(type(t))
t['cluster']=y_predicted_3
#print(t)
t1=t[t['cluster']==0]
t2=t[t['cluster']==1]
t3=t[t['cluster']==2]

plt.scatter(t1["consistency_time_milsecs"],t1["consistency_size_kb"],c='black')
plt.scatter(t2["consistency_time_milsecs"],t2["consistency_size_kb"],c='green')
plt.scatter(t3["consistency_time_milsecs"],t3["consistency_size_kb"],c='red')
#plt.scatter(km.cluster_centers_[:,0],km.cluster_centers_[:,1],color='yellow',marker='.',label='centroid')
plt.xlabel('time_in_milisecs')
plt.ylabel('size_in_kb')
plt.show()
#t_MinMax


# In[86]:


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


# In[87]:


total_df= pd.read_csv('final_new_more.csv') # for features collection 
print("size of final_new_more= ",total_df.shape)

df_hard_time_mem_feature=[]
for i in range(df_hard_time_mem.shape[0]):
    ont=df_hard_time_mem.iloc[i,:]['file']
    lab=df_hard_time_mem.iloc[i,:]['label_mem']
    #print(ont,lab)
    o=(np.array(total_df[total_df['file']==ont]))
    #print(o.shape)
    if o.shape[0]!=0:
    
        u=list(np.array(total_df[total_df['file']==ont])[0])
        u.append(str(lab))
        #print(u)
        df_hard_time_mem_feature.append(u)

    #break

total_df_cols=list(total_df.columns)
total_df_cols.append('label_mem')
#print(total_df_cols)
df_hard_time_mem_feature_df=pd.DataFrame(data=df_hard_time_mem_feature,columns=total_df_cols)
df_hard_time_mem_feature_df


# In[88]:


print(df_hard['consistency_time_milsecs'].max(),df_hard['consistency_time_milsecs'].min())
print(df_hard_time_mem_feature_df['consistency_time_milsecs'].max(),df_hard_time_mem_feature_df['consistency_time_milsecs'].min())
print(df_hard['consistency_size_kb'].max(),df_hard['consistency_size_kb'].min())
print(df_hard_time_mem_feature_df['consistency_size_kb'].max(),df_hard_time_mem_feature_df['consistency_size_kb'].min())


# In[89]:


# hard time with easy mem, medium mem and hard mem
l=df_hard_time_mem_feature_df.drop('file',axis=1)
x=l.groupby(l['label_mem']).agg(['min', 'max','mean'])
#x.to_csv('hard_time_mem_feature_16315.csv',index_label=True)
x
x_hard = x
x_hard


# In[90]:


d=dict(x)
print(d[('size_kb', 'mean')])
print(sum(list(d[('size_kb', 'mean')]))/3)


# In[91]:


from sklearn import preprocessing
from sklearn.ensemble import RandomForestClassifier
import operator
le = preprocessing.LabelEncoder()
p=df_hard_time_mem_feature_df
le.fit(p['label_mem'])
g=le.transform(p['label_mem']) 
print(g)
x_train_np=p.drop(['file','label_mem'],axis=1)
print(x_train_np.shape)
y_train_np=np.array(g)


model = RandomForestClassifier(n_estimators = 100, random_state = 2)
model.feature_names = list(x_train_np.columns.values)
model.fit(x_train_np, y_train_np)
ft=list(model.feature_names)
importance = model.feature_importances_
print(model.n_features_)
print(ft,type(ft))

dict1_imp={}
c=0
for i,v in enumerate(importance):
    #print('Feature: %0d, Score: %.5f' % (i,v))
    val= round(v,5)
    #print(val,type(val),ft[c])
    dict1_imp[ft[c]]=val
    c+=1
#print(dict1_imp)
dict1_imp_sort=dict(sorted(dict1_imp.items(), key=operator.itemgetter(1),reverse=True))

for key,val in dict1_imp_sort.items():
    print(key,val)
plt.bar([x for x in range(len(importance))], importance)
plt.show()


hard_feature_sort_dic=dict1_imp_sort

top_n_3=(list(hard_feature_sort_dic.keys())[:30])


# In[92]:


d= dict(x_hard)
hard_zero_features =[]
for key, value in dict1_imp_sort.items():
    if value == 0.0:
        if ( sum(list(d[(key, 'mean')]))/3 )== 0.0 :
            print(key,list(d[(key, 'mean')]))
            hard_zero_features.append(key)
print(hard_zero_features)
print(top_n_3)


# In[93]:


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

hard_zero_features.append('file')
hard_zero_features.append('label_mem')


#print(hard_zero_features)
print(df_hard_time_mem_feature_df.shape)
hard_time_mem_easy = df_hard_time_mem_feature_df[df_hard_time_mem_feature_df['label_mem']== 'easy']
print(hard_time_mem_easy.shape)
prior_easy= round(hard_time_mem_easy.shape[0] / df_hard_time_mem_feature_df.shape[0] ,5)

main_table=[]

for i in range(len(cols_list)):
    #if cols_list[i] not in hard_zero_features:
    if cols_list[i] in top_n_3:
        tmp_table=[]
        
        feature = cols_list[i]
        print("feature name= ",feature)
        tmp_table.append(feature)
        
        sov=np.array(hard_time_mem_easy[feature])
        print("feature values= ",sov)
        print("max value = ",np.max(sov),"min value = ",np.min(sov))
        tmp_table.append(round(np.min(sov),5))
        tmp_table.append(round(np.max(sov),5))
        
        mean = np.mean(sov)
        std = np.std(sov)
        print("mean= ",mean,"standard deviation= ",std)
        tmp_table.append(round(mean,5))
        tmp_table.append(round(std,5))
        #gamma=2
        #rx= mean - ((1- prior_easy) *gamma * std) 
        #ry= mean + ((1- prior_easy) *gamma * std)
        #rx= mean - (0.05*(gamma * std) )
        #ry= mean + (0.5*(gamma * std))
        l_weight=0.25
        r_weight=2
        rx= mean - (l_weight* std) 
        ry= mean + (r_weight* std)
        print("left range= ",rx,"right range= ", ry)
        
        val=(((rx <= sov) & (sov <= ry)).sum()) / hard_time_mem_easy.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within this range= ",v,"%")
        
        tmp_table.append(l_weight)
        tmp_table.append(r_weight)
        tmp_table.append(rx)
        tmp_table.append(ry)
        tmp_table.append(v)
        
        # 68–95–99.7 rule, also known as the empirical rule
        
        # for 1*std
        left = mean - std
        right = mean + std
        val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_easy.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 1 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        # for 2*std
        left = mean - (2*std)
        right = mean + (2*std)
        val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_easy.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 2 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        main_table.append(tmp_table)
        
        # for 3*std
        left = mean - (3*std)
        right = mean + (3*std)
        val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_easy.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 3 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        skew=scipy.stats.skew(sov)
        print("skewness value= ",skew)
        
        
        #sov_1=(sov-mean)/std
        sov_1=np.sort(sov)
        plt.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$')
        plt.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        plt.title("Normal Fit")
        plt.xlabel(feature)
        plt.ylabel("pdf")
        plt.legend()
        #plt.savefig('/home/ritam/ontology_hardness/images/'+str(feature)+'.jpeg')
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
            
        print("ends normality test ------------------------------------------------\n")
        
        #print((sov_1[0:4500]))
        #break
        '''


# In[94]:


hard_time_mem_easy


# In[95]:


cols=['f_name','min_value','max_value','mean','std','l_weight','r_weight','left_range','right_range', 'data_cover%','1_std%','2_std%','3_std%']
hard_time_easy_mem_table=pd.DataFrame(data=main_table,columns=cols)
print(hard_time_easy_mem_table.shape)
#hard_time_easy_mem_table.to_csv('hard_time_easy_mem_table.csv',index=False)
#hard_time_mem_easy.to_csv('hard_time_mem_easy.csv',index=False)


# In[96]:


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

hard_zero_features.append('file')
hard_zero_features.append('label_mem')


#print(hard_zero_features)
print(df_hard_time_mem_feature_df.shape)
hard_time_mem_medium = df_hard_time_mem_feature_df[df_hard_time_mem_feature_df['label_mem']== 'medium']
print(hard_time_mem_medium.shape)
prior_medium= round(hard_time_mem_medium.shape[0] / df_hard_time_mem_feature_df.shape[0] ,5)

main_table=[]

for i in range(len(cols_list)):
    #if cols_list[i] not in hard_zero_features:
    if cols_list[i] in top_n_3:
        tmp_table=[]
        
        feature = cols_list[i]
        print("feature name= ",feature)
        tmp_table.append(feature)
        
        sov=np.array(hard_time_mem_medium[feature])
        print("feature values= ",sov)
        print("max value = ",np.max(sov),"min value = ",np.min(sov))
        tmp_table.append(round(np.min(sov),5))
        tmp_table.append(round(np.max(sov),5))
        
        mean = np.mean(sov)
        std = np.std(sov)
        print("mean= ",mean,"standard deviation= ",std)
        tmp_table.append(round(mean,5))
        tmp_table.append(round(std,5))
        #gamma=2
        #rx= mean - ((1- prior_medium) *gamma * std) 
        #ry= mean + ((1- prior_medium) *gamma * std)
        #rx= mean - (0.05*(gamma * std) )
        #ry= mean + (0.5*(gamma * std))
        l_weight=0.25
        r_weight=2
        rx= mean - (l_weight* std) 
        ry= mean + (r_weight* std)
        print("left range= ",rx,"right range= ", ry)
        
        val=(((rx <= sov) & (sov <= ry)).sum()) / hard_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within this range= ",v,"%")
        
        tmp_table.append(l_weight)
        tmp_table.append(r_weight)
        tmp_table.append(rx)
        tmp_table.append(ry)
        tmp_table.append(v)
        
        # 68–95–99.7 rule, also known as the empirical rule
        
        # for 1*std
        left = mean - std
        right = mean + std
        val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 1 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        # for 2*std
        left = mean - (2*std)
        right = mean + (2*std)
        val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 2 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        main_table.append(tmp_table)
        
        # for 3*std
        left = mean - (3*std)
        right = mean + (3*std)
        val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_medium.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 3 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        skew=scipy.stats.skew(sov)
        print("skewness value= ",skew)
        
        
        #sov_1=(sov-mean)/std
        sov_1=np.sort(sov)
        plt.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$')
        plt.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        plt.title("Normal Fit")
        plt.xlabel(feature)
        plt.ylabel("pdf")
        plt.legend()
        #plt.savefig('/home/ritam/ontology_hardness/images/'+str(feature)+'.jpeg')
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
            
        print("ends normality test ------------------------------------------------\n")
        
        #print((sov_1[0:4500]))
        #break
        '''


# In[97]:


hard_time_mem_medium


# In[98]:


cols=['f_name','min_value','max_value','mean','std','l_weight','r_weight','left_range','right_range', 'data_cover%','1_std%','2_std%','3_std%']
hard_time_medium_mem_table=pd.DataFrame(data=main_table,columns=cols)
print(hard_time_medium_mem_table.shape)
#hard_time_medium_mem_table.to_csv('hard_time_medium_mem_table.csv',index=False)
#hard_time_mem_medium.to_csv('hard_time_mem_medium.csv',index=False)


# In[99]:


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

hard_zero_features.append('file')
hard_zero_features.append('label_mem')


#print(hard_zero_features)
print(df_hard_time_mem_feature_df.shape)
hard_time_mem_hard = df_hard_time_mem_feature_df[df_hard_time_mem_feature_df['label_mem']== 'hard']
print(hard_time_mem_hard.shape)
prior_hard= round(hard_time_mem_hard.shape[0] / df_hard_time_mem_feature_df.shape[0] ,5)

main_table=[]

for i in range(len(cols_list)):
    #if cols_list[i] not in hard_zero_features:
    if cols_list[i] in top_n_3:
        tmp_table=[]
        
        feature = cols_list[i]
        print("feature name= ",feature)
        tmp_table.append(feature)
        
        sov=np.array(hard_time_mem_hard[feature])
        print("feature values= ",sov)
        print("max value = ",np.max(sov),"min value = ",np.min(sov))
        tmp_table.append(round(np.min(sov),5))
        tmp_table.append(round(np.max(sov),5))
        
        mean = np.mean(sov)
        std = np.std(sov)
        print("mean= ",mean,"standard deviation= ",std)
        tmp_table.append(round(mean,5))
        tmp_table.append(round(std,5))
        #gamma=2
        #rx= mean - ((1- prior_hard) *gamma * std) 
        #ry= mean + ((1- prior_hard) *gamma * std)
        #rx= mean - (0.05*(gamma * std) )
        #ry= mean + (0.5*(gamma * std))
        l_weight=0.25
        r_weight=2
        rx= mean - (l_weight* std) 
        ry= mean + (r_weight* std)
        print("left range= ",rx,"right range= ", ry)
        
        val=(((rx <= sov) & (sov <= ry)).sum()) / hard_time_mem_hard.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within this range= ",v,"%")
        
        tmp_table.append(l_weight)
        tmp_table.append(r_weight)
        tmp_table.append(rx)
        tmp_table.append(ry)
        tmp_table.append(v)
        
        # 68–95–99.7 rule, also known as the empirical rule
        
        # for 1*std
        left = mean - std
        right = mean + std
        val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_hard.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 1 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        # for 2*std
        left = mean - (2*std)
        right = mean + (2*std)
        val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_hard.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 2 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        main_table.append(tmp_table)
        
        # for 3*std
        left = mean - (3*std)
        right = mean + (3*std)
        val=(((left <= sov) & (sov <= right)).sum()) / hard_time_mem_hard.shape[0]
        v =  round((val * 100 ),2)
        print("data covered within 3 standard deviation= ",v,"%")
        tmp_table.append(v)
        
        print("\n\n")
        
        #normality test
        print("normality test :----\n")
        # kurtosis and skew function
        kurt=scipy.stats.kurtosis(sov)
        print("kurtosis value= ",kurt)
        
        skew=scipy.stats.skew(sov)
        print("skewness value= ",skew)
        
        
        #sov_1=(sov-mean)/std
        sov_1=np.sort(sov)
        plt.plot(sov_1, norm.pdf(sov_1, mean, std),label='$\mathcal{N}$ '+ f'$( \mu \\approx {round(mean,3)} , \sigma \\approx {round(std,3)})$')
        plt.hist(sov_1,edgecolor='black',alpha=0.5,density= True)
        plt.title("Normal Fit")
        plt.xlabel(feature)
        plt.ylabel("pdf")
        plt.legend()
        #plt.savefig('/home/ritam/ontology_hardness/images/'+str(feature)+'.jpeg')
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
            
        print("ends normality test ------------------------------------------------\n")
        
        #print((sov_1[0:4500]))
        #break
        '''


# In[100]:


hard_time_mem_hard


# In[101]:


cols=['f_name','min_value','max_value','mean','std','l_weight','r_weight','left_range','right_range', 'data_cover%','1_std%','2_std%','3_std%']
hard_time_hard_mem_table=pd.DataFrame(data=main_table,columns=cols)
print(hard_time_hard_mem_table.shape)
#hard_time_hard_mem_table.to_csv('hard_time_hard_mem_table.csv',index=False)
#hard_time_mem_hard.to_csv('hard_time_mem_hard.csv',index=False)


# In[102]:


print(easy_time_easy_mem_table.shape)
print(easy_time_medium_mem_table.shape)
print(easy_time_hard_mem_table.shape)
print(medium_time_easy_mem_table.shape)
print(medium_time_medium_mem_table.shape)
print(medium_time_hard_mem_table.shape)
print(hard_time_easy_mem_table.shape)
print(hard_time_medium_mem_table.shape)
print(hard_time_hard_mem_table.shape)


# In[103]:


easy_time_easy_mem_table


# In[104]:


x_easy


# In[105]:


#(easy_feature_sort_dic)
#medium_feature_sort_dic
#hard_feature_sort_dic

n=50

easy_top_n_features=dict(list(easy_feature_sort_dic.items())[:n])
#print(easy_top_n_features)
medium_top_n_features=dict(list(medium_feature_sort_dic.items())[:n])
#print(medium_top_n_features)
hard_top_n_features=dict(list(hard_feature_sort_dic.items())[:n])
#print(hard_top_n_features)

easy_key=list(easy_top_n_features.keys())
#print(easy_key)
medium_key=list(medium_top_n_features.keys())
#print(medium_key)
hard_key=list(hard_top_n_features.keys())
#print(hard_key)

def intersection(lst1, lst2):
    return list(set(lst1) & set(lst2))
  
inter1 = (intersection(easy_key, medium_key))
print(len(inter1))

inter2 = (intersection(inter1, hard_key))
print((inter2))

#print(list(x_easy['EOG']['mean']))

main=[]
rows=[]
for i in range(len(inter2)):
    f=inter2[i]
    rows.append(f)
    #print(f)
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
    
    #print(tmp)
    main.append(tmp)
    #break


# In[106]:


print(len(main))
cols=['easy_time_easy_mem','easy_time_hard_mem','easy_time_medium_mem','medium_time_easy_mem','medium_time_hard_mem',
      'medium_time_medium_mem','hard_time_easy_mem','hard_time_hard_mem','hard_time_medium_mem']
top_n_mean_feature=pd.DataFrame(data=main,columns=cols,index=rows)
top_n_mean_feature


# In[107]:


# find the common features from easy , medium , hard  which needs to be rejected 
   
print(easy_zero_features)
print(medium_zero_features)
print(hard_zero_features)

def intersection(lst1, lst2):
   return list(set(lst1) & set(lst2))
 
inter1 = (intersection(easy_zero_features, medium_zero_features))
print(inter1)


# In[108]:


c=easy_time_mem_easy["consistency_size_kb"]
#print(c.max())
easy_time_mem_easy


# In[109]:


df=easy_time_mem_easy
i=df.sort_values(by=["consistency_size_kb"], ascending=False,ignore_index=True)
i


# In[110]:


hard_time_mem_easy_copy=hard_time_mem_easy.sort_values(by=["consistency_size_kb"], ascending=False,ignore_index=True)
easy_time_mem_easy_copy=(easy_time_mem_easy.sort_values(by=["consistency_size_kb"], ascending=False,ignore_index=True))[0:4]
#easy_time_mem_easy_copy
hard_time_mem_easy_copy


# In[111]:


from matplotlib import pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')


#print(rows)
for i in range(len(rows)):
    f=rows[i]
    h_x=list(hard_time_mem_easy_copy["consistency_size_kb"])
    h_y=list(hard_time_mem_easy_copy[f])
    e_x=list(easy_time_mem_easy_copy["consistency_size_kb"])
    e_y=list(easy_time_mem_easy_copy[f])
    print((f))
    print(h_y,e_y)
    plt.plot(e_x, e_y, label = "easy line")
    plt.plot(h_x, h_y, label = "hard line ")
    plt.legend()
    plt.show()

    #break


# In[112]:


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


# In[113]:


p10 = open("rows","wb")
pickle.dump(rows, p10)
p10.close()


# In[ ]:




