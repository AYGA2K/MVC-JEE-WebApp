package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.ClientDAO;
import ma.fstt.entity.Client;

@WebServlet(name = "updateclient", value = "/updateclient")
public class UpdateClientServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String clientIdStr = req.getParameter("id");
    if (clientIdStr != null && !clientIdStr.isEmpty()) {
      int clientId = Integer.parseInt(clientIdStr);

      ClientDAO clientDAO;
      try {
        clientDAO = new ClientDAO();
        Client client = clientDAO.getClientById(clientId);

        req.setAttribute("client", client);

        req.getRequestDispatcher("updateclient.jsp").forward(req, resp);

      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int id = Integer.parseInt(request.getParameter("id"));
      String nom = request.getParameter("nom");
      String prenom = request.getParameter("prenom");
      String adresse = request.getParameter("adresse");
      String email = request.getParameter("email");
      String telephone = request.getParameter("telephone");

      Client updatedClient = new Client(id, nom, prenom, adresse, email, telephone);

      ClientDAO clientDAO = new ClientDAO();
      clientDAO.updateClient(updatedClient);

      response.sendRedirect(request.getContextPath() + "/clients");
    } catch (NumberFormatException | SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating client.");
    }
  }

}
