<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Eliminar Solicitante | Bolsa de Empleo</title>  
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
                <c:if test="${sessionScope.administrador != null}">
                    <li>
                        <jsp:include page="../recursosReusables/menuAdministrador.jsp"/>      
                    </li>
                </c:if>
                <c:if test="${sessionScope.solicitante != null}">
                    <li>
                        <jsp:include page="../recursosReusables/menuSolicitante.jsp"/>               
                    </li>
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

                        <c:if test="${sessionScope.administrador != null}">
                            <s:form action="eliminarSolicitanteAdministradorProcess" method="post">
                                <s:hidden name="id"/>
                                <s:label name="nombre" label="Nombre"/>
                                <s:label name="apellidos" label="Apellidos"/>
                                <s:submit value="Eliminar" action="eliminarSolicitanteAdministradorProcess" onclick="return confirmBox();"/>
                            </s:form>
                        </c:if>
                        <c:if test="${sessionScope.solicitante != null}">
                            <s:form action="eliminarSolicitanteProcess" method="post">
                                <s:hidden name="id"/>
                                <s:label name="nombre" label="Nombre"/>
                                <s:label name="apellidos" label="Apellidos"/>
                                <s:submit value="Eliminar" action="eliminarSolicitanteProcess" onclick="return confirmBox();"/>
                            </s:form>
                        </c:if>

                        <script>
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea eliminar?");
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
