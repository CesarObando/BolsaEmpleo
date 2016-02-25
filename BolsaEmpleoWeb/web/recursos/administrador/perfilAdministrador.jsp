<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Perfil Administrador | Bolsa de Empleo</title>  
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
                <jsp:include page="../recursosReusables/menuAdministrador.jsp"/>
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
                            <s:form method="post" cssClass="form">
                                <s:hidden name="id"/>
                                <s:textfield name="cedula" readonly="true" label="Cédula"/>
                                <s:textfield name="nombre" readonly="true" label="Nombre"/>
                                <s:textfield name="apellidos" readonly="true" label="Apellidos"/>
                                <s:textfield name="username" readonly="true" label="Nombre de usuario"/>
                            </s:form>
                        </center>
                        <a href="../administrador/editarAdministrador.action" class="" style="background: transparent">Editar</a>
                        <a href="../administrador/eliminarAdministradorProcess.action" class="" onclick="return confirmBox('¿Desea eliminar su perfil?');" style="background: transparent">Eliminar</a>
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
