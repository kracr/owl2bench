/**every department has a list of courses, some mandatory courses for ug stduents and some departmental electives for ug,
 * pg and phd students)
 * students can take electives of the same department as well as outside the department (within the college)
 * faculties can teach courses within the department or outside the department.
 * In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the config.properties file */

package ABoxGen.InstanceGenerator;

import java.util.HashSet;

public class GetRandomCourse {
    String course;
    Generator gen;
    College college;
    Department dept;
    int otherDept;
  //students can take electives of the same department as well as outside the department (within the college)
    public String getRandomUGCourse(College college,int deptIndex) {
        this.gen = college.gen;
        this.college = college;
        this.dept=college.depts[deptIndex];
        course = dept.ugCourses[GetRandomNo.getRandomFromRange(0,dept.ugCourseNum-1)].courseInstance;
        return course;
    }
    public String getRandomElectiveWithInDept(College college, int deptIndex) {
        this.gen = college.gen;
        this.college = college;
        this.dept=college.depts[deptIndex];
        course = dept.electiveCourses[GetRandomNo.getRandomFromRange(0,dept.electiveCourseNum-1)].courseInstance;
        return course;
    }
    public String getRandomElectiveOutsideDept(College college, int deptIndex) {
        if(deptIndex !=0 && deptIndex!= (college.deptNum-1)) {
            if (GetRandomNo.getRandomFromRange(0,1) == 0) {
                otherDept = GetRandomNo.getRandomFromRange(0,deptIndex - 1);
            } else {
                otherDept = GetRandomNo.getRandomFromRange(deptIndex + 1,(college.deptNum) - 1);
            }
        }
        else if (deptIndex==0)
        {
            otherDept=GetRandomNo.getRandomFromRange(1 ,(college.deptNum - 1));
        }
        else {
            otherDept=GetRandomNo.getRandomFromRange(0,(college.deptNum - 2));
        }

        this.gen = college.gen;
        this.college = college;
        this.dept=college.depts[otherDept];
        course = dept.electiveCourses[GetRandomNo.getRandomFromRange(0,dept.electiveCourseNum-1)].courseInstance;
        return course;
    }
    
    //faculty can teach a course within the department or any course outside the department
    public String getRandomCourseOutsideDept(College college, int deptIndex) {
        this.gen = college.gen;
        this.college = college;
        if(deptIndex !=0 && deptIndex!= (college.deptNum-1)) {
            if (GetRandomNo.getRandomFromRange(0,1) == 0) {
                otherDept = GetRandomNo.getRandomFromRange(0,deptIndex - 1);
            } else {
                otherDept = GetRandomNo.getRandomFromRange(deptIndex + 1,(college.deptNum) - 1);
            }
        }
        else if (deptIndex==0)
        {
            otherDept=GetRandomNo.getRandomFromRange(1,(college.deptNum) - 1);
        }
        else {
            otherDept=GetRandomNo.getRandomFromRange(0,(college.deptNum) - 2);
        }
        this.dept=college.depts[otherDept];
        if(GetRandomNo.getRandomFromRange(0,1)==0){
            course = dept.ugCourses[GetRandomNo.getRandomFromRange(0,dept.ugCourseNum-1)].courseInstance;
        }
        else {
            course = dept.electiveCourses[GetRandomNo.getRandomFromRange(0,dept.electiveCourseNum - 1)].courseInstance;
        }
        return course;
    }
    public String getRandomCourseWithInDept(College college, int deptIndex) {
        this.gen = college.gen;
        this.college = college;
        this.dept=college.depts[deptIndex];
        if(GetRandomNo.getRandomFromRange(0,1)==0){
            course = dept.ugCourses[GetRandomNo.getRandomFromRange(0,dept.ugCourseNum-1)].courseInstance;
        }
        else {
            course = dept.electiveCourses[GetRandomNo.getRandomFromRange(0,dept.electiveCourseNum - 1)].courseInstance;
        }
        return course;
    }



}
