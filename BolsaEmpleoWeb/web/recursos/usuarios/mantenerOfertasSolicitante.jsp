<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Mantener Ofertas | Bolsa de Empleo</title>  
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
                <jsp:include page="../recursosReusables/menuSolicitante.jsp"/>
            </div>
        </header>
        <section>
            <div id="zone-content" class="clearfix container-12">  
                <jsp:include page="../recursosReusables/section.jsp"/>
                <aside class="grid-3 region" id="region-sidebar-second">
                    <div class="grid-9 region-content" id="region-content">
                        <table id="mytable" class="table table-bordred table-striped">
                            <s:if test="%{solicitudes.isEmpty()}">
                                <h2>No hay resultados que mostrar</h2>
                            </s:if>

                            <s:else>
                                <thead>
                                <td>Puesto</td>
                                <td>Empleador</td>
                                <td>Empresa</td>
                                <th>Eliminar</th>
                                </thead>
                                <tbody>
                                    <s:iterator value="solicitudes" var="solicitudActual">
                                        <tr>
                                            <td><s:property value="#solicitudActual.oferta.puesto"/></td>
                                            <td><s:property value="#solicitudActual.oferta.empleador.nombre"/></td>
                                            <td><s:property value="#solicitudActual.oferta.empleador.nombreEmpresa"/></td>
                                            <td><p data-placement="top" data-toggle="tooltip" title="Ver">
                                                    <s:url action="eliminarSolicitudSolicitanteProcess" var="url">
                                                        <s:param name="id" value="#solicitudActual.id"/>
                                                    </s:url>
                                                    <a href='<s:property value="#url"/>' onclick="return confirmBox();">  <button style="background-color: white"><img src="../imagenes/eliminar.png"/> </button> </a>
                                                </p>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </s:else>
                            <div>
                            </div       
                            </tbody>
                        </table>
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
