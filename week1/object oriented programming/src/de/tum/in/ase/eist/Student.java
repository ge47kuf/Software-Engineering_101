package de.tum.in.ase.eist;

import java.util.HashSet;
import java.util.Set;

public class Student extends Person {
	// TODO: implement according to problem statement
    private String matriculationNumber;
    private int semester;
    private Set<String> knowledge;
    private StudyLevel studyLevel;
    private Set<Skill> skills;

    public Student(String matriculationNumber, int semester, String name, String tumId, StudyLevel studyLevel, int age) {
        super(name, age, tumId);
        this.matriculationNumber = matriculationNumber;
        this.semester = semester;
        this.knowledge = new HashSet<String>();
        this.studyLevel = studyLevel;
        this.skills = new HashSet<>();
    }

    public void learnSkill(Skill skill) {
        if (skill != null) {
            this.skills.add(skill);
        }
    }

    public void acquireKnowledge(String knowledge) {
        if (knowledge != null) {
            this.knowledge.add(knowledge);
        }
    }

    public String getMatriculationNumber() {
        return matriculationNumber;
    }

    public int getSemester() {
        return semester;
    }

    public Set<String> getKnowledge() {
        return knowledge;
    }

    public StudyLevel getStudyLevel() {
        return studyLevel;
    }

    public Set<Skill> getSkills() {
        return skills;
    }
}
