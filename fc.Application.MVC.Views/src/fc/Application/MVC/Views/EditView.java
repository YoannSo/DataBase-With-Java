package fc.Application.MVC.Views;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import fc.Application.MVC.Controllers.EditController;
import fc.Application.MVC.Controllers.StartController;
import fc.Application.MVC.Controllers.UpdateController;
import fc.Application.MVC.ViewModels.CustomerViewModel;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class EditView extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_2;
	private Text text;
	private Text text_1;

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
	public EditView(Shell parent, int style) {
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
		shell = new Shell(getParent(), SWT.TITLE);
		shell.setSize(391, 261);
		shell.setText("Modification base");

		Label lblNom_1_1 = new Label(shell, SWT.NONE);
		lblNom_1_1.setText("Email");
		lblNom_1_1.setBounds(27, 105, 55, 34);

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(95, 107, 152, 21);
		if(getViewModel().emailAddress==null)
			text_2.setText("NONE");
		else {
			text_2.setText(getViewModel().emailAddress);

		}
		Button btnMettreJour = new Button(shell, SWT.NONE);
		btnMettreJour.setBounds(114, 145, 94, 41);
		btnMettreJour.setText("Mettre \u00E0 jour");
		btnMettreJour.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shell, UpdateController.class,getViewModel(),text_1.getText(),text.getText(),text_2.getText() ); // les ID commencent par 1 alors que tab par 0
			}
		});
		Button btnRetourListeClients = new Button(shell, SWT.NONE);
		btnRetourListeClients.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shell, StartController.class ); // les ID commencent par 1 alors que tab par 0
			}
		});
		btnRetourListeClients.setText("Retour liste clients");
		btnRetourListeClients.setBounds(266, 197, 109, 25);

		Label lblNom_1_1_1 = new Label(shell, SWT.NONE);
		lblNom_1_1_1.setText("Pr\u00E9nom");
		lblNom_1_1_1.setBounds(27, 65, 64, 34);

		text = new Text(shell, SWT.BORDER);
		text.setBounds(95, 67, 152, 21);
		text.setText(getViewModel().firstName);

		Label lblNom_1_1_1_1 = new Label(shell, SWT.NONE);
		lblNom_1_1_1_1.setText("Nom");
		lblNom_1_1_1_1.setBounds(27, 25, 55, 34);

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(95, 25, 152, 21);

		String nameString = getViewModel().lastName;
		byte[] bytesLastNameString=new byte[0];
		try {
			bytesLastNameString = nameString.getBytes("windows-1252");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String utfStringLastName = new String(bytesLastNameString, StandardCharsets.UTF_8);

		text_1.setText(utfStringLastName);

	}
}
