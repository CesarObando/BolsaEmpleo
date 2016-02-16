<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Recuperación de contraseña | Bolsa de Empleo</title>  
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
            </div>
        </header>
        <section >
            <div id="zone-content" class="clearfix container-12">     
                <jsp:include page="../recursosReusables/section.jsp"/>
                <aside class="grid-3 region" id="region-sidebar-second">
                    <div class="grid-9 region-content" id="region-content">
                        <center>
                            <s:form method="post" enctype="multipart/form-data" action="recuperarPasswordEmpresa" cssClass="form">
                                <s:textfield name="nombreUsuario" placeholder="Nombre de usuario"/>
                                <s:submit action="recuperarPasswordEmpresa" value="Solicitar Contraseña" class="button-submit"/>
                            </s:form>
                        </center>
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
