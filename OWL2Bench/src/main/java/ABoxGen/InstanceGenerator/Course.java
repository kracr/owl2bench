/**generates Courses offered in Each Department
* In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the config.properties file */

package ABoxGen.InstanceGenerator;


public class Course {
Generator gen;
Employee[] faculty;
String departmentInstance,courseInstance,facultyInstance,category,type;

    public Course(Department department,int courseIndex, String category){

        this.gen=department.gen;
        this.faculty=department.faculty;
        this.departmentInstance=department.departmentInstance;
        if(category=="UGCourse") {
        	this.type="UGC";
        }
        else if (category=="ElectiveCourse")
        {
        this.type="EC";	
        }
        this.courseInstance=departmentInstance + this.type + courseIndex;
        facultyInstance=faculty[courseIndex].employeeInstance;
        gen.classAssertion(gen.getClass(category),gen.getNamedIndividual(courseInstance));
        gen.objectPropertyAssertion(gen.getObjectProperty("offerCourse"),gen.getNamedIndividual(departmentInstance),gen.getNamedIndividual(courseInstance));
        gen.objectPropertyAssertion(gen.getObjectProperty("isTaughtBy"),gen.getNamedIndividual(courseInstance),gen.getNamedIndividual(facultyInstance));

    }
}
