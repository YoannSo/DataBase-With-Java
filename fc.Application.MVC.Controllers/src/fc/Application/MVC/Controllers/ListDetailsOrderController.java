package fc.Application.MVC.Controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import fc.Application.MVC.ViewModels.CustomerViewModel;
import fc.Application.MVC.ViewModels.OrdersDetailsViewModel;
import fc.Application.MVC.ViewModels.OrdersViewModel;
import fc.Application.MVC.ViewModels.ProductsViewModel;
import northwind.Customers;
import northwind.OrderDetails;
import northwind.Orders;

public class ListDetailsOrderController extends Controller implements Comparable<OrdersDetailsViewModel>
{	
	static String sortState;

	public ListDetailsOrderController() {
		super();
	}
	@Override
	public ActionResult run(Object ... args)
	{	


		int orderId=(int)args[0];
		OrderDetails tabOrdersDetails[]=m_Model.listOrderDetailsFromId(orderId);

		OrdersDetailsViewModel ordersDetailsReturned[]=new OrdersDetailsViewModel[tabOrdersDetails.length];
		int j=0;
		for(OrderDetails d:tabOrdersDetails) {
			ProductsViewModel product=new ProductsViewModel(d.getProducts().getId(),d.getProducts().getSupplierIds(),d.getProducts().getProductName(),d.getProducts().getStandardCost(),d.getProducts().getListPrice(),d.getProducts().getQuantityPerUnit(),d.getProducts().getCategory(),d.getProducts().getAttachments());
			ordersDetailsReturned[j]=new OrdersDetailsViewModel(d.getId(),orderId,d.getQuantity(),d.getUnitPrice(),d.getDiscount(),d.getDateAllocated(),d.getPurchaseOrderId(),d.getInventoryId(),d.getOrderDetailsStatus().getStatusName(),product);
			j++;
		}


		String sortType=(String)args[1];
		if(sortType=="name") {
			ordersDetailsReturned=nameSort(ordersDetailsReturned);
			if(ListDetailsOrderController.sortState=="name") {
				OrdersDetailsViewModel[] ordersDetailsReturnedReverse= new OrdersDetailsViewModel[ordersDetailsReturned.length];
				for(int i=0;i<ordersDetailsReturned.length;i++) {
					ordersDetailsReturnedReverse[i]=ordersDetailsReturned[ordersDetailsReturned.length-1-i];
				}
				ordersDetailsReturned=ordersDetailsReturnedReverse;
				ListDetailsOrderController.sortState="notName";
			}
			else {
				ListDetailsOrderController.sortState="name";		
			}
		}
		else if(sortType=="price") {
			ordersDetailsReturned=priceSort(ordersDetailsReturned);
			if(ListDetailsOrderController.sortState=="price") {
				OrdersDetailsViewModel[] ordersDetailsReturnedReverse= new OrdersDetailsViewModel[ordersDetailsReturned.length];
				for(int i=0;i<ordersDetailsReturned.length;i++) {
					ordersDetailsReturnedReverse[i]=ordersDetailsReturned[ordersDetailsReturned.length-1-i];
				}
				ordersDetailsReturned=ordersDetailsReturnedReverse;
				ListDetailsOrderController.sortState="notPrice";
			}
			else {
				ListDetailsOrderController.sortState="price";		
			}
		}
		else if(sortType=="quantity") {
			ordersDetailsReturned=quantitySort(ordersDetailsReturned);
			if(ListDetailsOrderController.sortState=="quantity") {
				OrdersDetailsViewModel[] ordersDetailsReturnedReverse= new OrdersDetailsViewModel[ordersDetailsReturned.length];
				for(int i=0;i<ordersDetailsReturned.length;i++) {
					ordersDetailsReturnedReverse[i]=ordersDetailsReturned[ordersDetailsReturned.length-1-i];
				}
				ordersDetailsReturned=ordersDetailsReturnedReverse;
				ListDetailsOrderController.sortState="notQuantity";
			}
			else {
				ListDetailsOrderController.sortState="quantity";		
			}
		}
		else if(sortType=="status") {
			ordersDetailsReturned=quantitySort(ordersDetailsReturned);
			if(ListDetailsOrderController.sortState=="status") {
				OrdersDetailsViewModel[] ordersDetailsReturnedReverse= new OrdersDetailsViewModel[ordersDetailsReturned.length];
				for(int i=0;i<ordersDetailsReturned.length;i++) {
					ordersDetailsReturnedReverse[i]=ordersDetailsReturned[ordersDetailsReturned.length-1-i];
				}
				ordersDetailsReturned=ordersDetailsReturnedReverse;
				ListDetailsOrderController.sortState="notStatus";
			}
			else {
				ListDetailsOrderController.sortState="status";		
			}
		}

		return View(ordersDetailsReturned);
	}
	public OrdersDetailsViewModel[] nameSort(OrdersDetailsViewModel[] tabOrders) {
		Arrays.sort(tabOrders, (a, b) -> a.products.productName.compareTo(b.products.productName));     
		return tabOrders;
	}
	public OrdersDetailsViewModel[] quantitySort(OrdersDetailsViewModel[] tabOrders) {
		Arrays.sort(tabOrders, (a, b) -> a.quantity.compareTo(b.quantity));
		return tabOrders;
	}
	public OrdersDetailsViewModel[] priceSort(OrdersDetailsViewModel[] tabOrders) {
		Arrays.sort(tabOrders, (a, b) -> a.unitPrice.compareTo(b.unitPrice));
		return tabOrders;
	}
	public OrdersDetailsViewModel[] statusSort(OrdersDetailsViewModel[] tabOrders) {
		Arrays.sort(tabOrders, (a, b) -> a.statusName.compareTo(b.statusName));
		return tabOrders;
	}

	@Override
	public int compareTo(OrdersDetailsViewModel o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
