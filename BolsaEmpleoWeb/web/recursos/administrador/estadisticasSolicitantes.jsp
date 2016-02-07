
<%@page import="Dominio.Solicitante"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="Utilitarios.EnviarCorreos"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Estadísticas Solicitantes | Bolsa de Empleo</title>  
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
                <jsp:include page="../recursosReusables/menuAdministrador.jsp"/>
            </div>
        </header>
        <section>
            <div id="zone-content" class="clearfix container-12">  
                <jsp:include page="../recursosReusables/section.jsp"/>
                <aside class="grid-3 region" id="region-sidebar-second">
                    <div class="grid-9 region-content" id="region-content">
                        <center>
                            <s:form action="verEstadisticasSolicitantesProcess" method="post" cssClass="form">
                                <s:textfield name="cedula" label="Cédula"/>
                                <s:textfield name="nombre" label="Nombre"/>
                                <s:textfield name="apellidos" label="Apellidos"/>
                                <s:submit method="buscar" value="Buscar"/>
                            </s:form>
                            <table id="mytable" class="table table-bordred table-striped">

<<<<<<< HEAD
                            <s:if test="%{solicitantes.isEmpty()}">
                                <h2>No hay resultados que mostrar</h2>
                            </s:if>
                            <s:else>
                                <thead>
                                <th>Cédula</th>
                                <th>Nombre</th>
                                <th>Apellidos</th>
                                <th>Ultima Actualización de datos</th>
                                <th></th>
                                </thead>
                                <tbody>
                                    <s:iterator value="solicitantes" var="solicitanteActual">
                                        <tr>
                                            <td align="center"><s:property value="#solicitanteActual.cedula"/></td>
                                            <td align="center"><s:property value="#solicitanteActual.nombre"/></td>
                                            <td align="center"><s:property value="#solicitanteActual.apellidos"/></td>
                                            <td align="center"><s:property value="#solicitanteActual.ultimaActualizacion"/></td>
                                            <td align="center">
                                                
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </s:else>
                            <div>
                            </div       
                            </tbody>
                        </table>
=======
                                <s:if test="%{administradores.isEmpty()}">
                                    <h2>No hay resultados que mostrar</h2>
                                </s:if>
                                <s:else>
                                    <thead>
                                    <th>Cédula</th>
                                    <th>Nombre</th>
                                    <th>Apellidos</th>
                                    <th>Ultima Actualización de datos</th>
                                    <th></th>
                                    </thead>
                                    <tbody>
                                        <s:iterator value="solicitantes" var="solicitanteActual">
                                            <tr>
                                                <td><s:property value="#solicitanteActual.cedula"/></td>
                                                <td><s:property value="#solicitanteActual.nombre"/></td>
                                                <td><s:property value="#solicitanteActual.apellidos"/></td>
                                                <td><s:property value="#solicitanteActual.ultimaActualizacion"/></td>
                                                <td>
                                                    <a onclick="return enviarCorreo(<s:property value="#solicitanteActual.correo"/>);">                                                
                                                        Notificar
                                                    </a>

                                                </td>
                                            </tr>
                                        </s:iterator>
                                    </s:else>
                                <div>
                                </div       
                                </tbody>
                            </table>
                        </center>
                        <script>
                            function enviarCorreo(String destinatario){
                            <%
                                String asunto = "Solicitud de Actualización de datos";
                                String cuerpo = "Por favor actualizar los datos de su cuenta de usuario en nuestra pagina web";
                                Solicitante solicitante = (Solicitante) session.getAttribute("solicitante");
                                EnviarCorreos enviarCorreo = new EnviarCorreos();
                                enviarCorreo.EnviarCorreo("cesar.b.c@hotmail.com", asunto, cuerpo);
                            %>
                            ]
                        </script>


>>>>>>> bc209fe5c3e89aae1d580037b748b571c4ed6540
                    </div>
                </aside> 
            </div>            
        </section>
        <footer >
            <jsp:include page="../recursosReusables/footer.jsp"/>          
        </footer>  
        <jsp:include page="../recursosReusables/facebook.jsp"/>
    </body>
</html>
