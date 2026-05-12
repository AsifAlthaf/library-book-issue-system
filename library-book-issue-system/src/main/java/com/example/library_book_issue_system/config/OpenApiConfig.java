package com.example.library_book_issue_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI libraryOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Local Development Server");
        
        Contact contact = new Contact();
        contact.setName("Library Management Team");
        contact.setEmail("support@library.com");
        
        License license = new License();
        license.setName("MIT License");
        license.setUrl("https://opensource.org/licenses/MIT");
        
        Info info = new Info();
        info.setTitle("Library Book Issue & Return Service API");
        info.setVersion("1.0.0");
        info.setDescription("A professional RESTful backend service for managing library books, members, and book issue/return operations. " +
                "This API provides complete CRUD operations, business rule enforcement, and comprehensive validation.");
        info.setContact(contact);
        info.setLicense(license);
        
        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(info);
        openAPI.setServers(List.of(localServer));
        
        return openAPI;
    }
}
