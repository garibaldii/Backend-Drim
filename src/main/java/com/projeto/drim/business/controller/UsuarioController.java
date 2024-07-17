package com.projeto.drim.business.controller;
import com.projeto.drim.business.service.UsuarioService;
import com.projeto.drim.infraestructure.entity.Usuario.UsuarioEntity;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;





    @ApiOperation(value = "Lista todos os usuários 🗒️", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✅ Lista de usuários retornada com sucesso!"),
            @ApiResponse(code = 500, message = "❌ Erro ao retornar a lista de usuários")
    })
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }





    @ApiOperation(value = "Lista UM usuário específico ☝️🗒️", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✅ Usuário específico retornado com sucesso!"),
            @ApiResponse(code = 404, message = "😢 Objeto não encontrado no banco de dados "),
            @ApiResponse(code = 500, message = "❌ Erro ao retornar o usuário específico")
    })
    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioEntity> buscarUsuario(@PathVariable String id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }





    @ApiOperation(value = "Salva um usuário no banco de dados 🆕🍃", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "✅ Usuário cadastrado com sucesso!"),
            @ApiResponse(code = 400, message = "❌ Erro de Validação, dados incorretos"),
            @ApiResponse(code = 500, message = "❌ Erro ao cadastrar o usuário")
    })
    @PostMapping("/usuario")
    public ResponseEntity<UsuarioEntity> salvarUsuario(@Valid @RequestBody UsuarioEntity usuario) {

        UsuarioEntity usuarioSalvo = usuarioService.save(usuario);
        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioSalvo.getId())
                .toUri();

        return ResponseEntity.created(localizacao).body(usuarioSalvo);
    }





    @ApiOperation(value = "Atualiza um usuário no banco de dados 🔄🍃", httpMethod = "PUT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✅ Usuário atualizado com sucesso!"),
            @ApiResponse(code = 404, message = "😢 Objeto não encontrado no banco de dados "),
            @ApiResponse(code = 400, message = "❌ Erro de Validação, dados incorretos"),
            @ApiResponse(code = 500, message = "❌ Erro ao atualizar o usuário")
    })
    @PutMapping("usuario/{id}")
    public ResponseEntity<UsuarioEntity> atualizarUsuario(@Valid @RequestBody UsuarioEntity usuarioBody, @PathVariable String id) {
        return ResponseEntity.ok(usuarioService.update(usuarioBody, id));
    }






    @ApiOperation(value = "Exclui um usuário no banco de dados 🗑️🍃", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✅ Usuário excluído com sucesso!"),
            @ApiResponse(code = 404, message = "😢 Objeto não encontrado no banco de dados "),
            @ApiResponse(code = 500, message = "❌ Erro ao excluir o usuário")
    })
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<ResponseStatus> deletarUsuario(@PathVariable String id) {
        usuarioService.deleteOne(id);

        return ResponseEntity.accepted().build();
    }







    @ApiOperation(value = "Exclui todos os usuários do banco de dados 🗑️🍃", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✅ Usuários excluídos com sucesso!"),
            @ApiResponse(code = 500, message = "❌ Erro ao excluir os usuários")
    })

    @DeleteMapping("/usuarios")
    public ResponseEntity<ResponseStatus> deletarTodosUsuarios() {
        usuarioService.deleteAll();

        return ResponseEntity.accepted().build();
    }
}