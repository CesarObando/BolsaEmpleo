<%-- 
    Document   : insertar_usuario
    Created on : Sep 23, 2015, 10:13:30 AM
    Author     : JonathanA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="shortcut icon" href="../imagenes/favicon.ico" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />

        <title>Registrar Usuario | Bolsa de Empleo</title>  

        <link rel="stylesheet" type="text/css" href="../css/system.base.css">
        <link rel="stylesheet" type="text/css" href="../css/system.menus.css">
        <link rel="stylesheet" type="text/css" href="../css/system.messages.css">
        <link rel="stylesheet" type="text/css" href="../css/system.theme.css">

        <!--menu-->
        <link rel="stylesheet" type="text/css" href="../css/superfish.css" media="screen">       
        <link rel="stylesheet" type="text/css" href="../css/superfish-smallscreen.css" media="screen">  

        <link rel="stylesheet" type="text/css" href="../css/omega-text.css">
        <link rel="stylesheet" type="text/css" href="../css/omega-branding.css">
        <link rel="stylesheet" type="text/css" href="../css/omega-menu.css">
        <link rel="stylesheet" type="text/css" href="../css/omega-forms.css">
        <link rel="stylesheet" type="text/css" href="../css/global.css">
        <!--responsive-->
        <style type="text/css" media="all and (min-width: 670px) and (min-device-width: 670px), all and (max-device-width: 1024px) and (min-width: 1024px) and (orientation:landscape)">
            @import url("../css/omega-obas-alpha-default.css");            
            @import url("../css/alpha-default-normal-12.css");            
        </style>
    </head>
    <body>
        <header>
            <div class="clearfix">
                <div id="zone-branding" class="container-12">
                    <div class="grid-12 ">
                        <div class="branding-data">
                            <div class="logo-ucr">
                                <!--<a href="http://www.ucr.ac.cr" target="blank">Universidad de Costa Ric</a>-->
                                <img class="img-responsive" src="../imagenes/logo-ucr.png" alt="" />
                            </div>
                            <div class="logo-img">                   
                                <!--aqui va un logo de la oficina de orientacion o bolsa de empleo-->
                                <!--<a href="http://www.ucr.ac.cr"><img class="img-responsive" src="//placehold.it/130x55&text=Logo" class="img-responsive" alt="Imagen responsive"></a>-->
                                <h3 class="site-name">Bolsa de Empleo</h3>                                    
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="../recursosReusables/menuPrincipal.jsp"/>
            </div>
        </header>
        <section>
            <div id="zone-content" class="clearfix container-12">     
                <aside class="grid-3 region-sidebar-first" id="region-sidebar-first">                            
                    <div class=" block-menu-block-1" id="block-menu-block-1">
                        <div class="content clearfix">                            
                            <ul class="menu">
                                <li class="first collapsed"><a href="http://www.muniparaiso.go.cr/" >¿Quiénes somos?</a></li>
                                <li class="first collapsed"> <a href="../recursos/curriculo/Plantilla.doc">Cómo hacer un Curriculo?</a></li>                                              
                                <li class="leaf"><a href="https://www.facebook.com/recintodeparaiso.ucr">Recinto de Paraiso</a></li>
                                <li class="leaf"><a href="#">Contactenos</a></li>                                            
                            </ul>                                
                        </div>
                    </div>
                    <section class="block block-menu block-menu-redes-sociales block-menu-menu-redes-sociales odd" id="block-menu-menu-redes-sociales">
                        <div class="block-inner clearfix">
                            <h2 class="block-title">Síguenos en facebook</h2>
                            <div class="content clearfix">
                                <div class="fb-like-box" data-href="https://www.facebook.com/pages/Oficina-Orientaci%C3%B3n-sitio-oficial/188921904531554?fref=ts" data-width="235" data-colorscheme="light" data-show-faces="true" data-header="false" data-stream="true" data-show-border="false"></div>
                            </div>
                        </div>
                    </section>
                </aside>
                <aside class="grid-3 region" id="region-sidebar-second">
                    <div class="grid-9 region-content" id="region-content">
                        <s:if test="hasActionErrors()">
                            <s:actionerror />
                        </s:if>
                        <s:if test="hasActionMessages()">
                            <s:actionmessage />
                        </s:if>
                        
                        <s:form method="post" action="editarSolicitanteProcess">
                            <s:textfield name="id" label="Id" readonly="true"/>
                            <s:textfield name="cedula" label="Cedula" readonly="true"/>
                            <s:textfield name="nombre" label="Nombre"/>
                            <s:textfield name="apellidos" label="Apellidos"/>
                            <s:textfield name="username" label="Nombre Usuario" readonly="true"/>
                            <s:password name="password" label="Clave"/> 
                            <img src="<s:url action="getImagen" namespace="/"><s:param name="idImagen">${id}</s:param></s:url>" width="100" height="100" />
                            <s:file name="archivoImagen" label="Seleccione una foto para su perfil"/>
                            <s:textfield name="edad" label="Edad"/>
                            <s:textfield name="sexo" label="Sexo" readonly="true"/>
                            <s:textfield name="escolaridad" label="Escolaridad"/>
                            <s:textfield name="titulos" label="Titulos"/>
                            <s:textfield name="experienciaLaboral" label="Experiencia Laboral"/>
                            <s:textarea name="detalleExperienciaLaboral" label="Detalle Experiencia Laboral"/>
                            <s:textfield name="telefonoFijo" label="Teléfono Casa"/> 
                            <s:textfield name="telefonoMovil" label="Teléfono Celular"/>
                            <s:textfield name="correo" label="Email"/>
                            <s:textfield name="idiomas" label="Idiomas que domina"/>
                            <s:submit action="editarSolicitanteProcess" value="Editar Solicitante"/>
                        </s:form>
                    </div>
                </aside> 
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
                                    <a href="/" rel="home" title="Oficina de Orientación" class="active"><img src="../imagenes/menu-bg.png" alt="Oficina de Orientación" id="logo" /></a> 
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
                                                    <span class="views-label views-label-changed">Última Actualización:</span><span class="field-content">23/9/2015 - 8:57</span>  
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

        <script type="text/javascript" src="../js/jquery.js"></script>
        <script type="text/javascript" src="../js/hoverIntent.js"></script>
        <script type="text/javascript" src="../js/superfish.js"></script>                
        <script type="text/javascript" src="../js/supersubs.js"></script>

        <script type="text/javascript">
            // initialise plugins
            jQuery(function () {
                jQuery('ul.sf-menu').superfish();
            });
        </script>


    </body>
</html>
