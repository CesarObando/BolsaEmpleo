<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div id="zone-menu" class="zone-menu clearfix container-12">
    <div class="grid-12">                        
        <div class="main-menu">
            <div class="content clearfix">
                <ul  class="menu sf-menu sf-horizontal">
                    <li>
                        <a href="../usuarios/principalSolicitante.jsp" class="">Inicio</a>
                    </li>
                    <c:if test="${sessionScope.solicitante == null}">
                        <li>
                            <a href="../usuarios/insertarSolicitante.jsp" class="">Registrate</a>               
                        </li>
                    </c:if>
                    <li>
                        <a>Ofertas</a>
                        <ul>
                            <s:a action="buscarOfertasSolicitante" >Buscar Ofertas</s:a>
                            <s:a action="buscarOfertasFavoritasProcess" >Ofertas Favoritas</s:a>
                            </ul>
                        </li>
                        <li>
                        <s:a action="buscarSolicitudesSolicitantes" >Eliminar Solicitudes</s:a>
                        </li>
                        <li>
                            <a>Trabajos Independientes</a> 
                            <ul>
                            <s:a action="buscarServiciosEmpleador" >Buscar</s:a>
                            <s:a action="insertarServicio" >Registrar</s:a>
                            <s:a action="buscarServicios" >Ver/Actualizar/Eliminar</s:a>
                            </ul>
                        </li>
                        <li>
                            <a href="../usuarios/verPerfilSolicitante.action" class="" >Actualizar/Eliminar perfil</a>
                        </li>
                        <li>
                        <s:url action="cerrarSesion" var="url"></s:url>
                        <a href='<s:property value="#url" />' class="">Cerrar Sesión</a>
                    </li>

                </ul>
            </div>                                
        </div>                          
    </div>
</div>