
<%@ page import="java.util.List" %>
<%@ page import="ma.fstt.entity.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Clients  List </title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto p-4">
        <h1 class="text-3xl font-bold mt-4 mb-8 text-center">Liste des Clients</h1>
        
        <table class="border-collapse border border-gray-400 mx-auto">
            <% List<Client> clients = (List<Client>) request.getAttribute("clients"); %>
            <tr class="bg-gray-200">
                <th class="border border-gray-400 px-4 py-2">ID</th>
                <th class="border border-gray-400 px-4 py-2">First Name</th>
                <th class="border border-gray-400 px-4 py-2">Last Name</th>
                <th class="border border-gray-400 px-4 py-2">Address</th>
                <th class="border border-gray-400 px-4 py-2">Email</th>
                <th class="border border-gray-400 px-4 py-2">Phone</th>
                <th class="border border-gray-400 px-4 py-2">Actions</th>
            </tr>

            <% for (Client client : clients) { %>
                <tr>
                    <td class="border border-gray-400 px-4 py-2"><%= client.getId() %></td>
                    <td class="border border-gray-400 px-4 py-2"><%= client.getNom() %></td>
                    <td class="border border-gray-400 px-4 py-2"><%= client.getPrenom() %></td>
                    <td class="border border-gray-400 px-4 py-2"><%= client.getAdresse() %></td>
                    <td class="border border-gray-400 px-4 py-2"><%= client.getEmail() %></td>
                    <td class="border border-gray-400 px-4 py-2"><%= client.getTelephone() %></td>
                    <td class="border border-gray-400 px-4 py-2">
                        <a href="deleteclient?id=<%= client.getId() %>" class="text-red-500 hover:text-red-700 mr-2">Delete</a>
                        <a href="updateclient?id=<%= client.getId() %>" class="text-blue-500 hover:text-blue-700">Update</a>
                    </td>
                </tr>
            <% } %>
        </table>
    </div>
</body>
</html>
