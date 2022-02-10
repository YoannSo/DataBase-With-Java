package northwind;
// Generated 17 avr. 2021 � 19:51:14 by Hibernate Tools 5.2.12.Final

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Customers generated by hbm2java
 */
@Entity
@Table(name = "customers", schema = "NORTHWIND", catalog = "NORTHWIND")
public class Customers implements java.io.Serializable {

	private Integer id;
	private String company;
	private String lastName;
	private String firstName;
	private String emailAddress;
	private String jobTitle;
	private String businessPhone;
	private String homePhone;
	private String mobilePhone;
	private String faxNumber;
	private String address;
	private String city;
	private String stateProvince;
	private String zipPostalCode;
	private String countryRegion;
	private String webPage;
	private String notes;
	private Blob attachments;
	private Set<Orders> orderses = new HashSet<Orders>(0);

	public Customers() {
	}

	public Customers(String company, String lastName, String firstName, String emailAddress, String jobTitle,
			String businessPhone, String homePhone, String mobilePhone, String faxNumber, String address, String city,
			String stateProvince, String zipPostalCode, String countryRegion, String webPage, String notes,
			Blob attachments, Set<Orders> orderses) {
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
		this.orderses = orderses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "company", length = 50)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "last_name", length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "first_name", length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "email_address", length = 50)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name = "job_title", length = 50)
	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Column(name = "business_phone", length = 25)
	public String getBusinessPhone() {
		return this.businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	@Column(name = "home_phone", length = 25)
	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "mobile_phone", length = 25)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "fax_number", length = 25)
	public String getFaxNumber() {
		return this.faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	@Column(name = "address", length = 199)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "state_province", length = 50)
	public String getStateProvince() {
		return this.stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	@Column(name = "zip_postal_code", length = 15)
	public String getZipPostalCode() {
		return this.zipPostalCode;
	}

	public void setZipPostalCode(String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

	@Column(name = "country_region", length = 50)
	public String getCountryRegion() {
		return this.countryRegion;
	}

	public void setCountryRegion(String countryRegion) {
		this.countryRegion = countryRegion;
	}

	@Column(name = "web_page", length = 199)
	public String getWebPage() {
		return this.webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	@Column(name = "notes", length = 199)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "attachments")
	public Blob getAttachments() {
		return this.attachments;
	}

	public void setAttachments(Blob attachments) {
		this.attachments = attachments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customers")
	public Set<Orders> getOrderses() {
		if(this.orderses==null) {
			System.out.println("LOL");
		}
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

}
