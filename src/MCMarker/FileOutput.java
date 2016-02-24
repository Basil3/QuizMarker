package MCMarker;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * The Class FileOutput.
 */
public class FileOutput {

	/**
	 * Write student list to file.
	 * This class will save a text file with the latest student list and their scores.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void WriteStudentListToFile() throws IOException {

		String studentDetails = "";

		FileWriter writeToFile = new FileWriter("./student_list.txt");
		{
			String headers = String.format("%-12s %-12s %-12s %-12s",
					"Student ID", "First Name", "Last Name", "Test Score\n\n");
			writeToFile.write(headers);
			for (Student student : MCMarkerGUI.studentList) {
				studentDetails = String.format("%-12s %-12s %-12s %-12s\n",
						student.getStudentID(), student.getFirstName(),
						student.getLastName(),
						String.valueOf(student.getScore()));
				writeToFile.write(studentDetails);
			}
			try {
				writeToFile.close();
				Shell shell = new Shell();
				MessageBox dialog = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK|
						 SWT.CANCEL); dialog.setText("Success");
						 dialog.setMessage("Student list successfully saved as \n"
						 		+ "student_list.txt to the \n"
						 		+ "source folder of this project."); dialog.open();
			} catch (IOException e) {
				// catch block for IO exception
				e.printStackTrace();
			}
		}
	}

}