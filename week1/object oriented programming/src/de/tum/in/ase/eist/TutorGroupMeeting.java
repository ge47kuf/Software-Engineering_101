package de.tum.in.ase.eist;

public abstract class TutorGroupMeeting {
	// TODO: implement according to problem statement
    private FixedDateTimeSlot timeSlot;
    private TutorGroup tutorGroup;
    private Skill skillToPractise;

    public TutorGroupMeeting(FixedDateTimeSlot timeSlot, TutorGroup tutorGroup, Skill skill) {
        this.timeSlot = timeSlot;
        this.tutorGroup = tutorGroup;
        this.skillToPractise = skill;

    }

    public abstract void practise();

    public FixedDateTimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Skill getSkillToPractise() {
        return skillToPractise;
    }

    public TutorGroup getTutorGroup() {
        return tutorGroup;
    }

    public void setTutorGroup(TutorGroup tutorGroup) {
        this.tutorGroup = tutorGroup;
    }
}
