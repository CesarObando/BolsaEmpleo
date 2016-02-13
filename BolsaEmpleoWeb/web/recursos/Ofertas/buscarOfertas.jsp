<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Buscar Ofertas | Bolsa de Empleo</title>  
    </head>
    <body onload="cambiarCanton()">
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
                            <s:form action="buscarOfertasProcess" cssClass="form" name="f1">
                                <s:textfield name="puesto" placeholder="Ingresa el puesto de la oferta a buscar"/>
                                <s:select name="categoria.id" list="categorias" listKey="id" listValue="nombre" headerValue="Seleccione una categoría" headerKey="-1"/>
                                <s:select name="provincia" list="#{'Alajuela':'Alajuela','Cartago':'Cartago','Guanacaste':'Guanacaste','Heredia':'Heredia','Limón':'Limón','Puntarenas':'Puntarenas','San José':'San José'}" headerKey="" headerValue="Selecciona una provincia" onchange="cambiarCanton()"/>
                                <s:select name="canton" list="#{}" headerKey="" headerValue="Selecciona un cantón" />
                                <s:submit method="buscar" value="Buscar"/>
                            </s:form>
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
