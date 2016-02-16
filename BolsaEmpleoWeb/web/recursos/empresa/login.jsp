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
                <jsp:include page="../recursosReusables/menuPrincipal.jsp"/>
            </div>
        </header>
        <section>
            <div id="zone-content" class="clearfix container-12" >  
                <jsp:include page="../recursosReusables/section.jsp"/>
                <c:if test="${sessionScope.empleador == null}" >
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
                                <center>
                                    <s:form method="post" enctype="multipart/form-data" action="iniciarSesionEmpleador " cssClass="form">
                                        <s:textfield  align="center" name="nombreUsuario" placeholder="Nombre de Usuario" style=""/>
                                        <s:password  align="center" name="clave" placeholder="Contraseña"/>
                                        <s:submit  align="center" action="iniciarSesionempleador" value="Iniciar Sesión" class="button-submit"/>
                                    </s:form>
                                </center>
                                <a href="../empresa/recuperarPassword.jsp">Recuperar la contraseña</a>
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
