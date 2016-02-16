<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Ver Oferta | Bolsa de Empleo</title>  
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
                <jsp:include page="../recursosReusables/menuEmpleador.jsp"/>
            </div>
        </header>
        <section>
            <div id="zone-content" class="clearfix container-12">  
                <jsp:include page="../recursosReusables/section.jsp"/>
                <aside class="grid-3 region" id="region-sidebar-second">
                    <div class="grid-9 region-content" id="region-content">
                        <s:if test="hasActionMessages()">
                            <script>
                                window.alert("${sessionScope.mensaje}");
                            </script>
                        </s:if>
                        <s:if test="hasActionErrors()">
                            <script>
                                window.alert("${sessionScope.mensaje}");
                            </script>
                        </s:if>
                        <center>
                            <s:form method="post" action="verOfertaProcess" cssClass="form">
                                <s:hidden name="id"/>
                                <s:textfield name="puesto" readonly="true" label="Puesto"/>
                                <s:if test="salario==0">
                                    <s:textfield name="salario" value="No especificado" label="Salario" readonly="true"/>
                                </s:if>
                                <s:else>
                                    <s:textfield name="salario" readonly="true" label="Salario"/>
                                </s:else>
                                <s:textfield name="cantidadVacantes" readonly="true" label="Cantidad de vacantes"/>
                                <s:textarea name="requerimientos" readonly="true" label="Requerimientos"/>
                                <s:textfield name="categoria.nombre" readonly="true" label="Categoría"/>
                                <s:textarea name="descripcion" readonly="true" label="Descripción"/>
                                <s:textfield name="provincia" readonly="true" label="Provincia"/>
                                <s:textfield name="canton" readonly="true" label="Cantón"/>
                            </s:form>
                        </center>
                        <a href="../Ofertas/editarOferta.action" class="">Editar Oferta</a>
                        <a href="../Ofertas/eliminarOfertaProcess.action" class="" onclick="return confirmBox();">Eliminar Oferta</a>
                        <script>
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea eliminar la oferta?");
                                if (answer == true) {
                                    return true;
                                }
                                else {
                                    return false;
                                }
                            }
                        </script>
                        <center>
                            <table id="mytable" class="table" title="Solicitantes">


                                <s:if test="%{solicitudes.isEmpty()}">
                                    <h2>No hay solicitudes para esta oferta</h2>
                                </s:if>

                                <s:else>
                                    <thead class="th">
                                    <th>Nombre</th>
                                    <th>Apellidos</th>
                                    <th>Ver</th>
                                    <th>Favorito</th>
                                    </thead>
                                    <tbody class="td">
                                        <s:iterator value="solicitudes" var="solicitudActual">
                                            <tr class="tr">
                                                <td><s:property value="#solicitudActual.solicitante.nombre"/></td>
                                                <td><s:property value="#solicitudActual.solicitante.apellidos"/></td>
                                                <td><p data-placement="top" data-toggle="tooltip" title="Ver">
                                                        <s:url action="verPerfilSolicitanteEmpleador" var="url">
                                                            <s:param name="idS" value="#solicitudActual.id"/>
                                                        </s:url>

                                                        <a href='<s:property value="#url"/>'> <button style="background-color: transparent"><img src="../imagenes/ver.png"/></button> </a>
                                                    </p>
                                                </td>
                                                <td>
                                                    <s:if test="%{#solicitudActual.favorito == true}">
                                                        <img src="../imagenes/favorito.png"/>
                                                    </s:if>
                                                    <s:if test="%{#solicitudActual.favorito == false}">
                                                        <img src="../imagenes/noFavorito.png"/>
                                                    </s:if>
                                                </td>
                                            </tr>
                                        </s:iterator>
                                    </s:else>
                                <div>
                                </div       
                                </tbody>
                            </table>
                        </center>
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
