package fc.Application.MVC.ViewModels;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Set;

import northwind.InventoryTransactions;
import northwind.OrderDetails;
import northwind.PurchaseOrderDetails;

public class ProductsViewModel {

	public Integer id;
	public String productName;
	public BigDecimal standardCost;
	public BigDecimal listPrice;
	public String quantityPerUnit;
	public String category;
	public Blob attachments;

	public ProductsViewModel(Integer id,String supplierIds, String productName, 
			BigDecimal standardCost, BigDecimal listPrice,
			String quantityPerUnit, String category, Blob attachments) {
		this.id=id;
		this.productName = productName;
		this.standardCost = standardCost;
		this.listPrice = listPrice;
		this.quantityPerUnit = quantityPerUnit;
		this.category = category;
		this.attachments = attachments;
	}
}
