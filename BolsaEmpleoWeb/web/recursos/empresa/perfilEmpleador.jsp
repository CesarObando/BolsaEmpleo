<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="../recursosReusables/head.jsp"/>
        <title>Perfil Empleador | Bolsa de Empleo</title>  
    </head>
    <body onload="nobackbutton();">
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
                            <s:form method="post" action="editarEmpleadorProcess" cssClass="form">
                                <s:hidden name="id"/>
                                <s:label name="cedula"/>
                                <s:label name="nombre"/>
                                <s:label name="apellidos"/>
                                <s:label name="correo" />
                                <s:label name="telefonoFijo"/> 
                                <s:label name="telefonoMovil"/>
                                <s:label name="cedulaJuridica"/>
                                <s:label name="nombreEmpresa"/>
                                <s:label name="direccion" style="width:450px;height:150px"/>
                                <s:label name="username"/>
                            </s:form>
                        </center>
                        <a href="../empresa/editarEmpleador.action" class="" style="background: transparent">Editar</a>
                        <a href="../empresa/eliminarEmpleadorProcess.action" class="" onclick="return confirmBox1();" style="background: transparent">Eliminar</a>
                        <a href="../empresa/editarEmpleadorProcess.action" class="" onclick="return confirmBox();" style="background: transparent">Renovar cuenta</a>
                    </div>
                    <script>
                        function confirmBox() {
                            var answer;
                            answer = window.confirm("¿Desea renovar la información?");
                            if (answer == true) {
                                return true;
                            }
                            else {
                                return false;
                            }
                        }
                    </script>
                    <script>
                        function confirmBox1() {
                            var answer;
                            answer = window.confirm("¿Desea eliminar la cuenta?");
                            if (answer == true) {
                                return true;
                            }
                            else {
                                return false;
                            }
                        }
                    </script>
                </aside> 
            </div>            
        </section>
        <footer >
            <jsp:include page="../recursosReusables/footer.jsp"/>          
        </footer>  
        <jsp:include page="../recursosReusables/facebook.jsp"/>
    </body>
</html>
