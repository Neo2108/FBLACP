import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PointSystem {

	
	/**
	 * finds the student with the highest points in the grade 9 students list
	 * @return the list of students who have points equal to the highest possible points in that quarter
	 */
	public static List<Student> nineWinners() {
		int winnerPoints = Collections.max(Student.getNinePoints()); //finds the highest number of points
		List<Student> winners = new ArrayList<Student>(); //list to hold all grade 9 students with the points number found above
		for (Student student : Student.getNineStudents()) {
			if (student.getPoints() == winnerPoints) {
				winners.add(student);
			}
		}

		return winners;
	}

	/**
	 * finds the student with the highest points in the grade 10 students list
	 * @return the list of students who have points equal to the highest possible points in that quarter
	 */
	public static List<Student> tenWinners() {
		int winnerPoints = Collections.max(Student.getTenPoints()); //finds the highest number of points
		List<Student> winners = new ArrayList<Student>(); //list to hold all grade 10 students with the points number found above
		for (Student student : Student.getTenStudents()) {
			if (student.getPoints() == winnerPoints) {
				winners.add(student);
			}
		}

		return winners;
	}

	/**
	 * finds the student with the highest points in the grade 11 students list
	 * @return the list of students who have points equal to the highest possible points in that quarter
	 */
	public static List<Student> elevenWinners() {
		int winnerPoints = Collections.max(Student.getElevenPoints()); //finds the highest number of points
		List<Student> winners = new ArrayList<Student>(); //list to hold all grade 11 students with the points number found above
		for (Student student : Student.getElevenStudents()) {
			if (student.getPoints() == winnerPoints) {
				winners.add(student);
			}
		}

		return winners;
	}

	/**
	 * finds the student with the highest points in the grade 12 students list
	 * @return the list of students who have points equal to the highest possible points in that quarter
	 */
	public static List<Student> twelveWinners() {
		int winnerPoints = Collections.max(Student.getTwelvePoints()); //finds the highest number of points
		List<Student> winners = new ArrayList<Student>(); //list to hold all grade we students with the points number found above
		for (Student student : Student.getTwelveStudents()) {
			if (student.getPoints() == winnerPoints) {
				winners.add(student);
			}
		}

		return winners;
	}

	public static List<Student> randomWinners() {
		Random rand = new Random(); 
		Student nineRandomWinner = Student.getNineStudents().get(rand.nextInt(Student.getNineStudents().size()));
		Student tenRandomWinner = Student.getTenStudents().get(rand.nextInt(Student.getTenStudents().size()));
		Student elevenRandomWinner = Student.getElevenStudents().get(rand.nextInt(Student.getElevenStudents().size()));
		Student twelveRandomWinner = Student.getTwelveStudents().get(rand.nextInt(Student.getTwelveStudents().size()));

		List<Student> winners = new ArrayList<Student>();
		winners.add(nineRandomWinner);
		winners.add(tenRandomWinner);
		winners.add(elevenRandomWinner);
		winners.add(twelveRandomWinner);

		return winners;

	}
		
}
