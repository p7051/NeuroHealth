/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository.service;

/**
 *
 * @author Pedro
 */
import com.example.demo.Paciente;
import com.example.demo.ResourceNotFoundException;
import com.example.demo.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

   public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente obterPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));
    }
public void salvarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }
 public Paciente getPacienteById(Long pacienteId) {
        return pacienteRepository.findById(pacienteId)
                                 .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + pacienteId));
    }
 
    // Outros métodos de serviço, como salvar, atualizar, excluir, etc.
}