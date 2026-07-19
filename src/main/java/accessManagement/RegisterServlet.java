package accessManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://10.0.0.6:3306/genesisportal";
    private static final String DB_USER = "towerapp";
    private static final String DB_PASSWORD = "TowerApp2026!";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("gmail");
        String plainPassword = request.getParameter("password");

        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        String sql = "INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)";

        response.setContentType("text/html");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                request.setAttribute("username", username);
                request.getRequestDispatcher("HTML/GenesisLandingPage/registerResponse.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("HTML/GenesisLandingPage/registerResponse.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}