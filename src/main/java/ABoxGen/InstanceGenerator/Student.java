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
            	
            	if (womenStudents) {
                    this.studentInstance = departmentInstance  + "UGwomenStudent" + studentIndex;
                }
                else
                {
                    this.studentInstance = departmentInstance  + "UGstudent" + studentIndex;
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
                    this.studentInstance = departmentInstance  + "PGwomenStudent" + studentIndex;
                }
                else
                {
                    this.studentInstance = departmentInstance  + "PGstudent" + studentIndex;
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
                    this.studentInstance = departmentInstance  + "PhDwomenStudent" + studentIndex;
                }
                else
                {
                    this.studentInstance = departmentInstance  + "PhDstudent" + studentIndex;
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

