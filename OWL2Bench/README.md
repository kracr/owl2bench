# ABoxGenerator
The axioms generated from the code are the assertion axioms (class, object property and data property). First of all, the instances (class assertion axioms) for the University class are generated and their number is equal to the number of universities specified by the user. 

Then for each University class instance, instances for College, Research Group and Publication are generated. 

For each College, Department instances are generated. 

For each Department, instances of Man, Woman, Program, and Course are generated. 

The number of instances that are generated for each class is selected automatically and randomly from the range specified in the configuration file. The other Classes are defined (in the TBox) in such a way that the reasoner can draw inferences for them. For example, the code generates the instances of type Man and Woman and reasoner can infer instances of type Person from this (since Person class is a union of Man and Woman).

Property assertion axioms are created using these instances. For example, an object property isDepartmentOf links a Department instance to a College instance. Similarly, a data property hasName is used to connect a department name to a Department instance. The reasoner uses all such assertion axioms to draw inferences. The code links a Man/Woman instances with a Department instance using the object property 'worksFor'. Using this, the reasoner can infer all the instances of type Employee since an Employee is a Person who works for some organization (Person is a union of Man and Woman, Department is subclassOf Organization, etc). Similarly all the other classes and properties. The number of instances of each class (other than University) and the number of connections between all the instances are again selected automatically and randomly from the range specified in the configuration file. 

In order to change the size of the generated ABox as well as to control the density (number of connections between different instances), the range (maximum and minimum values of the parameters) can be modified in the configuration file. Moreover, the output ontology format can also be specified in the configuration file. By default, the generated ontology format is RDF/XML.



           
