

package fc.Application.MVC.TestController;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import fc.Application.MVC.Controllers.ActionResult;
import fc.Application.MVC.Controllers.EditController;
import fc.Application.MVC.Controllers.ListDetailsOrderController;
import fc.Application.MVC.Controllers.StartController;
import fc.Application.MVC.Controllers.UpdateController;
import fc.Application.MVC.Model.Model;
import fc.Application.MVC.ViewModels.CustomerViewModel;
import fc.Application.MVC.ViewModels.OrdersDetailsViewModel;
import northwind.Customers;
import northwind.OrderDetails;



public class ControllerTest{
	private Model m_Model;
	private int nbCust;
	//Tous les tests ci-dessous sont expliqués dans le rapport

	public boolean testCustomers(Customers A,CustomerViewModel B) {
		if(A.getId()!=B.id)
			return false;
		if(A.getCompany()!=B.company)
			return false;
		if(A.getLastName()!=B.lastName)
			return false;
		if(A.getFirstName()!=B.firstName)
			return false;
		if(A.getEmailAddress()!=B.emailAddress)
			return false;
		if(A.getJobTitle()!=B.jobTitle)
			return false;
		if(A.getBusinessPhone()!=B.businessPhone)
			return false;
		if(A.getHomePhone()!=B.homePhone)
			return false;
		if(A.getMobilePhone()!=B.mobilePhone)
			return false;
		if(A.getFaxNumber()!=B.faxNumber)
			return false;
		if(A.getAddress()!=B.address)
			return false;
		if(A.getCity()!=B.city)
			return false;
		if(A.getStateProvince()!=B.stateProvince)
			return false;
		if(A.getZipPostalCode()!=B.zipPostalCode)
			return false;
		if(A.getCountryRegion()!=B.countryRegion)
			return false;
		if(A.getWebPage()!=B.webPage)
			return false;
		if(A.getNotes()!=B.notes)
			return false;
		return true;
	}

	public boolean testDetailsOrder(OrderDetails A,OrdersDetailsViewModel B) {
		if(A.getId()!=B.id)
			return false;
		if(A.getProducts().getId()!=B.products.id)
			return false;
		if(A.getProducts().getProductName()!=B.products.productName)
			return false;
		if(A.getQuantity()!=B.quantity)
			return false;
		if(A.getUnitPrice()!=B.unitPrice)
			return false;
		if(A.getDateAllocated()!=B.dateAllocated)
			return false;
		if(A.getPurchaseOrderId()!=B.purchaseOrderId)
			return false;
		if(A.getOrderDetailsStatus().getStatusName()!=B.statusName)
			return false;
		if(A.getInventoryId()!=B.inventoryId)
			return false;

		return true;
	}

	@Test @Before
	public void testStartController() {
		StartController startController=new StartController();
		Customers[] cust =(Customers[]) m_Model.listCustomers();
		
			ActionResult result=startController.run();
			CustomerViewModel[] custFromActionResult=(CustomerViewModel[]) result.m_ViewModel;
			for(int i=0;i<cust.length;i++)
			assertTrue(testCustomers(cust[i],custFromActionResult[i]));
		
	}
	@Test @Before
	public void testEditController() {
		EditController editControllerTest=new EditController();
		for(int i=0;i<this.nbCust;i++) {
			ActionResult result=editControllerTest.run(i);
			CustomerViewModel custFromActionResult=(CustomerViewModel) result.m_ViewModel;
			Customers cust = m_Model.customersWhereIdLike(custFromActionResult.id);
			assertTrue(testCustomers(cust,custFromActionResult));
		}
	}
	@Test
	public void testListDetailsOrderController() {
		ListDetailsOrderController controllerTest=new ListDetailsOrderController();
		int[] allId=m_Model.allIdOrder();
		for(int i:allId) {
			OrderDetails[] details = m_Model.listOrderDetailsFromId(i);
			OrdersDetailsViewModel[] detailsView=new OrdersDetailsViewModel[details.length];

			ActionResult result=controllerTest.run(i,"noSort");
			detailsView=(OrdersDetailsViewModel[]) result.m_ViewModel;
			for( int j=0;j<details.length;j++) {
				assertTrue(testDetailsOrder(details[j],detailsView[j]));
			}
		}
	}
	@Test @After
	public void testUpdateController() {
		UpdateController controllerTest=new UpdateController();

		StartController controllerStartTest=new StartController();

		ActionResult result=controllerStartTest.run();
		CustomerViewModel[] custsFromActionResult=(CustomerViewModel[]) result.m_ViewModel;

		Customers[] custs = m_Model.listCustomers();
		Customers save=custs[0];


		ActionResult resultUpdate=controllerTest.run(custsFromActionResult[0],"Sochaj","Yoann","yoann.sochaj@etu.unilim.fr"); //Anna bedecs ->Yoann Sochaj
		CustomerViewModel custModified=(CustomerViewModel) resultUpdate.m_ViewModel;
		Customers[] newCusts = m_Model.listCustomers();
		assertEquals(newCusts[0].getFirstName(),"Yoann");
		assertEquals(newCusts[0].getLastName(),"Sochaj");
		assertEquals(newCusts[0].getEmailAddress(),"yoann.sochaj@etu.unilim.fr");

		m_Model.update(custs[0], custs[0].getLastName(), custs[0].getFirstName(), custs[0].getEmailAddress());//on remet comme avant
	}
}


