package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.lang.StackWalker.Option;
import java.util.Optional;
import services.LoginService;
import services.LoginServiceImpl;

/**
 *
 * @author seth
 */
// @WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginService servicio = new LoginServiceImpl();
        Optional<String> userNameOptional = servicio.getUsername(request);
        
        if (userNameOptional.isPresent()) {
            response.setContentType("text/html; charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE HTML>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title> Hola " + userNameOptional.get() + "</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("        <h1> Hola: " + userNameOptional.get() + " has iniciado sesión con exito </h1>");
                out.println("       <p><a href='" + request.getContextPath() + "/index.html'> volver al index </a></p>");
                out.println("       <p><a href='" + request.getContextPath() + "/LogoutServlet'> Cerrar Sesión</a></p>");
                out.println("   </body>");
                out.println("</html>");
            }
            
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        LoginService auth = new LoginServiceImpl();
        
        if(auth.validateLogin(username, password)){
            HttpSession session = request.getSession();
            
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(15*60); // 15 min
            response.sendRedirect(request.getContextPath()+"/login.html");
        }else{
            response.sendRedirect(request.getContextPath()+"/unAuthorized.jsp");
        }        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
