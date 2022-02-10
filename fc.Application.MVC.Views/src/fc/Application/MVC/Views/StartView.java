package fc.Application.MVC.Views;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import fc.Application.MVC.Controllers.EditController;
import fc.Application.MVC.Controllers.ListDetailsOrderController;
import fc.Application.MVC.ViewModels.CustomerViewModel;
import fc.Application.MVC.ViewModels.OrdersDetailsViewModel;
import fc.Application.MVC.ViewModels.OrdersViewModel;

import org.eclipse.swt.widgets.TableItem;


public class StartView extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;

	public RunController m_Infrastructure;

	protected CustomerViewModel[] getViewModel()
	{
		if (m_Infrastructure != null) {

			return (CustomerViewModel[]) m_Infrastructure.m_ViewModel;

		}
		else
			return new CustomerViewModel[0];
	}
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public StartView(Shell parent, int style) {
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
		shell.setSize(449, 325);
		shell.setText("Northwind - Projet L3");

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(35, 72, 350, 156);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("ID");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(116);
		tblclmnNewColumn_1.setText("Ville");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(132);
		tblclmnNewColumn_2.setText("Adresse");

		TableColumn tblclmnStatut = new TableColumn(table, SWT.NONE);
		tblclmnStatut.setWidth(100);
		tblclmnStatut.setText("Statut");

		TableColumn tblclmnDate = new TableColumn(table, SWT.NONE);
		tblclmnDate.setWidth(100);
		tblclmnDate.setText("Date");




		Label lblClient = new Label(shell, SWT.NONE);
		lblClient.setBounds(35, 19, 55, 15);
		lblClient.setText("Client:");

		Button btnEditer = new Button(shell, SWT.NONE);
		btnEditer.setBounds(342, 40, 75, 25);
		btnEditer.setText("Editer");
		btnEditer.setEnabled(false);


		Combo combo = new Combo(shell, SWT.READ_ONLY);



		CustomerViewModel[] custs = getViewModel();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String idNomPrenom[]=combo.getText().split("-");
				int id=Integer.parseInt(idNomPrenom[0]);
				for(CustomerViewModel m:custs) {

					if(m.id==id) {

						OrdersViewModel[] order= new OrdersViewModel[m.orderses.size()];
						order=m.orderses.toArray(order);

						m_Infrastructure.runController(shell, ListDetailsOrderController.class,order[table.getSelectionIndex()].id,"noSort"); // les ID commencent par 1 alors que tab par 0
					}

				}
			}
		});
		for (CustomerViewModel cust : custs)
		{	
			String nameString =cust.id+"-"+cust.firstName+" "+cust.lastName;
			byte[] bytesNameString=new byte[0];
			try {
				bytesNameString = nameString.getBytes("windows-1252");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String utfString = new String(bytesNameString, StandardCharsets.UTF_8);

			//assertNotEquals(asciiEncodedString, germanString);
			combo.add(utfString);

		}	
		combo.setBounds(35, 40, 275, 23);

		Button btnExit = new Button(shell, SWT.NONE);
		btnExit.setBounds(193, 246, 75, 25);
		btnExit.setText("EXIT");
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Runtime.getRuntime().exit(0);			//ajout d'un btn exit
			}
		});
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				btnEditer.setEnabled(true);

				String idNomPrenom[]=combo.getText().split("-");
				int id=Integer.parseInt(idNomPrenom[0]);
				for(CustomerViewModel m:custs) {
					if(m.id==id) {
						for(OrdersViewModel order:m.orderses) {
							TableItem item = new TableItem(table, SWT.NONE);
							item.setText(new String[] { ""+order.id,order.shipCity,order.shipAddress,order.ordersStatus,order.orderDate.toString() });
						}
						break;

					}

				}
			}});
		btnEditer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String idNomPrenom[]=combo.getText().split("-");
				int id=Integer.parseInt(idNomPrenom[0]);
				for(CustomerViewModel m:custs) {
					if(m.id==id) {
						m_Infrastructure.runController(shell, EditController.class,m.id-1 ); // les ID commencent par 1 alors que tab par 0
						break;
					}
				}

			}
		});
	}

}


