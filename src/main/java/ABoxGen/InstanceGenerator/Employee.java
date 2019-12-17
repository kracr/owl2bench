package ABoxGen.InstanceGenerator;

public class Employee {
    Generator gen;
    Person person;
    String employeeInstance,departmentInstance,profile ;

    public Employee(Department department,int empIndex, String employeeType) {
        this.gen=department.gen;
        this.profile=department.profile;
        this.departmentInstance=department.departmentInstance;
        this.employeeInstance=departmentInstance+ employeeType + empIndex;
        department.personPerUniversity.add(employeeInstance);
        //gen.classAssertion(gen.getClass(employeeType),gen.getNamedIndividual(employeeInstance));
        this.person=new Person(this , employeeInstance);
        gen.objectPropertyAssertion(gen.getObjectProperty("is" + employeeType+ "Of"),gen.getNamedIndividual(employeeInstance),gen.getNamedIndividual(departmentInstance));
        if(employeeType=="FullProfessor" || employeeType=="AssistantProfessor" || employeeType=="AssociateProfessor" || employeeType=="VisitingProfessor" || employeeType=="PostDoc" || employeeType=="Lecturer" )
        {
            gen.dataPropertyAssertion(gen.getDataProperty("hasResearchInterest"),gen.getNamedIndividual(employeeInstance),gen.getLiteral("SomeResearchTopic"));
        }
    }
}


