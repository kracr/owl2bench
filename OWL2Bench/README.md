## ABoxGenerator
The axioms generated from the code are the assertion axioms (class, object property and data property). First of all, the instances (class assertion axioms) for the University class are generated and their number is equal to the number of universities specified by the user. 

Then for each University class instance, instances for College, Research Group and Publication are generated. 

For each College, Department instances are generated. 

For each Department, instances of Man, Woman, Program, and Course are generated. 

The number of instances that are generated for each class is selected automatically and randomly from the range specified in the configuration file. The other Classes are defined (in the TBox) in such a way that the reasoner can draw inferences for them. For example, the code generates the instances of type Man and Woman and reasoner can infer instances of type Person from this (since Person class is a union of Man and Woman).

Property assertion axioms are created using these instances. For example, an object property isDepartmentOf links a Department instance to a College instance. Similarly, a data property hasName is used to connect a department name to a Department instance. The reasoner uses all such assertion axioms to draw inferences. The code links a Man/Woman instances with a Department instance using the object property 'worksFor'. Using this, the reasoner can infer all the instances of type Employee since an Employee is a Person who works for some organization (Person is a union of Man and Woman, Department is subclassOf Organization, etc). Similarly all the other classes and properties. The number of instances of each class (other than University) and the number of connections between all the instances are again selected automatically and randomly from the range specified in the configuration file. 

## Usage

The user must have java and maven installed in the system. The user needs to provide two inputs, the number of universities and the OWL 2 profile (EL, QL, RL, DL) of interest. The ABox axioms are generated using OWL2Bench code, complying with the schema defined in the TBox of the selected profile. The size of the ABox depends on the number of universities. The final dataset on which the reasoners are evaluated consists of both TBox and ABox axioms.

# 1. Direct execution using executable jar :

We have provided an executable jar file that generates the datasets with default configurations (used in the experiments). In order to execute this Jar file, user need to give the inputs (in the same order): **Number of Universities** (mandatory), **Required OWL 2 Profile** (mandatory), and Seed (optional). 

For eg. : 

java -jar OWL2Bench.jar 10 EL 20 (where 10 is the number of universities,  EL is OWL 2 profile and 20 is the seed value)

java -jar OWL2Bench.jar 1 DL (where 1 is the number of universities,  DL is OWL 2 profile and the default seed value)
         
Number of universities makes the ABox scalable. By default, the number of ABox axioms for 1 university is approximately 50,000 that reaches upto 14,000,000 for 200 universities.      
To execute **OWL2Bench.jar** make sure the TBox for all profiles (UNIV-BENCH-OWL2EL.owl, UNIV-BENCH-OWL2QL.owl, UNIV-BENCH-OWL2RL.owl, UNIV-BENCH-OWL2DL.owl) and excel file for random names RandomNames.xlsx is present in the same directory as jar file. 


# 2. Using Source Code :

We are also providing the java code for ABox generation. User can download the project repository OWL2Bench. Extract it and save it in a folder. Open command line and change to the directory that contains the pom.xml of the project. Execute the maven command:

mvn compile

mvn install

Now, using maven's exec plugin, run the main class *Generator* and pass the list of arguments *Number of Universities, Required OWL 2 Profile and Seed* (same as above) using exec.args. For example-

mvn exec:java -Dexec.mainClass=ABoxGen.InstanceGenerator.Generator -Dexec.args="1 QL 1"

The output files are stored in files with names such as "OWL2"+ Profile + "-" + Number of Universities + ".owl" . For example. OWL2DL-1.owl, OWL2QL-1.owl, OWL2EL-10.owl, OWL2RL-100.owl. 

**Note:** 

Since we are providing the seed value as one of the inputs to the ABox generation algorithm along with the number of Universities and the desired OWL2 profile, the same set of instances is generated if the seed value remains the same across multiple runs. If the user does not specify the seed value then the default seed value of 1 will be used. Similar principle was used in UOBM as well.

In order to change the default size of the generated ABox as well as to control the density of each node (number of connections between different instances), the range (maximum and minimum values of the parameters) can be modified in the configuration file. Moreover, the output ontology format can also be specified in the configuration file (owx for OWL_XML, ofn for OWL_Functional, omn for OWL_Manchester, ttl for Turtle, rdf for RDF_XML). By default, the generated ontology format is RDF/XML.




           
