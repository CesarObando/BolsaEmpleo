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

                        <s:form method="get" action="reporte">
                            <s:label name="id" label="Id"/>
                            <s:label name="cedula" label="Cedula" readonly="true"/>
                            <s:hidden name="id"/>
                            <s:label name="cedula" label="Cédula" readonly="true"/>
                            <s:label name="nombre" label="Nombre"/>
                            <s:label name="apellidos" label="Apellidos"/>
                            <img src="<s:url action="getImagenEmpleador" namespace="/"><s:param name="idImagen">${id}</s:param></s:url>" width="100" height="100" />
                            <s:label name="edad" label="Edad"/>
                            <s:label name="sexo" label="Sexo" readonly="true"/>
                            <s:textarea name="escolaridad" label="Escolaridad" readonly="true"/>
                            <s:label name="titulos" label="Títulos"/>
                            <s:label name="experienciaLaboral" label="Años de experiencia laboral"/>
                            <s:textarea name="detalleExperienciaLaboral" label="Detalle Experiencia Laboral" readonly="true"/>
                            <s:label name="telefonoFijo" label="Teléfono Casa"/> 
                            <s:label name="telefonoMovil" label="Teléfono Celular"/>
                            <s:label name="correo" label="Email"/>
                            <s:label name="idiomas" label="Idiomas que domina"/>
                            <s:submit name="reporte" value="Exportar a PDF"></s:submit>

                        </s:form>


                        <c:if test="${sessionScope.solicitud.favorito == true}">
                            <a href="../empresa/editarSolicitudEmpleadorProcess.action" class="" onclick="return confirmBox();">Desmarcar como favorito</a>
                        </c:if>
                        <c:if test="${sessionScope.solicitud.favorito == false}">
                            <a href="../empresa/editarSolicitudEmpleadorProcess.action" class="" onclick="return confirmBox1();">Marcar como favorito</a>
                        </c:if>
                        <a href="../empresa/eliminarSolicitudEmpleadorProcess.action" class="" onclick="return confirmBox2();">Eliminar</a>
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
