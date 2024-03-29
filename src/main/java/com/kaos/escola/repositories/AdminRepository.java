package com.kaos.escola.repositories;

import com.kaos.escola.models.Admin;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query (value = "select * from tb_admin", nativeQuery = true)
    public List<Admin> admins ();

    @Query (value = "select * from tb_admin where nome = :nome and senha = :senha", nativeQuery = true)
    public Admin login(String nome, String senha);

    @Query (value = "update tb_admin set nome = :nome, set senha = :senha where id = :id", nativeQuery = true)
    public Admin editar(Long id, String nome, String senha);

    @Query (value = "select nome, senha from tb_admin where id = :id", nativeQuery = true)
    public Admin buscar(Long id);

    @Query (value = "delete from tb_admin where id = :id", nativeQuery = true)
    public Admin excluir(Long id);

}
