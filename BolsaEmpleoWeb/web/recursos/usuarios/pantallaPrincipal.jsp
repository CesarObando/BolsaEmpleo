<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Inicio | Bolsa de Empleo</title>  
    </head>
    <body onload="if (${sessionScope.sesionCerrada != null}) {
                nobackbutton();
            }
            ;
            cambiarCanton()">
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
                            <s:form action="buscarOfertasSolicitanteProcess" method="post" cssClass="form" name="f1">
                                <s:textfield name="puesto" placeholder="Ingresa el puesto de la oferta a buscar"/>
                                <s:select name="provincia" list="#{'Alajuela':'Alajuela','Cartago':'Cartago','Guanacaste':'Guanacaste','Heredia':'Heredia','Limón':'Limón','Puntarenas':'Puntarenas','San José':'San José'}" headerKey="" headerValue="Selecciona una provincia" onchange="cambiarCanton()"/>
                                <s:select name="canton" list="#{}" headerKey="" headerValue="Selecciona un cantón" />
                                <s:submit method="buscar" value="Buscar"/>
                            </s:form>
                        </center>
                        <center>
                            <table id="tablaDeIconos" class="table">
                                <td><a href="http://www.muni-carta.go.cr/bolsa-de-empleo-del-canton.html"><img class="img-responsive" src="../imagenes/IconosRelacionados/MuniCartago.png" /></a></td>
                                <td></td>
                                <td><a href="http://www.mtss.go.cr/"><img class="img-responsive" src="../imagenes/IconosRelacionados/mtss.png" alt="" /></a></td>
                                <td></td>
                                <td><a href="http://www.ina.ac.cr/"><img class="img-responsive" src="../imagenes/IconosRelacionados/ina.png" alt="" /></a></td>
                            </table>
                        </center>
                        <h6 class="h6">Si deseas publicar tu trabajo independiente registrate como solicitante haciendo click <a href="../usuarios/insertarSolicitante.jsp" style="background: transparent; color: blue">aquí</a> o si ya estás registrado <a href="../usuarios/login.jsp" style="background: transparent">inicia sesión.</a> </h6>
                        <h6 class="h6">Si deseas publicar una oferta profesional registrate como empleador haciendo click <a href="../empresa/registrarEmpleador.jsp" style="background: transparent; color: blue">aquí</a> o si ya estás registrado <a href="../empresa/login.jsp" style="background: transparent">inicia sesión.</a> </h6>
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
