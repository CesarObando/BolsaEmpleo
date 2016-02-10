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
                                <s:textfield name="cedula" readonly="true" label="Cédula"/>
                                <s:textfield name="nombre" readonly="true" label="Nombre"/>
                                <s:textfield name="apellidos" readonly="true" label="Apellidos"/>
                                <img src="<s:url action="getImagen" namespace="/"><s:param name="idImagen">${id}</s:param></s:url>" width="100" height="100" />
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
                                <s:textfield name="username" readonly="true" label="Nombre de usuario"/>
                                <s:if test="numSolicitudes==0">
                                    <s:label name="solicitudes" value="No has solicitado empleo en ninguna oferta"/>
                                </s:if>
                                <s:else>
                                    <s:textfield name="numSolicitudes" readonly="true" label="Solicitudes de empleo"/>
                                </s:else>
                                <s:if test="numSolicitudes==0">
                                    <s:label name="favoritos" value="No has sido elegido como favorito en ninguna oferta"/>
                                </s:if>
                                <s:else>
                                    <s:textfield name="numFavoritos" readonly="true" label="Has sido elegido como favorito en"/>
                                </s:else>
                            </s:form>
                        </center>
                            <a href="../usuarios/editarSolicitante.action" class="" style="background: transparent">Editar</a>
                        <a href="../usuarios/eliminarSolicitanteProcess.action" class="" style="background: transparent" onclick="return confirmBox();" >Eliminar</a>
                        <a href="../usuarios/editarSolicitanteProcess.action" class="" onclick="return confirmBox1();" style="background: transparent">Renovar cuenta</a>
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
