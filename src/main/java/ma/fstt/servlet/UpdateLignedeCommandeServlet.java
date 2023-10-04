package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.LignedeCommandeDAO;
import ma.fstt.entity.LignedeCommande;

@WebServlet(name = "updatelignedeCommande", value = "/updatelignedeCommande")
public class UpdateLignedeCommandeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String lignedeCommandeIdStr = req.getParameter("id");
    if (lignedeCommandeIdStr != null && !lignedeCommandeIdStr.isEmpty()) {
      int lignedeCommandeId = Integer.parseInt(lignedeCommandeIdStr);

      LignedeCommandeDAO lignedeCommandeDAO;
      try {
        lignedeCommandeDAO = new LignedeCommandeDAO();
        LignedeCommande lignedeCommande = lignedeCommandeDAO.getLigneById(lignedeCommandeId);

        req.setAttribute("lignedeCommande", lignedeCommande);

        req.getRequestDispatcher("updatelignedeCommande.jsp").forward(req, resp);

      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (SQLException e) {
        e.printStackTrace();
      }

    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int id = Integer.parseInt(request.getParameter("id"));
      int quantite = Integer.parseInt(request.getParameter("quantite"));

      LignedeCommandeDAO lignedeCommandeDAO = new LignedeCommandeDAO();
      LignedeCommande lignedeCommande = lignedeCommandeDAO.getLigneById(id);
      lignedeCommande.setQuantite(quantite);
      lignedeCommandeDAO.updateLigne(lignedeCommande);

      response.sendRedirect(request.getContextPath() + "/lignedeCommandes");
    } catch (NumberFormatException | SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating lignedeCommande.");
    }
  }

}
