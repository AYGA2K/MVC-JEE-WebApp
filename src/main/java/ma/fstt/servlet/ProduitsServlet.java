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
import java.util.List;

@WebServlet("/produits")
public class ProduitsServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ProduitDAO produitDAO;
    try {
      produitDAO = new ProduitDAO();
      List<Produit> produits = produitDAO.getAllProduits();

      request.setAttribute("produits", produits);

      request.getRequestDispatcher("produits.jsp").forward(request, response);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}
