<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div id="zone-menu" class="zone-menu clearfix container-12">
    <div class="grid-12">                        
        <div class="main-menu">
            <div class="content clearfix">
                <ul  class="menu sf-menu sf-horizontal nav">
                    <li>
                        <a href="../usuarios/pantallaPrincipal.jsp" class="">Inicio</a>
                    </li>
                    <c:if test="${sessionScope.solicitante == null || sessionScope.empleador == null}">
                        <li>
                            <a>Registrate</a> 
                            <ul>
                                <li><a href="../empresa/registrarEmpleador.jsp" >Empleador</a></li>
                                <li><a href="../usuarios/insertarSolicitante.jsp" >Solicitante</a> </li>
                            </ul>
                        </li>
                    </c:if>
                    <li>
                        <s:a action="buscarServiciosEmpleador" >Trabajos Independientes</s:a>
                        </li>
                    <c:if test="${sessionScope.solicitante == null || sessionScope.empleador == null}">
                        <li>
                            <a>Iniciar Sesión</a> 
                            <ul>
                                <li><a href="../empresa/login.jsp" >Empleador</a></li>
                                <li><a href="../usuarios/login.jsp" >Solicitante</a> </li>
                            </ul>
                        </li>
                    </c:if>
                </ul>
            </div>                                
        </div>                          
    </div>
</div>
