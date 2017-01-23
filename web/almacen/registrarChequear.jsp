<%@ page import="Almacen.ControlAlmacen" %>
<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>
<%@ page import="Almacen.Almacen" %>
<%--<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: JAVI
  Date: 24/10/2016
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <title>Chequear datos almacén</title>
</head>
<body>
<%
        ControlAlmacen controlAlmacen = new ControlAlmacen();
        String cif = request.getParameter("cif").trim();
        String nombreAl = request.getParameter("nombre").trim();
        String psw = request.getParameter("paswd").trim();
        String nombreUsu = request.getParameter("usuario").trim();
        String direccion = request.getParameter("direccion").trim();
        String cp = request.getParameter("cp").substring(0, 5);
        String telefono = request.getParameter("telefono").trim();
        Almacen elemento = new Almacen(cif, nombreAl, direccion, cp, telefono, nombreUsu, psw);
        if (controlAlmacen.insertar(elemento)) {

%>
<div><h1>Todo Correcto</h1></div>
<meta http-equiv="refresh" content="1 ; url=../index.jsp">
<%
} else {
%>
<div><h1>ERROR</h1><h1><% elemento.getErrores(); %></h1></div>
<%--<meta http-equiv="refresh" content="0.5 ; url=registrarAlma.jsp">--%>
<%
        }
%>


</body>
</html>
