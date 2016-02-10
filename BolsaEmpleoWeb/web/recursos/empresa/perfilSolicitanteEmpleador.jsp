<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Perfil Solicitante | Bolsa de Empleo</title>  
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
                            <s:form method="post" action="reporte" cssClass="form">
                                <s:hidden name="id"/>
                                <s:textfield name="cedula" readonly="true" label="Cédula"/>
                                <s:textfield name="nombre" readonly="true" label="Nombre"/>
                                <s:textfield name="apellidos" readonly="true" label="Apellidos"/>
                                <img src="<s:url action="getImagenEmpleador" namespace="/"><s:param name="idImagen">${id}</s:param></s:url>" width="100" height="100" />
                                <s:textfield name="edad" readonly="true" label="Edad"/>
                                <s:textfield name="sexo" readonly="true" label="Género"/>
                                <s:textfield name="escolaridad" readonly="true" label="Escolaridad"/>
                                <s:textarea name="titulos" readonly="true" label="Títulos"/>
                                <s:textfield name="experienciaLaboral" readonly="true" label="Años de experiencia laboral"/>
                                <s:textarea name="detalleExperienciaLaboral" readonly="true" label="Detalle de experiencia laboral"/>
                                <s:textfield name="telefonoFijo" readonly="true" label="Teléfono fijo"/> 
                                <s:textfield name="telefonoMovil" readonly="true" label="Teléfono móvil"/>
                                <s:textfield name="correo" readonly="true" label="Correo electrónico"/>
                                <s:textfield name="idiomas" readonly="true" label="Idiomas"/>
                                <s:submit name="reporte" value="Exportar a PDF"></s:submit>

                            </s:form>

                        </center>
                        <c:if test="${sessionScope.solicitud.favorito == true}">
                            <a href="../empresa/editarSolicitudEmpleadorProcess.action" class="" onclick="return confirmBox();" style="background: transparent">Desmarcar como favorito</a>
                        </c:if>
                        <c:if test="${sessionScope.solicitud.favorito == false}">
                            <a href="../empresa/editarSolicitudEmpleadorProcess.action" class="" onclick="return confirmBox1();" style="background: transparent">Marcar como favorito</a>
                        </c:if>
                        <a href="../empresa/eliminarSolicitudEmpleadorProcess.action" class="" onclick="return confirmBox2();" style="background: transparent">Eliminar</a>
                    </div>
                    <script>
                        function confirmBox() {
                            var answer;
                            answer = window.confirm("¿Desea desmarcar al solicitante como favorito?");
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
                            answer = window.confirm("¿Desea marcar al solicitante como favorito?");
                            if (answer == true) {
                                return true;
                            }
                            else {
                                return false;
                            }
                        }
                    </script>
                    <script>
                        function confirmBox2() {
                            var answer;
                            answer = window.confirm("¿Desea eliminar al solicitante?");
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
