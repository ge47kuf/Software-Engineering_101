package de.tum.in.ase.eist;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public abstract class TutorGroupMeeting {
    static final int NUMBER_OF_HOMEWORK_PRESENTATIONS = 3;
    final TimeSlot timeSlot;
    final TutorGroup tutorGroup;
    final Skill skillToPractice;
    public TutorGroupMeeting (TimeSlot timeSlot, TutorGroup tutorGroup, Skill skillToPractice) {
        this.timeSlot = timeSlot;
        this.tutorGroup = tutorGroup;
        this.skillToPractice = skillToPractice;
    }
    public TutorGroup getTutorGroup() {
        return tutorGroup;
    }
    public Skill getSkillToPractice() {
        return skillToPractice;
    }
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
    // is different in both class so give the implementation to the subclass
    public abstract void beginMessage(Student tutor);
    public abstract void endMessage(Student tutor);
    public void practice () {
        Student tutor = getTutorGroup().getTutor();
        // bad coding bc of casting down
        /*
        if (this instanceof PhysicalTutorGroupMeeting) {
            tutor.say("Welcome to the physical tutor meeting");
            tutor.say("Thank you for coming to " + ((PhysicalTutorGroupMeeting) this).getRoom() + " today.");
        } else {
            tutor.say("Welcome to the virtual tutor meeting");
            tutor.say("Thank you for joining using " + ((VirtualTutorGroupMeeting) this).getUrl() + " today.");
            tutor.say("Please turn on your cameras");
        }
         */
        // instead this
        beginMessage(tutor);

        tutor.say("We start with the homework presentation");
        List<Student> homeworkPresentationCandidates = new ArrayList<>(getTutorGroup().getStudents());
        for (int i = 0; i < NUMBER_OF_HOMEWORK_PRESENTATIONS; i++) {
            if (homeworkPresentationCandidates.isEmpty()) {
                break;
            }
            int randomIndex = ThreadLocalRandom.current().nextInt(homeworkPresentationCandidates.size());
            Student randomStudent = homeworkPresentationCandidates.get(randomIndex);
            randomStudent.presentHomework();
            homeworkPresentationCandidates.remove(randomIndex);
        }
        tutor.say("Next is the group work");
        Skill skill = getSkillToPractice();
        skill.apply();
        for (Student student : getTutorGroup().getStudents()) {
            student.learnSkill(skill);
        }
        tutor.say("Let's have a look at the new homework");

        /*
        if (this instanceof PhysicalTutorGroupMeeting) {
            tutor.say("Thank you that you have participated in " +
                    ((PhysicalTutorGroupMeeting) this).getRoom() + " today.");
        } else {
            tutor.say("Thank you that you have participated using the " +
                    ((VirtualTutorGroupMeeting) this).getUrl() + " today.");
        }
         */

        endMessage(tutor);
        tutor.say("See you next time!");
    }
}
