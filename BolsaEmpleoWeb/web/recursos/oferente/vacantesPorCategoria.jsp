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
                var table = document.getElementById('tablaVacantes');
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

        <font size="5">Vacantes de ${nombreCategoria}</font>

        <table id="tablaVacantes">
            <tr>
                <td width=200px>
                    Descripción
                </td>
                <td width=100px>
                    N. de puestos
                </td>
                <td width=100px>
                    Ver postulantes
                </td>
            </tr>
            <c:forEach items="${vacantes}" var="vacante" varStatus="cont">
                <tr>
                    <td> <c:out value="${vacante.descripcion}"/> </td>
                    <td> <c:out value="${vacante.numeroVacantes}"/></td>
                    <s:url var="urlPostulantes" action="listaPostulantesPorVacante">
                        <s:param name="idPuesto">${vacante.idPuesto}</s:param>
                    </s:url>
                    <c:choose>
                        <c:when test="${vacante.tienePostulantes == true}">
                            <td> <s:a href="%{urlPostulantes}">Ver...</s:a></td> 
                        </c:when> 
                        <c:otherwise>
                            <td>Sin postulantes</td>
                        </c:otherwise>   
                    </c:choose>
                </tr>
            </c:forEach>
        </table> 

    </body>    
</html>
