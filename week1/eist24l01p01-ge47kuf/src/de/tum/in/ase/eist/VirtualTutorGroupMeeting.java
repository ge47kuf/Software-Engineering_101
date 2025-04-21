package de.tum.in.ase.eist;

import java.util.Set;

public class VirtualTutorGroupMeeting extends TutorGroupMeeting {
	// TODO: implement according to problem statement
    private String url;

    public VirtualTutorGroupMeeting(FixedDateTimeSlot fixedDateTimeSlot, TutorGroup group, Skill skill, String url) {
        super(fixedDateTimeSlot, group, skill);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void practise() {
        System.out.println("Thank you for joining using " + this.url + " today.");

        Set<Student> students = getTutorGroup().getStudents();

        for(Student student : students) {
            student.learnSkill(this.getSkillToPractise());
        }

        System.out.println("See you next time!");
    }
}
