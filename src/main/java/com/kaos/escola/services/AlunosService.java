package com.kaos.escola.services;

import com.kaos.escola.dto.AlunosDTO;
import com.kaos.escola.dto.mapper.AlunosMapper;
import com.kaos.escola.models.Alunos;
import com.kaos.escola.repositories.AlunosRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunosService {

    private final AlunosRepository alunosRepo;
    private final AlunosMapper mapper;
    public AlunosService(AlunosRepository alunosRepo, AlunosMapper mapper){
        this.alunosRepo = alunosRepo;
        this.mapper = mapper;
    }

    public List<AlunosDTO> list(){
        return alunosRepo.alunos()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public AlunosDTO save(AlunosDTO dados){
        return mapper.toDTO(alunosRepo.save(mapper.toEntity(dados)));
    }

    public AlunosDTO findById(Long id) throws ChangeSetPersister.NotFoundException {
       return alunosRepo.findById(id).map(mapper::toDTO)
               .orElseThrow(ChangeSetPersister.NotFoundException::new);

    }

    public AlunosDTO edit(AlunosDTO dados) throws ChangeSetPersister.NotFoundException{
        Alunos aluno = alunosRepo.findById(dados.id())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

       aluno.setNome(dados.nome());
       aluno.setIdade(dados.idade());
       aluno.setTurma(dados.turma());
       aluno.setTurno(dados.turno());

        return mapper.toDTO(alunosRepo.save(aluno));


    }

    public void delete(Long id) throws ChangeSetPersister.NotFoundException{
        alunosRepo.delete(alunosRepo.findById(id).
                orElseThrow(ChangeSetPersister.NotFoundException::new));
    }
}
