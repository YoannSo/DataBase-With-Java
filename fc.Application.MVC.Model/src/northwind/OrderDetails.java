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
 * OrderDetails generated by hbm2java
 */
@Entity
@Table(name = "order_details", schema = "NORTHWIND", catalog = "NORTHWIND")
public class OrderDetails implements java.io.Serializable {

	private Integer id;
	private OrderDetailsStatus orderDetailsStatus;
	private Orders orders;
	private Products products;
	private BigDecimal quantity;
	private BigDecimal unitPrice;
	private double discount;
	private Date dateAllocated;
	private Integer purchaseOrderId;
	private Integer inventoryId;

	public OrderDetails() {
	}

	public OrderDetails(Orders orders, BigDecimal quantity, double discount) {
		this.orders = orders;
		this.quantity = quantity;
		this.discount = discount;
	}

	public OrderDetails(OrderDetailsStatus orderDetailsStatus, Orders orders, Products products, BigDecimal quantity,
			BigDecimal unitPrice, double discount, Date dateAllocated, Integer purchaseOrderId, Integer inventoryId) {
		this.orderDetailsStatus = orderDetailsStatus;
		this.orders = orders;
		this.products = products;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.dateAllocated = dateAllocated;
		this.purchaseOrderId = purchaseOrderId;
		this.inventoryId = inventoryId;
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
	@JoinColumn(name = "status_id")
	public OrderDetailsStatus getOrderDetailsStatus() {
		return this.orderDetailsStatus;
	}

	public void setOrderDetailsStatus(OrderDetailsStatus orderDetailsStatus) {
		this.orderDetailsStatus = orderDetailsStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	public Products getProducts() {
		return this.products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	@Column(name = "quantity", nullable = false, precision = 18, scale = 4)
	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Column(name = "unit_price", scale = 4)
	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "discount", nullable = false, precision = 17, scale = 0)
	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_allocated", length = 26)
	public Date getDateAllocated() {
		return this.dateAllocated;
	}

	public void setDateAllocated(Date dateAllocated) {
		this.dateAllocated = dateAllocated;
	}

	@Column(name = "purchase_order_id")
	public Integer getPurchaseOrderId() {
		return this.purchaseOrderId;
	}

	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	@Column(name = "inventory_id")
	public Integer getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

}
