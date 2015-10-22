<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/struts-tags" prefix="s"%>


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-es" lang="es-es" >
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="robots" content="@@(content)@@" />
        <meta name="keywords" content="@@(content)@@" />
        <meta name="description" content="" />
        <title></title>
        <link rel="stylesheet" type="text/css" href="recursos/css/datetimepicker.css">
        <script src="recursos/js/jquery.js"></script>
        <script type="text/javascript">
            function elegirOferente(value) {
               window.location = "actualizarInfoOferente?cedul=" + value;
            }
        </script>
    </head>

    <body>    				               
        <s:form method="post" enctype="multipart/form-data" action="listaPostulantes">
            <s:select name="cedul" label="Oferentes " 
                      headerKey="-1" headerValue="--- Lista de oferentes ---" 
                      list="postulantes" listKey="cedula" listValue="nombre"
                      onchange="elegirOferente(this.value)"/>
            <s:textfield name="oferente.cedula" label="Cédula "/>
            <s:textfield name="oferente.nombre" label="Nombre "/>
            <s:textfield name="oferente.apellidos" label="Apellidos "/>
            <s:textfield name="oferente.provincia" label="Provincia "/>
            <s:textfield name="oferente.ciudad" label="Ciudad "/>
            <s:textfield name="oferente.direccion" label="Dirección "/>
            <s:textfield name="oferente.codigoPostal" label="Cód. Postal " onkeypress="return soloNumeros(event);"/>
            <s:textfield name="oferente.email" label="E-mail "/>
            <s:textfield name="oferente.telefonoCasa" label="Tél. Hogar " onkeypress="return soloNumeros(event);"/>
            <s:textfield name="oferente.telefonoCelular" label="Tél celular " onkeypress="return soloNumeros(event);"/>
            <s:textfield name="oferente.fax" label="Fax " onkeypress="return soloNumeros(event);"/>
            <s:textfield name="fechaNacimientoString" id="fechaNacimiento" label="Fecha de Nacimiento "/>
            <s:submit action="borrarOferente" value="Borrar"/> 
        </s:form>          
        <script type="text/javascript" src="recursos/js/jquery.js"></script>
        <script type="text/javascript" src="recursos/js/datetimepicker.js"></script>
        <script type="text/javascript">
            $('#fechaNacimiento').datetimepicker({
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
    </body>

</html>