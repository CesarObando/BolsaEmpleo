<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Registrar Solicitante | Bolsa de Empleo</title>  
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
                            <s:form method="post" action="insertarSolicitanteProcess" enctype="multipart/form-data" cssClass="form">
                                <s:textfield name="cedula" type="number" requiredLabel="true" placeholder="Ingresa tu cédula con 9 dígitos"/>
                                <s:textfield name="nombre" type="text" requiredLabel="true" placeholder="Ingresa tu nombre"/>
                                <s:textfield name="apellidos" type="text" requiredLabel="true" placeholder="Ingresa tus apellidos"/>
                                <s:file name="archivoImagen" requiredLabel="true" placeholder="Seleccione una foto para su perfil" style="width:450px"/> 
                                <s:textfield name="edad" type="number" requiredLabel="true" placeholder="Ingresa tu edad"/>
                                <s:select requiredLabel="true" name="sexo" list="#{'M':'Masculino','F':'Femenino'}" headerKey="-1" headerValue="Selecciona tu género"/>
                                <s:select requiredLabel="true" name="escolaridad" list="#{'Educación Escolar':'Educación Escolar','Educación Media':'Educación Media','Educación Diversificada':'Eduación Diversificada','Educación Superior Universitaria':'Educación Superior Universitaria','Educación Superior no Universitaria':'Educación Superior no Universitaria'}" headerKey="-1" headerValue="Seleccione tu escolaridad"/>
                                <s:textarea name="titulos" placeholder="Ingresa los títulos que posees"/>
                                <s:textfield name="experienciaLaboral" type="number" placeholder="Ingresa los años de experiencia laboral que tienes"/>
                                <s:textarea name="detalleExperienciaLaboral" placeholder="Describe tu experiencia laboral detalladamente"/>
                                <s:textfield name="telefonoFijo" type="number" placeholder="Ingresa tu número fijo"/> 
                                <s:textfield name="telefonoMovil" type="number" placeholder="Ingresa tu número móvil"/>
                                <s:textfield name="correo" type="email" requiredLabel="true" placeholder="Ingresa tu correo electrónico"/>
                                <s:textfield name="idiomas" type="text" placeholder="Ingresa los idiomas que dominas"/>
                                <s:textfield name="username" requiredLabel="true" placeholder="Ingresa un nombre de usuario"/>
                                <s:password name="password" requiredLabel="true" placeholder="Ingresa una contraseña mayor o igual a 6 caracteres"/> 
                                <s:submit method="insertar" value="Insertar Solicitante" action="insertarSolicitanteProcess" onclick="return confirmBox();"/>
                            </s:form>
                        </center>
                        <a href="../usuarios/pantallaPrincipal.jsp" style="height: 10px;background: transparent;float: left">Cancelar</a>

                        <script>
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea registrar el solicitante?");
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
