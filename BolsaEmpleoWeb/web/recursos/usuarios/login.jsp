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
                            <s:form method="post" enctype="multipart/form-data" action="iniciarSesion" cssClass="form">
                                <s:textfield name="nombreUsuario" placeholder="Nombre de Usuario"/>
                                <s:password name="clave" placeholder="Contraseña"/>
                                <s:submit action="iniciarSesion" value="Iniciar Sesion" class="button-submit"/>
                            </s:form>
                        </center>
                            <a href="../usuarios/recuperarPassword.jsp">Recuperar la contraseña</a>
                    </div>
                </aside>
            </div>            
        </section> 
    </body>
    <footer >
        <jsp:include page="../recursosReusables/footer.jsp"/>          
    </footer>  
    <jsp:include page="../recursosReusables/facebook.jsp"/>
</body>
</html>
