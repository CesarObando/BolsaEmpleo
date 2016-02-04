<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Mantener Ofertas | Bolsa de Empleo</title>  
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
                <jsp:include page="../recursosReusables/menuEmpleador.jsp"/>
            </div>
        </header>
        <section>
            <div id="zone-content" class="clearfix container-12">  
                <jsp:include page="../recursosReusables/section.jsp"/>
                <aside class="grid-3 region" id="region-sidebar-second">
                    <div class="grid-9 region-content" id="region-content">
                        <s:form action="buscarOfertasProcess" cssClass="form">
                            <s:textfield name="puesto" label="Puesto"/>
                            <s:select name="categoria.id" list="categorias" listKey="id" listValue="nombre" headerValue="Seleccione una categoría" headerKey="-1"/>
                            <s:submit method="buscar" value="Buscar"/>
                        </s:form>   
                        <s:form cssClass="form">
                            
                            <table    cclass="form" >

                            <s:if test="%{ofertas.isEmpty()}">
                                <h2>No hay resultados que mostrar</h2>
                            </s:if>

                            <s:else>
                                <thead>
                                <td>Puesto</td>
                                <th>Ver</th>
                                </thead>
                                <tbody>
                                    <s:iterator value="ofertas" var="ofertaActual">
                                        <tr>
                                            <td><s:property value="#ofertaActual.puesto"/></td>
                                            <td><p data-placement="top" data-toggle="tooltip" title="Ver">
                                                    <s:url action="verOferta" var="url">
                                                        <s:param name="id" value="#ofertaActual.id"/>
                                                    </s:url>
                                                    <a href='<s:property value="#url" />'>  <button style="background-color: white"><img src="../imagenes/ver.png"/> </button> </a>
                                                </p>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </s:else>
                            <div>
                            </div       
                            </tbody>
                        </table>
                        </s:form>
                        



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
