package org.apache.jsp.recursos.Plantilla;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class newjsp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("        <link rel=\"shortcut icon\" href=\"../recursos/imagenes/favicon.ico\" />\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no\" />\r\n");
      out.write("\r\n");
      out.write("        <title>Inicio | Bolsa de Empleo</title>  \r\n");
      out.write("\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../recursos/css/system.base.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../recursos/css/system.menus.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../recursos/css/system.messages.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../recursos/css/system.theme.css\">\r\n");
      out.write("\r\n");
      out.write("        <!--menu-->\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../recursos/css/superfish.css\" media=\"screen\">       \r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../recursos/css/superfish-smallscreen.css\" media=\"screen\">  \r\n");
      out.write("\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../recursos/css/omega-text.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../recursos/css/omega-branding.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../recursos/css/omega-menu.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../recursos/css/omega-forms.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../recursos/css/global.css\">\r\n");
      out.write("        <!--responsive-->\r\n");
      out.write("        <style type=\"text/css\" media=\"all and (min-width: 670px) and (min-device-width: 670px), all and (max-device-width: 1024px) and (min-width: 1024px) and (orientation:landscape)\">\r\n");
      out.write("            @import url(\"../recursos/css/omega-obas-alpha-default.css\");            \r\n");
      out.write("            @import url(\"../recursos/css/alpha-default-normal-12.css\");            \r\n");
      out.write("        </style>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <header>\r\n");
      out.write("            <div class=\"clearfix\">\r\n");
      out.write("                <div id=\"zone-branding\" class=\"container-12\">\r\n");
      out.write("                    <div class=\"grid-12 \">\r\n");
      out.write("                        <div class=\"branding-data\">\r\n");
      out.write("                            <div class=\"logo-ucr\">\r\n");
      out.write("                                <!--<a href=\"http://www.ucr.ac.cr\" target=\"blank\">Universidad de Costa Ric</a>-->\r\n");
      out.write("                                <img class=\"img-responsive\" src=\"../recursos/imagenes/logo-ucr.png\" alt=\"\" />\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"logo-img\">                   \r\n");
      out.write("                                <!--aqui va un logo de la oficina de orientacion o bolsa de empleo-->\r\n");
      out.write("                                <!--<a href=\"http://www.ucr.ac.cr\"><img class=\"img-responsive\" src=\"//placehold.it/130x55&text=Logo\" class=\"img-responsive\" alt=\"Imagen responsive\"></a>-->\r\n");
      out.write("                                <h3 class=\"site-name\">Bolsa de Empleo</h3>                                    \r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../recursos/recursosReusables/menuPrincipal.jsp", out, false);
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </header>\r\n");
      out.write("        <section >\r\n");
      out.write("            <div id=\"zone-content\" class=\"clearfix container-12\">     \r\n");
      out.write("                <aside class=\"grid-3 region-sidebar-first\" id=\"region-sidebar-first\">                            \r\n");
      out.write("                    <div class=\" block-menu-block-1\" id=\"block-menu-block-1\">\r\n");
      out.write("                        <div class=\"content clearfix\">                            \r\n");
      out.write("                            <ul class=\"menu\">\r\n");
      out.write("                                <li class=\"first collapsed\"><a href=\"#\" >¿Quiénes somos?</a></li>\r\n");
      out.write("                                <li class=\"first collapsed\"> <a href=\"Curriculo\">Cómo hacer un Curriculo?</a></li>                                              \r\n");
      out.write("                                <li class=\"leaf\"><a href=\"#\">Recinto de Paraiso</a></li>\r\n");
      out.write("                                <li class=\"leaf\"><a href=\"#\">Contactenos</a></li>                                \r\n");
      out.write("                            </ul>                                \r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <section class=\"block block-menu block-menu-redes-sociales block-menu-menu-redes-sociales odd\" id=\"block-menu-menu-redes-sociales\">\r\n");
      out.write("                        <div class=\"block-inner clearfix\">\r\n");
      out.write("                            <h2 class=\"block-title\">Síguenos en facebook</h2>\r\n");
      out.write("                            <div class=\"content clearfix\">\r\n");
      out.write("                                <div class=\"fb-like-box\" data-href=\"https://www.facebook.com/pages/Oficina-Orientaci%C3%B3n-sitio-oficial/188921904531554?fref=ts\" data-width=\"235\" data-colorscheme=\"light\" data-show-faces=\"true\" data-header=\"false\" data-stream=\"true\" data-show-border=\"false\"></div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </section>\r\n");
      out.write("                </aside>\r\n");
      out.write("            </div>            \r\n");
      out.write("        </section> \r\n");
      out.write("        <footer >\r\n");
      out.write("            <div id=\"zone-footer-wrapper\" class=\"zone-wrapper zone-footer-wrapper clearfix\">  \r\n");
      out.write("                <div id=\"zone-footer\" class=\"zone zone-footer clearfix container-12\">\r\n");
      out.write("                    <div class=\"grid-12 region region-footer-first\" id=\"region-footer-first\">\r\n");
      out.write("                        <div class=\"region-inner region-footer-first-inner\">\r\n");
      out.write("                            <div class=\"block block-block block-6 block-block-6 odd block-without-title\" id=\"block-block-6\">\r\n");
      out.write("                                <div class=\"content clearfix\">                                        \r\n");
      out.write("                                    © 2014 Oficina de Orientación, UCR |&nbsp;<a href=\"http://www.orientacion.ucr.ac.cr/\">http://www.orientacion.ucr.ac.cr/</a>&nbsp;| teléfono 2511- 1970</p>\r\n");
      out.write("                                    <a href=\"/\" rel=\"home\" title=\"Oficina de Orientación\" class=\"active\"><img src=\"../recursos/imagenes/menu-bg.png\" alt=\"Oficina de Orientación\" id=\"logo\" /></a> \r\n");
      out.write("                                    <hgroup class=\"site-name-slogan\">      \r\n");
      out.write("                                        <h1 class=\"site-name\"><a href=\"/\" title=\"Inicio\" class=\"active\">Oficina de Orientación</a></h1>\r\n");
      out.write("                                        <h6 class=\"site-slogan\">Al servicio de la comunidad estudiantil</h6>\r\n");
      out.write("                                    </hgroup>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div><div class=\"block block-views block-4303892770851f3336c5ca7be219abf2 block-views-4303892770851f3336c5ca7be219abf2 even block-without-title\" id=\"block-views-4303892770851f3336c5ca7be219abf2\">\r\n");
      out.write("                            <div class=\"block-inner clearfix\">\r\n");
      out.write("                                <div class=\"content clearfix\">\r\n");
      out.write("                                    <div class=\"view view-display-id-block_ultima_actualizacion view-dom-id-8b4231c76c5966c475e85e52ab89b57e\">\r\n");
      out.write("                                        <div class=\"view-content\">\r\n");
      out.write("                                            <div class=\"views-row views-row-1 views-row-odd views-row-first views-row-last\">\r\n");
      out.write("                                                <div class=\"views-field views-field-changed\">    \r\n");
      out.write("                                                    <span class=\"views-label views-label-changed\">Última Actualización:</span><span class=\"field-content\">23/9/2015 - 8:57</span>  \r\n");
      out.write("                                                </div>  \r\n");
      out.write("                                            </div>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div>   \r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>  \r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>                \r\n");
      out.write("        </footer>  \r\n");
      out.write("        <!--plugin facebook-->\r\n");
      out.write("        <div id=\"fb-root\"></div>\r\n");
      out.write("        <script>(function (d, s, id) {\r\n");
      out.write("                var js, fjs = d.getElementsByTagName(s)[0];\r\n");
      out.write("                if (d.getElementById(id))\r\n");
      out.write("                    return;\r\n");
      out.write("                js = d.createElement(s);\r\n");
      out.write("                js.id = id;\r\n");
      out.write("                js.src = \"//connect.facebook.net/es_LA/sdk.js#xfbml=1&version=v2.0\";\r\n");
      out.write("                fjs.parentNode.insertBefore(js, fjs);\r\n");
      out.write("            }(document, 'script', 'facebook-jssdk'));\r\n");
      out.write("        </script>\r\n");
      out.write("\r\n");
      out.write("        <script type=\"text/javascript\" src=\"../recursos/js/jquery.js\"></script>\r\n");
      out.write("        <script type=\"text/javascript\" src=\"../recursos/js/hoverIntent.js\"></script>\r\n");
      out.write("        <script type=\"text/javascript\" src=\"../recursos/js/superfish.js\"></script>                \r\n");
      out.write("        <script type=\"text/javascript\" src=\"../recursos/js/supersubs.js\"></script>\r\n");
      out.write("\r\n");
      out.write("        <script type=\"text/javascript\">\r\n");
      out.write("            // initialise plugins\r\n");
      out.write("            jQuery(function () {\r\n");
      out.write("                jQuery('ul.sf-menu').superfish();\r\n");
      out.write("            });\r\n");
      out.write("        </script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
