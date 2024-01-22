/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository.service;

/**
 *
 * @author Pedro
 */
import com.example.demo.Medico;
import com.example.demo.ResourceNotFoundException;
import com.example.demo.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Medico obterPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com ID: " + id));
    }
public void salvarMedico(Medico medico) {
        medicoRepository.save(medico);
    }

public Medico getMedicoById(Long medicoId) {
        return medicoRepository.findById(medicoId)
                              .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com o ID: " + medicoId));
    }



    // Outros métodos de serviço, como salvar, atualizar, excluir, etc.
}