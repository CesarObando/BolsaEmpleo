<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Registrar Empleador | Bolsa de Empleo</title>  
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
                            <s:form method="post" action="registrarEmpleadorProcess" enctype="multipart/form-data" cssClass="form">
                                <s:textfield name="cedula" placeholder="Ingresa tu cédula con 9 dígitos" type="number" requiredLabel="true"/>
                                <s:textfield name="nombre" placeholder="Ingresa tu nombre" requiredLabel="true"/>
                                <s:textfield name="apellidos" placeholder="Ingresa tus apellidos" requiredLabel="true"/>
                                <s:textfield name="correo" placeholder="Ingresa tu correo electrónico" type="email" requiredLabel="true"/>
                                <s:textfield name="telefonoFijo" placeholder="Ingresa tu número fijo"/> 
                                <s:textfield name="telefonoMovil" placeholder="Ingresa tu número móvil"/>
                                <s:textfield name="cedulaJuridica" placeholder="Ingresa tu cédula jurídica"/>
                                <s:textfield name="nombreEmpresa" placeholder="Ingresa el nombre de tu empresa"/>
                                <s:textarea name="direccion" placeholder="Ingresa tu dirección"/>
                                <s:textfield name="username" placeholder="Ingresa un nombre de usuario" requiredLabel="true"/>
                                <s:password name="password" placeholder="Ingresa una contraseña mayor o igual a 6 caracteres" requiredLabel="true"/>
                                <s:submit action="registrarEmpleadorProcess" value="Registrar Empleador" onclick="return confirmBox('¿Desea registrar el empleador?');"/>
                            </s:form>
                            <a href="../usuarios/pantallaPrincipal.jsp" style="height: 10px;background: transparent;float: left">Cancelar</a>
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
