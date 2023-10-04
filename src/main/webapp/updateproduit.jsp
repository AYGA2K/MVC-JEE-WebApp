<%@ page import="ma.fstt.entity.Produit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title> 
    <script src="https://cdn.tailwindcss.com"></script>

</head>
<body class="bg-gray-100">
    <div class="container mx-auto p-4">
        <h1 class="text-3xl font-bold mt-4 mb-8 text-center">Update Product</h1>
        <% Produit produit = (Produit) request.getAttribute("produit"); %>
        <form action="updateproduit?id=<%= produit.getId() %>" method="post" class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-lg">
            <input type="hidden" name="id" value="<%= produit.getId() %>">
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="nom">Name:</label>
                <input type="text" name="nom" value="<%= produit.getNom() %>" class="bg-gray-200 appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-blue-500" required>
            </div>
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="description">Description:</label>
                <input type="text" name="description" value="<%= produit.getDescription() %>" class="bg-gray-200 appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-blue-500" required>
            </div>
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="prix">Price:</label>
                <input type="text" name="prix" value="<%= produit.getPrix() %>" class="bg-gray-200 appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-blue-500" required>
            </div>
            <div class="text-center">
                <input type="submit" name="ok" value="Update" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full inline-block text-center">
            </div>
        </form>
    </div>
</body>
</html>
