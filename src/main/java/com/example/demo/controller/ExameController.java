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
import com.example.demo.Medico;
import com.example.demo.Paciente;
import com.example.demo.repository.ExameRepository;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.repository.service.ExameService;
import com.example.demo.repository.service.MedicoService;
import com.example.demo.repository.service.PacienteService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExameController {

    private final PacienteService pacienteService;
   
    private final MedicoService medicoService;
    
    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ExameService exameService;
    // Construtor para injetar o ExameRepository
    @Autowired
public ExameController(
        ExameService exameService,
        PacienteService pacienteService,
        MedicoService medicoService) {
    this.exameService = exameService;
    this.pacienteService = pacienteService;
    this.medicoService = medicoService;
}
    
@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


@GetMapping("/index_cadastro_exame")
public String mostrarFormularioCadastro(Model model) {
    model.addAttribute("exame", new Exame());
    // Não crie instâncias de Paciente e Medico aqui
    return "index_cadastro_exame";
}

@PostMapping("/cadastrar-paciente")
public ResponseEntity<String> cadastrarPaciente(@ModelAttribute Paciente paciente) {
    try {
        // Lógica para salvar paciente no banco de dados
        pacienteService.salvarPaciente(paciente);
        return ResponseEntity.ok("Paciente cadastrado com sucesso!");
    } catch (Exception e) {
        // Manipule a exceção, faça o log ou forneça uma mensagem de erro
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar o paciente. Por favor, tente novamente.");
    }
}

    // Cadastrar Médico
    @PostMapping("/cadastrar-medico")
    public ResponseEntity<String> cadastrarMedico(@ModelAttribute Medico medico) {
        try {
            // Logic to save medico in the database
            medicoService.salvarMedico(medico);
            return ResponseEntity.ok("Médico cadastrado com sucesso!");
        } catch (Exception e) {
            // Handle the exception, log it, or provide an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar o médico. Por favor, tente novamente.");
        }
    }


    // Cadastrar Exame
    @PostMapping("/cadastrar-exame")
    public String cadastrarExame(@ModelAttribute Exame exame, 
                                 @RequestParam("medicoAvaliadorId") Long medicoAvaliadorId,
                                 @RequestParam("pacienteId") Long pacienteId) {
        // Retrieve Medico and Paciente objects from the database using their IDs
        Medico medicoAvaliador = medicoService.getMedicoById(medicoAvaliadorId);
        Paciente paciente = pacienteService.getPacienteById(pacienteId);

        // Set the retrieved Medico and Paciente objects in the Exame
        exame.setMedicoAvaliador(medicoAvaliador);
        exame.setPaciente(paciente);

        // Logic to save exame in the database
        exameService.salvarExame(exame);

        return "redirect:/index_exames"; // Redirect to the appropriate page
    }


    


    // Controlador para a página de detalhes do exame
    @GetMapping("/index_detalhes_exame/{exameId}")
    public String exibirDetalhesExame(@PathVariable Long exameId, Model model) {
        // Lógica para obter os detalhes do exame do repositório
        Exame exame = exameRepository.findById(exameId).orElse(null);

        // Adicione o exame ao modelo para ser usado na página Thymeleaf
        model.addAttribute("exame", exame);

        // Retorne o nome da página Thymeleaf para exibir
        return "index_detalhes_exame";
    }

  
    
    
 @GetMapping("/index_exames")
    public String showExames(Model model) {
        List<Exame> exames = exameRepository.findAll();
        List<Medico> medicos = medicoService.listarTodos();
        List<Paciente> pacientes = pacienteService.listarTodos();

        model.addAttribute("exames", exames);
        model.addAttribute("medicos", medicos);
        model.addAttribute("pacientes", pacientes);

        return "index_exames";
    }
    
    
    @GetMapping("/pesquisar_exame")
public String pesquisarExame(@RequestParam("exameId") Long exameId, Model model) {
    Optional<Exame> exameOptional = exameRepository.findById(exameId);

    if (exameOptional.isPresent()) {
        List<Exame> examesEncontrados = Collections.singletonList(exameOptional.get());
        model.addAttribute("exames", examesEncontrados);
    } else {
        // Exame não encontrado
        model.addAttribute("exames", Collections.emptyList());
    }

    // Adicione a lógica necessária para recuperar todos os exames ou exibir uma mensagem de erro

    return "index_exames";
    
    }


 @PostMapping("/editar_exame/{exameId}")
public String editarExame(@PathVariable Long exameId, @RequestParam Map<String, String> params) {
    Exame exame = exameService.getExameById(exameId);

    exame.setConvenio(params.get("convenio"));
    exame.setTipoExame(params.get("tipoExame"));
    exame.setDataExame(parseData(params.get("dataExame"))); // Utilize um método para converter a String para Date
    exame.setUsaMedicacao(Boolean.parseBoolean(params.get("usaMedicacao")));
    exame.setMedicoRequisitanteNome(params.get("medicoRequisitanteNome"));
    exame.setCrmMedicoRequisitante(params.get("crmMedicoRequisitante"));
    exame.setEquipamentoDescricao(params.get("equipamentoDescricao"));

    exameService.salvarExame(exame);

    return "redirect:/index_exames";
}

@PostMapping("/editar_paciente/{exameId}")
public String editarPaciente(@PathVariable Long exameId, @RequestParam Map<String, String> params) {
    Exame exame = exameService.getExameById(exameId);
    Paciente paciente = exame.getPaciente();

    paciente.setNome(params.get("nomePaciente"));
    paciente.setDataNascimento(parseData(params.get("dataNascimento")));
    paciente.setCpf(params.get("cpf"));
    paciente.setRg(params.get("rg"));
    paciente.setTelefone(params.get("telefone"));
    paciente.setEndereco(params.get("endereco"));
    paciente.setSexo(params.get("sexo"));

    exameService.salvarPaciente(paciente);

    return "redirect:/index_exames";
}

@PostMapping("/editar_medico/{exameId}")
public String editarMedico(@PathVariable Long exameId, @RequestParam Map<String, String> params) {
    Exame exame = exameService.getExameById(exameId);
    Medico medico = exame.getMedicoAvaliador();

    medico.setNome(params.get("nomeMedico"));
    medico.setCrm(params.get("crm"));

    exameService.salvarMedico(medico);

    return "redirect:/index_exames";
}

// Adicione os métodos para editar outras seções, se necessário

// Método utilitário para converter String para Date
private Date parseData(String dataString) {
    try {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
    } catch (ParseException e) {
        // Lide com a exceção, se necessário
        e.printStackTrace();
        return null;
    }
}

    
@PostMapping("/editar_observacoes/{exameId}")
    public String editarObservacoes(@PathVariable Long exameId, @RequestParam("observacoes") String observacoes) {
        // Lógica para editar observações do exame
        Exame exame = exameService.getExameById(exameId);
        exame.setObservacoes(observacoes);

        // Salve as alterações no banco de dados
        exameService.salvarExame(exame);

        return "redirect:/index_exames";
    }
 

    @PostMapping("/editar_laudo_medico/{exameId}")
    public String editarLaudoMedico(@PathVariable Long exameId, @RequestParam("laudoMedico") String laudoMedico) {
        // Lógica para editar laudo médico do exame
        Exame exame = exameService.getExameById(exameId);
        exame.setLaudoMedico(laudoMedico);

        // Salve as alterações no banco de dados
        exameService.salvarExame(exame);

        return "redirect:/index_exames";
    }
    @PostMapping("/editar_alteracoes/{exameId}")
public String editarAlteracoes(@PathVariable Long exameId, @RequestParam("alteracoes") String alteracoes) {
    // Lógica para editar as alterações do exame
    Exame exame = exameService.getExameById(exameId);
    exame.setAlteracoes(alteracoes);

    // Salve as alterações no banco de dados
    exameService.salvarExame(exame);

    return "redirect:/index_exames";
}
}




