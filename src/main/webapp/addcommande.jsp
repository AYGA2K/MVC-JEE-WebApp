<%@ page import="java.util.List" %>
<%@ page import="ma.fstt.entity.Client" %>
<%@ page import="ma.fstt.entity.Produit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a Commande</title>
  <script src="https://cdn.tailwindcss.com"></script>

</head>
<body class="bg-gray-100">
    <div class="container mx-auto p-4">
        <h1 class="text-3xl font-bold mt-4 mb-8 text-center">Add a Commande</h1>
        <form action="addcommande" method="post" class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-lg">
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="client_id">Client:</label>
                <select name="client_id" id="client_id" class="bg-gray-200 appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-blue-500" required>
                    <% List<Client> clients = (List<Client>) request.getAttribute("clients"); %>
                    <option value="" disabled selected>Select a client</option>
                    <% for (Client client : clients) { %>
                        <option value="<%= client.getId() %>"><%= client.getNom() %> <%= client.getPrenom() %></option>
                    <% } %>
                </select>
            </div>
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="produit_id">Produit:</label>
                <select name="produit_id" id="produit_id" class="bg-gray-200 appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-blue-500" required>
                    <% List<Produit> produits = (List<Produit>) request.getAttribute("produits"); %>
                    <option value="" disabled selected>Select a produit</option>
                    <% for (Produit produit : produits) { %>
                        <option value="<%= produit.getId() %>"><%= produit.getNom() %> - <%= produit.getDescription() %></option>
                    <% } %>
                </select>
            </div>
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="quantite">Quantity:</label>
                <input type="number" name="quantite" id="quantite" min="1" class="bg-gray-200 appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-blue-500" required>
            </div>
            <div class="text-center">
                <input type="submit" name="ok" value="Add Commande" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full inline-block text-center">
            </div>
        </form>
    </div>
</body>
</html>

