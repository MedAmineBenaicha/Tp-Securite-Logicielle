package com.example.banquesecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BanqueSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanqueSecurityApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    CommandLineRunner run(ClientService clientService) {
        return args -> {
            Role roleUser = clientService.saveRole(new Role(null, "ROLE_USER"));
            Role roleAdmin = clientService.saveRole(new Role(null, "ROLE_ADMIN"));

            Client user1 = clientService.saveUser(new Client(null, "user", "user", "user123", false, new ArrayList<>()));
            Client admin = clientService.saveUser(new Client(null, "admin1", "admin1", "admin123", true, new ArrayList<>()));

            clientService.addRoleToUser("admin1","ROLE_ADMIN");
            clientService.addRoleToUser("user","ROLE_USER");

            clientService.saveUser(user1);
            clientService.saveUser(admin);
        };
    }*/
}
