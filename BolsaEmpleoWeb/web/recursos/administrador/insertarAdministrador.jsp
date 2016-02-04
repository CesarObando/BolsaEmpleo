<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Insertar Administrador | Bolsa de Empleo</title>  
        <link rel="stylesheet" href="/css/Estilo.css"
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

                        <s:form method="post" action="insertarAdministradorProcess" cssClass="form">
                            <s:textfield name="cedula" label="Cedula" type="number" requiredLabel="true"/>
                            <s:textfield name="nombre" label="Nombre" requiredLabel="true"/>
                            <s:textfield name="apellidos" label="Apellidos" requiredLabel="true"/>
                            <s:textfield name="username" label="Nombre Usuario" requiredLabel="true"/>
                            <s:password name="password" label="Clave" requiredLabel="true"/> 
                            <s:submit action="insertarAdministradorProcess" value="Insertar Administrador" onclick="return confirmBox();"/>
                        </s:form>
                        <script>
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea registrar el administrador?");
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
