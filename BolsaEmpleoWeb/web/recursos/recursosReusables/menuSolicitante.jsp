
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
                        <s:a action="buscarOfertasSolicitante" >Buscar Ofertas</s:a>
                    </li>
                    <li>
                        <a href="../empresa/verPerfilSolicitante.action" class="" >Mantener información</a>
                    </li>
                    <li>
                        <s:a action="buscarSolicitudesSolicitante" >Administrar Solicitudes</s:a>
                    </li>
                </ul>
            </div>                                
        </div>                          
    </div>
</div>