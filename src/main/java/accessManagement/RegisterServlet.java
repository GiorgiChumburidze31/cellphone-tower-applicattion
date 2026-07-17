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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/genesisportal";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Giorgi_20152003";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	
        String username = request.getParameter("username");
        String email = request.getParameter("gmail");
        String password = request.getParameter("password");

        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	
           


            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);

            int rowsInserted = stmt.executeUpdate();

            response.setContentType("text/html");
            if (rowsInserted > 0) {
                response.getWriter().println("<h1>Account created for " + username + "</h1>");
            } else {
                response.getWriter().println("<h1>Something went wrong.</h1>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}