package com.kaos.escola.controllers;

import com.kaos.escola.dto.AlunosDTO;
import com.kaos.escola.models.Alunos;
import com.kaos.escola.services.AlunosService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AlunosController {

    private AlunosService service;

    @GetMapping("/alunos")
    public String index(Model model){
        model.addAttribute("lista", service.list());
        return "alunos/lista";
    }

    @GetMapping("aluno/cadastro")
    public String cadastroAlunos (){
        return "alunos/cadastro.html";
    }

    @PostMapping("aluno/cadastrar")
    public String cadastrarAlunos(AlunosDTO dados){
        service.save(dados);
        return "redirect:/alunos";
    }

    @GetMapping("aluno/editar/{id}")
    public String editarAluno(@PathVariable Long id, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("dados", service.findById(id));
        return "alunos/cadastro.html";
    }

    @PostMapping("aluno/editar")
    public String atualizarAlunos(AlunosDTO dados) throws ChangeSetPersister.NotFoundException {
        service.edit(dados);
        return "redirect:/alunos";
    }

    @GetMapping("aluno/apagar/{id}")
    public String apagarAlunos(@PathVariable Long id, Model model) throws ChangeSetPersister.NotFoundException {
        service.delete(id);
        return "redirect:/alunos";
    }

}
