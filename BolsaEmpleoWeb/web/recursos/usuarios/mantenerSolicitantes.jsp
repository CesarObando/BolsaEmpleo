<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Mantener Solicitantes | Bolsa de Empleo</title>  
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
                <jsp:include page="../recursosReusables/menuAdministrador.jsp"/>
            </div>
        </header>
        <section>
            <div id="zone-content" class="clearfix container-12">  
                <jsp:include page="../recursosReusables/section.jsp"/>
                <aside class="grid-3 region" id="region-sidebar-second">
                    <div class="grid-9 region-content" id="region-content">

                        <s:form action="buscarSolicitantesProcess" method="get">
                            <s:textfield name="cedula" label="Cédula"/>
                            <s:textfield name="nombre" label="Nombre"/>
                            <s:textfield name="apellidos" label="Apellidos"/>
                            <s:submit method="buscar" value="Buscar"/>
                        </s:form>
                        <table id="mytable" class="table table-bordred table-striped">

                            <s:if test="%{solicitantes.isEmpty()}">
                                <h2>No hay resultados que mostrar</h2>
                            </s:if>

                            <s:else>
                                <thead>
                                <th>Cédula</th>
                                <th>Nombre</th>
                                <th>Apellidos</th>
                                <th>Eliminar</th>
                                </thead>
                                <tbody>
                                    <s:iterator value="solicitantes" var="solicitanteActual">
                                        <tr>
                                            <td><s:property value="#solicitanteActual.cedula"/></td>
                                            <td><s:property value="#solicitanteActual.nombre"/></td>
                                            <td><s:property value="#solicitanteActual.apellidos"/></td>

                                            <td><p data-placement="top" data-toggle="tooltip" title="Delete">
                                                    <s:url action="eliminarSolicitanteAdministradorProcess" var="url">
                                                        <s:param name="id" value="#solicitanteActual.id"/>
                                                    </s:url>
                                                    <a href='<s:property value="#url" />' onclick="return confirmBox();">  <button style="background-color: white"><img src="../imagenes/eliminar.png"/> </button> </a>
                                                </p>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </s:else>
                            <div>
                            </div       
                            </tbody>
                        </table>     
                        <script>
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea eliminar al solicitante?");
                                if (answer == true) {
                                    return true;
                                }
                                else {
                                    return false;
                                }
                            }
                        </script>

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
