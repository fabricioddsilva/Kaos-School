package com.kaos.escola.repositories;

import com.kaos.escola.models.Alunos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface AlunosRepository extends CrudRepository<Alunos, Long> {
    @Query(value="select * from tb_alunos", nativeQuery = true)
    public List<Alunos>alunos();
}
