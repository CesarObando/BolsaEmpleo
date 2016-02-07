<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Mantener Solicitudes | Bolsa de Empleo</title>  
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
                            <s:form action="buscarSolicitudesAdministradorProcess" cssClass="form">
                                <s:textfield name="puesto" label="Puesto"/>
                                <s:select name="categoria.id" list="categorias" listKey="id" listValue="nombre" headerValue="Seleccione una categoría" headerKey="-1"/>
                                <s:submit method="buscar" value="Buscar" action="buscarSolicitudesAdministradorProcess"/>
                            </s:form>
                            <table id="mytable" class="table table-bordred table-striped">

                                <s:if test="%{solicitudes.isEmpty()}">
                                    <h2>No hay resultados que mostrar</h2>
                                </s:if>

                                <s:else>
                                    <thead>
                                    <th>Puesto</th>
                                    <th>Nombre Solicitante</th>
                                    <th>Apellidos Solicitante</th>
                                    <th>Nombre Empleador</th>
                                    <th>Apellidos Empleador</th>
                                    <th>Nombre Empresa</th>
                                    <th>Eliminar</th>
                                    </thead>
                                    <tbody>
                                        <s:iterator value="solicitudes" var="solicitudActual">
                                            <tr>
                                                <td><s:property value="#solicitudActual.oferta.puesto"/></td>
                                                <td><s:property value="#solicitudActual.solicitante.nombre"/></td>
                                                <td><s:property value="#solicitudActual.solicitante.apellidos"/></td>
                                                <td><s:property value="#solicitudActual.oferta.empleador.nombre"/></td>
                                                <td><s:property value="#solicitudActual.oferta.empleador.apellidos"/></td>
                                                <td><s:property value="#solicitudActual.oferta.empleador.nombreEmpresa"/></td>
                                                <td><p data-placement="top" data-toggle="tooltip" title="Delete">
                                                        <s:url action="eliminarSolicitudAdministradorProcess" var="url">
                                                            <s:param name="id" value="#solicitudActual.id"/>
                                                        </s:url>
                                                        <a href='<s:property value="#url" />' onclick="return confirmBox();"> <button style="background-color: white"><img src="../imagenes/eliminar.png"/></button> </a>
                                                    </p>
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
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea eliminar la solicitud?");
                                if (answer == true) {
                                    return true;
                                }
                                else {
                                    return false;
                                }
                            }
                        </script>
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
