package Dominio;

import java.sql.Date;
import java.util.LinkedList;

public class Empleador extends Usuario{
    
    private ContactoEmpleador contactoEmpleador;
    private LinkedList<Vacante> vacantes;

    public Empleador() {
        contactoEmpleador = new ContactoEmpleador();
        vacantes = new LinkedList<>();
    }

    public Empleador(ContactoEmpleador contactoEmpleador, LinkedList<Vacante> vacantes, String cedula, String nombreUsuario, String clave, String nombre, String apellidos, String provincia, String ciudad, String direccion, int codigoPostal, String email, String telefonoCasa, String telefonoCelular, String fax, Date fechaNacimiento, boolean oferente, boolean empleador, boolean administrador, LinkedList<Servicio> servicios) {
        super(cedula, nombreUsuario, clave, nombre, apellidos, provincia, ciudad, direccion, codigoPostal, email, telefonoCasa, telefonoCelular, fax, fechaNacimiento, oferente, empleador, administrador, servicios);
        this.contactoEmpleador = contactoEmpleador;
        this.vacantes = vacantes;
    }

    public ContactoEmpleador getContactoEmpleador() {
        return contactoEmpleador;
    }

    public void setContactoEmpleador(ContactoEmpleador contactoEmpleador) {
        this.contactoEmpleador = contactoEmpleador;
    }

    public LinkedList<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(LinkedList<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    @Override
    public String toString() {
        return "Empleador{" + "contactoEmpleador=" + contactoEmpleador + ", vacantes=" + vacantes + '}';
    }
    
    


    


    

    
    

   
    
    
    
}
