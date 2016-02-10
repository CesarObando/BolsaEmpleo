<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Editar Oferta | Bolsa de Empleo</title>  
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
                            <s:form method="post" action="editarOfertaProcess" cssClass="form">
                                <s:hidden name="id"/>
                                <s:textfield name="puesto" placeholder="Ingresa el nombre del puesto" readonly="true" disabled="true"/>
                                <s:textfield name="salario" placeholder="Ingresa el salario" type="number"/>
                                <s:textfield name="cantidadVacantes" placeholder="Ingresa la cantidad de vacantes" type="number"/>
                                <s:textarea name="requerimientos" placeholder="Ingresa los requerimientos"/>
                                <s:textarea name="descripcion" placeholder="Ingresa la descripción de la oferta"/>
                                <s:select name="categoria.id" requiredLabel="true" label="Categoría" list="categorias" listKey="id" listValue="nombre" headerKey="categoria.id"/>            
                                <s:submit action="editarOfertaProcess" value="Editar Oferta" onclick="return confirmBox();"/>
                            </s:form>
                            <a href="../empresa/principalEmpleador.jsp" style="height: 10px;background: transparent;float: left">Cancelar</a>
                        </center>
                        <script>
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea editar la oferta?");
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
