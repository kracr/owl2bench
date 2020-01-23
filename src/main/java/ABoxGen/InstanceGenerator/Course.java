//generates courses offered in each department
/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */

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
