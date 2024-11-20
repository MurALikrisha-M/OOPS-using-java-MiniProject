import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (("officer@123".equals(username) || "person@123".equals(username)) && "Changeme@123".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            if ("officer@123".equals(username)) {
                response.sendRedirect("officer_dashboard.html");
            } else {
                response.sendRedirect("civilian_dashboard.html");
            }
        } else {
            response.getWriter().println("Invalid username or password.");
        }
    }
}
