package com.guerlak.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.guerlak.model.Address;
import com.guerlak.model.Category;
import com.guerlak.model.Order;
import com.guerlak.model.OrderItem;
import com.guerlak.model.Payment;
import com.guerlak.model.Product;
import com.guerlak.model.User;
import com.guerlak.model.enums.OrderStatus;
import com.guerlak.model.enums.UserType;
import com.guerlak.repositories.AddressRepo;
import com.guerlak.repositories.CategoryRepo;
import com.guerlak.repositories.OrderItemRepo;
import com.guerlak.repositories.OrderRepo;
import com.guerlak.repositories.ProductRepo;
import com.guerlak.repositories.UserRepo;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private OrderItemRepo orderItemRepo;
	@Autowired
	private AddressRepo addressRepo;
	@Autowired
	BCryptPasswordEncoder bcrypt;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", bcrypt.encode("12345"),UserType.PESSOA_FISICA);
		User u2 = new User(null, "Alex Green", "alex@gmail.com", bcrypt.encode("12345"), UserType.PESSOA_FISICA); 
		User u3 = new User(null, "Rafa Leite", "guerlak@gmail.com", bcrypt.encode("aloha"), UserType.PESSOA_FISICA); 
		u3.addProfile(com.guerlak.model.enums.Profile.ADMIN);
		
		userRepo.saveAll(Arrays.asList(u1, u2, u3));
		
		Address ad1 = new Address(u1, "Rio e Janeiro", "Rio de Janeiro", "Onda Carioca", "222222");
		Address ad2 = new Address(u1, "Rio e Janeiro", "Rio de Janeiro","Guedes da Fontoura, 441", "22222");
		
		u1.getAddresses().add(ad1);
		u3.getAddresses().add(ad2);
		
		u1.setUserType(UserType.PESSOA_FISICA);
		
		userRepo.saveAll(Arrays.asList(u1, u2, u3));
		addressRepo.saveAll(Arrays.asList(ad1, ad2));
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		Category cat4 = new Category(null, "Sports");
		Category cat5 = new Category(null, "Wear");

		categoryRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, ""); 
		
		p1.getCategories().add(cat1);
		p2.getCategories().add(cat2);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.WAITING_PAYMENT);
		
		orderRepo.saveAll(Arrays.asList(o1));
		productRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p2, 5, p2.getPrice());	

		orderItemRepo.saveAll(Arrays.asList(oi1, oi2));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		
		o1.setPayment(pay1);
		orderRepo.save(o1);		
	}
}
