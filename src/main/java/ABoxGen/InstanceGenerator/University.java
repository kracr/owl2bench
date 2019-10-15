package ABoxGen.InstanceGenerator;

import java.util.HashSet;

public class University {
    int collegeNum,i;
    College[] womenColleges, coEdColleges;
    Generator gen;
    int univIndex;
    String univCode,univName,univInstance,cityName;
    int womenCollegeNum, coEdCollegeNum , researchGroupNum;
    ResearchGroup[] researchGroups;
    HashSet<String> personPerUniversity = new HashSet();
    HashSet<String> universityName;
    ConfigFile configFile;



    public University(Generator gen, int univIndex) {
        this.gen = gen;
        this.univIndex = univIndex;
        this.univInstance= "Univ" + this.univIndex;
        this.univCode= "U"+univIndex;
        this.universityName=gen.universityName;
        do {
            this.cityName =gen.map3.get(GetRandomNo.getRandomFromRange(0,37000));
           //System.out.println("cityname"+ cityName);
        }while(universityName.contains(cityName));
        universityName.add(cityName);
        this.univName= "University of " + cityName;//from data dictionary
        System.out.println("univname"+ univName);
        this.configFile=new ConfigFile();
        gen.classAssertion(gen.getClass("University"),gen.getNamedIndividual(univInstance) );
        gen.dataPropertyAssertion(gen.getDataProperty("hasName"),gen.getNamedIndividual(univInstance),gen.getLiteral(univName));
        gen.dataPropertyAssertion(gen.getDataProperty("hasCode"),gen.getNamedIndividual(univInstance),gen.getLiteral(univCode));
        this.researchGroupNum= GetRandomNo.getRandomFromRange(gen.researchGroupNum_Min,gen.researchGroupNum_Max);
        this.collegeNum = GetRandomNo.getRandomFromRange(gen.collegeNum_Min,gen.collegeNum_Max);
        this.womenCollegeNum = GetRandomNo.getRandomFromRange(gen.womenCollegeNum_Min, gen.womenCollegeNum_Max);
        this.coEdCollegeNum = collegeNum- womenCollegeNum ;
        this.coEdColleges = new College[this.coEdCollegeNum];
        this.womenColleges = new College[this.womenCollegeNum];
        this.researchGroups = new ResearchGroup[this.researchGroupNum];


        if (this.womenCollegeNum != 0) {
            for(i = 0; i < this.womenCollegeNum; ++i) {
                this.womenColleges[i] = new College(this,i,true);
            }
        }
            for(i = 0; i < this.coEdCollegeNum; ++i) {
                this.coEdColleges[i] = new College(this, i,false);
            }
        for(i = 0; i < this.researchGroupNum; ++i) {
            this.researchGroups[i] = new ResearchGroup(this, i);
        }

    }
}


