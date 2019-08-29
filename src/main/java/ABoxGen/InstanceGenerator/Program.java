package ABoxGen.InstanceGenerator;


public class Program {

    Generator gen;
    String programInstance, departmentInstance;



    public Program(Department department,String type) {

        this.departmentInstance = department.departmentInstance;
        this.gen = department.gen;
        this.programInstance= departmentInstance +  type + "prog";
        gen.classAssertion(gen.getClass("Program"),gen.getNamedIndividual(programInstance));
        gen.objectPropertyAssertion(gen.getObjectProperty("hasProgram"),gen.getNamedIndividual(departmentInstance ),gen.getNamedIndividual(programInstance));

    }
}
