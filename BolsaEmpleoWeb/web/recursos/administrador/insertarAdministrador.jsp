<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Insertar Administrador | Bolsa de Empleo</title>  

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
                            <s:form method="post" action="insertarAdministradorProcess" cssClass="form">
                                <s:textfield name="cedula" type="number" requiredLabel="true" placeholder="Ingresa tu cédula con 9 dígitos"/>
                                <s:textfield name="nombre" requiredLabel="true" placeholder="Ingresa tu nombre"/>
                                <s:textfield name="apellidos" requiredLabel="true" placeholder="Ingresa tus apellidos"/>
                                <s:textfield name="username" requiredLabel="true" placeholder="Ingresa un nombre de usuario"/>
                                <s:password name="password" requiredLabel="true" placeholder="Ingresa una contraseña mayor o igual a 6 caracteres"/> 
                                <s:submit action="insertarAdministradorProcess" value="Insertar Administrador" onclick="return confirmBox();"/>
                            </s:form>
                            <a href="../administrador/principalAdministrador.jsp" style="height: 10px;background: transparent;float: left">Cancelar</a>
                        </center>
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
