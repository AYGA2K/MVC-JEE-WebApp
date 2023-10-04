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

@WebServlet(name = "addclient", value = "/addclient")
public class AddClientServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    req.getRequestDispatcher("addclient.jsp").forward(req, resp);

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String nom = req.getParameter("nom");
    String prenom = req.getParameter("prenom");
    String adresse = req.getParameter("adresse");
    String email = req.getParameter("email");
    String telephone = req.getParameter("telephone");

    Client client = new Client();
    client.setNom(nom);
    client.setPrenom(prenom);
    client.setAdresse(adresse);
    client.setEmail(email);
    client.setTelephone(telephone);

    ClientDAO clientDAO;
    try {
      clientDAO = new ClientDAO();
      clientDAO.addClient(client);

      resp.sendRedirect("index.jsp");

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}
