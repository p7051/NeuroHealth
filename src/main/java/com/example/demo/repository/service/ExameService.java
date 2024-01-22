/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository.service;

import com.example.demo.Exame;
import com.example.demo.Medico;
import com.example.demo.Paciente;
import com.example.demo.repository.ExameRepository;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.repository.PacienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pedro
 */
@Service
public class ExameService {

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;
    
    @Autowired
    public ExameService(ExameRepository exameRepository) {
        this.exameRepository = exameRepository;
    }
    public Exame getExameById(Long exameId) {
        return exameRepository.findById(exameId).orElse(null);
    }

    public void salvarExame(Exame exame) {
        exameRepository.save(exame);
    }

    public void salvarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public void salvarMedico(Medico medico) {
        medicoRepository.save(medico);
    }
 // Método para buscar um exame pelo ID do paciente
   

    public Exame buscarExamePorPacienteId(Long pacienteId) {
        // Obtendo todos os exames do banco de dados
        List<Exame> todosExames = exameRepository.findAll();

        // Iterando sobre a lista de exames para encontrar o exame do paciente
        for (Exame exame : todosExames) {
            if (exame.getPaciente().getId().equals(pacienteId)) {
                // Se o ID do paciente corresponder, retornamos o exame
                return exame;
            }
        }

        // Se nenhum exame for encontrado para o ID do paciente, pode lançar uma exceção ou retornar null
        throw new RuntimeException("Exame não encontrado para o ID do paciente: " + pacienteId);
    }
 }