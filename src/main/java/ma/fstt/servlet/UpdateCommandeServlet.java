package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.CommandeDAO;
import ma.fstt.entity.Commande;

@WebServlet(name = "updatecommande", value = "/updatecommande")
public class UpdateCommandeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String commandeIdStr = req.getParameter("id");
    if (commandeIdStr != null && !commandeIdStr.isEmpty()) {
      int commandeId = Integer.parseInt(commandeIdStr);

      CommandeDAO commandeDAO;
      try {
        commandeDAO = new CommandeDAO();
        Commande commande = commandeDAO.getCommandeById(commandeId);

        req.setAttribute("commande", commande);

        req.getRequestDispatcher("updatecommande.jsp").forward(req, resp);

      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int id = Integer.parseInt(request.getParameter("id"));
      String datestr = request.getParameter("date");
      SimpleDateFormat sdf = new SimpleDateFormat(datestr);
      java.util.Date utilDate = sdf.parse(datestr);
      java.sql.Date date = new java.sql.Date(utilDate.getTime());
      CommandeDAO commandeDAO = new CommandeDAO();
      Commande updatedcommande = commandeDAO.getCommandeById(id);
      updatedcommande.setDate(date);

      commandeDAO.updateCommande(updatedcommande);

      response.sendRedirect(request.getContextPath() + "/commandes");
    } catch (NumberFormatException | SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating commande.");
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
