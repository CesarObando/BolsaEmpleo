<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Insertar Oferta | Bolsa de Empleo</title>  
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
                            <s:form method="post" action="insertarOfertaProcess" cssClass="form">
                                <s:textfield name="puesto" requiredLabel="true" placeholder="Ingresa el nombre del puesto"/>
                                <s:textfield name="salario" type="number" placeholder="Ingresa el salario"/>
                                <s:textfield name="cantidadVacantes" type="number" placeholder="Ingresa la cantidad de vacantes"/>
                                <s:textarea name="requerimientos" requiredLabel="true" placeholder="Ingresa los requerimientos"/>
                                <s:textarea name="descripcion" requiredLabel="true" placeholder="Ingresa la descripción de la oferta"/>
                                <s:select name="categoria.id" requiredLabel="true" list="listaCategorias" listKey="id" listValue="nombre" headerKey="-1" headerValue="Seleccione una categoría"/>            
                                <s:submit action="insertarOfertaProcess" value="Insertar Oferta" onclick="return confirmBox();"/>
                            </s:form>
                        </center>
                        <a href="../empresa/principalEmpleador.jsp" style="height: 10px;background: transparent;float: left">Cancelar</a>
                        <script>
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea registrar la oferta?");
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
