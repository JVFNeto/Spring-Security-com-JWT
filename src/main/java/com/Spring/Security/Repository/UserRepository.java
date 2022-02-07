package com.Spring.Security.Repository;

import com.Spring.Security.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByLogin(String login);// diz ao repositorio efetuar uma consulta no banco de dados
    //que vai fazer a busca pelo campo login

    Usuario  findById(long id);





}
