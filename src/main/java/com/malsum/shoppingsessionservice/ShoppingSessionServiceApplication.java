package com.malsum.shoppingsessionservice;

import com.malsum.shoppingsessionservice.entity.Item;
import com.malsum.shoppingsessionservice.entity.ItemWrapper;
import com.malsum.shoppingsessionservice.entity.Session;
import com.malsum.shoppingsessionservice.service.SessionService;
import com.malsum.shoppingsessionservice.service.SessionServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Random;

@SpringBootApplication
@EnableWebMvc
@EnableFeignClients
public class ShoppingSessionServiceApplication {

@Autowired
BeanFactory beanFactory;


//	@Bean(name = "itemWrapper")
//	@Scope(scopeName = "prototype")
//	public ItemWrapper createItemWrapper(int id, int quantity,double cost){
//		ItemWrapper itemWrapper = new ItemWrapper();
//		itemWrapper.setId(id);
//		itemWrapper.setQuantity(quantity);
//		itemWrapper.setCost(cost);
//		return itemWrapper;
//	}

	@Bean(name = "myBean")
	@Scope(scopeName = "prototype")
	public String createString(){
		return "string prototype  " + new Random().nextInt();
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingSessionServiceApplication.class, args);
	}

}
