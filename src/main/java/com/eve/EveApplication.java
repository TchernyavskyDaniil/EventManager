package com.eve;

import com.eve.entity.Address;
import com.eve.entity.Event;
import com.eve.entity.Role;
import com.eve.entity.User;
import com.eve.repository.AddressRepository;
import com.eve.repository.EventRepository;
import com.eve.repository.RoleRepository;
import com.eve.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication(scanBasePackages={
		"com.eve.config","com.eve.web.dto","com.eve.service",
		"com.eve.controller",
		"com.eve.entity", "com.eve.util","com.eve.repository"})
public class EveApplication  implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(EveApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	EventRepository eventRepository;

	@Transactional
	@Override
	public void run(String... strings) throws Exception {
		Set<Role> roles = new HashSet<>();
		Set<User> users = new HashSet<>();


		User user = userRepository.findByUsername("user");
		if (user!=null){
			userRepository.delete(user);
		}
		user = new User("user","password","email");
		user.setEnabled(true);
		Role roleUser = roleRepository.findByName("USER");
		if (roleUser!=null){
			roleRepository.delete(roleUser);
		}
		roleUser = new Role("USER");


		roles.add(roleUser);
		users.add(user);

		user.setRoles(roles);
		roleUser.setUsers(users);


		userRepository.save(user);
		roleRepository.save(roles);





		Address address1 = new Address("home","Russia",
				"Krasnodar","Street1");

		Event event = new Event("Party",new Date(),"kokoko");

		Set<Event> events = new HashSet<>();
		events.add(event);

		address1.setEvents(events);


		user.setEvents(events);

		event.setOwner(user);
		event.setAddress(address1);

		addressRepository.save(address1);
		eventRepository.save(event);


	}
}
