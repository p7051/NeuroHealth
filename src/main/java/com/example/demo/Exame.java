/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

/**
 *
 * @author Pedro
 */
import javax.persistence.*;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Exame")
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String convenio;
    
    @ManyToOne
    @JoinColumn(name = "medico_avaliador_id")
    private Medico medicoAvaliador;

    private boolean usaMedicacao;
    private String observacoes;
    private String medicoRequisitanteNome;
    private String crmMedicoRequisitante;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private String tipoExame;
    private String alteracoes;
    private String laudoMedico;
    private String equipamentoDescricao;
 
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataExame;

    // Getters e setters
    public Date getDataExame() {
        return dataExame;
    }

    public void setDataExame(Date dataExame) {
        this.dataExame = dataExame;
    }
    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public Medico getMedicoAvaliador() {
        return medicoAvaliador;
    }

    public void setMedicoAvaliador(Medico medicoAvaliador) {
        this.medicoAvaliador = medicoAvaliador;
    }

    public boolean isUsaMedicacao() {
        return usaMedicacao;
    }

    public void setUsaMedicacao(boolean usaMedicacao) {
        this.usaMedicacao = usaMedicacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getMedicoRequisitanteNome() {
        return medicoRequisitanteNome;
    }

    public void setMedicoRequisitanteNome(String medicoRequisitanteNome) {
        this.medicoRequisitanteNome = medicoRequisitanteNome;
    }

    public String getCrmMedicoRequisitante() {
        return crmMedicoRequisitante;
    }

    public void setCrmMedicoRequisitante(String crmMedicoRequisitante) {
        this.crmMedicoRequisitante = crmMedicoRequisitante;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(String tipoExame) {
        this.tipoExame = tipoExame;
    }

    public String getAlteracoes() {
        return alteracoes;
    }

    public void setAlteracoes(String alteracoes) {
        this.alteracoes = alteracoes;
    }

    public String getLaudoMedico() {
        return laudoMedico;
    }

    public void setLaudoMedico(String laudoMedico) {
        this.laudoMedico = laudoMedico;
    }

    public String getEquipamentoDescricao() {
        return equipamentoDescricao;
    }

    public void setEquipamentoDescricao(String equipamentoDescricao) {
        this.equipamentoDescricao = equipamentoDescricao;
    }
}
