<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Ver Servicio | Bolsa de Empleo</title>  
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
                            <s:form method="post" action="reporte" cssClass="form">
                                <s:label name="titulo" readonly="true"/>
                                <s:label name="solicitante.nombre" readonly="true"/>
                                <s:label name="solicitante.apellidos" readonly="true"/>
                                <s:label name="descripcion" readonly="true" style="width:450px;height:150px"/>
                                <s:label name="categoria.nombre" readonly="true"/>
                                <s:submit name="reporte" value="Ver información del solicitante"></s:submit>
                            </s:form>
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
