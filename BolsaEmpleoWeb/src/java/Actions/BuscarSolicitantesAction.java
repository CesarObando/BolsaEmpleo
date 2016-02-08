/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Business.SolicitanteBusiness;
import Dominio.Solicitante;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import Utilitarios.EnviarCorreos;

/**
 *
 * @author JonathanA
 */
public class BuscarSolicitantesAction extends ActionSupport implements Preparable, ServletRequestAware {

    private final String BUSCAR_SOLICITANTES = "buscarSolicitantes";
    private LinkedList<Solicitante> solicitantes;
    private HttpServletRequest request;
    private String cedula;
    private String nombre;
    private String apellidos;
    private String correo;

    public BuscarSolicitantesAction() {
    }

    public String execute() throws Exception {
        return ActionSupport.SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
    }
    
    

    public String buscar() throws DataException {
        SolicitanteBusiness solicitanteBusiness = new SolicitanteBusiness();
        cedula = request.getParameter("cedula");
        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apellidos");
        try {
            solicitantes = solicitanteBusiness.buscarSolicitantesFiltrados(cedula, nombre, apellidos);
            correo = request.getParameter("correo");
        } catch (SQLException e) {
            Logger.getLogger(BuscarSolicitantesAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return BUSCAR_SOLICITANTES;
    }

    public String enviarCorreo() {
        EnviarCorreos enviarCorreos = new EnviarCorreos();
        String asunto = "Solicitud de Actualización de datos";
        String cuerpo = "Por favor actualizar los datos de su cuenta de usuario en nuestra pagina web";
        correo = request.getParameter("correo");
        return enviarCorreos.EnviarCorreo(correo, asunto, cuerpo);
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public LinkedList<Solicitante> getSolicitantes() {
        return solicitantes;
    }

    public void setSolicitantes(LinkedList<Solicitante> solicitantes) {
        this.solicitantes = solicitantes;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

//    <script>
//                            function enviarCorreo(String destinatario){
//                            <%
//                                String asunto = "Solicitud de Actualización de datos";
//                                String cuerpo = "Por favor actualizar los datos de su cuenta de usuario en nuestra pagina web";
//                                Solicitante solicitante = (Solicitante) session.getAttribute("solicitante");
//                                EnviarCorreos enviarCorreo = new EnviarCorreos();
//                                enviarCorreo.EnviarCorreo("cesar.b.c@hotmail.com", asunto, cuerpo);
//                            %>
//                            ]
//                        </script>
}
