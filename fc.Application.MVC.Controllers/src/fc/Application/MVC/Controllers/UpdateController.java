package fc.Application.MVC.Controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fc.Application.MVC.Model.Model;
import fc.Application.MVC.ViewModels.CustomerViewModel;
import fc.Application.MVC.ViewModels.OrdersViewModel;
import northwind.Customers;
import northwind.Orders;

public class UpdateController extends Controller{

	public UpdateController() {
		super();
	}
	@Override
	public ActionResult run(Object ... args)
	{	CustomerViewModel cust = (CustomerViewModel) args[0];

	Customers tabCust[] = m_Model.listCustomers();
	for(Customers c:tabCust) {
		if(c.getId()==cust.id) {
			cust.firstName=(String)args[2];
			cust.lastName=(String)args[1];
			cust.emailAddress=(String)args[3];
			String nomString = (String)args[1];
			String prenomString = (String)args[2];
			String emailString = (String)args[3];
			byte[] bytesNameString=new byte[0];
			byte[] bytesPrenomString=new byte[0];
			byte[] bytesEmailString=new byte[0];
			try {
				bytesNameString = nomString.getBytes("UTF-8");
				bytesPrenomString = prenomString.getBytes("UTF-8");
				bytesEmailString = emailString.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String utfNomString="";
			String utfEmailString="";
			String utfPrenomString="";

			try {
				utfNomString = new String(bytesNameString, "windows-1252");
				utfPrenomString = new String(bytesPrenomString, "windows-1252");
				utfEmailString = new String(bytesEmailString, "windows-1252");

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cust.lastName=utfNomString;
			c.setLastName(utfNomString);
			c.setFirstName(utfPrenomString);
			cust.firstName=utfPrenomString;
			c.setEmailAddress(utfEmailString);
			cust.emailAddress=utfEmailString;

			Model.update(c, c.getLastName(), c.getFirstName(), c.getEmailAddress());
			m_Model = Model.getModel();
			break;
		}
	}
	return View(cust);
	}
}
