package de.tum.in.ase.eist;

import java.util.Set;

public class LocalTutorGroupMeeting extends TutorGroupMeeting {
	// TODO: implement according to problem statement
    private String room;
    public LocalTutorGroupMeeting(FixedDateTimeSlot fixedDateTimeSlot, TutorGroup group, Skill skill, String room) {
        super(fixedDateTimeSlot, group, skill);
        this.room = room;
    }

    @Override
    public void practise() {
        System.out.println("Thank you for coming to " + this.room + " today.");

        Set<Student> students = this.getTutorGroup().getStudents();
        for (Student student : students) {
            student.learnSkill(this.getSkillToPractise());
        }

        System.out.println("See you next time!");
    }

    public String getRoom() {
        return room;
    }
}
