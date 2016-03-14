<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Inicio | Bolsa de Empleo</title>  
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
                        <h1 class="h1">Bienvenido/a ${sessionScope.solicitante.nombre}</h1><br><br><br>
                        <br><p>Como solicitante podrás buscar ofertas de trabajo que se han publicado, marcarlas como favoritas y aplicar a las mismas para que 
                            los empleadores puedan visualizar tu información y tengan la oportunidad de contactarte. Para esto ve a la pestaña con el nombre de
                            "Ofertas".</p>
                        <p>También, tendrás la oportunidad de administrar tus solicitudes permitiéndote eliminarlas en la pestaña "Eliminar Solicitudes" en caso
                            de que ya no quieras aplicar a una oferta.</p>
                        <p>Por otra parte, en la pestaña titulada "Trabajos independientes", podrás publicar y ofrecer tus servicios no profesionales para que los
                            empleadores interesados tengan en cuenta tus  como trabajador independiente. Además, podrás editar tus publicaciones y eliminarlas. 
                            Adicionalmente, podrás buscar los servicios que hayan publicado otros usuarios en caso de tengas interés de visualizarlos. </p>
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
