package ABoxGen.InstanceGenerator;

import java.util.HashSet;
import java.util.Iterator;

public class EvaluationCommittee {
    University university;
    GetRandomPerson getRandomPerson;
    University universities[];
    Generator gen;
    String member;
    College clg;
    Department dept;
    HashSet<String> hash1, hash2;

    public EvaluationCommittee(Generator gen,University universities[]) {

        this.gen = gen;
        this.universities = universities;
        int i;
        getRandomPerson = new GetRandomPerson();
        for (i = 0; i < gen.univNum; ++i) {
            this.university = universities[i];
            for (int j = 0; j < university.coEdCollegeNum; ++j) {
                this.clg = university.coEdColleges[j];
                for (int k = 0; k < clg.deptNum; ++k) {
                    this.dept = clg.depts[k];
                    gen.objectPropertyAssertion(gen.getObjectProperty("hasThesisEvaluationCommittee"),gen.getNamedIndividual(dept.departmentInstance),gen.getNamedIndividual(dept.departmentInstance + "thesisEC"));
                    gen.classAssertion(gen.getClass("ThesisEvaluationCommittee"),gen.getNamedIndividual(dept.departmentInstance + "thesisEC") );

                        hash1 = new HashSet();
                        hash2 = new HashSet();
                        for (int m = 0; m < 3; ++m) {
                            member = getRandomPerson.getRandomInternalProfessor(dept);
                            if (member != null) {
                                hash1.add(member);
                            }
                        }
                        Iterator<String> n = hash1.iterator();
                        while (n.hasNext()) {
                            gen.objectPropertyAssertion(gen.getObjectProperty("hasMember"),gen.getNamedIndividual(dept.departmentInstance + "thesisEC"),gen.getNamedIndividual(n.next()));
                        }

                    for (int m = 0; m < 3; ++m) {
                        member = getRandomPerson.getRandomExternalProfessor(universities,i);
                        if (member != null) {
                            hash2.add(member);
                        }
                    }
                    Iterator<String> o = hash2.iterator();
                    while (o.hasNext()) {
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMember"),gen.getNamedIndividual(dept.departmentInstance + "thesisEC"),gen.getNamedIndividual(o.next()));
                    }
                    }
            }
            for (int j = 0; j < university.womenCollegeNum; ++j) {
                this.clg = university.womenColleges[j];
                for (int k = 0; k < clg.deptNum; ++k) {
                    this.dept = clg.depts[k];
                    gen.objectPropertyAssertion(gen.getObjectProperty("hasThesisEvaluationCommittee"),gen.getNamedIndividual(dept.departmentInstance),gen.getNamedIndividual(dept.departmentInstance + "thesisEC"));
                    gen.classAssertion(gen.getClass("ThesisEvaluationCommittee"),gen.getNamedIndividual(dept.departmentInstance + "thesisEC") );

                    hash1 = new HashSet();
                    hash2 = new HashSet();
                    for (int m = 0; m < 3; ++m) {
                        member = getRandomPerson.getRandomInternalProfessor(dept);
                        if (member != null) {
                            hash1.add(member);
                        }
                    }
                    Iterator<String> n = hash1.iterator();
                    while (n.hasNext()) {
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMember"),gen.getNamedIndividual(dept.departmentInstance + "thesisEC"),gen.getNamedIndividual(n.next()));
                    }

                    for (int m = 0; m < 3; ++m) {
                        member = getRandomPerson.getRandomExternalProfessor(universities,i);
                        if (member != null) {
                            hash2.add(member);
                        }
                    }
                    Iterator<String> o = hash2.iterator();
                    while (o.hasNext()) {
                        gen.objectPropertyAssertion(gen.getObjectProperty("hasMember"),gen.getNamedIndividual(dept.departmentInstance + "thesisEC"),gen.getNamedIndividual(o.next()));
                    }
                }
            }
            }
        }
}