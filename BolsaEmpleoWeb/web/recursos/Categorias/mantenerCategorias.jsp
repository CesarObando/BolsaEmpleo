<%-- 
    Document   : mantenerCategorias
    Created on : 04-ene-2016, 15:34:11
    Author     : Tin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Actualizar Categorías | Bolsa de Empleo</title>  
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
                        <s:if test="hasActionMessages()">
                            <script>
                                window.alert("${sessionScope.mensaje}");
                            </script>
                        </s:if>
                        <s:if test="hasActionErrors()">
                            <script>
                                window.alert("${sessionScope.mensaje}");
                            </script>
                        </s:if>
                        <center>
                            <s:form action="buscarCategoriasProcess" method="post" cssClass="form">
                                <s:textfield name="nombre" label="Nombre"/>
                                <s:submit method="buscar" value="Buscar"/>
                            </s:form>
                            <table id="mytable" class="table">

                                <s:if test="%{categorias.isEmpty()}">
                                    <h2>No hay resultados que mostrar</h2>
                                </s:if>

                                <s:else>
                                    <thead class="th">
                                    <th>Nombre</th>
                                    <th>Editar</th>
                                    <th>Eliminar</th>
                                    </thead>
                                    <tbody class="td">
                                        <s:iterator value="categorias" var="categoriaActual">
                                            <tr class="tr">
                                                <td><s:property value="#categoriaActual.nombre"/></td>

                                                <td><p data-placement="top" data-toggle="tooltip" title="Edit">
                                                        <s:url action="editarCategoria" var="url">
                                                            <s:param name="id" value="#categoriaActual.id"/>
                                                        </s:url>
                                                        <a href='<s:property value="#url" />'>  <button style="background-color: transparent"><img src="../imagenes/editar1.png"/> </button> </a>

                                                    </p>
                                                </td>
                                                <td><p data-placement="top" data-toggle="tooltip" title="Delete">
                                                        <s:url action="eliminarCategoriaProcess" var="url">
                                                            <s:param name="id" value="#categoriaActual.id"/>
                                                        </s:url>
                                                        <a href='<s:property value="#url" />' onclick="return confirmBox();">  <button style="background-color: transparent"><img src="../imagenes/eliminar.png"/> </button> </a>
                                                    </p>
                                                </td>
                                            </tr>
                                        </s:iterator>
                                    </s:else>
                                <div>
                                </div       
                                </tbody>
                            </table>     
                        </center>
                        <script>
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea eliminar la categoría?");
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
