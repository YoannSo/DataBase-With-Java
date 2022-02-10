package fc.Application.MVC.Views;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import fc.Application.MVC.Controllers.StartController;
import fc.Application.MVC.ViewModels.CustomerViewModel;

import org.eclipse.swt.widgets.Button;

public class UpdateView extends Dialog {

	protected Object result;
	protected Shell shell;
	public RunController m_Infrastructure;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CustomerViewModel getViewModel()
	{
		return (CustomerViewModel)m_Infrastructure.m_ViewModel;
	}
	public UpdateView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
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

		Label lblLesModificationsOnt = new Label(shell, SWT.NONE);
		lblLesModificationsOnt.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblLesModificationsOnt.setBounds(10, 75, 424, 43);
		String nameString = getViewModel().lastName;
		String firtName = getViewModel().firstName;
		byte[] bytesFirstNameString=new byte[0];
		byte[] bytesLastNameString=new byte[0];
		try {
			bytesFirstNameString = firtName.getBytes("windows-1252");

			bytesLastNameString = nameString.getBytes("windows-1252");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String utfStringLastName = new String(bytesLastNameString, StandardCharsets.UTF_8);
		String utfStringFirstName = new String(bytesFirstNameString, StandardCharsets.UTF_8);
		lblLesModificationsOnt.setText("Les modifications sur "+utfStringFirstName+" "+utfStringLastName+" ont bien \u00E9t\u00E9 mise \u00E0 jour");

		Button btnRetourAu = new Button(shell, SWT.NONE);
		btnRetourAu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shell, StartController.class ); // les ID commencent par 1 alors que tab par 0
			}
		});
		btnRetourAu.setBounds(166, 124, 123, 25);
		btnRetourAu.setText("Retour au menu");

	}
}
