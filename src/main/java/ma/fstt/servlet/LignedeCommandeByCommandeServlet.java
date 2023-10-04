package ma.fstt.servlet;

import ma.fstt.dao.LignedeCommandeDAO;
import ma.fstt.entity.LignedeCommande;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/lignedecommandesbycommande")
public class LignedeCommandeByCommandeServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Get the Commande ID from the request parameter
    int commandeId = Integer.parseInt(request.getParameter("commandeId"));

    LignedeCommandeDAO lignedeCommandeDAO;
    try {
      lignedeCommandeDAO = new LignedeCommandeDAO();

      // Retrieve LignedeCommande objects for the specified Commande
      List<LignedeCommande> lignedeCommandes = lignedeCommandeDAO.getLignesForCommande(commandeId);

      request.setAttribute("lignedeCommandes", lignedeCommandes);

      request.getRequestDispatcher("lignedecommandesbycommande.jsp").forward(request, response);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}
