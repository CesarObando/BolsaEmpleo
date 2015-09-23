
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="zone-menu" class="zone-menu clearfix container-12">
    <div class="grid-12">                        
        <div class="main-menu">
            <div class="content clearfix">
                <ul  class="menu sf-menu sf-horizontal">
                    <li>
                        <a href="principalOferente"  class="sf-depth-2">Oferente</a>
                        <ul>
                            <li>
                                <a href="EspecialidadesOferente">Especialidades</a>
                            </li>
                            <li>
                                <a href="ExperienciasOferente">Experiencias</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="sf-depth-1 ">Servicios</a>
                        <ul>
                            <li>
                                <a href="Servicios">Mis servicios</a>
                            </li>
                            <li>
                                <a href="ServiciosBuscar">Buscar Servicios</a>
                            </li>
                        </ul>
                    </li>
                    <li><a href="#" class="sf-depth-1 ">Empleos</a>
                        <ul>
                            <li >
                                <a href="VerVacantes">Vacantes</a>
                            </li>
                            <li >
                                <a href="EntrevistasSolicitadas">Entrevistas Solicitadas</a>
                            </li>
                        </ul>
                    </li>
                    <c:if test="${!empty sessionScope.usuario}">
                        <li>
                            <a href="logout">Cerrar Sesión</a>  
                        </li>
                    </c:if>
                </ul>
            </div>                                
        </div>                          
    </div>
</div>