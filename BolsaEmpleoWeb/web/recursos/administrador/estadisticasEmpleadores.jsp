<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Estadísticas Empleadores | Bolsa de Empleo</title>  
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
                        <s:form action="verEstadisticasEmpleadoresProcess" method="post" cssClass="form">
                            <s:textfield name="cedula" label="Cédula"/>
                            <s:textfield name="nombre" label="Nombre"/>
                            <s:textfield name="apellidos" label="Apellidos"/>
                            <s:submit method="buscar" value="Buscar"/>
                        </s:form>
                        <table id="mytable" class="table table-bordred table-striped">

                            <s:if test="%{empleadores.isEmpty()}">
                                <h2>No hay resultados que mostrar</h2>
                            </s:if>

                            <s:else>
                                <thead>
                                <th>Cédula</th>
                                <th>Nombre</th>
                                <th>Apellidos</th>
                                <th>Nombre de empresa</th>
                                <th>Ultima Actualización de datos</th>
                                <th></th>
                                </thead>
                                <tbody>
                                    <s:iterator value="empleadores" var="empleadorActual">
                                        <tr>
                                            <td><s:property value="#empleadorActual.cedula"/></td>
                                            <td><s:property value="#empleadorActual.nombre"/></td>
                                            <td><s:property value="#empleadorActual.apellidos"/></td>
                                            <td><s:property value="#empleadorActual.nombreEmpresa"/></td>
                                            <td><s:property value="#empleadorActual.ultimaActualizacion"/></td>
                                            <td><a href="mailto:<s:property value="#empleadorActual.correo"/>?subject=Actualizar datos de su perfil de usuario.&body='Estimado usuario, se le solicita que por favor renueve los datos de su cuenta en nuestro sistema. Gracias'">Notificar</a></td>
                                        </tr>
                                    </s:iterator>
                                </s:else>
                            <div>
                            </div       
                            </tbody>
                        </table>     
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
