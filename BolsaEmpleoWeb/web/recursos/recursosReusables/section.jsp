<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<section>    
    <aside class="grid-3 region-sidebar-first" id="region-sidebar-first">                            
        <div class=" block-menu-block-1" id="block-menu-block-1">
            <div class="content clearfix">
                <div class="grid-3">                        
                    <div class="main-menu">
                        <div class="content clearfix">
                            <ul class="menu sf-menu sf-horizontal">
                                <li ><a href="../recursosReusables/acercaDe.jsp" >¿Quiénes somos?</a></li>
                                <li > <a>¿Cómo hacer un currículum?</a>
                                    <ul>
                                        <s:a action="download.action">Descargar plantilla</s:a>
                                        <s:a action="descargarEjemplo.action">Descargar ejemplo</s:a>
                                    </ul>
                                </li>                                              
                                <li ><a href="https://www.facebook.com/recintodeparaiso.ucr">Recinto de Paraíso</a></li>
                                <li ><a >Contáctenos</a>
                                    <ul>
                                        <a href="http://bolsaempleocomentariosyquejas.blogspot.com/">Blog</a>
                                    </ul>
                                </li>                                            
                            </ul>                                
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <section class="block block-menu block-menu-redes-sociales block-menu-menu-redes-sociales odd" id="block-menu-menu-redes-sociales">
            <div class="block-inner clearfix">
                <h2 class="block-title" style="float: left;width: 200px;font-size: 20px">Síguenos en Facebook</h2>
                <div class="content clearfix">
                    <div class="fb-like-box" data-href="https://www.facebook.com/pages/Oficina-Orientaci%C3%B3n-sitio-oficial/188921904531554?fref=ts" data-width="235" data-colorscheme="light" data-show-faces="true" data-header="false" data-stream="true" data-show-border="false"></div>
                </div>
            </div>
        </section>
    </aside>           
</section> 

