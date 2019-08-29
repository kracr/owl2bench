package ABoxGen.InstanceGenerator;


public class Student {

    Boolean womenStudents;
    String departmentInstance, studentInstance, programInstance;
    String deptName;
    Generator gen;
    Person person;

    public Student(Department department,int studentIndex,String program) {

        this.womenStudents = department.womenStudents; //if !women student, also assign male or female
        this.departmentInstance = department.departmentInstance;
        this.programInstance = department.programInstance;
        this.gen = department.gen;
        this.deptName = department.deptName;

        if (womenStudents) {
            this.studentInstance = departmentInstance  + "womenStudent" + studentIndex;
        }
        else
        {
            this.studentInstance = departmentInstance  + "student" + studentIndex;
        }
            department.personPerUniversity.add(studentInstance);
            if (program == "UG"){
            gen.classAssertion(gen.getClass("UGStudent"),gen.getNamedIndividual(studentInstance));
            this.person=new Person(this, studentInstance);
            gen.objectPropertyAssertion(gen.getObjectProperty("enrollIn"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(departmentInstance));
            gen.objectPropertyAssertion(gen.getObjectProperty("hasMajor"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(deptName));
            gen.objectPropertyAssertion(gen.getObjectProperty("enrollFor"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(programInstance));
        }
            else if (program == "PG")
            {
                gen.classAssertion(gen.getClass("PGStudent"),gen.getNamedIndividual(studentInstance));
                this.person=new Person(this, studentInstance);
                gen.objectPropertyAssertion(gen.getObjectProperty("enrollIn"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(departmentInstance));
                gen.objectPropertyAssertion(gen.getObjectProperty("hasMajor"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(deptName));
                gen.objectPropertyAssertion(gen.getObjectProperty("enrollFor"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(programInstance));

            }
            else if (program == "PhD")
            {
                gen.classAssertion(gen.getClass("PhDStudent"),gen.getNamedIndividual(studentInstance));
                this.person=new Person(this, studentInstance);
                gen.objectPropertyAssertion(gen.getObjectProperty("enrollIn"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(departmentInstance));
                gen.objectPropertyAssertion(gen.getObjectProperty("hasMajor"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(deptName));
                gen.objectPropertyAssertion(gen.getObjectProperty("enrollFor"),gen.getNamedIndividual(studentInstance),gen.getNamedIndividual(programInstance));

            }


        }

    }

