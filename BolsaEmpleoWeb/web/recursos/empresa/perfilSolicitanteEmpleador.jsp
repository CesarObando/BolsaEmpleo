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
                                <s:label name="cedula" readonly="true"/>
                                <s:label name="nombre"/>
                                <s:label name="apellidos"/>
                                <img src="<s:url action="getImagenEmpleador" namespace="/"><s:param name="idImagen">${id}</s:param></s:url>" width="100" height="100" />
                                <s:label name="edad" label="Edad"/>
                                <s:label name="sexo" readonly="true"/>
                                <s:label name="escolaridad" readonly="true"/>
                                <s:label name="titulos" style="width:450px;height:150px"/>
                                <s:label name="experienciaLaboral" label="Años de experiencia laboral"/>
                                <s:label name="detalleExperienciaLaboral" style="width:450px;height:150px"/>
                                <s:label name="telefonoFijo"/> 
                                <s:label name="telefonoMovil"/>
                                <s:label name="correo"/>
                                <s:label name="idiomas"/>
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
