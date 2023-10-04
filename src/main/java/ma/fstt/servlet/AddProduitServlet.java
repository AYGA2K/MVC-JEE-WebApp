package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.entity.Produit;

@WebServlet(name = "addproduit", value = "/addproduit")
public class AddProduitServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    req.getRequestDispatcher("addproduit.jsp").forward(req, resp);

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String nom = request.getParameter("nom");
    String description = request.getParameter("description");
    double prix = Double.parseDouble(request.getParameter("prix"));

    Produit produit = new Produit();
    produit.setNom(nom);
    produit.setPrix(prix);
    produit.setDescription(description);

    ProduitDAO produitDAO;
    try {
      produitDAO = new ProduitDAO();
      produitDAO.addProduit(produit);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    response.sendRedirect("index.jsp");
  }
}
