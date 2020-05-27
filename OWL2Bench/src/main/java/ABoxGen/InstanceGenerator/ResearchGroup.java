/**assigns RAs and project name to each research group
* In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the config.properties file */

package ABoxGen.InstanceGenerator;


public class ResearchGroup {
    String researchGroupInstance,researchProjectInstance,RAInstance,profile;
    Generator gen;
    Person person;
    int RANum;
    //ConfigFile configFile;
    public ResearchGroup(University university,int index) {
        //configFile=new ConfigFile();
        this.profile=university.profile;
        this.researchGroupInstance = university.univInstance + "RG" + index;
        this.researchProjectInstance =  researchGroupInstance+ "RP";
        this.gen=university.gen;
        gen.classAssertion(gen.getClass("ResearchGroup"),gen.getNamedIndividual(researchGroupInstance) );
        //gen.classAssertion(gen.getClass("ResearchGroup"), gen.getNamedIndividual(researchGroupInstance));
        gen.classAssertion(gen.getClass("ResearchProject"), gen.getNamedIndividual(researchProjectInstance));
        gen.objectPropertyAssertion(gen.getObjectProperty("hasResearchGroup"), gen.getNamedIndividual(university.univInstance), gen.getNamedIndividual(researchGroupInstance));
        gen.objectPropertyAssertion(gen.getObjectProperty("hasResearchProject"), gen.getNamedIndividual(researchGroupInstance), gen.getNamedIndividual(researchProjectInstance));
        this.RANum = GetRandomNo.getRandomFromRange(gen.RANum_Min,gen.RANum_Max);
        for (int i = 0; i < this.RANum; ++i) {
            this.RAInstance =  researchGroupInstance+ "RA" + i;
            //System.out.print(RAInstance);
            //gen.classAssertion(gen.getClass("ResearchAssistant"), gen.getNamedIndividual(RAInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("hasResearchAssistant"), gen.getNamedIndividual(researchGroupInstance),gen.getNamedIndividual(RAInstance));
            this.person=new Person(this , RAInstance );
        }
    }
}
