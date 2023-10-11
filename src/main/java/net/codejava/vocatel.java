package net.codejava;

import net.codejava.repository.RoleRepository;
import net.codejava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringJwtAuthExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtAuthExampleApplication.class, args);
	}
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Bean
	public void createUserAndRole(){

	}
}
