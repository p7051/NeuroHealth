/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

/**
 *
 * @author Pedro
 */




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password) {
        // Lógica para verificar o tipo de usuário com base nas credenciais
        String userType = getUserType(username, password);

        // Redirecionar com base no tipo de usuário
        if ("medico".equals(userType) || "tecnico".equals(userType) || "secretaria".equals(userType)) {
            return "redirect:/index_exames";
        } else {
            // Se não for medico, tecnico ou secretaria, assume-se que é um paciente
            // Redirecionar para index_resultado_exame com o campo do usuário como ID e o campo da senha como nome do paciente
            return "redirect:/index_resultado_exame?id=" + username + "&nomePaciente=" + password;
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    private String getUserType(String username, String password) {
        // Lógica para verificar as credenciais (substitua com sua própria lógica)
        if ("usuario_medico".equals(username) && "senha_medico".equals(password)) {
            return "medico";
        } else if ("usuario_tecnico".equals(username) && "senha_tecnico".equals(password)) {
            return "tecnico";
        } else if ("usuario_secretaria".equals(username) && "senha_secretaria".equals(password)) {
            return "secretaria";
        } else {
            return "outro";
        }
    }
}