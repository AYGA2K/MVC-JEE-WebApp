package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.CommandeDAO;
import ma.fstt.dao.LignedeCommandeDAO;
import ma.fstt.entity.Commande;
import ma.fstt.entity.LignedeCommande;

@WebServlet("/deletecommande")
public class DeleteCommandeServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int commandeId = Integer.parseInt(request.getParameter("id"));

    try {
      CommandeDAO commandeDAO = new CommandeDAO();
      LignedeCommandeDAO lignedeCommandeDAO = new LignedeCommandeDAO();
      Commande commande = commandeDAO.getCommandeById(commandeId);
      List<LignedeCommande> list = commande.getLignesdeCommande();
      for (LignedeCommande lignedeCommande : list) {
        lignedeCommandeDAO.deleteLigne(lignedeCommande.getId());
      }
      commandeDAO.deleteCommande(commandeId);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    response.sendRedirect("index.jsp");
  }
}
