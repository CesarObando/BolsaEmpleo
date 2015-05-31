<%-- 
    Document   : login
    Created on : Oct 6, 2014, 11:49:21 PM
    Author     : ricardo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML+RDFa 1.1//EN">
<html lang="es" >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="shortcut icon" href="recursos/imagenes/favicon.ico" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />

        <title></title>  

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
    </head>
    <body>
        <s:form method="post" enctype="multipart/form-data" action="IniciarSesion">
            <s:if test="hasActionMessages()">
                <s:actionmessage />
            </s:if>
            <s:textfield name="nombreUsuario" placeholder="Nombre Usuario o Correo"/>
            <s:password name="clave" placeholder="Contraseña"/>
            <s:submit action="iniciarSesionUsuario" value="Iniciar Sesion" class="button-submit"/>
        </s:form> 

        <script type="text/javascript">
            // initialise plugins
            jQuery(function() {
                jQuery('ul.sf-menu').superfish();
            });
        </script>

    </body>
</html>
