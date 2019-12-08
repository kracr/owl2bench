package ABoxGen.InstanceGenerator;


public class ResearchGroup {
    String researchGroupInstance,researchProjectInstance,RAInstance,profile;
    Generator gen;
    Person person;
    int RANum;
    ConfigFile configFile;
    public ResearchGroup(University university,int index) {
        configFile=new ConfigFile();
        this.profile=university.profile;
        this.researchGroupInstance = university.univInstance + "ResearchGroup" + index;
        this.researchProjectInstance =  researchGroupInstance+ "ResearchProject";
        this.gen=university.gen;
        gen.classAssertion(gen.getClass("ResearchGroup"),gen.getNamedIndividual(researchGroupInstance) );
        //gen.classAssertion(gen.getClass("ResearchGroup"), gen.getNamedIndividual(researchGroupInstance));
        gen.classAssertion(gen.getClass("ResearchProject"), gen.getNamedIndividual(researchProjectInstance));
        gen.objectPropertyAssertion(gen.getObjectProperty("hasResearchGroup"), gen.getNamedIndividual(university.univInstance), gen.getNamedIndividual(researchGroupInstance));
        gen.objectPropertyAssertion(gen.getObjectProperty("hasResearchProject"), gen.getNamedIndividual(researchGroupInstance), gen.getNamedIndividual(researchProjectInstance));
        this.RANum = GetRandomNo.getRandomFromRange(gen.RANum_Min,gen.RANum_Max);
        for (int i = 0; i < this.RANum; ++i) {
            this.RAInstance =  researchGroupInstance+ "ResearchAssistant" + i;
            //System.out.print(RAInstance);
            //gen.classAssertion(gen.getClass("ResearchAssistant"), gen.getNamedIndividual(RAInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("hasResearchAssistant"), gen.getNamedIndividual(researchGroupInstance),gen.getNamedIndividual(RAInstance));
            this.person=new Person(this , RAInstance );
        }
    }
}
