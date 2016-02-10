<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Ver Oferta | Bolsa de Empleo</title>  
    </head>
    <body>
        <header>
            <div class="clearfix">
                <jsp:include page="../recursosReusables/header.jsp"/>
                <c:if test="${sessionScope.solicitante == null}">
                    <jsp:include page="../recursosReusables/menuPrincipal.jsp"/>
                </c:if>

                <c:if test="${sessionScope.solicitante != null}">
                    <jsp:include page="../recursosReusables/menuSolicitante.jsp"/>
                </c:if>
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
                            <s:form method="post" action="insertarSolicitudProcess" cssClass="form">
                                <s:hidden name="id"/>
                                <s:textfield name="puesto" readonly="true" label="Puesto"/>
                                <s:textfield name="empleador.nombre" readonly="true" label="Nombre de empleador"/>
                                <s:textfield name="empleador.apellidos" readonly="true" label="Apellidos"/>
                                <s:textfield name="empleador.nombreEmpresa" readonly="true" label="Nombre de la empresa"/>
                                <s:if test="salario==0">
                                    <s:textfield name="salario" value="No especificado" label="Salario" readonly="true"/>
                                </s:if>
                                <s:else>
                                    <s:textfield name="salario" readonly="true" label="Salario"/>
                                </s:else>
                                <s:textfield name="cantidadVacantes" label="Cantidad de Vacantes" readonly="true"/>
                                <s:textarea name="requerimientos" readonly="true" label="Requerimientos"/>
                                <s:textfield name="categoria.nombre" readonly="true" label="Categoría"/>
                                <s:textarea name="descripcion" readonly="true" label="Descripción"/>
                                <c:if test="${sessionScope.solicitante != null}">
                                    <s:submit value="Solicitar" action="insertarSolicitudProcess" onclick="return confirmBox();"/>
                                </c:if>
                            </s:form>

                            <c:if test="${sessionScope.solicitante == null}">
                                <s:a href="../usuarios/insertarSolicitante.jsp">Registrate para enviar tu solicitud a esta oferta</s:a>
                            </c:if>

                        </center>
                        <script>
                            function confirmBox() {
                                var answer;
                                answer = window.confirm("¿Desea registrar la solicitud?");
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
