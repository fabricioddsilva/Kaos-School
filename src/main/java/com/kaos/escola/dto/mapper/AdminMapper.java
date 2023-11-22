package com.kaos.escola.dto.mapper;

import com.kaos.escola.dto.AdminDTO;
import com.kaos.escola.models.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public AdminDTO toDTO(Admin admin){
        if(admin == null){
            return null;
        }
        return new AdminDTO(admin.getId(), admin.getNome(), admin.getSenha(),
                admin.getMatricula());
    }

    public Admin toEntity(AdminDTO adminDTO){

        if (adminDTO == null){
            return null;
        }

        Admin admin = new Admin();
        if(adminDTO.id() != null){
            admin.setId(adminDTO.id());
        }
        admin.setNome(adminDTO.nome());
        admin.setSenha(adminDTO.senha());
        admin.setMatricula(adminDTO.matricula());

        return admin;
    }
}
