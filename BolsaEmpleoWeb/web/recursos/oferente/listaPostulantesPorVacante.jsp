<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-es" lang="es-es" >
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title></title>
        <script src="recursos/js/jquery/jquery.js"></script>

        <script type="text/javascript">
            function makeTableScroll() {
                // Constant retrieved from server-side via JSP
                var maxRows = 6;
                var table = document.getElementById('tablaPostulantes');
                var wrapper = table.parentNode;
                var rowsInTable = table.rows.length;
                var height = 0;
                if (rowsInTable > maxRows) {
                    for (var i = 0; i < maxRows; i++) {
                        height += table.rows[i].clientHeight;
                    }
                    wrapper.style.height = height + "px";
                }
            }
        </script>
    </head>

    <body onload="makeTableScroll();">

        <s:form method="post" enctype="multipart/form-data" action="listaPostulantesPorVacante">
        <font size="5">Postulantes al puesto seleccionado</font>
        <table id="tablaPostulantes">
            <tr>
                <td width=100px>
                    Cedula
                </td>
                <td width=200px>
                    Nombre completo
                </td>
                <td width=100px>
                    Acción
                </td>
            </tr>         
            <c:forEach items="${postulantes}" var="postulante" varStatus="cont">
                <tr>
                    <td> <c:out value="${postulante.cedula}"/> </td>
                    <td> <c:out value="${postulante.nombre}"/></td>
                    <s:url var="url" action="borrarOferenteVacante">
                        <s:param name="idPuesto">${idPuesto}</s:param>
                        <s:param name="value">${postulante.cedula}</s:param>
                    </s:url>
                    <td> <s:a href="%{url}">Borrar</s:a></td>
                </tr>
            </c:forEach> 
            </s:form>
        </table>
    </body>    
</html>
