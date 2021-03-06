<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Mantener Ofertas | Bolsa de Empleo</title>  
    </head>
    <body onload="cambiarCanton()">
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
                            <s:form action="buscarOfertasAdministradorProcess" cssClass="form" name="f1">
                                <s:textfield name="puesto" placeholder="Ingresa el puesto a buscar"/>
                                <s:select name="categoria.id" list="categorias" listKey="id" listValue="nombre" headerValue="Seleccione una categoría" headerKey="-1"/>
                                <s:select name="provincia" onchange="cambiarCanton()" list="#{'Alajuela':'Alajuela','Cartago':'Cartago','Guanacaste':'Guanacaste','Heredia':'Heredia','Limón':'Limón','Puntarenas':'Puntarenas','San José':'San José'}" headerKey="" headerValue="Selecciona una provincia"/>
                                <s:select name="canton" list="#{}" headerKey="" headerValue="Selecciona un cantón" />
                                <s:submit method="buscar" value="Buscar" action="buscarOfertasAdministradorProcess"/>
                            </s:form>
                            <table id="mytable" class="table">

                                <s:if test="%{ofertas.isEmpty()}">
                                    <h2>No hay resultados que mostrar</h2>
                                </s:if>

                                <s:else>
                                    <thead class="th">
                                    <th>Puesto</th>
                                    <th>Empleador</th>
                                    <th>Apellidos</th>
                                    <th>Empresa</th>
                                    <th>Eliminar</th>
                                    </thead>
                                    <tbody class="td">
                                        <s:iterator value="ofertas" var="ofertaActual">
                                            <tr class="tr">
                                                <td><s:property value="#ofertaActual.puesto"/></td>
                                                <td><s:property value="#ofertaActual.empleador.nombre"/></td>
                                                <td><s:property value="#ofertaActual.empleador.apellidos"/></td>
                                                <td><s:property value="#ofertaActual.empleador.nombreEmpresa"/></td>
                                                <td><p data-placement="top" data-toggle="tooltip" title="Delete">
                                                        <s:url action="eliminarOfertaAdministradorProcess" var="url">
                                                            <s:param name="id" value="#ofertaActual.id"/>
                                                        </s:url>
                                                        <a href='<s:property value="#url" />' onclick="return confirmBox('¿Desea eliminar la oferta?');">  <button style="background-color: transparent"><img src="../imagenes/eliminar.png"/></button> </a>
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
