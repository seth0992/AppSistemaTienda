/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import models.Usuario;
import services.UsuarioService;
import services.UsuarioServiceImpl;

/**
 *
 * @author seth
 */
@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "form":
                    showForm(req, resp);
                    break;
                case "delete":
                    deleteUser(req, resp);
                    break;
                default:
                    listUsers(req, resp);
                    break;
            }
        } else {
            listUsers(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("create")) {
            saveUser(req, resp);
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Usuario> usuarios = usuarioService.listar();
        req.setAttribute("usuarios", usuarios);
        getServletContext().getRequestDispatcher("/usuarios.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Optional<Usuario> usuario = usuarioService.porId(id);
            if (usuario.isPresent()) {
                req.setAttribute("usuario", usuario.get());
            }
        }
        getServletContext().getRequestDispatcher("/formUsuario.jsp").forward(req, resp);
    }

    private void saveUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        String nombre = req.getParameter("nombre");
        String username = req.getParameter("username");
        String pass = req.getParameter("password");

        Usuario usuario = new Usuario();
        if (idParam != null && !idParam.isEmpty()) {
            usuario.setIdUsuario(Integer.parseInt(idParam));
        }
        usuario.setNombre(nombre);
        usuario.setUsername(username);
        usuario.setPassword(pass); // Recuerda encriptar la contraseña en un entorno de producción

        usuarioService.guardar(usuario);
        resp.sendRedirect(req.getContextPath() + "/usuarios");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        usuarioService.eliminar(id);
        resp.sendRedirect(req.getContextPath() + "/usuarios");
    }
}
