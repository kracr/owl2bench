//Generates student instances. A student could be of type UG, PG and PhD. Adds axioms of type enrollIn, enrollFor and hasMajor for each student. 
/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */

package ABoxGen.InstanceGenerator;


public class Student {

    Boolean womenStudents;
    String departmentInstance, studentInstance, programInstance;
    String deptName,profile;
    Generator gen;
    Person person;

    public Student(Department department,int studentIndex,String program) {
    	this.profile=department.profile;
        this.womenStudents = department.womenStudents; //if !women student, also assign male or female
        this.departmentInstance = department.departmentInstance;
        this.programInstance = department.programInstance;
        this.gen = department.gen;
        this.deptName = department.deptName;

        
            department.personPerUniversity.add(studentInstance);
            if (program == "UG"){
            	//UGWS for UG Women Student
            	if (womenStudents) {
                    this.studentInstance = departmentInstance  + "UGWS" + studentIndex;
                }
                else
                	//UGS for UG Student
                {
                    this.studentInstance = departmentInstance  + "UGS" + studentIndex;
                }
            //gen.classAssertion(gen.getClass("Student"),gen.getNamedIndividual(studentInstance));
            this.person=new Person(this, studentInstance);
            this.programInstance = department.ugProgram.programInstance;
            gen.objectPropertyAssertion(gen.getObjectProperty("enrollIn"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(departmentInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("hasMajor"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(deptName));
            gen.objectPropertyAssertion(gen.getObjectProperty("enrollFor"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(programInstance));
        }
            else if (program == "PG")
            {
            	
            	if (womenStudents) {
                    this.studentInstance = departmentInstance  + "PGWS" + studentIndex;
                }
                else
                {
                    this.studentInstance = departmentInstance  + "PGS" + studentIndex;
                }
                //gen.classAssertion(gen.getClass("Student"),gen.getNamedIndividual(studentInstance));
                this.person=new Person(this, studentInstance);
                this.programInstance = department.pgProgram.programInstance;
                gen.objectPropertyAssertion(gen.getObjectProperty("enrollIn"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(departmentInstance));
                gen.objectPropertyAssertion(gen.getObjectProperty("hasMajor"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(deptName));
                gen.objectPropertyAssertion(gen.getObjectProperty("enrollFor"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(programInstance));

            }
            else if (program == "PhD")
            {
            	
            	if (womenStudents) {
                    this.studentInstance = departmentInstance  + "PhDWS" + studentIndex;
                }
                else
                {
                    this.studentInstance = departmentInstance  + "PhDS" + studentIndex;
                }
                //gen.classAssertion(gen.getClass("Student"),gen.getNamedIndividual(studentInstance));
                this.person=new Person(this, studentInstance);
                this.programInstance = department.phdProgram.programInstance;
                gen.objectPropertyAssertion(gen.getObjectProperty("enrollIn"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(departmentInstance));
                gen.objectPropertyAssertion(gen.getObjectProperty("hasMajor"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(deptName));
                gen.objectPropertyAssertion(gen.getObjectProperty("enrollFor"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(programInstance));

            }


        }

    }

