/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository;

/**
 *
 * @author Pedro
 */
import com.example.demo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
 

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // Se necessário, adicione métodos específicos para consultas no banco de dados
}
