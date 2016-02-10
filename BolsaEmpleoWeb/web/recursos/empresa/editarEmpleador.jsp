<%@page import="Dominio.Empleador"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Editar Empleador | Bolsa de Empleo</title>  
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
                <jsp:include page="../recursosReusables/menuEmpleador.jsp"/>
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
                            <s:form method="post" action="editarEmpleadorProcess" cssClass="form">
                                <s:hidden name="id"/>
                                <s:textfield name="cedula" placeholder="Ingresa tu cédula con 9 dígitos" readonly="true" disabled="true" label="Cédula"/>
                                <s:textfield name="nombre" placeholder="Ingresa tu nombre" requiredLabel="true" label="Nombre"/>
                                <s:textfield name="apellidos" placeholder="Ingresa tus apellidos" requiredLabel="true" label="Apellidos"/>
                                <s:textfield name="correo" placeholder="Ingresa tu correo electrónico" requiredLabel="true" type="email" label="Correo electrónico"/>
                                <s:textfield name="telefonoFijo" placeholder="Ingresa tu número fijo" label="Teléfono fijo"/> 
                                <s:textfield name="telefonoMovil" placeholder="Ingresa tu número móvil" label="Teléfono móvil"/>
                                <s:textfield name="cedulaJuridica" placeholder="Ingresa tu cédula jurídica" label="Cédula jurídica"/>
                                <s:textfield name="nombreEmpresa" placeholder="Ingresa el nombre de tu empresa" label="Nombre de la empresa"/>
                                <s:textarea name="direccion" placeholder="Ingresa tu dirección" label="Dirección"/>
                                <s:textfield name="username" placeholder="Ingresa un nombre de usuario" readonly="true" disabled="true" label="Nombre de usuario"/>
                                <s:password name="pass" placeholder="Ingresa una contraseña mayor o igual a 6 caracteres" requiredLabel="true" label="Contraseña"/>
                                <s:submit action="editarEmpleadorProcess" value="Editar Empleador" onclick="return confirmBox();"/>
                            </s:form>
                            <a href="../empresa/principalEmpleador.jsp" style="height: 10px;background: transparent;float: left">Cancelar</a>
                        </center>
                        <script>
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea editar la información?");
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
