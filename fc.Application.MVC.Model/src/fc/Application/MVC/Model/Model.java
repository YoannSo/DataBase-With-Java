package fc.Application.MVC.Model;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;


import northwind.Customers;
import northwind.OrderDetails;
import northwind.OrderDetailsStatus;
import northwind.Orders;
import northwind.Products;
public class Model
{
	public static Customers m_Customers[];
	private static Model s_Model = null;
	private static EntityManagerFactory emf;
	private static	EntityManager em;
	public Model(int taille) {
		m_Customers=new Customers[taille];
	}
	public static Model getModel()
	{
		return null;
	}
	public static void update(Customers cust,String nom,String prenom,String email) {

		String nomString = nom;
		byte[] bytesNameString = nomString.getBytes();
		String utfString="";
		try {
			utfString = new String(bytesNameString, "Windows-1252");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		em.getTransaction().begin();
		Customers custChange=em.find(Customers.class,cust.getId());
		System.out.println(utfString);
		custChange.setLastName(utfString);
		custChange.setFirstName(prenom);
		custChange.setEmailAddress(email);
		em.merge(custChange);
		em.flush();
		em.getTransaction().commit();


	}
	public static Customers[] listCustomers() {
		emf = Persistence.createEntityManagerFactory("fc.Application.MVC.Model");
		em = emf.createEntityManager();
		List<Customers> customersList =em.createNamedQuery("listCustomers").getResultList();
		Customers[] customerTab=new Customers[customersList.size()];
		for(int i=0;i<customerTab.length;i++) {
			customerTab[i]=customersList.get(i);
		}

		return customerTab;

	}
	public static Orders[] listOrderFromId(int id) {

		List<Orders> ordersList =em.createNamedQuery("listOrders").setParameter("custName", id).getResultList();	
		Orders[] orderTab=new Orders[ordersList.size()];
		for(int i=0;i<orderTab.length;i++) {
			orderTab[i]=ordersList.get(i);
		}

		return orderTab;
	}
	public static OrderDetails[] listOrderDetailsFromId(int id) {
		List<OrderDetails> ordersDetailsList =em.createNamedQuery("listOrdersDetails").setParameter("order", id).getResultList();	
		OrderDetails[] orderDetailsTab=new OrderDetails[ordersDetailsList.size()];
		for(int i=0;i<orderDetailsTab.length;i++) {
			List<Products> orderProduct= em.createNamedQuery("test").setParameter("idDetailsOrder",ordersDetailsList.get(i).getId()).getResultList();
			List<OrderDetailsStatus> orderStatus= em.createNamedQuery("ordersStatusFromOrderDetails").setParameter("idOrder", ordersDetailsList.get(i).getId()).getResultList();
			orderDetailsTab[i]=ordersDetailsList.get(i);
			if(orderProduct.size()!=0)
				orderDetailsTab[i].setProducts(orderProduct.get(0));
			if(orderProduct.size()!=0)
				orderDetailsTab[i].setOrderDetailsStatus(orderStatus.get(0));
		}

		return orderDetailsTab;
	}
	public static Customers customersWhereIdLike(int id) {
		List<Customers> customers =em.createNamedQuery("customersFromId").setParameter("custId", id).getResultList();			
		if(customers.size()!=0)
			return customers.get(0);
		else
			return null;
	}
	public static int[] allIdOrder() {
		List<Integer> allId =em.createNamedQuery("allIdOrder").getResultList();			
		int[] idTab=new int[allId.size()];
		for(int i=0;i<idTab.length;i++) {
			idTab[i]=allId.get(i);
		}
		return idTab;
	}

	public static void openEntityManager() {
		emf = Persistence.createEntityManagerFactory("fc.Application.MVC.Model");
		em = emf.createEntityManager();
	}
	public static void closeEntityManager() {
		Model.em.close();
		Model.emf.close();
	}


}
