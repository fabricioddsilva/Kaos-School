package com.kaos.escola.controllers;

import com.kaos.escola.models.Alunos;
import com.kaos.escola.repositories.AlunosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AlunosController {

    @Autowired
    private AlunosRepository alunosRepo;

    @GetMapping("/alunos")
    public String index(Model model){
        List<Alunos> alunos = alunosRepo.alunos();
        model.addAttribute("lista", alunos);
        return "alunos/lista";
    }

    @GetMapping("aluno/cadastro")
    public String cadastroAlunos (){
        return "alunos/cadastro";
    }

    @PostMapping("aluno/cadastrar")
    public String cadastrarAlunos(Alunos dados){
        alunosRepo.save(dados);
        return "redirect:/alunos";
    }

    @GetMapping("aluno/editar/{id}")
    public String editarAluno(@PathVariable Long id, Alunos dados, Model model){
        Optional<Alunos> aluno = alunosRepo.findById(id);
        model.addAttribute("dados", aluno);
        return "alunos/cadastro";
    }

    @PostMapping("aluno/editar")
    public String atualizarAlunos(Alunos dados, Model model){
        Optional<Alunos> aluno = alunosRepo.findById(dados.getId());

        if(aluno != null){
            Alunos alunos = aluno.get();
            alunos.setNome(dados.getNome());
            alunos.setIdade(dados.getIdade());
            alunos.setTurma(dados.getTurma());
            alunos.setTurno(dados.getTurno());
            alunosRepo.save(dados);
            return "redirect:/alunos";
        }else{
            model.addAttribute("erro!", "Alguma informação está incorreta!");
            return "redirect:/alunos";
        }

    }

    @GetMapping("aluno/apagar/{id}")
    public String apagarAlunos(@PathVariable Long id, Model model){
        Optional<Alunos> aluno = alunosRepo.findById(id);
        if(aluno != null){
            alunosRepo.deleteById(id);
            return "redirect:/alunos";
        }else{
            model.addAttribute("erro!", "O aluno com o ID " + id + " não foi encontrado.");
            return "redirect:/alunos";
        }
    }

}
