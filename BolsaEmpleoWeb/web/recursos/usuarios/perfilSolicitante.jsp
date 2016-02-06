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
                <jsp:include page="../recursosReusables/menuSolicitante.jsp"/>
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
                            <s:form method="post" action="editarSolicitanteProcess" cssClass="form">
                                <s:hidden name="id"/>
                                <s:label name="cedula" label="Cédula" readonly="true"/>
                                <s:label name="nombre" label="Nombre"/>
                                <s:label name="apellidos" label="Apellidos"/>
                                <s:label name="username" label="Nombre Usuario" readonly="true"/>
                                <img src="<s:url action="getImagen" namespace="/"><s:param name="idImagen">${id}</s:param></s:url>" width="100" height="100" />
                                <s:label name="edad" label="Edad"/>
                                <s:label name="sexo" label="Sexo" readonly="true"/>
                                <s:label name="escolaridad" label="Escolaridad" readonly="true"/>
                                <s:textarea name="titulos" label="Títulos" readonly="true" style="width:300px;height:150px"/>
                                <s:label name="experienciaLaboral" label="Años de experiencia laboral"/>
                                <s:textarea name="detalleExperienciaLaboral" label="Detalle Experiencia Laboral" readonly="true" style="width:300px;height:150px"/>
                                <s:label name="telefonoFijo" label="Teléfono Casa"/> 
                                <s:label name="telefonoMovil" label="Teléfono Celular"/>
                                <s:label name="correo" label="Email"/>
                                <s:label name="idiomas" label="Idiomas que domina"/>
                                <s:if test="numSolicitudes==0">
                                    <s:label name="solicitudes" value="No has solicitado empleo en ninguna oferta"/>
                                </s:if>
                                <s:else>
                                    <s:label name="numSolicitudes" label="Solicitudes de empleo"/>
                                </s:else>
                                <s:if test="numSolicitudes==0">
                                    <s:label name="favoritos" value="No has sido elegido como favorito en ninguna oferta"/>
                                </s:if>
                                <s:else>
                                    <s:label name="numFavoritos" label="Has sido elegido como favorito en"/>
                                </s:else>
                            </s:form>
                        </center>
                        <a href="../usuarios/editarSolicitante.action" class="">Editar</a>
                        <a href="../usuarios/eliminarSolicitanteProcess.action" class="" onclick="return confirmBox();">Eliminar</a>
                        <a href="../usuarios/editarSolicitanteProcess.action" class="" onclick="return confirmBox1();">Renovar cuenta</a>
                        <script>
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea eliminar su perfil?");
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
                                answer = window.confirm("¿Desea renovar la información?");
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
