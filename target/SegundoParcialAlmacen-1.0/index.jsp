<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.modelo.GestorAlmacen"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Producto"%>
<%
    if(session.getAttribute("gestor") == null){
        GestorAlmacen aux = new GestorAlmacen();
        aux.insertarProducto(new Producto(1, "Coca Cola", 100, 10, "Bebidas"));
        aux.insertarProducto(new Producto(2, "Pepsi", 50, 11, "Bebidas"));
        aux.insertarProducto(new Producto(3, "Frack", 100, 2.50, "Galletas"));
        aux.insertarProducto(new Producto(4, "Serranitas", 80, 1.50, "Galletas"));
        
        session.setAttribute("gestor", aux);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ALMACEN DE PRODUCTOS</title>
    </head>
    <body>
    <section style="padding-top: 25px;margin-left: 15%;margin-right: 15%;">
            <fieldset>
                <legend> </legend>
                <table border="0" cellspacing="0" cellpadding="0" align="center" >
                    <tbody>
                        <tr align="left">
                            <td>SEGUNDO PARCIAL TEM-742</td>                        
                        </tr>
                        <tr align="left">
                            <td>Nombre: Josue ALiaga Sosa</td>                         
                        </tr>
                        <tr align="left">
                            <td>Carnet: 9917884</td>   
                        </tr>        
                    </tbody>
                </table>                                         
            </fieldset>
        </section>
        
        <h1 align="center">PRODUCTOS</h1>
        <a href="MainController?op=nuevo">Nuevo Producto</a>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Descripción</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${sessionScope.gestor.getLista()}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.descripcion}</td>
                    <td>${item.cantidad}</td>
                    <td>${item.precio}</td>
                    <td>${item.categoria}</td>
                    <td><a href="MainController?op=modificar&id=${item.id}">Editar</a></td>
                    <td><a href="MainController?op=eliminar&id=${item.id}">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
