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

                        <s:form method="post" action="registrarEmpleadorProcess" enctype="multipart/form-data">
                            <s:textfield name="cedula" label="Cédula" type="number" requiredLabel="true"/>
                            <s:textfield name="nombre" label="Nombre" requiredLabel="true"/>
                            <s:textfield name="apellidos" label="Apellidos" requiredLabel="true"/>
                            <s:textfield name="correo" label="Email" type="email" requiredLabel="true"/>
                            <s:textfield name="telefonoFijo" label="Teléfono Fijo"/> 
                            <s:textfield name="telefonoMovil" label="Teléfono Celular"/>
                            <s:textfield name="cedulaJuridica" label="Cédula Jurídica"/>
                            <s:textfield name="nombreEmpresa" label="Nombre de la empresa"/>
                            <s:textarea name="direccion" label="Dirección"/>
                            <s:textfield name="username" label="Nombre Usuario" requiredLabel="true"/>
                            <s:password name="pass" label="Clave" requiredLabel="true"/>
                            <s:submit action="registrarEmpleadorProcess" value="Registrar Empleador" onclick="return confirmBox();"/>
                        </s:form>
                        <script>
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea registrar el empleador?");
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
