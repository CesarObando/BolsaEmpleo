<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Inicio | Bolsa de Empleo</title>  
        <s:head/>
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
                <jsp:include page="../recursosReusables/menuAdministrador.jsp"/>
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
                        <h1 class="h1">Bienvenido/a ${sessionScope.administrador.nombre}</h1><br><br><br>
                        <br><p>Como administrador podrás registrar categorías en la pestaña "Categoría" para que los usuarios las puedan utilizar en el momento de
                            registrar su oferta o servicio y también en sus búsquedas. Además, podrás editarlas y eliminarlas.</p>
                        <p> Por otra parte, en la pestaña "Solicitante", tendrás la oportunidad de buscar a los solicitantes para ver su información básica y la 
                            fecha de su última actualización para que puedan notificarle su situación, o bien eliminarlo. Podrás realizar la misma función para los
                            empleadores en la pestaña "Empleadores".</p>
                        <p>También, podrás buscar ofertas, servicios y solicitudes en sus respectivas pestañas y así, en caso de ser necesario, eliminar esa 
                            información.</p>
                        <p>Además, podrás actualizar tu información como administrador, eliminar tu perfil y también registrar un nuevo administrador. Para esto
                        ve a la pestaña titulada "Administrador".</p>
                        <center>
                            <table id="tablaDeIconos" class="table">
                                <td><a href="http://www.muni-carta.go.cr/bolsa-de-empleo-del-canton.html"><img class="img-responsive" src="../imagenes/IconosRelacionados/MuniCartago.png" /></a></td>
                                <td></td>
                                <td><a href="http://www.mtss.go.cr/"><img class="img-responsive" src="../imagenes/IconosRelacionados/mtss.png" alt="" /></a></td>
                                <td></td>
                                <td><a href="http://www.ina.ac.cr/"><img class="img-responsive" src="../imagenes/IconosRelacionados/ina.png" alt="" /></a></td>
                            </table>
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
