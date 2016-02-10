<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Editar Solicitante | Bolsa de Empleo</title>  
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
                            <s:form method="post" action="editarSolicitanteProcess" enctype="multipart/form-data" cssClass="form">
                                <s:hidden name="id"/>
                                <s:textfield name="cedula" placeholder="Ingresa tu cédula con 9 dígitos" disabled="true" label="Cédula"/>
                                <s:textfield name="nombre" placeholder="Ingresa tu nombre" type="text" requiredLabel="true" label="Nombre"/>
                                <s:textfield name="apellidos" placeholder="Ingresa tus apellidos" type="text" requiredLabel="true" label="Apellidos"/>
                                <img src="<s:url action="getImagen" namespace="/"><s:param name="idImagen">${id}</s:param></s:url>" width="100" height="100" />
                                <s:file name="archivoImagen" label="Foto"/>
                                <s:textfield name="edad" placeholder="Ingresa tu edad" type="number" requiredLabel="true" label="Edad"/>
                                <s:select requiredLabel="true" name="sexo" list="#{'M':'Masculino','F':'Femenino'}" headerKey="sexo" headerValue="sexo" label="Género"/>
                                <s:select requiredLabel="true" label="Escolaridad" name="escolaridad" list="#{'Educación Escolar':'Educación Escolar','Educación Media':'Educación Media','Educación Diversificada':'Eduación Diversificada','Educación Superior Universitaria':'Educación Superior Universitaria','Educación Superior no Universitaria':'Educación Superior no Universitaria'}" headerKey="escolaridad" headerValue="escolaridad"/>
                                <s:textarea name="titulos" placeholder="Ingresa los títulos que posees" label="Títulos"/>
                                <s:textfield name="experienciaLaboral" placeholder="Ingresa los años de experiencia laboral que tienes" type="number" label="Años de experiencia laboral"/>
                                <s:textarea name="detalleExperienciaLaboral" placeholder="Describe tu experiencia laboral detalladamente" label="Detalle de experiencia laboral"/>
                                <s:textfield name="telefonoFijo" placeholder="Ingresa tu número fijo" type="number" label="Teléfono fijo"/> 
                                <s:textfield name="telefonoMovil" placeholder="Ingresa tu número móvil" type="number" label="Teléfono móvil"/>
                                <s:textfield name="correo" placeholder="Ingresa tu correo electrónico" type="email" requiredLabel="true" label="Correo electrónico"/>
                                <s:textfield name="idiomas" placeholder="Ingresa los idiomas que dominas" type="text" label="Idiomas"/>
                                <s:textfield name="username" placeholder="Ingresa un nombre de usuario" disabled="true" label="Nombre de usuario"/>
                                <s:password name="password" placeholder="Ingresa una contraseña mayor o igual a 6 caracteres" requiredLabel="true" label="Contraseña"/> 
                                <s:submit action="editarSolicitanteProcess" value="Editar Solicitante" onclick="return confirmBox();"/>
                            </s:form>
                                <a href="../usuarios/principalSolicitante.jsp" style="height: 10px;background: transparent;float: left">Cancelar</a>
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
