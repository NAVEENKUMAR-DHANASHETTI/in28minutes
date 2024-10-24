package com.naveen.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoServices {
	
	public static List<User> users = new ArrayList<User>();
	
	private static int usersCount = 0;
	
	static {
		users.add(new User(++usersCount, "Avinash", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "Raju", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount, "Ravi", LocalDate.now().minusYears(28)));
	}

	
	
	public List<User> findAll(){
		return users;
	}
	
	public User finOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().get();
	}
	
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
		
	}

}
