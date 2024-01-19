/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Pedro
 */


@Controller
public class TelaController {
    
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    
    @GetMapping("/index_pacientes")
    public String showListPage() {
        return "index_pacientes";
    }
    @GetMapping("/index_detalhes_paciente")
    public String showDetalhesPage() {
        return "index_detalhes_paciente";
    }
   @GetMapping("/cadastro")
    public String showCadPage() {
        return "cadastro";
    } 
}
