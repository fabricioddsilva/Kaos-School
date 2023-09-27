package com.kaos.escola.controllers;

import com.kaos.escola.models.Administradores;
import com.kaos.escola.models.Alunos;
import com.kaos.escola.repositories.AdminRepository;
import com.kaos.escola.services.AdminService;
import jakarta.persistence.Table;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/cadastro")
    public String cadastro(){
        return "admin/cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Administradores dados){
        adminRepo.save(dados);
        return "redirect:/";
    }

    @PostMapping("/admin")
    public String loginAdmin(Administradores dados, Model model, HttpSession session){
        Administradores login = adminRepo.login(dados.getNome(), dados.getSenha());

        if (login != null) {
            session.setAttribute("login", login);

            return "redirect:/admin/lista";
        } else {
            model.addAttribute("erro", "Usuário ou Senha Inválidos");
            return "index";
        }
    }

    @GetMapping("/admin/lista")
    public String listaAdmin(Administradores dados, HttpSession session, Model model){
        Administradores admin = (Administradores) session.getAttribute("login");
        model.addAttribute("admin", admin);
        model.addAttribute("lista", adminService.lista());
        return "admin/lista";
    }

    @GetMapping("/deslogar")
    public String deslogar(HttpSession session){
        session.invalidate();
        return "index";
    }

    @GetMapping("admin/editar/{id}")
    public String editarAdmin(@PathVariable("id") Long id, Model model){
        Optional<Administradores> dados = adminRepo.findById(id);
        model.addAttribute("dados", dados);
        return "admin/cadastro";
    }

    @PostMapping("admin/editar")
    public String atualizarAlunos(Administradores dados, Model model, HttpSession session){
        Optional<Administradores> conta = adminRepo.findById(dados.getId());

        if(conta != null){
            Administradores admin = conta.get();
            admin.setNome(dados.getNome());
            admin.setSenha(dados.getSenha());
            adminRepo.save(dados);
            return "redirect:/";
        }else{
            model.addAttribute("erro!", "Alguma informação está incorreta!");
            return "redirect:/admin";
        }

    }

    @GetMapping("admin/excluir/{id}")
    public String excluirAdmin(@PathVariable("id") Long id){
        adminRepo.deleteById(id);
        return "redirect:/";
    }


}

