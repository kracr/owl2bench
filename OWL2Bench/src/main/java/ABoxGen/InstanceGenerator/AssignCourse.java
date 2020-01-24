//Assign courses to each student, faculty and teaching assistants
/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */

package ABoxGen.InstanceGenerator;

import java.util.HashSet;
import java.util.Iterator;

public class AssignCourse {
    University university;
    Generator gen;
    String course,courseTA;
    College clg;
    Department dept;
    GetRandomCourse getRandomCourse;
    ConfigFile configFile;
    int numOfElectivesOutsideDept, numOfElectivesWithInDept,numOfElectives,courseOutsideDepartment,courseWithInDepartment;
    HashSet<String> hash1, hash2, hash3;

    //student takes 4 mandatory ug courses, and minimum 2 elective courses
    public AssignCourse(College college) {
        this.gen = college.gen;
        this.clg = college;
        this.getRandomCourse = new GetRandomCourse();
        configFile=new ConfigFile();
        for (int i = 0; i < college.deptNum; ++i) {
            dept = college.depts[i];
            //for ug students
            for (int j = 0; j < dept.ugStudentNum; ++j) {

                hash1=new HashSet();
                hash2=new HashSet();
                hash3=new HashSet();
                for (int k = 0; k < 4; ++k) {
                    course = getRandomCourse.getRandomUGCourse(clg,i);
                    if (course != null) {
                        hash1.add(course);
                    }
                }
                Iterator<String> l = hash1.iterator();
                while (l.hasNext()) {
                    gen.objectPropertyAssertion(gen.getObjectProperty("takesCourse"),gen.getNamedIndividual(dept.ugStudents[j].studentInstance),gen.getNamedIndividual(l.next()));
                }
                numOfElectives = GetRandomNo.getRandomFromRange(gen.numOfElectives_Min,gen.numOfElectives_Max);
                numOfElectivesOutsideDept = GetRandomNo.getRandomFromRange(gen.numOfElectivesOutsideDept_Min,numOfElectives);
                numOfElectivesWithInDept = numOfElectives - numOfElectivesOutsideDept;


                if (numOfElectivesWithInDept != 0) {
                    while (hash2.size() != numOfElectivesWithInDept) {
                        course = getRandomCourse.getRandomElectiveWithInDept(clg,i);
                        if (course != null) {
                            hash2.add(course);
                        }
                    }
                    Iterator<String> m = hash2.iterator();
                    while (m.hasNext()) {
                        gen.objectPropertyAssertion(gen.getObjectProperty("takesCourse"),gen.getNamedIndividual(dept.ugStudents[j].studentInstance),gen.getNamedIndividual(m.next()));
                    }
                    }

                while (hash3.size() != numOfElectivesOutsideDept) {
                    course = getRandomCourse.getRandomElectiveOutsideDept(clg,i); //i is dept index so that course is not picked frm same deptt
                    if (course != null) {
                        hash3.add(course);
                    }
                }
                Iterator<String> n = hash3.iterator();
                while (n.hasNext()) {
                    gen.objectPropertyAssertion(gen.getObjectProperty("takesCourse"),gen.getNamedIndividual(dept.ugStudents[j].studentInstance),gen.getNamedIndividual(n.next()));
                }
            }

            //for pg student

            for (int j = 0; j < dept.pgStudentNum; ++j) {
                hash2=new HashSet();
                hash3=new HashSet();
                numOfElectives = GetRandomNo.getRandomFromRange(gen.numOfElectives_Min,gen.numOfElectives_Max);
                numOfElectivesOutsideDept = GetRandomNo.getRandomFromRange(gen.numOfElectivesOutsideDept_Min,numOfElectives);
                numOfElectivesWithInDept = numOfElectives - numOfElectivesOutsideDept;
                if (numOfElectivesWithInDept != 0) {
                    while (hash2.size() != numOfElectivesWithInDept) {
                        course = getRandomCourse.getRandomElectiveWithInDept(clg,i);
                        if (course != null) {
                            hash2.add(course);
                        }
                    }
                    Iterator<String> l = hash2.iterator();
                    while (l.hasNext()) {
                        gen.objectPropertyAssertion(gen.getObjectProperty("takesCourse"),gen.getNamedIndividual(dept.pgStudents[j].studentInstance),gen.getNamedIndividual(l.next()));
                    }
                }

                while (hash3.size() != numOfElectivesOutsideDept) {
                    course = getRandomCourse.getRandomElectiveOutsideDept(clg,i); //i is dept index so that course is not picked frm same deptt
                    if (course != null) {
                        hash3.add(course);
                    }
                }
                Iterator<String> l = hash3.iterator();
                while (l.hasNext()) {
                    gen.objectPropertyAssertion(gen.getObjectProperty("takesCourse"),gen.getNamedIndividual(dept.pgStudents[j].studentInstance),gen.getNamedIndividual(l.next()));
                }

                gen.classAssertion(gen.getClass("TeachingAssistant"),gen.getNamedIndividual(dept.pgStudents[j].studentInstance) );
                do {
                    if (GetRandomNo.getRandomFromRange(0,1) == 0) {
                        courseTA = getRandomCourse.getRandomElectiveWithInDept(clg,i);
                    } else {
                        courseTA = getRandomCourse.getRandomUGCourse(clg,i);
                    }
                }while(hash2.contains(courseTA)|| hash3.contains(courseTA) );
                gen.objectPropertyAssertion(gen.getObjectProperty("isTeachingAssistantOf"),gen.getNamedIndividual(dept.pgStudents[j].studentInstance),gen.getNamedIndividual(courseTA));

            }
//phd course
            for (int j = 0; j < dept.phdStudentNum; ++j) {
                hash2=new HashSet();
                hash3=new HashSet();
                numOfElectives = GetRandomNo.getRandomFromRange(gen.numOfElectives_Min,gen.numOfElectives_Max);
                numOfElectivesOutsideDept = GetRandomNo.getRandomFromRange(gen.numOfElectivesOutsideDept_Min,numOfElectives);
                numOfElectivesWithInDept = numOfElectives - numOfElectivesOutsideDept;
                if (numOfElectivesWithInDept != 0) {
                    while (hash2.size() != numOfElectivesWithInDept) {
                        course = getRandomCourse.getRandomElectiveWithInDept(clg,i);
                        if (course != null) {
                            hash2.add(course);
                        }
                    }
                    Iterator<String> l = hash2.iterator();
                    while (l.hasNext()) {
                        gen.objectPropertyAssertion(gen.getObjectProperty("takesCourse"),gen.getNamedIndividual(dept.phdStudents[j].studentInstance),gen.getNamedIndividual(l.next()));
                    }
                }

                while (hash3.size() != numOfElectivesOutsideDept) {
                    course = getRandomCourse.getRandomElectiveOutsideDept(clg,i); //i is dept index so that course is not picked frm same deptt
                    if (course != null) {
                        hash3.add(course);
                    }
                }
                Iterator<String> l = hash3.iterator();
                while (l.hasNext()) {
                    gen.objectPropertyAssertion(gen.getObjectProperty("takesCourse"),gen.getNamedIndividual(dept.phdStudents[j].studentInstance),gen.getNamedIndividual(l.next()));
                }

                gen.classAssertion(gen.getClass("TeachingAssistant"),gen.getNamedIndividual(dept.phdStudents[j].studentInstance) );
                do {
                    if (GetRandomNo.getRandomFromRange(0,1) == 0) {
                        courseTA = getRandomCourse.getRandomElectiveWithInDept(clg,i);
                    } else {
                        courseTA = getRandomCourse.getRandomUGCourse(clg,i);
                    }
                }while(hash2.contains(courseTA)|| hash3.contains(courseTA) );
                gen.objectPropertyAssertion(gen.getObjectProperty("isTeachingAssistantOf"),gen.getNamedIndividual(dept.phdStudents[j].studentInstance),gen.getNamedIndividual(courseTA));
            }

            //assign courses to faculties

            for (int j = 0; j < dept.assistantProfessorNum; ++j) {
                hash2=new HashSet();
                courseOutsideDepartment = GetRandomNo.getRandomFromRange(0,1);
                if(courseOutsideDepartment==0)
                    courseWithInDepartment = 2;
                else
                {
                    courseWithInDepartment = 1;
                    course = getRandomCourse.getRandomCourseOutsideDept(clg,i);
                    gen.objectPropertyAssertion(gen.getObjectProperty("teachesCourse"),gen.getNamedIndividual(dept.assistantProfessors[j].employeeInstance),gen.getNamedIndividual(course));

                }
                while (hash2.size() != courseWithInDepartment) {
                    course = getRandomCourse.getRandomCourseWithInDept(clg,i);
                    if (course != null) {
                        hash2.add(course);
                    }
                }
                Iterator<String> l = hash2.iterator();
                while (l.hasNext()) {
                    gen.objectPropertyAssertion(gen.getObjectProperty("teachesCourse"),gen.getNamedIndividual(dept.assistantProfessors[j].employeeInstance),gen.getNamedIndividual(l.next()));
                }

            }
            for (int j = 0; j < dept.associateProfessorNum; ++j) {
                hash2=new HashSet();
                courseOutsideDepartment = GetRandomNo.getRandomFromRange(0,1);
                if(courseOutsideDepartment==0)
                    courseWithInDepartment = 2;
                else
                {
                    courseWithInDepartment = 1;
                    course = getRandomCourse.getRandomCourseOutsideDept(clg,i);
                    gen.objectPropertyAssertion(gen.getObjectProperty("teachesCourse"),gen.getNamedIndividual(dept.associateProfessors[j].employeeInstance),gen.getNamedIndividual(course));

                }
                while (hash2.size() != courseWithInDepartment) {
                    course = getRandomCourse.getRandomCourseWithInDept(clg,i);
                    if (course != null) {
                        hash2.add(course);
                    }
                }
                Iterator<String> l = hash2.iterator();
                while (l.hasNext()) {
                    gen.objectPropertyAssertion(gen.getObjectProperty("teachesCourse"),gen.getNamedIndividual(dept.associateProfessors[j].employeeInstance),gen.getNamedIndividual(l.next()));
                }

            }
            for (int j = 0; j < dept.fullProfessorNum; ++j) {
                hash2=new HashSet();
                courseOutsideDepartment = GetRandomNo.getRandomFromRange(0,1);
                if(courseOutsideDepartment==0)
                    courseWithInDepartment = 2;
                else
                {
                    courseWithInDepartment = 1;
                    course = getRandomCourse.getRandomCourseOutsideDept(clg,i);
                    gen.objectPropertyAssertion(gen.getObjectProperty("teachesCourse"),gen.getNamedIndividual(dept.fullProfessors[j].employeeInstance),gen.getNamedIndividual(course));

                }
                while (hash2.size() != courseWithInDepartment) {
                    course = getRandomCourse.getRandomCourseWithInDept(clg,i);
                    if (course != null) {
                        hash2.add(course);
                    }
                }
                Iterator<String> l = hash2.iterator();
                while (l.hasNext()) {
                    gen.objectPropertyAssertion(gen.getObjectProperty("teachesCourse"),gen.getNamedIndividual(dept.fullProfessors[j].employeeInstance),gen.getNamedIndividual(l.next()));
                }

            }


            for (int j = 0; j < dept.visitingProfessorNum; ++j) {

                courseOutsideDepartment = GetRandomNo.getRandomFromRange(0,1);
                if(courseOutsideDepartment==1){
                    course = getRandomCourse.getRandomCourseOutsideDept(clg,i);
                    gen.objectPropertyAssertion(gen.getObjectProperty("teachesCourse"),gen.getNamedIndividual(dept.visitingProfessors[j].employeeInstance),gen.getNamedIndividual(course));
                }
                else
                {
                    course = getRandomCourse.getRandomCourseWithInDept(clg,i);
                    gen.objectPropertyAssertion(gen.getObjectProperty("teachesCourse"),gen.getNamedIndividual(dept.visitingProfessors[j].employeeInstance),gen.getNamedIndividual(course));
                }
            }

            for (int j = 0; j < dept.lecturerNum; ++j) {
                hash2=new HashSet();
                hash3=new HashSet();
                courseOutsideDepartment = GetRandomNo.getRandomFromRange(1,2);
                if(courseOutsideDepartment==1)
                    courseWithInDepartment = 2;
                else
                {
                    courseWithInDepartment = 1;
                }
                while (hash2.size() != courseWithInDepartment) {
                    course = getRandomCourse.getRandomCourseWithInDept(clg,i);
                    if (course != null) {
                        hash2.add(course);
                    }
                }
                Iterator<String> l = hash2.iterator();
                while (l.hasNext()) {
                    gen.objectPropertyAssertion(gen.getObjectProperty("teachesCourse"),gen.getNamedIndividual(dept.lecturers[j].employeeInstance),gen.getNamedIndividual(l.next()));
                }

                while (hash3.size() != courseOutsideDepartment) {
                    course = getRandomCourse.getRandomCourseOutsideDept(clg,i);
                    if (course != null) {
                        hash3.add(course);
                    }
                }
                Iterator<String> m = hash3.iterator();
                while (m.hasNext()) {
                    gen.objectPropertyAssertion(gen.getObjectProperty("teachesCourse"),gen.getNamedIndividual(dept.lecturers[j].employeeInstance),gen.getNamedIndividual(m.next()));
                }
            }

            for (int j = 0; j < dept.postDocNum; ++j) {
                hash2=new HashSet();
                courseWithInDepartment = GetRandomNo.getRandomFromRange(1,2);
                while (hash2.size() != courseWithInDepartment) {
                    course = getRandomCourse.getRandomCourseWithInDept(clg,i);
                    if (course != null) {
                        hash2.add(course);
                    }
                }
                Iterator<String> l = hash2.iterator();
                while (l.hasNext()) {
                    gen.objectPropertyAssertion(gen.getObjectProperty("teachesCourse"),gen.getNamedIndividual(dept.postDocs[j].employeeInstance),gen.getNamedIndividual(l.next()));
                }
            }

        }
    }
}

