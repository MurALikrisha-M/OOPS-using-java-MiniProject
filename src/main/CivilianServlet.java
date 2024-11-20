import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CivilianServlet")
public class CivilianServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int violationId = Integer.parseInt(request.getParameter("violationId"));
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM violations WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, violationId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String paymentQuery = "UPDATE violations SET is_paid = true WHERE id = ?";
                PreparedStatement paymentStmt = connection.prepareStatement(paymentQuery);
                paymentStmt.setInt(1, violationId);
                paymentStmt.executeUpdate();
                response.getWriter().println("Payment successful for violation ID " + violationId);
            } else {
                response.getWriter().println("Violation not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
