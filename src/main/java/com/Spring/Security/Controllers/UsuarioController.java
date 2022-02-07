package com.Spring.Security.Controllers;


import com.Spring.Security.Models.Usuario;
import com.Spring.Security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private final UserRepository ur;
    private final PasswordEncoder encoder;

    public UsuarioController(UserRepository ur, PasswordEncoder encoder) {
        this.ur = ur;
        this.encoder = encoder;
    }

    //ResponseEntity é o tipo
    @GetMapping("/list")
    public ResponseEntity<List<Usuario>> listarTodos(){//Metodo (Achei interressante essa tipagem)

        return ResponseEntity.ok(ur.findAll());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity <Usuario> listUnico(@PathVariable(value="id")long id){

        return ResponseEntity.ok(ur.findById(id));
    }

    @PostMapping("/salvar")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){//mais uma vez, essa tipagem
        //a linha abaixo encripta a senha do usuario
        usuario.setSenha(encoder.encode(usuario.getsenha()));
        return ResponseEntity.ok(ur.save(usuario));
    }

    @GetMapping("/valid")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
                                                @RequestParam String senha) {

        Optional<Usuario> optUsuario = ur.findByLogin(login);
        if (optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        Usuario usuario = optUsuario.get();
        boolean valid = encoder.matches(senha, usuario.getsenha());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);

    }
    @DeleteMapping("/del")
    //Como ele vai deletar, ele não retorna nada, então seu tipo é void
    public void deletaUser(@RequestBody Usuario usuario ) { // tipo objeto

        ur.delete(usuario); // como é do tipo void, ele não tem return
    }
    @PutMapping("produto")
    public ResponseEntity<Usuario> atualizaUser(@RequestBody Usuario usuario){
        //a linha abaixo encripta a senha do usuario
        usuario.setSenha(encoder.encode(usuario.getsenha()));
        return ResponseEntity.ok(ur.save(usuario));
    }


}
