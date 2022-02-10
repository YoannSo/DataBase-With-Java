package northwind;
// Generated 17 avr. 2021 � 19:51:14 by Hibernate Tools 5.2.12.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Invoices generated by hbm2java
 */
@Entity
@Table(name = "invoices", schema = "NORTHWIND", catalog = "NORTHWIND")
public class Invoices implements java.io.Serializable {

	private Integer id;
	private Orders orders;
	private Date invoiceDate;
	private Date dueDate;
	private BigDecimal tax;
	private BigDecimal shipping;
	private BigDecimal amountDue;

	public Invoices() {
	}

	public Invoices(Orders orders, Date invoiceDate, Date dueDate, BigDecimal tax, BigDecimal shipping,
			BigDecimal amountDue) {
		this.orders = orders;
		this.invoiceDate = invoiceDate;
		this.dueDate = dueDate;
		this.tax = tax;
		this.shipping = shipping;
		this.amountDue = amountDue;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "invoice_date", length = 26)
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "due_date", length = 26)
	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name = "tax", scale = 4)
	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	@Column(name = "shipping", scale = 4)
	public BigDecimal getShipping() {
		return this.shipping;
	}

	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}

	@Column(name = "amount_due", scale = 4)
	public BigDecimal getAmountDue() {
		return this.amountDue;
	}

	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}

}
