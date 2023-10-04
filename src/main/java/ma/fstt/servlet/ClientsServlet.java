package ma.fstt.servlet;

import ma.fstt.dao.ClientDAO;
import ma.fstt.entity.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/clients")
public class ClientsServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ClientDAO clientDAO;
    try {
      clientDAO = new ClientDAO();
      List<Client> clients = clientDAO.getAllClients();

      request.setAttribute("clients", clients);

      request.getRequestDispatcher("clients.jsp").forward(request, response);

    } catch (ClassNotFoundException | SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
