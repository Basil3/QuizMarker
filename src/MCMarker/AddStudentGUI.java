package MCMarker;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


// TODO: Auto-generated Javadoc
/**
 * The Class AddStudentGUI.
 */
public class AddStudentGUI extends Dialog {

	/** The result. */
	protected Object result;
	
	/** The shl add student dialog. */
	protected Shell shlAddStudentDialog;
	
	/** The text. */
	private Text text;
	
	/** The text_1. */
	private Text text_1;
	
	/** The text_2. */
	private Text text_2;
	
	/** The text_3. */
	private Text text_3;

	/**
	 * Create the dialog.
	 *
	 * @param parent the parent
	 * @param style the style
	 */
	public AddStudentGUI(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlAddStudentDialog.open();
		shlAddStudentDialog.layout();
		Display display = getParent().getDisplay();
		while (!shlAddStudentDialog.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlAddStudentDialog = new Shell(getParent(), getStyle());
		shlAddStudentDialog.setSize(450, 300);
		shlAddStudentDialog.setText("Add Student Dialog");
		
		Label lblPleaseAddYour = new Label(shlAddStudentDialog, SWT.NONE);
		lblPleaseAddYour.setBounds(22, 24, 338, 15);
		lblPleaseAddYour.setText("Please add your details below for your new student:");
		
		Label lblStudentId = new Label(shlAddStudentDialog, SWT.NONE);
		lblStudentId.setBounds(22, 64, 124, 15);
		lblStudentId.setText("Student ID:");
		
		Label lblFirstName = new Label(shlAddStudentDialog, SWT.NONE);
		lblFirstName.setText("First Name:");
		lblFirstName.setBounds(22, 96, 124, 15);
		
		Label lblLastName = new Label(shlAddStudentDialog, SWT.NONE);
		lblLastName.setText("Last Name:");
		lblLastName.setBounds(22, 130, 124, 15);
		
		Label lblExamScore = new Label(shlAddStudentDialog, SWT.NONE);
		lblExamScore.setText("Exam Score:");
		lblExamScore.setBounds(22, 170, 124, 15);
		
		text = new Text(shlAddStudentDialog, SWT.BORDER);
		text.setBounds(184, 58, 176, 21);
		
		text_1 = new Text(shlAddStudentDialog, SWT.BORDER);
		text_1.setBounds(184, 90, 176, 21);
		
		text_2 = new Text(shlAddStudentDialog, SWT.BORDER);
		text_2.setBounds(184, 124, 176, 21);
		
		text_3 = new Text(shlAddStudentDialog, SWT.BORDER);
		text_3.setBounds(184, 164, 176, 21);
		
		Button btnAddStudent = new Button(shlAddStudentDialog, SWT.NONE);
		btnAddStudent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//get details of new student and add to the student list
				String studentID = text.getText();
				String firstName = text_1.getText();
				String lastName = text_2.getText();
				double score = Double.parseDouble(text_3.getText());
				Student s = new Student(studentID, firstName, lastName, score);
				MCMarkerGUI.addStudentToList(s);
				shlAddStudentDialog.close();
			}
		});
		btnAddStudent.setBounds(22, 210, 113, 25);
		btnAddStudent.setText("Add Student");
		
		Button btnCancel = new Button(shlAddStudentDialog, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAddStudentDialog.close();
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setBounds(247, 210, 113, 25);

	}
}
