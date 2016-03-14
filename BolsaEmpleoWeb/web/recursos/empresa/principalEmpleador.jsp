<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Principal Empleador | Bolsa de Empleo</title>  
    </head>
    <body>
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
                            <h1 class="h1">Bienvenido/a ${sessionScope.empleador.nombre}</h1><br><br><br>
                        <br><p>Como empleador podrás publicar tus ofertas de trabajo para que otros usuarios puedan visualizarlas y aplicar a las mismas. Podrás
                            editar tus ofertas y eliminarlas. Además, tendrás la oportunidad de ver las personas que han hecho una solicitud para tu oferta y así 
                            marcarlas como favoritas o descartarlas. Para acceder a estas funcionalidades ve a la pestaña con el nombre de "Ofertas".</p>
                        <p>Por otra parte, en la pestaña titulada "Trabajos independientes", podrás buscar servicios no profesionales que ofrecen otros usuarios
                            del sistema para que los empleadores puedan contactarlos.</p>
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
