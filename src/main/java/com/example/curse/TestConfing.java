package com.example.curse;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.curse.entities.Category;
import com.example.curse.entities.Order;
import com.example.curse.entities.OrderItem;
import com.example.curse.entities.Payment;
import com.example.curse.entities.Product;
import com.example.curse.entities.User;
import com.example.curse.entities.enums.OrderStatus;
import com.example.curse.repositories.CategoryRepository;
import com.example.curse.repositories.OrderItemRepository;
import com.example.curse.repositories.OrderRepository;
import com.example.curse.repositories.ProductRepository;
import com.example.curse.repositories.UserRepository;

//POR HORA ESA CLASSE VAI SERVIR PARA POPULAR O BANCO COM ALGUNS OBJETOS//classe de configuração para estanciar os dados

   @Configuration// para dizer que essa é uma classe especifica de configuração
   @Profile("test")//para dizer que é uma configuração especifica para o perfil de teste
   public class TestConfing implements CommandLineRunner{//sera execultada quando o programa for iniciado com essa implementação
	
	//injeção de dependencia com framework
	@Autowired
	private UserRepository userRepository ;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

   @Override
   public void run(String... args) throws Exception {//tudo que estiver dentro desse metodo vai ser execultado quando a aplicação for iniciada
	    
	   Category cat1 = new Category(null, "Electronics"); 
	   Category cat2 = new Category(null, "Books"); 
	   Category cat3 = new Category(null, "Computers"); 
	   
	   
	   Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
	   Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
	   Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
	   Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
	   Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
	   
	   categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));//salvar no banco de dados as categorias que foi criada
	   productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
	   
	   
	   //adicionando categoria ao produto
	   p1.getCategories().add(cat2);
	   p2.getCategories().add(cat1);
	   p3.getCategories().add(cat3);
	   p4.getCategories().add(cat3);
	   p5.getCategories().add(cat2);
	   
	   productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));//salvar os produtos de novo com cada associação feita
	   
	   
	    User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
	    User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
	    
	    Order o1 = new Order(null, Instant.parse("2021-06-20T19:53:07Z"),OrderStatus.PAID, u1); //passando o id no valor null o intante e como parametro a String e a operação parse vai estanciar e o 3 valor no parametro é um usuario
	    Order o2 = new Order(null, Instant.parse("2021-07-21T03:42:10Z"),OrderStatus.WAITING_PAYMENT, u2); 
	    Order o3 = new Order(null, Instant.parse("2021-07-22T15:21:22Z"),OrderStatus.WAITING_PAYMENT, u1); 
	
	    userRepository.saveAll(Arrays.asList(u1,u2));//salva esses usuarios no banco//saveAll vai passar uma lista de objetos e ele salva essa lista no banco
	    orderRepository.saveAll(Arrays.asList(o1,o2,o3));
	    
	    OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());//estou falando que o oi1 é do pedido o1 produto p1 quantidade 2 e o preço é feito um getPrice
	    OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
	    OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
	    OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
	    
	    orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));//salvando os 4 items no banco de dados
   
	    //peculharidade> para salvar um obejeto dependente em uma relação um para um não chama o repositoty do proprio obejeto//associação de mão dupla em memoria
	    Payment pay1 = new Payment(null,Instant.parse("2021-06-20T21:53:07Z"),o1);
	    o1.setPayment(pay1);//associou o pedido  1 com o pagamento pay1
	    
	    orderRepository.save(o1);
   
   }
	
}
