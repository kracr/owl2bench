/** For each University instance, College instances (Both Women and Co-Ed) and Research Groups are generated. 
 * And Basic hasName, hasCode data property assertion axioms are generated
* In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the config.properties file */

package ABoxGen.InstanceGenerator;

import java.util.HashSet;

public class University {
    int collegeNum,i;
    College[] womenColleges, coEdColleges, colleges;
    Generator gen;
    int univIndex;
    String univCode,univName,univInstance,cityName;
    int womenCollegeNum, coEdCollegeNum , researchGroupNum;
    ResearchGroup[] researchGroups;
    HashSet<String> personPerUniversity = new HashSet();
    HashSet<String> universityName;
   
    String profile;

    public University(Generator gen, int univIndex) {
        this.gen = gen;
        this.profile=gen.profile;
        this.univIndex = univIndex;
        this.univInstance= "U" + this.univIndex;
        this.univCode= "U"+univIndex;
        this.universityName=gen.universityName;
        do {
            this.cityName =gen.map3.get(GetRandomNo.getRandomFromRange(0,37000));
           
        }while(universityName.contains(cityName));
        universityName.add(cityName);
        this.univName= "U" + univIndex + " University of " + cityName;//from data dictionary eg. University of London
        System.out.println( univName);
     
        //:U0 rdf:type :University
        gen.classAssertion(gen.getClass("University"),gen.getNamedIndividual(univInstance) );
        //:U0 :hasName :University of XYZ
        gen.dataPropertyAssertion(gen.getDataProperty("hasName"),gen.getNamedIndividual(univInstance),gen.getLiteral(univName));
        //:U0 :hasCode :U0
        gen.dataPropertyAssertion(gen.getDataProperty("hasCode"),gen.getNamedIndividual(univInstance),gen.getLiteral(univCode));
        //random research group from the defined range
        this.researchGroupNum= GetRandomNo.getRandomFromRange(gen.researchGroupNum_Min,gen.researchGroupNum_Max);
        this.researchGroups = new ResearchGroup[this.researchGroupNum];
        //random College number from the defined range
        this.collegeNum = GetRandomNo.getRandomFromRange(gen.collegeNum_Min,gen.collegeNum_Max);
      
        
        //random women college number from the defined range
        this.womenCollegeNum = GetRandomNo.getRandomFromRange(gen.womenCollegeNum_Min, gen.womenCollegeNum_Max);
        //coed colleges = total colleges- women colleges
        this.coEdCollegeNum = collegeNum- womenCollegeNum ;
        this.coEdColleges = new College[this.coEdCollegeNum];
        this.womenColleges = new College[this.womenCollegeNum];
        
        
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


