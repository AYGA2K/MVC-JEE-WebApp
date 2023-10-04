
package ma.fstt.servlet;

import ma.fstt.dao.ProduitDAO;
import ma.fstt.entity.Produit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "updateproduit", value = "/updateproduit")
public class UpdateProduitServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String produitIdStr = req.getParameter("id");
    if (produitIdStr != null && !produitIdStr.isEmpty()) {
      int produitId = Integer.parseInt(produitIdStr);

      ProduitDAO produitDAO;
      try {
        produitDAO = new ProduitDAO();
        Produit produit = produitDAO.getProduitById(produitId);

        req.setAttribute("produit", produit);

        req.getRequestDispatcher("updateproduit.jsp").forward(req, resp);

      } catch (ClassNotFoundException | SQLException e) {
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
      String description = request.getParameter("description");
      double prix = Double.parseDouble(request.getParameter("prix"));

      Produit updatedProduit = new Produit(id, nom, description, prix);

      ProduitDAO produitDAO = new ProduitDAO();
      produitDAO.updateProduit(updatedProduit);

      response.sendRedirect(request.getContextPath() + "/produits");
    } catch (NumberFormatException | SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
