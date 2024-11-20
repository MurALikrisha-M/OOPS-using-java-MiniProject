import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OfficerServlet")
public class OfficerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleId = request.getParameter("vehicleId");
        String violationType = request.getParameter("violationType");
        double fineAmount = Double.parseDouble(request.getParameter("fineAmount"));

        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO violations (vehicle_id, violation_type, fine_amount, is_paid) VALUES (?, ?, ?, false)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, vehicleId);
            statement.setString(2, violationType);
            statement.setDouble(3, fineAmount);
            statement.executeUpdate();
            response.getWriter().println("Violation added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Violation> violations = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM violations";
            ResultSet rs = connection.prepareStatement(query).executeQuery();
            while (rs.next()) {
                violations.add(new Violation(rs.getInt("id"), rs.getString("vehicle_id"), rs.getString("violation_type"),
                        rs.getDouble("fine_amount"), rs.getBoolean("is_paid")));
            }
            request.setAttribute("violations", violations);
            request.getRequestDispatcher("view_violations.html").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
