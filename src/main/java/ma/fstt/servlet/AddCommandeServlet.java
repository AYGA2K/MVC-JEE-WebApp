package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.ClientDAO;
import ma.fstt.dao.CommandeDAO;
import ma.fstt.dao.LignedeCommandeDAO;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.entity.Client;
import ma.fstt.entity.Commande;
import ma.fstt.entity.LignedeCommande;
import ma.fstt.entity.Produit;

@WebServlet(name = "addcommande", value = "/addcommande")
public class AddCommandeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ProduitDAO produitDAO;
    try {
      ClientDAO clientDAO = new ClientDAO();
      List<Client> clients = clientDAO.getAllClients();
      produitDAO = new ProduitDAO();
      List<Produit> produits = produitDAO.getAllProduits();

      req.setAttribute("produits", produits);
      req.setAttribute("clients", clients);

      req.getRequestDispatcher("addcommande.jsp").forward(req, resp);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String clientIdStr = req.getParameter("client_id");
    String produitIdStr = req.getParameter("produit_id");
    String quantiteStr = req.getParameter("quantite");

    int clientId = Integer.parseInt(clientIdStr);
    int produitId = Integer.parseInt(produitIdStr);
    int quantite = Integer.parseInt(quantiteStr);
    ClientDAO clientDAO;
    Client client;
    Commande commande;
    ProduitDAO produitDAO;

    try {

      clientDAO = new ClientDAO();
      client = clientDAO.getClientById(clientId);
      commande = new Commande();
      commande.setClient(client);
      java.util.Date utilDate = new java.util.Date();
      Date date = new Date(utilDate.getTime());
      commande.setDate(date);
      produitDAO = new ProduitDAO();
      Produit produit = produitDAO.getProduitById(produitId);

      CommandeDAO commandeDAO = new CommandeDAO();
      commande = commandeDAO.addCommande(commande);

      LignedeCommande ligne = new LignedeCommande();
      ligne.setQuantite(quantite);
      ligne.setProduit(produit);
      ligne.setCommande(commande);
      System.out.println(ligne);
      LignedeCommandeDAO lignedeCommandeDAO;

      lignedeCommandeDAO = new LignedeCommandeDAO();
      lignedeCommandeDAO.addLigne(ligne);
      resp.sendRedirect("index.jsp");
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }
}
