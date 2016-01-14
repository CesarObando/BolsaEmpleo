
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="zone-menu" class="zone-menu clearfix container-12">
    <div class="grid-12">                        
        <div class="main-menu">
            <div class="content clearfix">
                <ul  class="menu sf-menu sf-horizontal nav">
                    <li>
                        <a href="../recursos/pantallaPrincipal.jsp" class="">Inicio</a>
                    </li>
                    <c:if test="${sessionScope.solicitante == null}">
                        <li>
                            <a>Registrate</a> 
                            <ul>
                                <li><a href="../recursos/empresa/registrarEmpleador.jsp" >Empleador</a></li>
                                <li><a href="../recursos/usuarios/insertarSolicitante.jsp" >Usuario</a> </li>
                            </ul>
                        </li>
                    </c:if>
                </ul>
            </div>                                
        </div>                          
    </div>
</div>
