package MCMarker;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


// TODO: Auto-generated Javadoc
/**
 * The Class DisplayStudentsGUI.
 */
public class DisplayStudentsGUI extends Dialog {

	/** The result. */
	protected Object result;
	
	/** The shell. */
	protected Shell shell;

	/**
	 * Create the dialog.
	 *
	 * @param parent the parent
	 * @param style the style
	 */
	public DisplayStudentsGUI(Shell parent, int style) {
		super(parent, style);
		setText("Student List");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 300);
		shell.setText(getText());

	}

}
