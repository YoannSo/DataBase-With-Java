package fc.Application.MVC.ViewModels;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import northwind.Customers;
import northwind.Employees;
import northwind.InventoryTransactions;
import northwind.Invoices;
import northwind.OrderDetails;
import northwind.OrdersStatus;
import northwind.OrdersTaxStatus;
import northwind.Shippers;

public class OrdersViewModel {
	public Integer id;
	//public MovieViewModel customers;
	//public Employees employees;
	public String ordersStatus;
	//public OrdersTaxStatus ordersTaxStatus;
	//public Shippers shippers;
	public Date orderDate;
	//public Date shippedDate;
	public String shipName;
	public String shipAddress;
	public String shipCity;
	public String shipStateProvince;
	public String shipZipPostalCode;
	public String shipCountryRegion;
	//public BigDecimal shippingFee;
	//public BigDecimal taxes;
	public String paymentType;
	//public Date paidDate;
	public String notes;
	//public Double taxRate;
	//public OrdersDetailsViewModel orderDetailses;
	//public Set<Invoices> invoiceses = new HashSet<Invoices>(0);
	//public Set<InventoryTransactions> inventoryTransactionses = new HashSet<InventoryTransactions>(0);
	public Set<OrdersDetailsViewModel> orderDetailses = new HashSet<OrdersDetailsViewModel>(0);

	public OrdersViewModel(int id,Date orderDate,String ordersStatus,String shipName,String shipAddress,String shipCity,String shipStateProvince,String shipCountryRegion,String shipZipPostalCode,String paymentType,String notes, Set<OrdersDetailsViewModel> orderDetailses) {

		//this.employees = employees;
		this.ordersStatus = ordersStatus;
		//this.ordersTaxStatus = ordersTaxStatus;
		//this.shippers = shippers;
		this.orderDate = orderDate;
		//this.shippedDate = shippedDate;
		this.id=id;
		this.shipName = shipName;
		this.shipAddress = shipAddress;
		this.shipCity = shipCity;
		this.shipStateProvince = shipStateProvince;
		this.shipZipPostalCode = shipZipPostalCode;
		this.shipCountryRegion = shipCountryRegion;
		/*this.shippingFee = shippingFee;
		this.taxes = taxes;*/
		this.paymentType = paymentType;
		//this.paidDate = paidDate;
		this.notes = notes;
		/*this.taxRate = taxRate;
		this.invoiceses = invoiceses;
		this.inventoryTransactionses = inventoryTransactionses;*/
		this.orderDetailses = orderDetailses;
	}
}
