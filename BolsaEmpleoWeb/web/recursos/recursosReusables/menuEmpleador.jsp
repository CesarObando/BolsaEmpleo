
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="zone-menu" class="zone-menu clearfix container-12">
    <div class="grid-12">                        
        <div class="main-menu">
            <div class="content clearfix">
                <ul  class="menu sf-menu sf-horizontal">
                    <li>
                        <a href="../empresa/principalEmpleador.jsp" class="">Inicio</a>
                    </li>
                    <c:if test="${sessionScope.empleador == null}">
                        <li>
                            <a href="../empresa/registrarEmpleador.jsp" class="">Registrate</a>               
                        </li>
                    </c:if>
                    <li>
                        <a>Ofertas</a>  
                        <ul>
                            <li><a href="../Ofertas/insertarOferta.action">Registrar Oferta</a></li>
                            <li><a href="../Ofertas/buscarOfertas.action">Actualizar/Eliminar</a></li>
                        </ul>
                    </li>
                    <li>
                        <s:a action="buscarServiciosEmpleador" >Trabajos Independientes</s:a>
                    </li>
                    <li>
                        <a href="../empresa/verPerfilEmpleador.action" class="" >Actualizar/Eliminar perfil</a>
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