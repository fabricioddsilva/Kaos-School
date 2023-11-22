package com.kaos.escola.repositories;

import com.kaos.escola.models.Alunos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface AlunosRepository extends JpaRepository<Alunos, Long> {
    @Query(value="select * from tb_alunos", nativeQuery = true)
    public List<Alunos>alunos();
}
