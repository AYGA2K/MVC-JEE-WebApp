package ma.fstt.servlet;

import ma.fstt.dao.CommandeDAO;
import ma.fstt.entity.Commande;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/commandes")
public class CommandesServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    CommandeDAO commandeDAO;
    try {
      commandeDAO = new CommandeDAO();
      List<Commande> commandes = commandeDAO.getAllCommandes();

      request.setAttribute("commandes", commandes);

      request.getRequestDispatcher("commandes.jsp").forward(request, response);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}
