package ABoxGen.InstanceGenerator;


public class Course {
Generator gen;
String departmentInstance,courseInstance;

    public Course(Department department,int courseIndex, String category){

        this.gen=department.gen;
        this.departmentInstance=department.departmentInstance;
        this.courseInstance=departmentInstance + category + department.deptName + courseIndex;
        gen.classAssertion(gen.getClass("Course"),gen.getNamedIndividual(courseInstance));
        gen.objectPropertyAssertion(gen.getObjectProperty("offerCourse"),gen.getNamedIndividual(departmentInstance),gen.getNamedIndividual(courseInstance));

    }
}
