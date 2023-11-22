package com.kaos.escola.dto.mapper;

import com.kaos.escola.dto.AlunosDTO;
import com.kaos.escola.models.Alunos;
import org.springframework.stereotype.Component;

@Component
public class AlunosMapper {

    public AlunosDTO toDTO(Alunos alunos){
        if(alunos == null){
            return null;
        }
        return new AlunosDTO(alunos.getId(), alunos.getNome(), alunos.getIdade(),
                alunos.getTurma(), alunos.getTurno());
    }

    public Alunos toEntity(AlunosDTO alunosDTO){

        if (alunosDTO == null){
            return null;
        }

        Alunos aluno = new Alunos();
        if(alunosDTO.id() != null){
            aluno.setId(alunosDTO.id());
        }
        aluno.setNome(alunosDTO.nome());
        aluno.setIdade(alunosDTO.idade());
        aluno.setTurma(alunosDTO.turma());
        aluno.setTurno(alunosDTO.turno());
        return aluno;
    }


}
