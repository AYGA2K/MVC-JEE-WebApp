<%@ page import="java.util.List" %>
<%@ page import="ma.fstt.entity.Commande" %>
<%@ page import="ma.fstt.entity.LignedeCommande" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Commandes</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto p-4">
        <h1 class="text-3xl font-bold mt-4 mb-8 text-center">Liste des Commandes</h1>
        
        <table class="border-collapse border border-gray-400 mx-auto">
            <% List<Commande> commandes = (List<Commande>) request.getAttribute("commandes"); %>
            <tr class="bg-gray-200">
                <th class="border border-gray-400 px-4 py-2">ID</th>
                <th class="border border-gray-400 px-4 py-2">Client</th>
                <th class="border border-gray-400 px-4 py-2">Date</th>
                <th class="border border-gray-400 px-4 py-2">Quantité</th> <!-- Added this column -->
                <th class="border border-gray-400 px-4 py-2">Actions</th>
            </tr>

            <% for (Commande commande : commandes) { %>
                <tr>
                    <td class="border border-gray-400 px-4 py-2"><%= commande.getId() %></td>
                    <td class="border border-gray-400 px-4 py-2"><%= commande.getClient().getNom() %> <%= commande.getClient().getPrenom() %></td>
                    <td class="border border-gray-400 px-4 py-2"><%= commande.getDate() %></td>
                    <td class="border border-gray-400 px-4 py-2">
                        <% List<LignedeCommande> lignesdeCommande = commande.getLignesdeCommande(); %>
                        <% if (lignesdeCommande != null && !lignesdeCommande.isEmpty()) { %>
                            <% for (LignedeCommande ligne : lignesdeCommande) { %>
                                <%= ligne.getQuantite() %>
                            <% } %>
                        <% } %>
                    </td>
                    <td class="border border-gray-400 px-4 py-2">
                        <a href="deletecommande?id=<%= commande.getId() %>" class="text-red-500 hover:text-red-700 mr-2">Delete</a>
                    </td>
                </tr>
            <% } %>
        </table>
    </div>
</body>
</html>

