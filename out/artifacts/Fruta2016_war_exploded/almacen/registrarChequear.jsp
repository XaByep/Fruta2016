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
    ControlAlmacen controlAlmacen=new ControlAlmacen();
    String cif=request.getParameter("cif");
    String nombreAl =request.getParameter("nombre");
    String psw= DigestUtils.md5Hex(request.getParameter("paswd"));
    String nombreUsu=request.getParameter("usuario");
    String direccion=request.getParameter("direccion");
    String cp=request.getParameter("cp").substring(0,5);
    String telefono=request.getParameter("telefono");
    Almacen elemento=new Almacen(cif, nombreAl,direccion,cp,telefono,nombreUsu,psw);
    if(!controlAlmacen.verificar(nombreUsu,cif)){
            controlAlmacen.insertar(elemento);
            %><div><h1>Todo Correcto</h1></div>
        <%
    }else{
        %><div><h1>Existe</h1></div><%
    }
%>


</body>
</html>
