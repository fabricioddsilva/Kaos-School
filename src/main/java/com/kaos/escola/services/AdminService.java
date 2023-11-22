package com.kaos.escola.services;

import com.kaos.escola.dto.AdminDTO;
import com.kaos.escola.dto.mapper.AdminMapper;
import com.kaos.escola.models.Admin;
import com.kaos.escola.repositories.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository adminRepo;
    private final AdminMapper mapper;

    public AdminService(AdminRepository adminRepo, AdminMapper mapper){
        this.adminRepo = adminRepo;
        this.mapper = mapper;
    }

    public List<AdminDTO> list(){
        return adminRepo.admins()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public AdminDTO save (AdminDTO dados){
        return mapper.toDTO(adminRepo.save(mapper.toEntity(dados)));
    }

    public AdminDTO findById(Long id) throws ChangeSetPersister.NotFoundException {
        return adminRepo.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public AdminDTO edit(AdminDTO dados) throws ChangeSetPersister.NotFoundException {
        Admin admin = adminRepo.findById(dados.id())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        admin.setNome(dados.nome());
        admin.setSenha(dados.senha());

        return mapper.toDTO(adminRepo.save(admin));
    }

    public void delete(Long id) throws ChangeSetPersister.NotFoundException{
        adminRepo.delete(adminRepo.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    public AdminDTO login (AdminDTO dados){
        String nome = dados.nome();
        String senha = dados.senha();
        return mapper.toDTO(adminRepo.login(nome,senha));
    }
}
