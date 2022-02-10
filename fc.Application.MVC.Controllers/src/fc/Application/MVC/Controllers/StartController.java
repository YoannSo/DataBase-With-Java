package fc.Application.MVC.Controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fc.Application.MVC.ViewModels.CustomerViewModel;
import fc.Application.MVC.ViewModels.OrdersDetailsViewModel;
import fc.Application.MVC.ViewModels.OrdersViewModel;
import fc.Application.MVC.ViewModels.ProductsViewModel;
import northwind.Customers;
import northwind.OrderDetails;
import northwind.Orders;

public class StartController extends Controller{

	public StartController() {
		super();
	}
	@Override
	public ActionResult run(Object ... args)
	{	Customers custs[] = m_Model.listCustomers();

	CustomerViewModel custsViewModel[] = new CustomerViewModel[custs.length];
	for (int i=0; i < custs.length; i++)
	{				

		Customers cust = custs[i];
		Set<OrdersViewModel> ordersList=new HashSet<OrdersViewModel>();
		Set<OrdersDetailsViewModel> ordersDetailsList=new HashSet<OrdersDetailsViewModel>();

		Orders tabOrders[]=m_Model.listOrderFromId(i+1);
		for(Orders o:tabOrders) {
			ordersDetailsList=new HashSet<OrdersDetailsViewModel>();
			OrderDetails[] orderDetailsTab=m_Model.listOrderDetailsFromId(o.getId()); // va nous permettre de recuperer les OrderDetails avec les produits et les status

			for(OrderDetails d:orderDetailsTab) {
				ProductsViewModel product=new ProductsViewModel(d.getProducts().getId(),d.getProducts().getSupplierIds(),d.getProducts().getProductName(),d.getProducts().getStandardCost(),d.getProducts().getListPrice(),d.getProducts().getQuantityPerUnit(),d.getProducts().getCategory(),d.getProducts().getAttachments());
				ordersDetailsList.add(new OrdersDetailsViewModel(d.getId(),o.getId(),d.getQuantity(),d.getUnitPrice(),d.getDiscount(),d.getDateAllocated(),d.getPurchaseOrderId(),d.getInventoryId(),d.getOrderDetailsStatus().getStatusName(),product));
			}
			ordersList.add(new OrdersViewModel(o.getId(),o.getOrderDate(),o.getOrdersStatus().getStatusName(),o.getShipName(),o.getShipAddress(),o.getShipCity(),o.getShipStateProvince(),o.getShipZipPostalCode(),o.getShipCountryRegion(),o.getPaymentType(),o.getNotes(),ordersDetailsList));
		}


		CustomerViewModel tempCustModel=new CustomerViewModel(cust.getId(),cust.getCompany(),cust.getLastName(),cust.getFirstName(),cust.getEmailAddress(),cust.getJobTitle(),cust.getBusinessPhone(),cust.getHomePhone(),cust.getMobilePhone(),cust.getFaxNumber(),cust.getAddress(),cust.getCity(),cust.getStateProvince(),cust.getZipPostalCode(),cust.getCountryRegion(),cust.getWebPage(),cust.getNotes(),cust.getAttachments(),ordersList);

		custsViewModel[i]=tempCustModel;	}
	return View(custsViewModel);
	}
}
