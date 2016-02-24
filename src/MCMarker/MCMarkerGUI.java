package MCMarker;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFileChooser;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.List;


public class MCMarkerGUI {

	public List list_1;
	protected Shell shell;
	private Text text;
	public static ArrayList<Student> studentList = new ArrayList<>();
	public static StyledText monoText;
	public static FileWriter writer;

	

	/**
	 * Adds a new student to the list of students
	 * 
	 * @param s Student being added to the list
	 * @return List of Students
	 */
	public static ArrayList<Student> addStudentToList(Student s) {
		studentList.add(s);
		System.out.println(studentList);
		return studentList;

	}
	

	public static void main(String[] args) {
		try {
			MCMarkerGUI window = new MCMarkerGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 523);
		shell.setText("OOSD2 Quiz Marker");
		shell.setLayout(new FormLayout());

		Label lblPleaseSelectThe = new Label(shell, SWT.NONE);
		FormData fd_lblPleaseSelectThe = new FormData();
		fd_lblPleaseSelectThe.top = new FormAttachment(0, 23);
		fd_lblPleaseSelectThe.right = new FormAttachment(100, -127);
		fd_lblPleaseSelectThe.left = new FormAttachment(0, 26);
		lblPleaseSelectThe.setLayoutData(fd_lblPleaseSelectThe);
		lblPleaseSelectThe
				.setText("Please select the file containing student answers:");
		//button to select file for student answers
		Button btnSelectFile = new Button(shell, SWT.NONE);
		fd_lblPleaseSelectThe.bottom = new FormAttachment(btnSelectFile, -6);
		FormData fd_btnSelectFile = new FormData();
		fd_btnSelectFile.top = new FormAttachment(0, 56);
		fd_btnSelectFile.left = new FormAttachment(0, 26);
		btnSelectFile.setLayoutData(fd_btnSelectFile);
		btnSelectFile.addSelectionListener(new SelectionAdapter() {

			@Override
			//file picker for the student answers file
			public void widgetSelected(SelectionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System
						.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(fileChooser);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: "
							+ selectedFile.getAbsolutePath());
					String file = selectedFile.getAbsolutePath();
					studentList = FileInput.getStudentListFromFile(file);
					System.out.println(studentList);
				}
			}
		});
		btnSelectFile.setText("Select Student Answers File");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(btnSelectFile, 16);
		fd_lblNewLabel.left = new FormAttachment(lblPleaseSelectThe, 0, SWT.LEFT);
		fd_lblNewLabel.right = new FormAttachment(100, -169);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Search for Student by Student ID:");

		text = new Text(shell, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(btnSelectFile, 50);
		fd_text.left = new FormAttachment(0, 26);
		text.setLayoutData(fd_text);
		
		//search button
		Button btnSearch = new Button(shell, SWT.NONE);
		fd_btnSelectFile.right = new FormAttachment(btnSearch, 0, SWT.RIGHT);
		fd_text.right = new FormAttachment(btnSearch, -68);
		FormData fd_btnSearch = new FormData();
		fd_btnSearch.left = new FormAttachment(0, 243);
		fd_btnSearch.right = new FormAttachment(100, -41);
		fd_btnSearch.top = new FormAttachment(text, 0, SWT.TOP);
		btnSearch.setLayoutData(fd_btnSearch);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			//search button functionality, checking student against list
			public void widgetSelected(SelectionEvent e) {
				boolean match = false;
				Student foundStudent = new Student();
				String input = text.getText();
				System.out.println(input);
				for (Student student : studentList) {
					if (input.equalsIgnoreCase(student.getStudentID())) {
						System.out.println(student.toString());
						foundStudent = student;
						match = true;
						break;
					} else {
						System.out.println("no match");
						match = false;
					}
				}
				if (match == true) {
					MessageBox dialog = new MessageBox(shell,
							SWT.ICON_INFORMATION | SWT.OK | SWT.CANCEL);
					dialog.setText("Search Results");
					dialog.setMessage("Student Name: "
							+ foundStudent.getFirstName() + " "
							+ foundStudent.getLastName() + "\n" + "StudentID: "
							+ foundStudent.getStudentID() + "\n" + "Score: "
							+ foundStudent.getScore());
					dialog.open();
				} else {
					MessageBox dialog = new MessageBox(shell,
							SWT.ICON_INFORMATION | SWT.OK | SWT.CANCEL);
					dialog.setText("Search Results");
					dialog.setMessage("Sorry, no matching student found");
					dialog.open();
				}

			}

		});
		btnSearch.setText("Search");
		//button for adding a new student
		Button btnAddNewStudent = new Button(shell, SWT.NONE);
		btnAddNewStudent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//open the Add Student dialog box if add new student is selected
				AddStudentGUI addStudent = new AddStudentGUI(shell, 0);
				addStudent.open();
			}
		});
		FormData fd_btnAddNewStudent = new FormData();
		fd_btnAddNewStudent.left = new FormAttachment(0, 26);
		fd_btnAddNewStudent.top = new FormAttachment(text, 23);
		btnAddNewStudent.setLayoutData(fd_btnAddNewStudent);
		btnAddNewStudent.setText("Add New Student");

		Button btnDeleteStudent = new Button(shell, SWT.NONE);
		btnDeleteStudent.addSelectionListener(new SelectionAdapter() {
			@Override
			//functionality for removing student from the list
			public void widgetSelected(SelectionEvent e) {
				int selected = list_1.getSelectionIndex();
				if (selected > -1) {
					String selectedItem = list_1.getItem(selected);
					String selectedItem1 = selectedItem.substring(0,
							selectedItem.indexOf(' '));
					System.out.println(selectedItem1);
					for (Student student : studentList) {
						if (student.getStudentID().equalsIgnoreCase(
								selectedItem1)) {
							studentList.remove(student);

							System.out.println("student removed");
							break;
						} else {
							System.out.println("No match for deleting student");
						}
					}
					System.out.println(selectedItem);
					list_1.remove(selected);
				} else {
					MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR
							| SWT.OK | SWT.CANCEL);
					dialog.setText("Error");
					dialog.setMessage("Sorry, you must select a student from the student list");
					dialog.open();
				}

			}
		});
		fd_btnAddNewStudent.right = new FormAttachment(btnDeleteStudent, -68);
		FormData fd_btnDeleteStudent = new FormData();
		fd_btnDeleteStudent.top = new FormAttachment(btnAddNewStudent, 0,
				SWT.TOP);
		fd_btnDeleteStudent.left = new FormAttachment(btnSearch, 0, SWT.LEFT);
		fd_btnDeleteStudent.right = new FormAttachment(100, -41);
		btnDeleteStudent.setLayoutData(fd_btnDeleteStudent);
		btnDeleteStudent.setText("Delete Student");

		Button btnDisplayAllStudents = new Button(shell, SWT.NONE);
		btnDisplayAllStudents.addSelectionListener(new SelectionAdapter() {
			@Override
			//functionality for displaying all students
			public void widgetSelected(SelectionEvent e) {
				if (studentList.size() != 0) {
					list_1.removeAll();
					Collections.sort(studentList, new CustomComparator());
					for (Student student : studentList) {
						String studentDetails = String.format(
								"%-10s %-10s %-10s %-10s",
								student.getStudentID(), student.getFirstName(),
								student.getLastName(),
								String.valueOf(student.getScore()));
						Font font1 = JFaceResources.getTextFont();
						list_1.setFont(font1);
						list_1.add(studentDetails);
					}

				} else {
					MessageBox dialog = 
							new MessageBox(shell, SWT.ERROR | SWT.OK| SWT.CANCEL);
							dialog.setText("No data");
							dialog.setMessage("Sorry, there are no students to display");
							dialog.open(); 	
				}
			}
		});
		FormData fd_btnDisplayAllStudents = new FormData();
		fd_btnDisplayAllStudents.right = new FormAttachment(btnSelectFile, 0, SWT.RIGHT);
		fd_btnDisplayAllStudents.left = new FormAttachment(0, 26);
		fd_btnDisplayAllStudents.top = new FormAttachment(btnAddNewStudent, 27);
		btnDisplayAllStudents.setLayoutData(fd_btnDisplayAllStudents);
		btnDisplayAllStudents.setText("Display All Students");

		Label lblOutput = new Label(shell, SWT.NONE);
		FormData fd_lblOutput = new FormData();
		fd_lblOutput.top = new FormAttachment(btnDisplayAllStudents, 22);
		fd_lblOutput.left = new FormAttachment(0, 34);
		lblOutput.setLayoutData(fd_lblOutput);

		list_1 = new List(shell, SWT.BORDER);
		FormData fd_list_1 = new FormData();
		fd_list_1.bottom = new FormAttachment(lblOutput, 128, SWT.BOTTOM);
		fd_list_1.top = new FormAttachment(lblOutput, 6);
		fd_list_1.right = new FormAttachment(btnSearch, 0, SWT.RIGHT);
		fd_list_1.left = new FormAttachment(0, 26);
		list_1.setLayoutData(fd_list_1);
		list_1.add("No data added yet");

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.right = new FormAttachment(btnSearch, 0, SWT.RIGHT);
		fd_lblNewLabel_1.left = new FormAttachment(lblPleaseSelectThe, 0,
				SWT.LEFT);
		fd_lblNewLabel_1.bottom = new FormAttachment(lblOutput, 0, SWT.BOTTOM);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		String labelText = String.format("%-22s %-17s %-22s %-25s",
				"StudentID", "First Name", "Last Name", "Score");
		lblNewLabel_1.setText(labelText);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			//saving the student list to file
			public void widgetSelected(SelectionEvent e) {
				try {
					FileOutput.WriteStudentListToFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(list_1, 14);
		fd_btnNewButton.left = new FormAttachment(lblPleaseSelectThe, 0, SWT.LEFT);
		fd_btnNewButton.right = new FormAttachment(btnSelectFile, 0, SWT.RIGHT);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Save current student list to file");

	}
	
	/**
	 * Comparator class for sorting the student list.
	 * 
	 * Sorts the students out in order of their last name for display.
	 *
	 */

	public class CustomComparator implements Comparator<Student> {
		@Override
		/**
		 * Compares student last names to each other for sorting
		 * 
		 */
		public int compare(Student s1, Student s2) {
			return s1.getLastName().compareTo(s2.getLastName());
		}
	}
}
