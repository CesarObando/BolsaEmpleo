<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Mantener Servicios | Bolsa de Empleo</title>  
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
                <c:if test="${sessionScope.empleador == null}">
                    <jsp:include page="../recursosReusables/menuPrincipal.jsp"/>
                </c:if>

                <c:if test="${sessionScope.empleador != null}">
                    <jsp:include page="../recursosReusables/menuEmpleador.jsp"/>
                </c:if>
            </div>
        </header>
        <section>
            <div id="zone-content" class="clearfix container-12">  
                <jsp:include page="../recursosReusables/section.jsp"/>
                <aside class="grid-3 region" id="region-sidebar-second">
                    <div class="grid-9 region-content" id="region-content">
                        <center>
                            <s:form action="buscarServiciosEmpleadorProcess" cssClass="form">
                                <s:textfield name="titulo" placeholder="Ingresa el nombre del trabajo independiente a buscar"/>
                                <s:select name="categoria.id" list="categorias" listKey="id" listValue="nombre" headerValue="Seleccione una categoría" headerKey="-1"/>
                                <s:submit method="buscar" value="Buscar"/>
                            </s:form>
                            <table id="mytable" class="table">



                                <s:if test="%{servicios.isEmpty()}">
                                    <h2>No hay resultados que mostrar</h2>
                                </s:if>

                                <s:else>
                                    <thead class="th">
                                    <th>Título</th>
                                    <th>Solicitante</th>
                                    <th>Apellidos</th>
                                    <th>Ver</th>
                                    </thead>
                                    <tbody class="td">
                                        <s:iterator value="servicios" var="servicioActual">
                                            <tr class="tr">
                                                <td><s:property value="#servicioActual.titulo"/></td>
                                                <td><s:property value="#servicioActual.solicitante.nombre"/></td>
                                                <td><s:property value="#servicioActual.solicitante.apellidos"/></td>
                                                <td><p data-placement="top" data-toggle="tooltip" title="Ver">
                                                        <s:url action="verServicioEmpleador" var="url">
                                                            <s:param name="idS" value="#servicioActual.id"/>
                                                        </s:url>
                                                        <a href='<s:property value="#url" />'> <button style="background-color: transparent"><img src="../imagenes/ver.png"/> </button> </a>
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
                        <a href="../usuarios/insertarSolicitante.jsp" style="background: transparent;float: left">Para ofrecer tu servicio como trabajador independiente registrate como solicitante</a>
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
