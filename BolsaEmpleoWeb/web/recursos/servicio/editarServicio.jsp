<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Editar Servicio | Bolsa de Empleo</title>  
    </head>
    <body onload="editarCanton('<s:property value="canton"/>', '<s:property value="provincia"/>')">
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
                            <s:form method="post" action="editarServicioProcess" cssClass="form" name="f1" enctype="multipart/form-data">
                                <s:hidden name="id"/>
                                <img src="<s:url action="getImagenServicio" namespace="/"><s:param name="idImagen">${id}</s:param></s:url>" width="100" height="100" />
                                <s:file name="archivoImagen" label="Foto" style="width:450px"/>
                                <s:textfield name="titulo" placeholder="Ingresa el nombre del trabajo independiente" requiredLabel="true" label="Nombre"/>
                                <s:textarea name="descripcion" placeholder="Ingresa una descripción" requiredLabel="true" label="Descripción"/>
                                <s:select name="categoria.id" list="categorias" listKey="id" listValue="nombre" headerKey="categoria.id" requiredLabel="true" label="Categoría"/>            
                                <s:select requiredLabel="true" name="provincia" onchange="cambiarCanton()" label="Provincia" onselect="" list="#{'Alajuela':'Alajuela','Cartago':'Cartago','Guanacaste':'Guanacaste','Heredia':'Heredia','Limón':'Limón','Puntarenas':'Puntarenas','San José':'San José'}" headerKey="provincia"/>
                                <s:select name="canton" requiredLabel="true" label="Cantón" list="#{}" headerKey="canton" />
                                <s:submit action="editarServicioProcess" value="Editar Servicio" onclick="return confirmBox('¿Desea editar el servicio?');"/>
                            </s:form>
                            <a href="../usuarios/principalSolicitante.jsp" style="height: 10px;background: transparent;float: left">Cancelar</a>
                        </center>
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
