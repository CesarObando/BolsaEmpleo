<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Perfil Empleador | Bolsa de Empleo</title>  
    </head>
    <body onload="nobackbutton();">
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
                                <s:label name="cedula" label="Cédula" readonly="true"/>
                                <s:label name="nombre" label="Nombre"/>
                                <s:label name="apellidos" label="Apellidos"/>
                                <s:label name="correo" label="Email"/>
                                <s:label name="telefonoFijo" label="Teléfono Fijo"/> 
                                <s:label name="telefonoMovil" label="Teléfono Celular"/>
                                <s:label name="cedulaJuridica" label="Cédula Jurídica" readonly="true"/>
                                <s:label name="nombreEmpresa" label="Nombre de la empresa"/>
                                <s:label name="direccion" label="Dirección"/>
                                <s:label name="username" label="Nombre Usuario" readonly="true"/>
                            </s:form>
                        </center>
                        <a href="../empresa/editarEmpleador.action" class="">Editar</a>
                        <a href="../empresa/eliminarEmpleadorProcess.action" class="" onclick="return confirmBox1();">Eliminar</a>
                        <a href="../empresa/editarEmpleadorProcess.action" class="" onclick="return confirmBox();">Renovar cuenta</a>
                    </div>
                    <script>
                        function confirmBox() {
                            var answer;
                            answer = window.confirm("¿Desea renovar la información?");
                            if (answer == true) {
                                return true;
                            }
                            else {
                                return false;
                            }
                        }
                    </script>
                    <script>
                        function confirmBox1() {
                            var answer;
                            answer = window.confirm("¿Desea eliminar la cuenta?");
                            if (answer == true) {
                                return true;
                            }
                            else {
                                return false;
                            }
                        }
                    </script>
                </aside> 
            </div>            
        </section>
        <footer >
            <jsp:include page="../recursosReusables/footer.jsp"/>          
        </footer>  
        <jsp:include page="../recursosReusables/facebook.jsp"/>
    </body>
</html>
