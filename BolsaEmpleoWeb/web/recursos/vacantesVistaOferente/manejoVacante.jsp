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

            <title>Crear Vacante | Bolsa de Empleo</title>  

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
            <link rel="stylesheet" type="text/css" href="recursos/css/datetimepicker.css">
            <!--responsive-->
            <style type="text/css" media="all and (min-width: 670px) and (min-device-width: 670px), all and (max-device-width: 1024px) and (min-width: 1024px) and (orientation:landscape)">
                @import url("recursos/css/omega-obas-alpha-default.css");            
                @import url("recursos/css/alpha-default-normal-12.css");            
            </style>
            <script type="text/javascript">
                function isNumber(evt) {
                    evt = (evt) ? evt : window.event;
                    var charCode = (evt.which) ? evt.which : evt.keyCode;
                    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                        return false;
                    }
                    return true;
                }
                function elegirVacante(value) {
                    window.location = "modificarVacante?vacante.idPuesto=" + value; //or you can submit a form from here or make an ajax call
                }
                function cambiarCiudades() {
                    var val = document.getElementById('provincia').selectedOptions[0].text;
                    switch (val) {
                        case "San José":
                            var lista = ["No especificar", "San José", "Escazú", "Desamparados", "Puriscal", "Tarrazú", "Aserrí", "Mora", "Goicoechea",
                                "Santa Ana", "Alajuelita", "Vázquez de Coronado", "Acosta", "Tibás", "Moravia",
                                "Montes de Oca", "Turrubares", "Dota", "Curridabat", "Pérez Zeledón", "León Cortés"];
                            break;
                        case "Alajuela":
                            var lista = ["No especificar", "Alajuela", "San Ramón", "Grecia", "San Mateo", "Atenas", "Naranjo", "Palmares", "Poás",
                                "Orotina", "San Carlos", "Zarcero", "Valverde Vega", "Upala", "Los Chiles", "Guatuso"];
                            break;
                        case "Heredia":
                            var lista = ["No especificar", "Heredia", "Barva", "Santo Domingo", "Santa Barbara", "San Rafael", "San Isidro", "Belén",
                                "Flores", "San Pablo", "Sarapiquí"];
                            break;
                        case "Cartago":
                            var lista = ["No especificar", "Cartago", "Paraíso", "La Unión", "Jiménez", "Turrialba", "Alvarado", "Oreamuno", "El Guarco"];
                            break;
                        case "Limón":
                            var lista = ["Limón", "Pococí", "Siquirres", "Talamanca", "Matina", "Guácimo"];
                            break;
                        case "Puntarenas":
                            var lista = ["No especificar", "Puntarenas", "Esparza", "Buenos Aires", "Montes de Oro", "Osa", "Aguirre", "Golfito",
                                "Coto Brus", "Parrita", "Corredores", "Garabito"];
                            break;
                        case "No especificar":
                            var lista = ["No especificar"];
                            break;
                    }
                    document.getElementById('ciudad').options.length = 0;
                    var sel = document.getElementById('ciudad');
                    for (var i = 0; i < lista.length; i++) {
                        var opt = document.createElement('option');
                        opt.innerHTML = lista[i];
                        opt.value = lista[i];
                        sel.appendChild(opt);
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
                    <c:if test="${sessionScope.usuario!=null}">
                        <c:if test="${empty sessionScope.usuario}">
                            <section class="block block-block block-8 block-block-8 even" id="block-block-8">
                                <div class="block-inner clearfix">
                                    <h2 class="block-title">Iniciar Sesión</h2>
                                    <div class="content clearfix">      
                                        <c:import url="/recursos/usuarios/login.jsp" context="/BolsaEmpleoWeb" />
                                    </div>
                                </div>
                            </section> 
                        </c:if> 
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


                    <s:form method="post" enctype="multipart/form-data" action="VacanteAction">
                        <s:select name="vacante.idPuesto" label="Puestos publicados " 
                                  headerKey="-1" headerValue="--- Nueva vacante ---" 
                                  list="vacantesEmpleador" listKey="idPuesto" listValue="descripcion"
                                  onchange="elegirVacante(this.value)"/>
                        <s:textfield name="vacante.descripcion" label="Descripción "/>                          
                        <s:textfield name="vacante.requerimientos" label="Requerimientos "/>
                        <s:textfield name="vacante.numeroVacantes" label="Num. de vacantes " onkeypress="return isNumber(event)"/>
                        <s:textfield name="vacante.diasLaborar" label="Num. días " onkeypress="return isNumber(event)"/>
                        <s:textfield name="horaEntradaString" id="horaEntrada" label="Hora de entrada "/>
                        <s:textfield name="horaSalidaString" id="horaSalida" label="Hora de salida "/>
                        <s:select list="provincias" id="provincia" name="vacante.provincia" label="Provincia " onchange="cambiarCiudades()"/>
                        <s:select list="ciudades" id="ciudad" name="vacante.ciudad" label="Ciudad "/>
                        <s:textfield name="fechaCreacionString" id="fechaCreacion" label="Fecha de creación "/>
                        <s:select list="categoriasVacante" name="vacante.categoria.idCategoria" listKey="idCategoria" listValue="nombre" label="Categoría "/>
                        <s:textfield name="vacante.sueldo" label="Salario " onkeypress="return isNumber(event)"/>
                        <s:submit action="insertarVacante" value="Ingresar"/>
                        <s:submit action="actualizarVacante" value="Actualizar"/>
                        <s:submit action="borrarVacante" value="Borrar"/>
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

        <c:if test="${sessionScope.empleador!=null}">
            <c:if test="${!empty sessionScope.empleador}">
                <a href="PrincipalEmpleador">Empleador</a><br>
            </c:if>
        </c:if>
        <c:if test="${sessionScope.usuario.administrador!=null}">
            <c:if test="${sessionScope.usuario.administrador}">
                <a href="PrincipalAdministrador">Administrador</a><br>
            </c:if>
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

        <script type="text/javascript" src="recursos/js/hoverIntent.js"></script>
        <script type="text/javascript" src="recursos/js/superfish.js"></script>                
        <script type="text/javascript" src="recursos/js/supersubs.js"></script>
        <script type="text/javascript" src="recursos/js/jquery.js"></script>
        <script type="text/javascript" src="recursos/js/datetimepicker.js"></script>
        <script type="text/javascript">
            $('#horaEntrada').datetimepicker({
                datepicker: false,
                format: 'H:i',
                step: 5
            });
            $('#horaSalida').datetimepicker({
                datepicker: false,
                format: 'H:i',
                step: 5
            });
            $('#fechaCreacion').datetimepicker({
                format: 'd-m-Y',
                timepicker: false,
                lang: 'es',
                todayButton: true
            });
            function soloNumeros(e)
            {
                var numeroTecla = window.event ? window.event.keyCode : e.which;
                if ((numeroTecla === 8) || (numeroTecla === 46))
                    return true;

                return /\d/.test(String.fromCharCode(numeroTecla));
            }
        </script>
        <script type="text/javascript">
            // initialise plugins
            jQuery(function () {
                jQuery('ul.sf-menu').superfish();
            });
        </script>

    </body>
</html>

