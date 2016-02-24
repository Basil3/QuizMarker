package MCMarker;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

// TODO: Auto-generated Javadoc
/**
 * The Class FileInput.
 */
public class FileInput {

	/**
	 * Instantiates a new file input.
	 */
	public FileInput() {
		// empty constructor
	}
	
	/** The shell. */
	protected static Shell shell;
	
	/** The student list. */
	static ArrayList<Student> studentList = new ArrayList<Student>();

	/**
	 * Gets the student list from file.
	 *
	 * @param file the file
	 * @return the student list from file
	 */
	public static ArrayList<Student> getStudentListFromFile(String file) {

		BufferedReader bufIn = null;
		String[] entries = null;

		try {
			//read in the file
			bufIn = new BufferedReader(new FileReader(file));

			String line;
			//split each line at the comma
			while ((line = bufIn.readLine()) != null) {
				entries = line.split(",");

				ArrayList<Integer> choice = new ArrayList<Integer>();
				//loop through each line and add the choices by the student to an array
				for (int i = 3; i < entries.length; i++) {
					choice.add(Integer.parseInt(entries[i]));
				}
				//create a new student from each line in the file
				Student student = new Student(entries[0], entries[1],
						entries[2], choice, 0);
				//calculate the score for each student
				double score = Math.ceil(student.calculateScore(student
						.getChoices()));
				//add the score to the student object
				student.setScore(score);
				//add the newly created student to the student list
				studentList.add(student);

			}
			
		} catch (IOException e) {
			System.err.println(e);
		} catch (IndexOutOfBoundsException e) {
			//display dialog if exception is caught
			shell = new Shell();
			MessageBox dialog = new MessageBox(shell, SWT.ERROR | SWT.OK
					| SWT.CANCEL);
			dialog.setText("Incorrect data");
			dialog.setMessage("Incorrect data in file, please choose another file.");
			dialog.open();
			System.err.println(e);
		} finally {
			if (bufIn != null) {
				try {
					bufIn.close();
				} catch (IOException e) {
					System.err.println(e);
				}
			}

		}
		return studentList;

	}
	
	/**
	 * Gets the correct scores.
	 *
	 * @param file the file
	 * @return the correct scores
	 * @throws ArrayIndexOutOfBoundsException the array index out of bounds exception
	 */
	public static int[] getCorrectScores(String file) throws ArrayIndexOutOfBoundsException {

		BufferedReader bufIn = null;
		String[] entries = null;
		int[] correctScores = new int[20];

		try {

			bufIn = new BufferedReader(new FileReader(file));

			String line;

			while ((line = bufIn.readLine()) != null) {
				entries = line.split(",");

				for (int i = 0; i < entries.length; i++) {
					correctScores[i] = Integer.parseInt(entries[i]);
				}

			}

		} catch (IOException e) {
			System.err.println(e);
		} catch (IndexOutOfBoundsException e){
			shell = new Shell();
			MessageBox dialog = new MessageBox(shell, SWT.ERROR | SWT.OK
					| SWT.CANCEL);
			dialog.setText("Incorrect data");
			dialog.setMessage("Incorrect data in file, please choose another file.");
			dialog.open();
			System.err.println(e);
		} finally {
			if (bufIn != null) {
				try {
					bufIn.close();
				} catch (IOException e) {
					System.err.println(e);
				}
			}

		}
		return correctScores;

	}

}
