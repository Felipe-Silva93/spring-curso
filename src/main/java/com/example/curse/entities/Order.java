package com.example.curse.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.curse.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "td_order")
public class Order implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	//para garantir que o intant seja mostrada conforma o padrão iso0681
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant moment;//para registrar um intante se ultiliza o tipo Instant antes do java 8 era o tiop Date
	
	private Integer orderStatus;//o tipo foi trocado apenas internamente mais para o uso esterno ele ainda é do tipo OrderStatus
	
	@ManyToOne
	@JoinColumn(name = "client_id")//entre parenter vai ser passado qual é o nome da chave estrangeira que vai ter no banco de dados
	private User client;
	
	//quando for mapear os Order associado com os orderitem será feito um macete
	@OneToMany(mappedBy ="id.order")//isso é feito por que no OrderItem tem o ide no id tem o pedido
	private Set<OrderItem>items =new HashSet<>();
	
	@OneToOne(mappedBy = "order",cascade=CascadeType.ALL)//no caso um para um estamos mapeando as duas entidades para ter o mesmo id se o pedido for codigo 5 o pagamento tambem vai ser codigo 5
	private Payment payment;
	
	public Order() {
		
	}
	
	
	public Order(Long id, Instant moment,OrderStatus orderStatus, User client ) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);//atribuir o OderStatus para o atributo orderStatus
		this.client = client;
		
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Instant getMoment() {
		return moment;
	}
	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	public OrderStatus getOrderStatus() {
		return  OrderStatus.valueOf(orderStatus);//pegar o numero  interno da classe do tipo Integer e converter para OrderStatus
	}


	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();//receber um numero do tipo OderStatus converter para Integer e guardar na variavel
	
		}
	}
	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
	public Set<OrderItem>getItem(){//fazendo isso os pedidos recohece os items dele
		return items;
	}
	
	public Double getTotal() {
		double sum = 0.0;
		for(OrderItem x : items) {//para todo item de pedido faça
			sum+=x.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}


	


	


	
	
	
}
