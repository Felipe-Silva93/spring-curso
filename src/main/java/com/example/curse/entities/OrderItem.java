package com.example.curse.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.curse.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="tb_order_item")
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId//anotação diferente
	private  OrderItemPK id = new OrderItemPK();//atributo indentificador correspondente a chave primaria//toda vez for criar uma classe que é o id conposto é preciso estanciar
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
		
	}
		//estanciando Order order, Product product é preciso fazer os gets e sets
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	@JsonIgnore
	public Order getOrder() {//esse get está chamando o pedido associado ao item de pedido e o pedido chama ele de novo então foi colocado um @JasonIgnore
		return id.getOrder();
	}

	public void serOrder(Order order) {//informando o pedido no parametro o metodo vai no id e vai jogar o pedido la dentro
		id.setOrder(order);
	}
	

	public Product getProduct() {
		return id.getProduct();
	}

	public void serProduct(Product product) {//informando o pedido no parametro o metodo vai no id e vai jogar o pedido la dentro
		id.setProduct(product);
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSubTotal() {//esse é um resultado do sub total de um intem
		return price*quantity;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
}
