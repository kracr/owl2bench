/**
 * Generates Class Assertion Axioms for Man and Woman Instances. 
 * Each Man and Woman instance generates some Data Property Axioms. 
 * also, these Man and Woman instances are connected to different object and data property assertion axioms.
 * Using these instances and properties, Reasoners can infer instances of sub-classes of Person ( such as Student, Employee( Faculty and Staff) and RA)  
 * In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the config.properties file */

package ABoxGen.InstanceGenerator;


import org.apache.tools.ant.types.resources.First;

public class Person {
    Generator gen;
    int gender,narcissism;
    String profile;
    //assign man or woman
    //assign student/faculty/staff
  //Add axioms related to Students (gender, name age etc)
    public Person(Student student, String instance) {
        this.gen=student.gen;
        this.profile=student.profile;
        //if ((profile.matches("DL")) || (profile.matches("EL"))) {
        narcissism = GetRandomNo.getRandomFromRange(0,10000);
        if (narcissism==555) {
        	//gen.objectPropertyAssertion(gen.getObjectProperty("loves"),gen.getNamedIndividual(instance),gen.getNamedIndividual(instance));
        }
        
        //For a Women College, All the student instance would be Women, while for Co-ed Colleges, Man and Woman Students are generated with 0.5 probability
        if(!student.womenStudents){
        	//System.out.println("coed assigned");
        		
            gender = GetRandomNo.getRandomFromRange(0,1);
            //Assign man or woman to a person instance . Person is Man or Woman ( for DL and RL only)
            if(gender==0) {
                gen.classAssertion(gen.getClass("Woman"),gen.getNamedIndividual(instance));
                //System.out.println("coed assigned 1");
                }
            else {
                gen.classAssertion(gen.getClass("Man"),gen.getNamedIndividual(instance));}
            //System.out.println("coed assigned 2");
        }
        else {
        	//System.out.println("woman assigned");
            gen.classAssertion(gen.getClass("Woman"),gen.getNamedIndividual(instance));
        }
        String firstName=gen.map1.get(GetRandomNo.getRandomFromRange(0,30000));
        String lastName= gen.map2.get(GetRandomNo.getRandomFromRange(0,150000));
     
        String name= firstName+ " " + lastName;
        
        //object and data property assertion axioms for each person
        gen.dataPropertyAssertion(gen.getDataProperty("hasFirstName"),gen.getNamedIndividual(instance),gen.getLiteral(firstName));
        gen.dataPropertyAssertion(gen.getDataProperty("hasLastName"),gen.getNamedIndividual(instance),gen.getLiteral(lastName));
        gen.dataPropertyAssertion(gen.getDataProperty("hasName"),gen.getNamedIndividual(instance),gen.getLiteral(name));
        gen.dataPropertyAssertion(gen.getDataProperty("hasEmailAddress"),gen.getNamedIndividual(instance),gen.getLiteral(instance + "@bench.com"));
        gen.dataPropertyAssertion(gen.getDataProperty("hasTelephone"),gen.getNamedIndividual(instance),gen.getLiteral("xxxxxxxxxx"));
        gen.dataPropertyAssertion(gen.getDataProperty("hasID"),gen.getNamedIndividual(instance),gen.getLiteral(instance));
        gen.dataPropertyAssertion(gen.getDataProperty("hasAge"),gen.getNamedIndividual(instance),gen.getLiteral("x"));
    }
  //Add axioms related to Employees (gender, name age etc)
    public Person(Employee employee, String instance) {
        this.gen=employee.gen;
        this.profile=employee.profile;
     
       // if ((profile.matches("DL")) || (profile.matches("EL"))) {
        narcissism = GetRandomNo.getRandomFromRange(0,10000);
        if (narcissism==555) {
        	//gen.objectPropertyAssertion(gen.getObjectProperty("loves"),gen.getNamedIndividual(instance),gen.getNamedIndividual(instance));
        }
        
       
  
            gender = GetRandomNo.getRandomFromRange(0,1);
            if(gender==0)
                gen.classAssertion(gen.getClass("Woman"),gen.getNamedIndividual(instance));
            else
                gen.classAssertion(gen.getClass("Man"),gen.getNamedIndividual(instance));
    

        String firstName=gen.map1.get(GetRandomNo.getRandomFromRange(0,30000));
        String lastName= gen.map2.get(GetRandomNo.getRandomFromRange(0,150000));
        String name= firstName+ " " + lastName;

        gen.dataPropertyAssertion(gen.getDataProperty("hasFirstName"),gen.getNamedIndividual(instance),gen.getLiteral(firstName));
        gen.dataPropertyAssertion(gen.getDataProperty("hasLastName"),gen.getNamedIndividual(instance),gen.getLiteral(lastName));
        gen.dataPropertyAssertion(gen.getDataProperty("hasName"),gen.getNamedIndividual(instance),gen.getLiteral(name));
        gen.dataPropertyAssertion(gen.getDataProperty("hasEmailAddress"),gen.getNamedIndividual(instance),gen.getLiteral(instance + "@bench.com"));
        gen.dataPropertyAssertion(gen.getDataProperty("hasTelephone"),gen.getNamedIndividual(instance),gen.getLiteral("xxxxxxxxxx"));
        gen.dataPropertyAssertion(gen.getDataProperty("hasID"),gen.getNamedIndividual(instance),gen.getLiteral(instance));
        gen.dataPropertyAssertion(gen.getDataProperty("hasAge"),gen.getNamedIndividual(instance),gen.getLiteral("x"));
    }
    
