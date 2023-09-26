package com.kaos.escola.services;

import com.kaos.escola.models.Administradores;
import com.kaos.escola.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    public List<Administradores> lista(){
        List<Administradores> lista = adminRepo.admins();
        return lista;
    }
}
