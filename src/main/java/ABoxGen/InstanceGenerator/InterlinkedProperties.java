package ABoxGen.InstanceGenerator;

import java.util.HashSet;
import java.util.Iterator;

public class InterlinkedProperties {


    University university;
    GetRandomPerson getRandomPerson;
    GetRandomInterest getRandomInterest;
    University universities[];
    ConfigFile configFile;
    Generator gen;
    String crazyAbout;
   // int univNum;
    HashSet<String> hash1,hash2,hash3,hash4,hash5;
    int sameHomeTownNum,isFriendOfNum,likesNum,lovesNum,isCrazyAboutNum,dislikesNum;
    //takes course, is advised by
    //teaches course

    public InterlinkedProperties(Generator gen,University universities[]){
        this.gen=gen;
        //this.univNum=gen.univNum;
        this.universities=universities;
        getRandomInterest=new GetRandomInterest();
        getRandomPerson=new GetRandomPerson();
        configFile=new ConfigFile();
        for(int i=0;i<gen.univNum;++i){
            university=universities[i];
            Iterator<String> j=university.personPerUniversity.iterator();

            //for(int check=0;check<200000;++check) {
              //  System.out.println("****************************************" + university.personPerUniversity.size() + gen.univNum + "**" + universities.length);
            //}
            while(j.hasNext()) {
                String person1=j.next();
                sameHomeTownNum = GetRandomNo.getRandomFromRange(gen.sameHomeTownNum_Min,gen.sameHomeTownNum_Max);
                isFriendOfNum = GetRandomNo.getRandomFromRange(gen.isFriendOfNum_Min,gen.isFriendOfNum_Max);
                likesNum=GetRandomNo.getRandomFromRange(gen.likesNum_Min,gen.likesNum_Max);
                lovesNum=GetRandomNo.getRandomFromRange(gen.lovesNum_Min,gen.lovesNum_Max);
                isCrazyAboutNum=GetRandomNo.getRandomFromRange(gen.isCrazyAboutNum_Min,gen.isCrazyAboutNum_Max);
                dislikesNum=GetRandomNo.getRandomFromRange(gen.dislikesNum_Min,gen.dislikesNum_Max);

                hash1=new HashSet();
                for (int a = 0; a < sameHomeTownNum; ++a) {
                    String person = getRandomPerson.getRandomStudentFacultyOrStaff(gen,universities);
                    if (person != null) {
                        hash1.add(person);
                    }
                    Iterator<String> k = hash1.iterator();
                    while (k.hasNext()) {
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasSameHomeTownWith"),gen.getNamedIndividual(person1),gen.getNamedIndividual(k.next()));
                    }
                }
                hash2= new HashSet();
                for (int a = 0; a < isFriendOfNum; ++a) {
                    String person = getRandomPerson.getRandomStudentFacultyOrStaff(gen,universities);
                    if (person != null) {
                        hash2.add(person);
                    }
                    Iterator<String> k = hash2.iterator();
                    while (k.hasNext()) {
                        gen.objectPropertyAssertion(gen.getObjectProperty("isFriendOf"),gen.getNamedIndividual(person1),gen.getNamedIndividual(k.next()));
                    }
                }
                hash3= new HashSet();
                for (int a = 0; a < likesNum; ++a) {
                    String interest = getRandomInterest.getInterest();
                    if (interest != null) {
                        hash3.add(interest);
                    }
                    Iterator<String> k = hash3.iterator();
                    while (k.hasNext()) {
                        gen.objectPropertyAssertion(gen.getObjectProperty("likes"),gen.getNamedIndividual(person1),gen.getNamedIndividual(k.next()));
                    }
                }
                hash4= new HashSet();
                for (int a = 0; a < lovesNum; ++a) {
                    String interest = getRandomInterest.getInterest();
                    if ((interest != null) && (!hash3.contains(interest))) {
                        hash4.add(interest);
                    }
                    Iterator<String> k = hash4.iterator();
                    while (k.hasNext()) {
                        gen.objectPropertyAssertion(gen.getObjectProperty("loves"),gen.getNamedIndividual(person1),gen.getNamedIndividual(k.next()));
                    }
                }
                //isCrazyAbout just one
                if(isCrazyAboutNum!=0) {
                    crazyAbout=getRandomInterest.getInterest();
                    gen.objectPropertyAssertion(gen.getObjectProperty("isCrazyAbout"),gen.getNamedIndividual(person1),gen.getNamedIndividual(crazyAbout));
                }
                hash5= new HashSet();
                for (int a = 0; a < dislikesNum; ++a) {
                    String interest = getRandomInterest.getInterest();
                    if ((interest != null) && (crazyAbout!=interest)&&(!hash3.contains(interest))&&(!hash4.contains(interest))) {
                        hash4.add(interest);
                    }
                    Iterator<String> k = hash4.iterator();
                    while (k.hasNext()) {
                        gen.objectPropertyAssertion(gen.getObjectProperty("dislikes"),gen.getNamedIndividual(person1),gen.getNamedIndividual(k.next()));
                    }
                }
            }
        }
    }
}
