<%--
  Created by IntelliJ IDEA.
  User: Juangra
  Date: 14/10/2016
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Frutas Harnina</title>
    <link rel="stylesheet" href="../estilos/login.css">
  </head>

  <body>
    <% if (session.getAttribute("idSesion") == null){
      session.setAttribute("idSesion", session.getId());
    }
      if (session.getAttribute("idSesion") == session.getId()){
        System.out.println("Todo va bien");
        //System.out.println(session.getAttribute("idSesion"));
      }
    %>

    <div class="container">
      <section id="content">
        <form action="Principal/principal.jsp">
          <h1>Iniciar Sesión</h1>
          <div>
            <input name="usuario" type="text" placeholder="Usuario" required="" id="username" />
          </div>
          <div>
            <input name="paswd" type="password" placeholder="Contraseña" required="" id="password" />
          </div>
          <div>
            <input type="submit" value="Iniciar Sesión" />
            <a href="almacen/registrarAlma.jsp">Registrar</a>
          </div>
        </form><!-- form -->
      </section><!-- content -->
    </div>

  </body>

</html>
