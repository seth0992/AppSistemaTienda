package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import services.LoginService;
import services.LoginServiceImpl;

/**
 *
 * @author seth
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        LoginService auth = new LoginServiceImpl();
        Optional<String> username = auth.getUsername(request);
        
        if (username.isPresent()) {
            HttpSession session = request.getSession();
            session.invalidate();
        }
        
        response.sendRedirect(request.getContextPath() + "/login.html");
    }
    
}
