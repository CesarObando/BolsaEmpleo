
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="zone-menu" class="zone-menu clearfix container-12">
    <div class="grid-12">                        
        <div class="main-menu">
            <div class="content clearfix">
                <ul  class="menu sf-menu sf-horizontal">
                    <li>
                        <a href="../recursos/pantallaPrincipal.jsp" class="">Inicio</a>
                    </li>
                    <c:if test="${sessionScope.solicitante == null}">
                        <li>
                            <a href="../empresa/registrarEmpleador.jsp" class="">Registrate</a>               
                        </li>
                    </c:if>
                </ul>
            </div>                                
        </div>                          
    </div>
</div>
