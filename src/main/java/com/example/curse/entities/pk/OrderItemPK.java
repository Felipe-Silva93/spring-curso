package com.example.curse.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.curse.entities.Order;
import com.example.curse.entities.Product;

//CLASSE AUXILIAR PARA REFERENCIAS AS CHAVER PRIMARIAS,CARDINALIDADE ETC.

@Embeddable//nesse caso por ser uma classe auxiliar e uma clave primaria conposta é usado essa anotação
public class OrderItemPK implements Serializable {//serializeble é para quando voce quer que esses objetos eja transformados em cadeia de bits//permitindo que o objeto trafegue na rede, seja gravado em arquivos etc

	private static final long serialVersionUID = 1L;
	 
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
	
	
	
}
