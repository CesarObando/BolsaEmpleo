
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="zone-menu" class="zone-menu clearfix container-12">
    <div class="grid-12">                        
        <div class="main-menu">
            <div class="content clearfix">
                <ul  class="menu sf-menu sf-horizontal">
                    <li>
                        <a href="PantallaPrincipal" class="">Inicio</a>
                    </li>
                    <li>
                        <a href="RegistrarUsuario" class="">Registrate</a>                        
                    </li>
                    <c:if test="${!empty sessionScope.usuario}">
                        <li>
                            <a href="#"  class="">Cerrar Sesión</a>                        
                        </li>
                    </c:if>
                </ul>
            </div>                                
        </div>                          
    </div>
</div>
