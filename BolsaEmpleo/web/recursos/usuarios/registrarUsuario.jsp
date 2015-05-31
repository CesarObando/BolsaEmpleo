<%-- 
    Document   : registrarUsuario
    Created on : Jul 18, 2014, 11:31:05 PM
    Author     : ricardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML+RDFa 1.1//EN">
<html lang="es" >
    <head>
        <sj:head jquerytheme="blitzer" jqueryui="true"></sj:head>

            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <link rel="shortcut icon" href="recursos/imagenes/favicon.ico" />
            <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />

            <title>Registrar Usuario | Bolsa de Empleo</title>  

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

                function fun(valor) {
                    var elem = document.getElementById('archivo');
                    var elem2 = document.getElementById('labelCurriculo');

                    if (valor === "Vacante") {
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
                        <c:import url="/recursos/recursosReusables/menuPrincipal.jsp" context="/BolsaEmpleoWeb" />
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
                                    <jsp:include page="login.jsp"/>                                
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

                    <s:if test="hasActionErrors()">
                        <s:actionerror />
                    </s:if>
                    <s:if test="hasActionMessages()">
                        <s:actionmessage />
                    </s:if>
                    <s:form method="post" enctype="multipart/form-data" action="RegistrarUsuarioAction">

                        <c:if test="${sessionScope.usuario == null}" >
                            <s:textfield name="cedula" label="Cedula"/>
                        </c:if>
                        <c:if test="${sessionScope.usuario != null}" >
                            <s:textfield name="cedula" label="Cedula" readonly="true"/>
                        </c:if>

                        <s:textarea name="nombre" label="Nombre"/>
                        <s:textfield name="apellidos" label="Apellidos"/>
                        <s:textfield name="nombreUsuario" label="Nombre Usuario"/>

                        <s:password name="clave" label="Clave"/> 
                        <s:password name="repitaClave" label="Compruebe clave"/> 
                        <c:if test="${sessionScope.usuario != null}" >
                            <s:password name="actualClave" label="Contraseña actual"/> 
                            <s:submit action="cambiarContrasena" value="Cambiar Contraseña"/>
                        </c:if>

                        <s:select label="Provincia" name="provincia"
                                  list="provincias"/>
                        <s:textfield name="ciudad" label="Ciudad"/> 
                        <s:textfield name="direccion" label="Dirección"/> 
                        <s:textfield name="codigoPostal" label="Código Postal"/> 
                        <s:textfield name="email" label="Email"/> 
                        <s:textfield name="telefonoCasa" label="Teléfono Casa"/> 
                        <s:textfield name="telefonoCelular" label="Teléfono Celular"/> 
                        <s:textfield name="fax" label="Fax"/> 
                        <sj:datepicker name="fechaNacimiento" label="Fecha nacimiento"  displayFormat="dd/mm/yy"></sj:datepicker>

                        <c:if test="${sessionScope.usuario == null}" >
                            <s:radio list="radio" label="Tipo de usuario" name="radioUsuario" id="radioUsuario" value="defaultRadio" onchange="fun(this.value)"/>
                        </c:if>
                        <c:if test="${sessionScope.usuario == null}" >
                            <s:label id="labelCurriculo" value="Curriculo"/>
                            <s:file id="archivo" name="archivoCurriculo"/>  
                        </c:if>  

                        <c:if test="${sessionScope.usuario != null}" >
                                <s:label id="labelCurriculo" value="Curriculo"/>
                                <s:file id="archivo" name="archivoCurriculo"/> 
                        </c:if>  

                        <c:if test="${sessionScope.usuario == null}" >
                            <s:submit action="insertarUsuario" key="Aceptar" value="Registrar Datos"/>
                        </c:if>
                        <c:if test="${sessionScope.usuario != null}" >
                            <s:submit action="actualizarUsuario" value="Actualizar"/>
                        </c:if>


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

        <c:if test="${sessionScope.usuario.administrador}">    
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
