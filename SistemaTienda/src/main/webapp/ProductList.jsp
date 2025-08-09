<%-- 
    Document   : listaProductos
    Created on : 1 dic 2024, 13:56:43
    Author     : seth
--%>

<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="models.Product"%>
<%@page import="services.ProductServiceImpl"%>
<%@page import="services.ProductService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listas Productos</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </head>
    <body>

        <div class="container">
            <h1>Lista de Productos</h1>

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">Producto</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Precio</th>
                    </tr>
                </thead>
                <tbody>

                    <core:forEach var="p" items="${productList}">
                    <tr>
                        <th scope="row"> ${p.getId()} </th>
                        <td>${p.getNombre()}</td>
                        <td>${p.getTipo()}</td>
                        <td>${p.getPrecio()}</td>
                    </tr>
                  </core:forEach>              
                </tbody>
            </table>


        </div>


    </body>
</html>
