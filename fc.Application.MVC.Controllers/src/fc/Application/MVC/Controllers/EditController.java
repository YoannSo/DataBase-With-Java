package fc.Application.MVC.Controllers;

import java.util.HashSet;
import java.util.Set;

import fc.Application.MVC.ViewModels.CustomerViewModel;
import fc.Application.MVC.ViewModels.OrdersDetailsViewModel;
import fc.Application.MVC.ViewModels.OrdersViewModel;
import fc.Application.MVC.ViewModels.ProductsViewModel;
import northwind.Customers;
import northwind.OrderDetails;
import northwind.Orders;

public class EditController extends Controller
{	
	public EditController() {
		super();
	}
	@Override
	public ActionResult run(Object ... args)
	{
		Integer custId = (Integer)args[0];
		Customers cust = m_Model.listCustomers()[custId];

		Set<OrdersViewModel> ordersList=new HashSet<OrdersViewModel>();
		Set<OrdersDetailsViewModel> ordersDetailsList=new HashSet<OrdersDetailsViewModel>();
		Orders tabOrders[]=m_Model.listOrderFromId(custId);
		for(Orders o:tabOrders) {
			ordersDetailsList=new HashSet<OrdersDetailsViewModel>();
			OrderDetails[] orderDetailsTab=m_Model.listOrderDetailsFromId(o.getId()); // va nous permettre de recuperer les OrderDetails avec les produits et les status
			for(OrderDetails d:orderDetailsTab) {
				ProductsViewModel product=new ProductsViewModel(d.getProducts().getId(),d.getProducts().getSupplierIds(),d.getProducts().getProductName(),d.getProducts().getStandardCost(),d.getProducts().getListPrice(),d.getProducts().getQuantityPerUnit(),d.getProducts().getCategory(),d.getProducts().getAttachments());
				ordersDetailsList.add(new OrdersDetailsViewModel(d.getId(),o.getId(),d.getQuantity(),d.getUnitPrice(),d.getDiscount(),d.getDateAllocated(),d.getPurchaseOrderId(),d.getInventoryId(),d.getOrderDetailsStatus().getStatusName(),product));
			}
			ordersList.add(new OrdersViewModel(o.getId(),o.getShippedDate(),o.getOrdersStatus().getStatusName(),o.getShipName(),o.getShipAddress(),o.getShipCity(),o.getShipStateProvince(),o.getShipZipPostalCode(),o.getShipCountryRegion(),o.getPaymentType(),o.getNotes(),ordersDetailsList));
		}
		CustomerViewModel tempMovieModel=new CustomerViewModel(cust.getId(),cust.getCompany(),cust.getLastName(),cust.getFirstName(),cust.getEmailAddress(),cust.getJobTitle(),cust.getBusinessPhone(),cust.getHomePhone(),cust.getMobilePhone(),cust.getFaxNumber(),cust.getAddress(),cust.getCity(),cust.getStateProvince(),cust.getZipPostalCode(),cust.getCountryRegion(),cust.getWebPage(),cust.getNotes(),cust.getAttachments(),ordersList);
		return View(tempMovieModel);
	}
}
