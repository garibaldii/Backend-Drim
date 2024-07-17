package com.projeto.drim.business.service;



import com.projeto.drim.infraestructure.entity.Usuario.UsuarioEntity;
import com.projeto.drim.infraestructure.repository.UsuarioRepository;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<UsuarioEntity> findAll() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity findById(String id) {
        return usuarioRepository.findById(id).get();
    }

    public UsuarioEntity save(UsuarioEntity usuarioEntity) {
            try {
                return usuarioRepository.save(usuarioEntity);
            }
            catch (ValidationException e) {
                throw new ValidationException();
            }
            catch(DuplicateKeyException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
    }



    public void deleteOne(String id) {
        try {
            if (findById(id) != null) {
            usuarioRepository.deleteById(id);
            }
        }
        catch(NoSuchElementException  e){
            throw new NoSuchElementException(e.getMessage());
        }

    }




    public void deleteAll(){
        usuarioRepository.deleteAll();
    }





    public UsuarioEntity update(UsuarioEntity usuarioBody, String id){
        try{
            UsuarioEntity usuarioBancoDados = findById(id);
            usuarioBancoDados.setNome(usuarioBody.getNome());
            usuarioBancoDados.setEmail(usuarioBody.getEmail());
            usuarioBancoDados.setSenha(usuarioBody.getSenha());
            usuarioBancoDados.setNumeroContato(usuarioBody.getNumeroContato());

            return usuarioRepository.save(usuarioBancoDados);
        }
        catch(NoSuchElementException  e){
            throw new NoSuchElementException();
        }
        catch(Exception e){
            throw new ValidationException();
        }
    }
}
