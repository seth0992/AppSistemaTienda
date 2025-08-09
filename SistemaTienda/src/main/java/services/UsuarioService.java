/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.List;
import java.util.Optional;
import models.Usuario;

/**
 *
 * @author seth
 */
public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(int id);
    void guardar(Usuario usuario);
    void eliminar(int id);
}