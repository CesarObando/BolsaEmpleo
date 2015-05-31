<%-- 
    Document   : areaEspecialidad
    Created on : Jul 25, 2014, 1:16:06 AM
    Author     : ricardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>

<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML+RDFa 1.1//EN">
<html lang="es" >
    <head>
        <sj:head jquerytheme="blitzer" jqueryui="true"></sj:head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <link rel="shortcut icon" href="recursos/imagenes/favicon.ico" />
            <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />

            <title>Especialidad | Bolsa de Empleo</title>  

            <link rel="stylesheet" type="text/css" href="recursos/css/system.base.css">
            <link rel="stylesheet" type="text/css" href="recursos/css/system.menus.css">
            <link rel="stylesheet" type="text/css" href="recursos/css/system.messages.css">
            <link rel="stylesheet" type="text/css" href="recursos/css/system.theme.css">

            <!--menu-->
            <link rel="stylesheet" type="text/css" href="recursos/css/superfish.css" media="screen">       
            <link rel="stylesheet" type="text/css" href="recursos/css/superfish-smallscreen.css" media="screen">  

            <link rel="stylesheet" type="text/css" href="recursos/css/omega-text.css">
            <link rel="stylesheet" type="text/css" href="recursos/css/omega-branding.css">
            <link rel="stylesheet" type="text/css" href="recursos/css/omega-menu.css">
            <link rel="stylesheet" type="text/css" href="recursos/css/omega-forms.css">
            <link rel="stylesheet" type="text/css" href="recursos/css/global.css">
            <!--responsive-->
            <style type="text/css" media="all and (min-width: 670px) and (min-device-width: 670px), all and (max-device-width: 1024px) and (min-width: 1024px) and (orientation:landscape)">
                @import url("recursos/css/omega-obas-alpha-default.css");            
                @import url("recursos/css/alpha-default-normal-12.css");            
            </style>
            <script type="text/javascript">

                window.addEventListener("load", function () {
                    document.getElementById('tfNivelEst').style.display = 'none';
                    document.getElementById('btnNivelEst').style.display = 'none';
                    document.getElementById('tfInstEst').style.display = 'none';
                    document.getElementById('btnInstEst').style.display = 'none';
                    document.getElementById('tfAreaEsp').style.display = 'none';
                    document.getElementById('btnAreaEsp').style.display = 'none';
                });

                function select1(valor) {

                    var elem = document.getElementById('tfNivelEst');
                    var elem2 = document.getElementById('btnNivelEst');

                    if (valor === "-1") {
                        elem.style.display = 'block';
                        elem2.style.display = 'block';
                    }
                    else {
                        elem.style.display = 'none';
                        elem2.style.display = 'none';
                    }
                }

                function select2(valor) {
                    var elem = document.getElementById('tfInstEst');
                    var elem2 = document.getElementById('btnInstEst');

                    if (valor === "-1") {
                        elem.style.display = 'block';
                        elem2.style.display = 'block';
                    }
                    else {
                        elem.style.display = 'none';
                        elem2.style.display = 'none';
                    }
                }

                function select3(valor) {
                    var elem = document.getElementById('tfAreaEsp');
                    var elem2 = document.getElementById('btnAreaEsp');

                    if (valor === "-1") {
                        elem.style.display = 'block';
                        elem2.style.display = 'block';
                    }
                    else {
                        elem.style.display = 'none';
                        elem2.style.display = 'none';
                    }
                }
            </script>

        </head>
        <body>
            <header>
                <div class="clearfix">
                    <div id="zone-branding" class="container-12">
                        <div class="grid-12 ">
                            <div class="branding-data">
                                <div class="logo-ucr">
                                    <!--<a href="http://www.ucr.ac.cr" target="blank">Universidad de Costa Ric</a>-->
                                    <img class="img-responsive" src="recursos/imagenes/logo-ucr.png" alt="" />
                                </div>
                                <div class="logo-img">                   
                                    <!--aqui va un logo de la oficina de orientacion o bolsa de empleo-->
                                    <!--<a href="http://www.ucr.ac.cr"><img class="img-responsive" src="//placehold.it/130x55&text=Logo" class="img-responsive" alt="Imagen responsive"></a>-->
                                    <h3 class="site-name">Bolsa de Empleo</h3>                                    
                                </div>
                            </div>
                        </div>
                    </div>


                <c:if test="${sessionScope.usuario == null}" > 
                    <c:if test="${empty sessionScope.usuario}">
                        <jsp:include page="../recursosReusables/menuPrincipal.jsp"/>  
                    </c:if>
                </c:if>
                <c:if test="${sessionScope.empleador != null}" > 
                    <c:if test="${!empty sessionScope.empleador}">
                        <jsp:include page="../recursosReusables/menuEmpleador.jsp"/>  
                    </c:if>
                </c:if>
                <c:if test="${sessionScope.oferente != null}" > 
                    <c:if test="${!empty sessionScope.oferente}">
                        <jsp:include page="../recursosReusables/menuOferente.jsp"/>  
                    </c:if>
                </c:if>
                <c:if test="${sessionScope.administrador != null}" > 
                    <c:if test="${sessionScope.usuario.administrador}">
                        <jsp:include page="../recursosReusables/menuAdministrador.jsp"/>  
                    </c:if>
                </c:if>   


            </div>
        </header>    
        <section >
            <div id="zone-content" class="clearfix container-12">    
                <aside class="grid-3 region-sidebar-first" id="region-sidebar-first">                            

                    <div class=" block-menu-block-1" id="block-menu-block-1">
                        <div class="content clearfix">                            
                            <ul class="menu">
                                <li class="first collapsed"><a href="#" >¿Quiénes somos?</a></li>
                                <li class="first collapsed"> <a href="Curriculo">Cómo hacer un Curriculo?</a></li>                                              
                                <li class="leaf"><a href="#">Recinto de Paraiso</a></li>
                                <li class="leaf"><a href="#">Contactenos</a></li>                                
                            </ul>                                
                        </div>
                    </div>  
                    <!--si no existe un usuario en session lo muestra-->
                    <c:if test="${empty sessionScope.usuario}">
                        <section class="block block-block block-8 block-block-8 even" id="block-block-8">
                            <div class="block-inner clearfix">
                                <h2 class="block-title">Iniciar Sesión</h2>
                                <div class="content clearfix">
                                    <jsp:include page="usuarios/login.jsp"/>                                
                                </div>
                            </div>
                        </section> 
                    </c:if>                  
                    <section class="block block-menu block-menu-redes-sociales block-menu-menu-redes-sociales odd" id="block-menu-menu-redes-sociales">
                        <div class="block-inner clearfix">
                            <h2 class="block-title">Síguenos en facebook</h2>
                            <div class="content clearfix">
                                <div class="fb-like-box" data-href="https://www.facebook.com/pages/Oficina-Orientaci%C3%B3n-sitio-oficial/188921904531554?fref=ts" data-width="235" data-colorscheme="light" data-show-faces="true" data-header="false" data-stream="true" data-show-border="false"></div>
                            </div>
                        </div>
                    </section>
                </aside>

                <div class="grid-9 region-content" id="region-content">

                    <s:if test="hasActionMessages()">
                        <s:actionmessage />
                    </s:if>
                    <s:form method="post" enctype="multipart/form-data" action="EspecialidadesOferente">

                        <s:textfield name="nombreTitulo" label="Nombre título"/>
                        <sj:datepicker name="fechaInicio" label="Fecha Inicio"  displayFormat="dd/mm/yy"></sj:datepicker>
                        <sj:datepicker name="fechaFin" label="Fecha Fin"  displayFormat="dd/mm/yy"></sj:datepicker>
                        <s:label value="En blanco para indicar que todavía está en dicho estudio"/>


                        <s:select label="Nivel estudio" list="nivelesEstudio" name="nivelEstudioV" 
                                  listKey="idNivelEstudio" listValue="descripcion" onchange="select1(this.value)"/>
                        <s:textfield placeholder="%{getText('Nuevo nivel estudio')}" name="nuevoNivelEstudio" id="tfNivelEst"/> <s:submit action="insertarNivelEstudio" value="+" id="btnNivelEst"/>

                        <s:select label="Institución" list="institucionesEstudio" name="institucionEstudioV"
                                  listKey="idInstitucion" listValue="nombre" onchange="select2(this.value)"/>
                        <s:textfield  placeholder="%{getText('Nueva Institución')}"  name="nuevoInstitucionEstudio" id="tfInstEst"/> <s:submit action="insertarInstitucionEstudio" value="+" id="btnInstEst"/>

                        <s:select label="Area especialidad" list="areasEspecialidad" name="areaEspecialidadV" 
                                  listKey="idAreaEspecialidad" listValue="descripcion" onchange="select3(this.value)"/> 
                        <s:textfield placeholder="%{getText('Nueva area especialidad')}"  name="nuevoAreaEspecialidad" id="tfAreaEsp"/> <s:submit action="insertarAreaEspecialidad" value="+" id="btnAreaEsp"/>


                        <s:submit action="agregarEspecialidad" value="Agregar especialidad"/>

                        <table id="tablaEspecialidades">
                            <tr>
                                <td>F. Inicio</td>
                                <td>F. Fin</td>
                                <td>Nombre Título</td>
                                <td>Institucion Estudio</td>
                                <td>Nivel Estudio</td>
                                <td>Area Especialidad</td>
                                <td>Eliminar</td>
                            </tr>
                            <s:iterator value="oferente.especialidades">
                                <tr>
                                    <td><s:property value="fechaInicio"/></td>
                                    <td><s:property value="fechaFin"/></td>
                                    <td><s:property value="nombreTitulo"/></td>
                                    <td><s:property value="institucionEstudio"/></td>  
                                    <td><s:property value="nivelEstudio"/></td> 
                                    <td><s:property value="areaEspecialidad"/></td> 
                                    <td><a href='quitarEspecialidad?value=<s:property value="idEspecialidadOferente"/>'><s:property value="idEspecialidadOferente"/></a></td> 
                                </tr>
                            </s:iterator>
                        </table> 
                    </s:form>


                </div>                          
            </div>            
        </section>    
        <footer >
            <div id="zone-footer-wrapper" class="zone-wrapper zone-footer-wrapper clearfix">  
                <div id="zone-footer" class="zone zone-footer clearfix container-12">
                    <div class="grid-12 region region-footer-first" id="region-footer-first">
                        <div class="region-inner region-footer-first-inner">
                            <div class="block block-block block-6 block-block-6 odd block-without-title" id="block-block-6">
                                <div class="content clearfix">                                        
                                    © 2014 Oficina de Orientación, UCR |&nbsp;<a href="http://www.orientacion.ucr.ac.cr/">http://www.orientacion.ucr.ac.cr/</a>&nbsp;| teléfono 2511- 1970</p>
                                    <a href="/" rel="home" title="Oficina de Orientación" class="active"><img src="recursos/imagenes/menu-bg.png" alt="Oficina de Orientación" id="logo" /></a> 
                                    <hgroup class="site-name-slogan">      
                                        <h1 class="site-name"><a href="/" title="Inicio" class="active">Oficina de Orientación</a></h1>
                                        <h6 class="site-slogan">Al servicio de la comunidad estudiantil</h6>
                                    </hgroup>
                                </div>
                            </div>
                        </div><div class="block block-views block-4303892770851f3336c5ca7be219abf2 block-views-4303892770851f3336c5ca7be219abf2 even block-without-title" id="block-views-4303892770851f3336c5ca7be219abf2">
                            <div class="block-inner clearfix">
                                <div class="content clearfix">
                                    <div class="view view-display-id-block_ultima_actualizacion view-dom-id-8b4231c76c5966c475e85e52ab89b57e">
                                        <div class="view-content">
                                            <div class="views-row views-row-1 views-row-odd views-row-first views-row-last">
                                                <div class="views-field views-field-changed">    
                                                    <span class="views-label views-label-changed">Última Actualización:</span><span class="field-content">10/10/2014 - 23:35</span>  
                                                </div>  
                                            </div>
                                        </div>
                                    </div>   
                                </div>
                            </div>
                        </div>  
                    </div>
                </div>
            </div>                
        </footer>  

        <c:if test="${!empty sessionScope.empleador}">
            <a href="PrincipalEmpleador">Empleador</a><br>
        </c:if>
        <c:if test="${sessionScope.usuario.administrador}">
            <a href="PrincipalAdministrador">Administrador</a><br>
        </c:if>

        <!--plugin facebook-->
        <div id="fb-root"></div>
        <script>(function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/es_LA/sdk.js#xfbml=1&version=v2.0";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>

        <script type="text/javascript" src="recursos/js/jquery.js"></script>
        <script type="text/javascript" src="recursos/js/hoverIntent.js"></script>
        <script type="text/javascript" src="recursos/js/superfish.js"></script>                
        <script type="text/javascript" src="recursos/js/supersubs.js"></script>

        <script type="text/javascript">
            // initialise plugins
            jQuery(function () {
                jQuery('ul.sf-menu').superfish();
            });
        </script>

    </body>
</html>
