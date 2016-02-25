<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<div id="zone-footer-wrapper" class="zone-wrapper zone-footer-wrapper clearfix">  
    <div id="zone-footer" class="zone zone-footer clearfix container-12">
        <div class="grid-12 region region-footer-first" id="region-footer-first">
            <div class="region-inner region-footer-first-inner">
                <div class="block block-block block-6 block-block-6 odd block-without-title" id="block-block-6">
                    <div class="content clearfix">                                        
                        <p>© 2016 UNIVERSIDAD DE COSTA RICA |&nbsp;<a href="http://www.ucr.ac.cr/">http://www.ucr.ac.cr/</a>&nbsp;| teléfono 2511- 0000</p>
                        <img src="../imagenes/firma-ucr-ico1.png" alt="UCR" id="logo" style="width: 120px;height: 80px"/>
                        <hgroup class="site-name-slogan">      
                            <h1 class="site-name"><a href="/" title="Inicio" class="active">UCR</a></h1>
                            <h6 class="site-slogan">Al servicio de la comunidad</h6>
                        </hgroup>
                    </div>
                </div>
            </div><div class="block block-views block-4303892770851f3336c5ca7be219abf2 block-views-4303892770851f3336c5ca7be219abf2 even block-without-title" id="block-views-4303892770851f3336c5ca7be219abf2">
                <div class="block-inner clearfix">
                    <div class="content clearfix">
                        <div class="view view-display-id-block_ultima_actualizacion view-dom-id-8b4231c76c5966c475e85e52ab89b57e">
                            <div class="view-content">
                                <div class="views-row views-row-1 views-row-odd views-row-first views-row-last">
                                    <div class="views-field views-field-changed">    
                                        <span class="views-label views-label-changed">Última Actualización:</span><span class="field-content">23/2/2015 - 9:30</span>
                                        <c:if test="${sessionScope.administrador != null}">
                                            <br><span class="views-label views-label-changed">Numero de visitas a la pagina:</span><span class="field-content">${sessionScope.numeroVisitas}</span>
                                        </c:if>
                                    </div>  
                                </div>
                            </div>
                        </div>   
                    </div>
                </div>
            </div>  
        </div>
    </div>
</div>                
