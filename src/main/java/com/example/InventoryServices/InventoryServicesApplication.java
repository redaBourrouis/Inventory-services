package com.example.InventoryServices;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
}

@RepositoryRestResource
interface ProductRepo extends JpaRepository<Product, Long> {

}

@SpringBootApplication
public class InventoryServicesApplication {


    public static void main(String[] args) {
        SpringApplication.run(InventoryServicesApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepo productRepo, RepositoryRestConfiguration repositoryRestConfiguration) {
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Product.class);
            productRepo.save(new Product(null, "PRODUIT1", 6788));
            productRepo.save(new Product(null, "PRODUIT2", 4890));
            productRepo.save(new Product(null, "PRODUIT3", 2900));

            productRepo.findAll().forEach(System.out::println);
        };


    }
}
