<%-- 
    Document   : login
    Created on : Oct 6, 2014, 11:49:21 PM
    Author     : ricardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="zone-menu" class="zone-menu clearfix container-12">
    <div class="grid-12">                        
        <div class="main-menu">
            <div class="content clearfix">
                <ul  class="menu sf-menu sf-horizontal">
                    <li>
                        <a href="../administrador/principalAdministrador.jsp" class="">Inicio</a>
                    </li>
                    <c:if test="${sessionScope.administrador == null}">
                        <li>
                            <a href="../usuarios/insertarAdministrador.jsp" class="">Registrate</a>               
                        </li>
                    </c:if>
                    <li>
                        <a>Categoría</a> 
                        <ul>
                            <li><a href="../Categorias/insertarCategoria.jsp" >Insertar</a></li>
                            <li><a href="../Categorias/buscarCategorias.jsp" >Mantener</a> </li>
                        </ul>
                    </li>
                    <li>
                        <a>Solicitante</a> 
                        <ul>
                            <li><a href="../usuarios/buscarSolicitantes.jsp" >Mantener</a> </li>
                        </ul>
                    </li>
                    <li>
                        <a>Oferta</a> 
                        <ul>
                            <s:a action="buscarOfertasAdministrador" >Mantener</s:a>
                        </ul>
                    </li>
                    <li>
                        <a>Solicitud</a> 
                        <ul>
                            <s:a action="buscarSolicitudesAdministrador" >Mantener</s:a>
                        </ul>
                    </li>
                    <li>
                        <a>Empleador</a> 
                        <ul>
                            <li><a href="../administrador/buscarEmpleadores.jsp" >Mantener</a> </li>
                        </ul>
                    </li>
                    <li>
                        <a>Servicios</a> 
                        <ul>
                            <s:a action="buscarServiciosAdministrador" >Mantener</s:a>
                        </ul>
                    </li>
                    <li>
                        <a href="../administrador/verPerfilAdministrador.action" class="" >Mantener información</a>
                    </li>
                    <li>
                        <a href="../administrador/insertarAdministrador.jsp" class="" >Nuevo Administrador</a>
                    </li>
                </ul>
            </div>                                
        </div>                          
    </div>
</div>