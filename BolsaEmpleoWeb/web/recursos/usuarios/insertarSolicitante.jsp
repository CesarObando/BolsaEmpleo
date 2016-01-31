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

                        <s:form method="post" action="insertarSolicitanteProcess" enctype="multipart/form-data">
                            <s:textfield name="cedula" label="Cédula" type="number" requiredLabel="true"/>
                            <s:textfield name="nombre" label="Nombre" type="text" requiredLabel="true"/>
                            <s:textfield name="apellidos" label="Apellidos" type="text" requiredLabel="true"/>
                            <s:textfield name="username" label="Nombre Usuario" requiredLabel="true"/>
                            <s:password name="password" label="Clave" requiredLabel="true"/> 
                            <s:file name="archivoImagen" label="Seleccione una foto para su perfil" requiredLabel="true"/> 
                            <s:textfield name="edad" label="Edad" type="number" requiredLabel="true"/>
                            <s:select label="Género" requiredLabel="true" name="sexo" list="#{'M':'Masculino','F':'Femenino'}" headerKey="-1" headerValue="Seleccione su género"/>
                            <s:select label="Escolaridad" requiredLabel="true" name="escolaridad" list="#{'Educación Escolar':'Educación Escolar','Educación Media':'Educación Media','Educación Diversificada':'Eduación Diversificada','Educación Superior Universitaria':'Educación Superior Universitaria','Educación Superior no Universitaria':'Educación Superior no Universitaria'}" headerKey="-1" headerValue="Seleccione su escolaridad"/>
                            <s:textarea name="titulos" label="Títulos"/>
                            <s:textfield name="experienciaLaboral" label="Años de Experiencia Laboral" type="number"/>
                            <s:textarea name="detalleExperienciaLaboral" label="Detalle de Experiencia Laboral"/>
                            <s:textfield name="telefonoFijo" label="Teléfono Fijo" type="number"/> 
                            <s:textfield name="telefonoMovil" label="Teléfono Celular" type="number" />
                            <s:textfield name="correo" label="Email" type="email" requiredLabel="true"/>
                            <s:textfield name="idiomas" label="Idiomas que domina" type="text"/>
                            <s:submit method="insertar" value="Insertar Solicitante" action="insertarSolicitanteProcess" onclick="return confirmBox();"/>
                        </s:form>
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
