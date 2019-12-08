package ABoxGen.InstanceGenerator;


public class Course {
Generator gen;
Employee[] faculty;
String departmentInstance,courseInstance,facultyInstance;

    public Course(Department department,int courseIndex, String category){

        this.gen=department.gen;
        this.faculty=department.faculty;
        this.departmentInstance=department.departmentInstance;
        this.courseInstance=departmentInstance + category + department.deptName + courseIndex;
        facultyInstance=faculty[courseIndex].employeeInstance;
        gen.classAssertion(gen.getClass(category),gen.getNamedIndividual(courseInstance));
        gen.objectPropertyAssertion(gen.getObjectProperty("offerCourse"),gen.getNamedIndividual(departmentInstance),gen.getNamedIndividual(courseInstance));
        gen.objectPropertyAssertion(gen.getObjectProperty("isTaughtBy"),gen.getNamedIndividual(courseInstance),gen.getNamedIndividual(facultyInstance));

    }
}
