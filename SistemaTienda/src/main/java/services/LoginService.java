
package services;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 *
 * @author seth
 */
public interface LoginService {
    
    Optional<String> getUsername(HttpServletRequest req);
    
    boolean validateLogin(String username, String password);
}
