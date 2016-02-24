package MCMarker;
import java.util.ArrayList;

/**
 * The Class Student.
 */
public class Student {
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The student id. */
	private String studentID;
	
	/** The choices. */
	private ArrayList<Integer> choices;
	
	/** The score. */
	private double score;
	
	/**
	 * Instantiates a new student.
	 */
	public Student(){
		//default constructor
	}
	
	
	//various overloaded constructors
	/**
	 * Instantiates a new student.
	 *
	 * @param studentID the student id
	 * @param lastName the last name
	 * @param firstName the first name
	 * @param choices the choices
	 */
	public Student (String studentID, String lastName, String firstName, 
			ArrayList<Integer> choices){
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
		this.setChoices(choices);
	}
	
	/**
	 * Instantiates a new student.
	 *
	 * @param studentID the student id
	 * @param lastName the last name
	 * @param firstName the first name
	 * @param choices the choices
	 * @param score the score
	 */
	public Student (String studentID, String lastName, String firstName, 
			ArrayList<Integer> choices, double score){
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
		this.setChoices(choices);
		this.score = score;
	}


	/**
	 * Instantiates a new student.
	 *
	 * @param studentID the student id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param score the score
	 */
	public Student(String studentID, String firstName, String lastName,
			double score) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
		this.score = score;
	}

	/**
	 * Instantiates a new student.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param studentID the student id
	 */
	public Student(String firstName, String lastName, String studentID) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
	}
	
	/**
	 * Gets the choices for an exam attempt
	 *
	 * @return the choices
	 */
	public ArrayList<Integer> getChoices() {
		return choices;
	}


	/**
	 * Gets the first name of the Student.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the Student.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the Student.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the Student.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the student id of the Student.
	 *
	 * @return the student id
	 */
	public String getStudentID() {
		return studentID;
	}

	/**
	 * Sets the student id of the Student.
	 *
	 * @param studentID the new student id
	 */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	/**
	 * Calculate score the Student got for the exam.
	 *
	 * @param choices the choices
	 * @return the double
	 */
	public double calculateScore(ArrayList<Integer> choices) {
		
		double score = 0;
		//put the answers from the correctanswers.dat file into an array
		int[] correctAnswers = FileInput.getCorrectScores("./CorrectAnswers.dat");
		//score the array by looping through and matching student answers against correct answers
		for (int i = 0; i < correctAnswers.length; ++i) {
			if (choices.get(i) == 0) {
				score += 0;
			} else if (correctAnswers[i] == choices.get(i)) {
				score += 1;
			} else if (correctAnswers[i] != choices.get(i)) {
				score -= 0.25;
			} else {
				System.out.println("Something went wrong here");
			}
		}
		return score;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public double getScore() {
		return score;
	}


	/**
	 * Sets the score for the student.
	 *
	 * @param score the new score
	 */
	public void setScore(double score) {
		this.score = score;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName
				+ ", studentID=" + studentID + ", choices=" + choices + 
				", score=" + score + "]";
	}


	/**
	 * Sets the choices for the exam.
	 *
	 * @param choices an arrayList of integers for the quiz choices
	 */
	public void setChoices(ArrayList<Integer> choices) {
		this.choices = choices;
	}
	
	
	
	
	
	

}

