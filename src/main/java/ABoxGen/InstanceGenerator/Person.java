package ABoxGen.InstanceGenerator;


import org.apache.tools.ant.types.resources.First;

public class Person {
    Generator gen;
    int gender;
    String profile;
    //assign man or woman
    //assign student/faculty/staff
    public Person(Student student, String instance) {
        this.gen=student.gen;
        this.profile=student.profile;
        
       
        
        if(!student.womenStudents){
        	
        	if (profile=="DL" || profile=="RL") {
            gender = GetRandomNo.getRandomFromRange(0,1);
            if(gender==0)
                gen.classAssertion(gen.getClass("Woman"),gen.getNamedIndividual(instance));
            else
                gen.classAssertion(gen.getClass("Man"),gen.getNamedIndividual(instance));}
        	else 
        		gen.classAssertion(gen.getClass("Person"),gen.getNamedIndividual(instance));
        }
        else {
            gen.classAssertion(gen.getClass("Woman"),gen.getNamedIndividual(instance));
        }
        String firstName=gen.map1.get(GetRandomNo.getRandomFromRange(0,30000));
        String lastName= gen.map2.get(GetRandomNo.getRandomFromRange(0,150000));
        String name= firstName+ " " + lastName;
        //System.out.println("name"+ name);

        gen.dataPropertyAssertion(gen.getDataProperty("hasFirstName"),gen.getNamedIndividual(instance),gen.getLiteral(firstName));
        gen.dataPropertyAssertion(gen.getDataProperty("hasLastName"),gen.getNamedIndividual(instance),gen.getLiteral(lastName));
        gen.dataPropertyAssertion(gen.getDataProperty("hasName"),gen.getNamedIndividual(instance),gen.getLiteral(name));
        gen.dataPropertyAssertion(gen.getDataProperty("hasEmailAddress"),gen.getNamedIndividual(instance),gen.getLiteral(instance + "@bench.com"));
        gen.dataPropertyAssertion(gen.getDataProperty("hasTelephone"),gen.getNamedIndividual(instance),gen.getLiteral("xxxxxxxxxx"));
        gen.dataPropertyAssertion(gen.getDataProperty("hasID"),gen.getNamedIndividual(instance),gen.getLiteral(instance));
        gen.dataPropertyAssertion(gen.getDataProperty("hasAge"),gen.getNamedIndividual(instance),gen.getLiteral("x"));
    }

    public Person(Employee employee, String instance) {
        this.gen=employee.gen;
        this.profile=employee.profile;
            gender = GetRandomNo.getRandomFromRange(0,1);
            if(gender==0)
                gen.classAssertion(gen.getClass("Woman"),gen.getNamedIndividual(instance));
            else
                gen.classAssertion(gen.getClass(
                        "Man"),gen.getNamedIndividual(instance));

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
    public Person(ResearchGroup researchGroup, String instance) {
        this.gen=researchGroup.gen;
        this.profile=researchGroup.profile;

        gender = GetRandomNo.getRandomFromRange(0,1);
        if(gender==0)
        {
            gen.classAssertion(gen.getClass("Woman"),gen.getNamedIndividual(instance));
        	//System.out.println(instance);
            }
        else
            {gen.classAssertion(gen.getClass("Man"),gen.getNamedIndividual(instance));
        	//System.out.println(instance);
            }

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