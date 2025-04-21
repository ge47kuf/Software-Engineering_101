package de.tum.in.ase.eist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TutorGroup {
	// TODO: implement according to problem statement
    private String id;
    private WeeklyTimeSlot timeSlot;
    private Person tutor;
    private Set<Student> students;
    private List<TutorGroupMeeting> meetings;

    public TutorGroup(String id, WeeklyTimeSlot timeSlot, Person person) {
        this.id = id;
        this.timeSlot = timeSlot;
        this.tutor = person;
        this.students = new HashSet<Student>();
        this.meetings = new ArrayList<TutorGroupMeeting>();
    }

    protected void addMeeting(TutorGroupMeeting tutorGroupMeeting) {
        if (tutorGroupMeeting != null) {
            meetings.add(tutorGroupMeeting);
            tutorGroupMeeting.setTutorGroup(this);
        }
    }

    public void register(Student student) {
        if (student != null) {
            students.add(student);
        }
    }


    public List<TutorGroupMeeting> getMeetings() {
        return meetings;
    }

    public Person getTutor() {
        return tutor;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public String getId() {
        return id;
    }

    public WeeklyTimeSlot getTimeSlot() {
        return timeSlot;
    }
}
