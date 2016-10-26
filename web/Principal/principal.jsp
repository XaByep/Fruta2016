<%@ page import="Usuario.ControlUsuario" %>
<%@ page import="Usuario.Usuario" %>
<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>
<%@ page import="javax.swing.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Yo
  Date: 25/10/2016
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Principal Almacenes</title>
    </head>
    <body>
    <%
        ControlUsuario controlUsuario = new ControlUsuario();
        String usuario = request.getParameter("usuario").trim();
        String contraseña = request.getParameter("paswd").trim();
        Usuario usuario1 = new Usuario(usuario, contraseña);
        if (controlUsuario.comprobarUsuario(usuario1)){
            %><div>Logueado correctamente</div>
    <%
        }else {
//            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos","Error de autenticacion",JOptionPane.ERROR_MESSAGE);
           %><div align="center"><h1>Usuario o contraseña incorrectos</h1></div>
    <META HTTP-EQUIV="REFRESH" CONTENT="1;URL = ../index.jsp "><%
        }
    %>
    </body>
</html>
