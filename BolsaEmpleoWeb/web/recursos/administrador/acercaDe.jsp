<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Iniciar Sesión | Bolsa de Empleo</title>  
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
                <c:if test="${sessionScope.administrador!=null}">
                    <jsp:include page="../recursosReusables/menuAdministrador.jsp"/>
                </c:if>
                <c:if test="${sessionScope.solicitante!=null}">
                    <jsp:include page="../recursosReusables/menuSolicitante.jsp"/>
                </c:if>
                <c:if test="${sessionScope.empleador!=null}">
                    <jsp:include page="../recursosReusables/menuEmpleador.jsp"/>
                </c:if>
                <c:if test="${sessionScope.administrador==null && sessionScope.solicitante==null && sessionScope==null}">
                    <jsp:include page="../recursosReusables/menuPrincipal.jsp"/>
                </c:if>
            </div>
        </header>
        <section >
            <div id="zone-content" class="clearfix container-12">     
                <jsp:include page="../recursosReusables/section.jsp"/>
                <c:if test="${sessionScope.administrador == null}" >
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
                            <s:else>
                                <h1 class="h1">Bolsa de Empleo</h1><br>
                                <br><br><br><h2 class="h2">¿Quiénes somos?</h2><br><br>
                                <br><p>Somos un grupo de estudiantes de la Universidad de Costa Rica que se encarga de realizar, ofrecer y mantener este servicio
                                    de bolsa de empleo perteneciente al Trabajo Comunal de dicha institución.</p>
                                <h2 class="h2">Misión</h2><br><br>
                                <br><p>Ofrecer el servicio de bolsa de empleo a personas de toda la comunidad incluyendo tanto profesionales como no profesionales.</p>
                                <h2 class="h2">Visión</h2><br><br>
                                <br><p>Facilitar a los usuarios el proceso de buscar empleo y ofrecer sus servicios como trabajadores independientes.</p>
                                <center><img src="../imagenes/tcu.jpg" id="logo" style="width: 200px;height: 200px"/></center>
                            </s:else>
                        </div>
                    </aside>
                </c:if>
            </div>            
        </section> 
    </body>
    <footer >
        <jsp:include page="../recursosReusables/footer.jsp"/>          
    </footer>  
    <jsp:include page="../recursosReusables/facebook.jsp"/>
</body>
</html>
