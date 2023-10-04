<%@ page import="ma.fstt.entity.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Client</title>
      <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto p-4">
        <h1 class="text-3xl font-bold mt-4 mb-8 text-center">Update Client</h1>
        
        <% Client client = (Client) request.getAttribute("client"); %>
        <form action="updateclient?id=<%= client.getId() %>" method="post" class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-lg">
            <input type="hidden" name="id" value="<%= client.getId() %>">
            
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="nom">First Name:</label>
                <input type="text" name="nom" id="nom" value="<%= client.getNom() %>" class="bg-gray-200 appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-blue-500" required>
            </div>
            
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="prenom">Last Name:</label>
                <input type="text" name="prenom" id="prenom" value="<%= client.getPrenom() %>" class="bg-gray-200 appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-blue-500" required>
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="email">Email:</label>
                <input type="text" name="email" id="email" value="<%= client.getEmail() %>" class="bg-gray-200 appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-blue-500" required>
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="telephone">Phone Number:</label>
                <input type="text" name="telephone" id="telephone" value="<%= client.getTelephone() %>" class="bg-gray-200 appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-blue-500" required>
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="adresse">Address:</label>
                <input type="text" name="adresse" id="adresse" value="<%= client.getAdresse() %>" class="bg-gray-200 appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-blue-500" required>
            </div>

            <div class="text-center">
                <input type="submit" name="ok" value="Update" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full inline-block text-center">
            </div>
        </form>
    </div>
</body>
</html>

