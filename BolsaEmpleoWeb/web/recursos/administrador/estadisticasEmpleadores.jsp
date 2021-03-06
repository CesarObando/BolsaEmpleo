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
                        <center>
                            <s:form action="verEstadisticasEmpleadoresProcess" method="post" cssClass="form">
                                <s:textfield name="cedula" label="Cédula"/>
                                <s:textfield name="nombre" label="Nombre"/>
                                <s:textfield name="apellidos" label="Apellidos"/>
                                <s:submit method="buscar" value="Buscar"/>
                            </s:form>
                            <table id="mytable" class="table">

                                <s:if test="%{empleadores.isEmpty()}">
                                    <h2>No hay resultados que mostrar</h2>
                                </s:if>

                                <s:else>
                                    <thead class="th">
                                    <th>Cédula</th>
                                    <th>Nombre</th>
                                    <th>Apellidos</th>
                                    <th>Nombre de empresa</th>
                                    <th>Ultima Actualización de datos</th>
                                    <th></th>
                                    <th>Eliminar</th>
                                    </thead>
                                    <tbody class="td">
                                        <s:iterator value="empleadores" var="empleadorActual">
                                            <tr class="tr">
                                                <td><s:property value="#empleadorActual.cedula"/></td>
                                                <td><s:property value="#empleadorActual.nombre"/></td>
                                                <td><s:property value="#empleadorActual.apellidos"/></td>
                                                <td><s:property value="#empleadorActual.nombreEmpresa"/></td>
                                                <td><s:property value="#empleadorActual.direccion"/></td>
                                                <td>
                                                    <s:url action="enviarCorreoEmpresaProcess" var="url">
                                                        <s:param name="correo" value="#empleadorActual.correo"/>
                                                    </s:url>
                                                    <a href='<s:property value="#url" />'>Notificar</a>
                                                </td>
                                                <td><p data-placement="top" data-toggle="tooltip" title="Delete">
                                                        <s:url action="eliminarEmpleadorAdministradorProcess" var="url">
                                                            <s:param name="id" value="#empleadorActual.id"/>
                                                        </s:url>
                                                        <a href='<s:property value="#url" />' onclick="return confirmBox('¿Desea eliminar al empleador?');">  <button style="background-color: transparent"><img src="../imagenes/eliminar.png"/></button> </a>
                                                    </p>
                                                </td>
                                            </tr>
                                        </s:iterator>
                                    </s:else>
                                <div>
                                </div       
                                </tbody>
                            </table> 
                            <div id="paginador" aling="center" class="div" ></div>  
                            <script type="text/javascript">
                                var p = new Paginador(
                                        document.getElementById('paginador'),
                                        document.getElementById('mytable')
                                        );
                                p.Mostrar();
                            </script>
                        </center>
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
