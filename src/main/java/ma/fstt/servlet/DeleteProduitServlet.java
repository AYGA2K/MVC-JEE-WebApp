package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.ProduitDAO;

@WebServlet("/deleteproduit")
public class DeleteProduitServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int productId = Integer.parseInt(request.getParameter("id"));

    ProduitDAO produitDAO;
    try {
      produitDAO = new ProduitDAO();
      produitDAO.deleteProduit(productId);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    response.sendRedirect("index.jsp");
  }
}