    //Add axioms related to Research Assistants (gender, name age etc)
    public Person(ResearchGroup researchGroup, String instance) {
        this.gen=researchGroup.gen;
        this.profile=researchGroup.profile;
       // if ((profile.matches("DL")) || (profile.matches("EL"))) {
        narcissism = GetRandomNo.getRandomFromRange(0,10000);
        if (narcissism==555) {
        	//gen.objectPropertyAssertion(gen.getObjectProperty("loves"),gen.getNamedIndividual(instance),gen.getNamedIndividual(instance));
        }
        
       
      
        	//System.out.println("manwoamn3");
            gender = GetRandomNo.getRandomFromRange(0,1);
            if(gender==0)
                gen.classAssertion(gen.getClass("Woman"),gen.getNamedIndividual(instance));
            else
                gen.classAssertion(gen.getClass("Man"),gen.getNamedIndividual(instance));

        String firstName=gen.map1.get(GetRandomNo.getRandomFromRange(0,30000));
        String lastName= gen.map2.get(GetRandomNo.getRandomFromRange(0,150000));
        String name= firstName+ " " + lastName;
        gen.dataPropertyAssertion(gen.getDataProperty("hasFirstName"),gen.getNamedIndividual(instance),gen.getLiteral(firstName));
        gen.dataPropertyAssertion(gen.getDataProperty("hasLastName"),gen.getNamedIndividual(instance),gen.getLiteral(lastName));
        gen.dataPropertyAssertion(gen.getDataProperty("hasName"),gen.getNamedIndividual(instance),gen.getLiteral(name));
        gen.dataPropertyAssertion(gen.getDataProperty("hasEmailAddress"),gen.getNamedIndividual(instance),gen.getLiteral(instance + "@bench.com"));
        gen.dataPropertyAssertion(gen.getDataProperty("hasTelephone"),gen.getNamedIndividual(instance),gen.getLiteral("xxxxxxxxxx"));
        gen.dataPropertyAssertion(gen.getDataProperty("hasID"),gen.getNamedIndividual(instance),gen.getLiteral(instance));
        gen.dataPropertyAssertion(gen.getDataProperty("hasAge"),gen.getNamedIndividual(instance),gen.getLiteral("x"));
    }
}

//add random likes for games//crazy about //fan etc instances