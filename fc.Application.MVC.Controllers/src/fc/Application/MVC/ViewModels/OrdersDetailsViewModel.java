package fc.Application.MVC.ViewModels;

import java.math.BigDecimal;
import java.util.Date;

import northwind.OrderDetailsStatus;
import northwind.Orders;
import northwind.Products;

public class OrdersDetailsViewModel {

	public Integer id;
	public Integer idOrder;
	public ProductsViewModel products;
	public BigDecimal quantity;
	public BigDecimal unitPrice;
	public double discount;
	public Date dateAllocated;
	public Integer purchaseOrderId;
	public Integer inventoryId;
	public String statusName;

	public OrdersDetailsViewModel(Integer id,Integer idOrder, BigDecimal quantity,
			BigDecimal unitPrice, double discount, Date dateAllocated, Integer purchaseOrderId, Integer inventoryId,String statusName,ProductsViewModel products) {
		this.id=id;
		this.idOrder=idOrder;
		this.products = products;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.dateAllocated = dateAllocated;
		this.purchaseOrderId = purchaseOrderId;
		this.inventoryId = inventoryId;
		this.statusName=statusName;
	}

}
