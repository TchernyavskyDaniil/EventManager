package com.eve;

import com.eve.entity.Role;
import com.eve.entity.User;
import com.eve.repository.RoleRepository;
import com.eve.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.transaction.Transactional;
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

	@Transactional
	@Override
	public void run(String... strings) throws Exception {



	}
}
