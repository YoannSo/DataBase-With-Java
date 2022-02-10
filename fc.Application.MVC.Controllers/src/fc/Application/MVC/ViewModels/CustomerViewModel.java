package fc.Application.MVC.ViewModels;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import northwind.Customers;
import northwind.Orders;

public class CustomerViewModel
{
	public Integer id;
	public String company;
	public String lastName;
	public String firstName;
	public String emailAddress;
	public String jobTitle;
	public String businessPhone;
	public String homePhone;
	public String mobilePhone;
	public String faxNumber;
	public String address;
	public String city;
	public String stateProvince;
	public String zipPostalCode;
	public String countryRegion;
	public String webPage;
	public String notes;
	public Blob attachments;
	public Set<OrdersViewModel> orderses = new HashSet<OrdersViewModel>(0);

	public CustomerViewModel(int id,String company, String lastName, String firstName, String emailAddress, String jobTitle,
			String businessPhone, String homePhone, String mobilePhone, String faxNumber, String address, String city,
			String stateProvince, String zipPostalCode, String countryRegion, String webPage, String notes,
			Blob attachments,Set<OrdersViewModel> orderses) {
		this.id=id;
		this.company = company;
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
		this.jobTitle = jobTitle;
		this.businessPhone = businessPhone;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
		this.faxNumber = faxNumber;
		this.address = address;
		this.city = city;
		this.stateProvince = stateProvince;
		this.zipPostalCode = zipPostalCode;
		this.countryRegion = countryRegion;
		this.webPage = webPage;
		this.notes = notes;
		this.attachments = attachments;
		this.orderses=orderses;

	}
	public void setOrderses(Set<OrdersViewModel> ordersList) {
		this.orderses=ordersList;
	}
}
