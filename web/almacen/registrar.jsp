<%@ page import="Poblacion.MunicipioProceso" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Poblacion.Municipio" %>
<%@ page import="javax.swing.text.html.HTML" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Juangra
  Date: 20/10/2016
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registro</title>
        <link rel="stylesheet" href="../css/style.css">
    </head>

    <body>

        <div id="form-main">
            <div id="form-div">
                <form class="form" id="form1">

                    <p class="name">
                        <input name="usuario" type="text" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="Usuario" id="name" />
                    </p>
                    <p class="paswd">
                        <input name="paswd" type="password" class="validate[required,custom[paswd]] feedback-input" id="paswd" placeholder="Contraseña" />
                    </p>

                    <p class="nombre">
                        <input name="nombre" type="text" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="Nombre" id="nombre" />
                    </p>

                    <p class="cif">
                        <input name="cif" type="text" class="validate[required,custom[cif]] feedback-input" id="cif" placeholder="N.I.F/C.I.F" />
                    </p>

                    <p class="direccion">
                        <input name="direccion" type="text" class="validate[required,custom[direccion]] feedback-input" id="direccion" placeholder="Dirección" />
                    </p>

                    <p class="telefono">
                        <input name="telefono" type="text" class="validate[required,custom[telefono]] feedback-input" id="telefono" placeholder="Teléfono" />
                    </p>

                    <p class="cp">
                        <%
                            ArrayList<Municipio> listaMunicipios = new MunicipioProceso().listaMunicipioArrayList();

                        %>
                        <select name="cp" class="validate[required,length[0,100]] feedback-input cp" id="cp" >
                            <% for (Municipio municipio: listaMunicipios) {%>
                            <option value="<%=municipio%>"><%=municipio%></option>
                            <%}%>
                        </select>
                    </p>

                    <div class="submit">
                        <input type="submit" value="Registrar" id="button-blue"/>
                        <div class="ease"></div>
                    </div>
                </form>
            </div>
        </div>
        <script src="registrar.jsp"></script>
    </body>
</html>
