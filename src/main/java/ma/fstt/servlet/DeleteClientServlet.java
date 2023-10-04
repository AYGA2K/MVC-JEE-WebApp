package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.ClientDAO;

@WebServlet(name = "deleteclient", value = "/deleteclient")
public class DeleteClientServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int clientId = Integer.parseInt(request.getParameter("id"));

    ClientDAO clientDAO;
    try {
      clientDAO = new ClientDAO();
      clientDAO.deleteClient(clientId);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    response.sendRedirect(request.getContextPath() + "/clients");
  }
}
