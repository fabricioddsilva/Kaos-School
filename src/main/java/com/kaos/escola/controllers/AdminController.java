package com.kaos.escola.controllers;

import com.kaos.escola.dto.AdminDTO;
import com.kaos.escola.models.Admin;
import com.kaos.escola.repositories.AdminRepository;
import com.kaos.escola.services.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AdminController {

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
    public String cadastrar(AdminDTO dados){
        adminService.save(dados);
        return "redirect:/";
    }

    @PostMapping("/admin")
    public String loginAdmin(AdminDTO dados, Model model, HttpSession session){
        AdminDTO login = adminService.login(dados);

        if (login != null) {
            session.setAttribute("login", login);

            return "redirect:/admin/lista";
        } else {
            model.addAttribute("erro", "Usuário ou Senha Inválidos");
            return "index";
        }
    }

    @GetMapping("/admin/lista")
    public String listaAdmin(AdminDTO dados, HttpSession session, Model model){
        AdminDTO admin = (AdminDTO) session.getAttribute("login");
        model.addAttribute("admin", admin);
        model.addAttribute("lista", adminService.list());
        return "admin/lista";
    }

    @GetMapping("/deslogar")
    public String deslogar(HttpSession session){
        session.invalidate();
        return "index";
    }

    @GetMapping("admin/editar/{id}")
    public String editarAdmin(@PathVariable("id") Long id, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("dados", adminService.findById(id));
        return "admin/cadastro";
    }

    @PostMapping("admin/editar")
    public String atualizarAlunos(AdminDTO dados) throws ChangeSetPersister.NotFoundException {
        adminService.edit(dados);
            return "redirect:/";

    }

    @GetMapping("admin/excluir/{id}")
    public String excluirAdmin(@PathVariable("id") Long id, HttpSession session) throws ChangeSetPersister.NotFoundException {
        session.invalidate();
        adminService.delete(id);
        return "redirect:/";
    }


}

