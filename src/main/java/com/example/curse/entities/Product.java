package com.example.curse.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	//implementar as relaçoes entre entidade(tabelas)//foi estanciada para garantir que a coleção não comece valendo nula ela deve começar vazia porem estanciada//foi usado o HashSet ao invez do Set por que o Set é uma interface e não pode ser estanciada da mesma frma que se usa um List e do outro lado um ArreyList
	@ManyToMany        //@Transient serve para ignorar a relação abaixo assim ele se usa provisoriamente
	@JoinTable(name = "tb_product_category",joinColumns = @JoinColumn(name = "Product_id"),/*definindo a chave estrangeira de uma entidade oposta*/
	inverseJoinColumns = @JoinColumn(name="category_id"))//nome da tabela e quais são as chaves estranjeiras que vai se associar com as tabelas de produtos e tabelas de categoria
	private Set<Category>categories = new HashSet<>();//em vez de List será usado o Set para garantir que não vai ter produtos repetidos

	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items =new HashSet<>();
	
	public Product() {
		
	}

	public Product(Long id, String name, String description, Double price, String imgUrl) {//não se coloca a coleção no contrutor pois ela já está sendo estanciada
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	@JsonIgnore
	public Set<Order>getOrders(){
		Set<Order>set = new HashSet<>();
		for(OrderItem x: items){//percorrer cada objeto orderItem
			set.add(x.getOrder());
		}
		return set;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
}
