package ABoxGen.InstanceGenerator;

import java.util.HashSet;

public class GetRandomCourse {
    String course;
    Generator gen;
    College college;
    Department dept;
    int otherDept;

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
