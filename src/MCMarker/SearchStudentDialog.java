package MCMarker;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

/**
 * 
 * Dialog for Searching for students
 *
 */
public class SearchStudentDialog extends Dialog {

	protected Object result;
	protected Shell shlSearchResults;

	/**
	 * Create the search student dialog
	 * 
	 * @param parent the parent Shell
	 * @param style the style of dialog used
	 */
	public SearchStudentDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlSearchResults.open();
		shlSearchResults.layout();
		Display display = getParent().getDisplay();
		while (!shlSearchResults.isDisposed()) {
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
		shlSearchResults = new Shell(getParent(), getStyle());
		shlSearchResults.setSize(302, 222);
		shlSearchResults.setText("Search Results");
		
		Label lblStudentFoundDetails = new Label(shlSearchResults, SWT.NONE);
		lblStudentFoundDetails.setBounds(46, 41, 229, 15);
		lblStudentFoundDetails.setText("Student found.  Details below:");
		
		Label lblName = new Label(shlSearchResults, SWT.NONE);
		lblName.setBounds(46, 108, 169, 15);
		lblName.setText("Student ID:");
		
		Label label = new Label(shlSearchResults, SWT.NONE);
		label.setText("Name:");
		label.setBounds(45, 78, 169, 15);
		
		Label lblScore = new Label(shlSearchResults, SWT.NONE);
		lblScore.setText("Score:");
		lblScore.setBounds(46, 141, 169, 15);

	}
	

}
