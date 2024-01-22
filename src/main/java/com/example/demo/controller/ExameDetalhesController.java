/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

/**
 *
 * @author Pedro
 */
import com.example.demo.Exame;
import com.example.demo.repository.service.ExameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExameDetalhesController {

    private final ExameService exameService;  // Injete o serviço ExameService

    public ExameDetalhesController(ExameService exameService) {
        this.exameService = exameService;
    }

    @GetMapping("/index_resultado_exame")
public String showExameDetalhes(@RequestParam String id, @RequestParam String nomePaciente, Model model) {
    // Convertendo a String do ID para Long
    Long pacienteId = Long.valueOf(id);

    // Aqui você deve recuperar os dados do exame para o paciente com o ID fornecido
    Exame exame = exameService.buscarExamePorPacienteId(pacienteId);

    // Adicione o objeto exame ao modelo para ser acessado no Thymeleaf
    model.addAttribute("exame", exame);

    // Retorna o nome da página Thymeleaf para ser renderizada
    return "index_resultado_exame";
}
}