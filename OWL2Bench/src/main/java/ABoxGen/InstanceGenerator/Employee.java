/** adds axioms 'isEmployeeOf' and 'hasResearchInterest' for employees
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */

package ABoxGen.InstanceGenerator;
public class Employee {
    Generator gen;
    Person person;
    String employeeInstance,departmentInstance,profile ;

    public Employee(Department department,int empIndex, String employeeType, String type) {
        this.gen=department.gen;
        this.profile=department.profile;
        this.departmentInstance=department.departmentInstance;
        this.employeeInstance=departmentInstance+ type + empIndex;
        department.personPerUniversity.add(employeeInstance);
        //gen.classAssertion(gen.getClass(employeeType),gen.getNamedIndividual(employeeInstance));
        this.person=new Person(this , employeeInstance);
        gen.objectPropertyAssertion(gen.getObjectProperty("is" + employeeType+ "Of"),gen.getNamedIndividual(employeeInstance),gen.getNamedIndividual(departmentInstance));
        if(employeeType=="FullProfessor" || employeeType=="AssistantProfessor" || employeeType=="AssociateProfessor" || employeeType=="VisitingProfessor" || employeeType=="PostDoc" || employeeType=="Lecturer" )
        {
        	//research interest for each faculty
            gen.dataPropertyAssertion(gen.getDataProperty("hasResearchInterest"),gen.getNamedIndividual(employeeInstance),gen.getLiteral("SomeResearchTopic"));
        }
    }
}


