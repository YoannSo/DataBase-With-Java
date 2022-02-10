package fc.Application.MVC.Views;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import fc.Application.MVC.Controllers.ListDetailsOrderController;
import fc.Application.MVC.Controllers.StartController;
import fc.Application.MVC.ViewModels.CustomerViewModel;
import fc.Application.MVC.ViewModels.OrdersDetailsViewModel;
import fc.Application.MVC.ViewModels.OrdersViewModel;

import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

public class ListDetailsOrderView extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;
	public RunController m_Infrastructure;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public OrdersDetailsViewModel[] getViewModel()
	{
		return (OrdersDetailsViewModel[])m_Infrastructure.m_ViewModel;
	}
	public ListDetailsOrderView(Shell parent, int style) {
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
		shell.setSize(473, 321);

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 24, 434, 213);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn productNameColumn = new TableColumn(table, SWT.NONE);
		productNameColumn.setWidth(170);
		productNameColumn.setText("Produit");

		productNameColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shell, ListDetailsOrderController.class,getViewModel()[0].idOrder,"name");
			}
		});

		TableColumn quantityColumn = new TableColumn(table, SWT.NONE);
		quantityColumn.setWidth(90);
		quantityColumn.setText("Quantit\u00E9");
		quantityColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shell, ListDetailsOrderController.class,getViewModel()[0].idOrder,"quantity");
			}
		});

		TableColumn priceColumn = new TableColumn(table, SWT.NONE);
		priceColumn.setWidth(85);
		priceColumn.setText("Prix unitaire");
		priceColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shell, ListDetailsOrderController.class,getViewModel()[0].idOrder,"price");
			}
		});

		TableColumn statusColumn = new TableColumn(table, SWT.NONE);
		statusColumn.setWidth(86);
		statusColumn.setText("Statut");
		statusColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shell, ListDetailsOrderController.class,getViewModel()[0].idOrder,"status");
			}
		});



		Button btnRevenir = new Button(shell, SWT.NONE);
		btnRevenir.setBounds(359, 243, 75, 25);
		btnRevenir.setText("Revenir");
		btnRevenir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shell, StartController.class ); // les ID commencent par 1 alors que tab par 0
			}
		});
		Label lblTitre = new Label(shell, SWT.NONE);
		lblTitre.setBounds(10, 3, 133, 15);
		lblTitre.setText("Details des commandes");
		OrdersDetailsViewModel[] orderDetailses=getViewModel();


		table.removeAll();
		if(orderDetailses.length>0) {
			shell.setText("D\u00E9tails de commande "+orderDetailses[0].idOrder );

			for(OrdersDetailsViewModel orderDetails:orderDetailses) {    
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { orderDetails.products.productName,""+orderDetails.quantity,""+orderDetails.unitPrice,orderDetails.statusName });			    		  
			}
		}
		else {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { "No orderDetailsFound" });			    		  
		}
	}

}
