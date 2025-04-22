package de.tum.in.ase.eist;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public final class TutorGroupStatistics {

	private TutorGroupStatistics() {

	}

	public static double averageDuration(Stream<TutorGroupMeeting> meetingStream) {
		return meetingStream
				.mapToLong(meeting -> meeting.getTimeSlot().getDuration().toSeconds())
				.average()
				.orElse(0.0);
	}

	public static Set<Skill> repeatedSkills(Stream<TutorGroupMeeting> meetings) {
		List<Skill> skillList = meetings.map(TutorGroupMeeting::getSkillToPractise).toList();

		Set<Skill> result = new HashSet<>();

		for (int i = 0; i < skillList.size(); i++) {
			Skill skill = skillList.get(i);
			for (int j = i + 1; j < skillList.size(); j++) {
				if (skill.equals(skillList.get(j))) {
					result.add(skill);
					break;
				}
			}
		}
		return result;
	}
}
