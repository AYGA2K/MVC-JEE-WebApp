
<%@ page import="java.util.List" %>
<%@ page import="ma.fstt.entity.Produit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Produits</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto p-4">
        <h1 class="text-3xl font-bold mt-4 mb-8 text-center">Liste des Produits</h1>
        
        <table class="border-collapse border border-gray-400 mx-auto">
            <% List<Produit> produits = (List<Produit>) request.getAttribute("produits"); %>
            <tr class="bg-gray-200">
                <th class="border border-gray-400 px-4 py-2">ID</th>
                <th class="border border-gray-400 px-4 py-2">Name</th>
                <th class="border border-gray-400 px-4 py-2">Description</th>
                <th class="border border-gray-400 px-4 py-2">Price</th>
                <th class="border border-gray-400 px-4 py-2">Actions</th>
            </tr>

            <% for (Produit produit : produits) { %>
                <tr>
                    <td class="border border-gray-400 px-4 py-2"><%= produit.getId() %></td>
                    <td class="border border-gray-400 px-4 py-2"><%= produit.getNom() %></td>
                    <td class="border border-gray-400 px-4 py-2"><%= produit.getDescription() %></td>
                    <td class="border border-gray-400 px-4 py-2"><%= produit.getPrix() %></td>
                    <td class="border border-gray-400 px-4 py-2">
                        <a href="deleteproduit?id=<%= produit.getId() %>" class="text-red-500 hover:text-red-700 mr-2">Delete</a>
                        <a href="updateproduit?id=<%= produit.getId() %>" class="text-blue-500 hover:text-blue-700">Update</a>
                    </td>
                </tr>
            <% } %>
        </table>
    </div>
</body>
</html>
