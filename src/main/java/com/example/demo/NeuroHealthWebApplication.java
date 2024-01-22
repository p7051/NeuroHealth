package com.example.demo;

import com.example.demo.repository.PacienteRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Controller
@EnableJpaRepositories("com.example.demo.repository")
@EntityScan("com.example.demo")
@SpringBootApplication(scanBasePackages = "com.example")
public class NeuroHealthWebApplication {
@Autowired
    private PacienteRepository pacienteRepository;
    private static List<Paciente> listaPaciente = new ArrayList<>();
    public Long filmeid;    
    

    public static void main(String[] args) {
        SpringApplication.run(NeuroHealthWebApplication.class, args);
    }

    @GetMapping("/")
    public String showInicioPage() {
        return "inicio";
    }
     @GetMapping("/cadastro")
    public String showCadPage() {
        return "cadastro";
    } 

}