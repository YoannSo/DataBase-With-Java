package northwind;
// Generated 17 avr. 2021 � 19:51:14 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * OrdersTaxStatus generated by hbm2java
 */
@Entity
@Table(name = "orders_tax_status", schema = "NORTHWIND", catalog = "NORTHWIND")
public class OrdersTaxStatus implements java.io.Serializable {

	private int id;
	private String taxStatusName;
	private Set<Orders> orderses = new HashSet<Orders>(0);

	public OrdersTaxStatus() {
	}

	public OrdersTaxStatus(int id, String taxStatusName) {
		this.id = id;
		this.taxStatusName = taxStatusName;
	}

	public OrdersTaxStatus(int id, String taxStatusName, Set<Orders> orderses) {
		this.id = id;
		this.taxStatusName = taxStatusName;
		this.orderses = orderses;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "tax_status_name", nullable = false, length = 50)
	public String getTaxStatusName() {
		return this.taxStatusName;
	}

	public void setTaxStatusName(String taxStatusName) {
		this.taxStatusName = taxStatusName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordersTaxStatus")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

}