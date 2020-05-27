/**Each department has UG,PG or PhD Programs. 
* In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the config.properties file */

package ABoxGen.InstanceGenerator;


public class Program {

    Generator gen;
    String programInstance, departmentInstance;



    public Program(Department department,String type) {

        this.departmentInstance = department.departmentInstance;
        this.gen = department.gen;
        this.programInstance= departmentInstance +  type;
        gen.classAssertion(gen.getClass(type+"Program"),gen.getNamedIndividual(programInstance));
        gen.objectPropertyAssertion(gen.getObjectProperty("hasProgram"),gen.getNamedIndividual(departmentInstance ),gen.getNamedIndividual(programInstance));

    }
}
