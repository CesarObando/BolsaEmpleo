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
                <c:if test="${sessionScope.solicitante == null}">
                    <jsp:include page="../recursosReusables/menuPrincipal.jsp"/>
                </c:if>

                <c:if test="${sessionScope.solicitante != null}">
                    <jsp:include page="../recursosReusables/menuSolicitante.jsp"/>
                </c:if>
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
                            <s:form method="post" action="insertarSolicitudProcess" cssClass="form">
                                <s:hidden name="id"/>
                                <s:textfield name="puesto" readonly="true" label="Puesto"/>
                                <s:textfield name="empleador.nombre" readonly="true" label="Nombre de empleador"/>
                                <s:textfield name="empleador.apellidos" readonly="true" label="Apellidos"/>
                                <s:textfield name="empleador.nombreEmpresa" readonly="true" label="Nombre de la empresa"/>
                                <s:if test="salario==0">
                                    <s:textfield name="salario1" value="No especificado" label="Salario" readonly="true"/>
                                </s:if>
                                <s:else>
                                    <s:textfield name="salario" readonly="true" label="Salario"/>
                                </s:else>
                                <s:textfield name="cantidadVacantes" label="Cantidad de Vacantes" readonly="true"/>
                                <s:textarea name="requerimientos" readonly="true" label="Requerimientos"/>
                                <s:textfield name="categoria.nombre" readonly="true" label="Categoría"/>
                                <s:textarea name="descripcion" readonly="true" label="Descripción"/>
                                <s:textfield name="provincia" readonly="true" label="Provincia"/>
                                <s:textfield name="canton" readonly="true" label="Cantón"/>
                                <c:if test="${sessionScope.solicitante != null}">
                                    <s:submit value="Solicitar" action="insertarSolicitudProcess" onclick="return confirmBox('¿Desea registrar la solicitud?');"/>
                                </c:if>
                            </s:form>
                            <s:form action="reporteOferta" style="float: right" cssClass="form">
                                <s:submit value="Exportar a PDF"></s:submit>
                            </s:form>
                            <s:form method="post" action="marcarOfertaFavoritaProcess" cssClass="form" style="float: right">
                                <s:hidden name="id"/>
                                <c:if test="${sessionScope.solicitante != null}">
                                    <c:if test="${insertado == true}">

                                        <s:submit value="Desmarcar favorito" action="marcarOfertaFavoritaProcess" onclick="return confirmBox('¿Desea desmarcar al solicitante como favorito?');"/>
                                    </c:if>
                                    <c:if test="${insertado == false}">
                                        <s:submit value="Marcar favorito" action="marcarOfertaFavoritaProcess" onclick="return confirmBox('¿Desea marcar al solicitante como favorito?');"/>
                                    </c:if>
                                </c:if>
                            </s:form>
                            <br><br><br>
                            <c:if test="${sessionScope.solicitante != null}">
                                <a href="../usuarios/principalSolicitante.jsp" style="height: 10px;background: transparent;float: left">Cancelar</a>
                            </c:if>
                            <c:if test="${sessionScope.solicitante == null}">
                                <s:a href="../usuarios/insertarSolicitante.jsp">Registrate para enviar tu solicitud a esta oferta</s:a> o <s:a href="../usuarios/login.jsp">inicia sesión.</s:a>
                                    <a href="../usuarios/pantallaPrincipal.jsp" style="height: 10px;background: transparent;float: left">Cancelar</a>
                            </c:if>
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
