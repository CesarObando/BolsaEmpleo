package Dominio;

import java.io.File;
import java.sql.Date;
import java.util.LinkedList;

public class Oferente extends Usuario {

    private File archivoCurriculo;
    private LinkedList<Vacante> vacantes;
    private LinkedList<ExperienciaLaboral> experienciasLaborales;
    private LinkedList<EspecialidadOferente> especialidades;

    public Oferente(File archivoCurriculo, LinkedList<Vacante> vacantes, String cedula, String nombreUsuario, String clave, String nombre, String apellidos, String provincia, String ciudad, String direccion, int codigoPostal, String email, String telefonoCasa, String telefonoCelular, String fax, Date fechaNacimiento, boolean oferente, boolean empleador, boolean administrador, LinkedList<Servicio> servicios) {
        super(cedula, nombreUsuario, clave, nombre, apellidos, provincia, ciudad, direccion, codigoPostal, email, telefonoCasa, telefonoCelular, fax, fechaNacimiento, oferente, empleador, administrador, servicios);
        this.archivoCurriculo = archivoCurriculo;
        this.vacantes = vacantes;
        experienciasLaborales = new LinkedList<>();
        especialidades = new LinkedList<>();
    }
    
    public Oferente() {
        experienciasLaborales = new LinkedList<>();
        especialidades = new LinkedList<>();
    }

    public LinkedList<ExperienciaLaboral> getExperienciasLaborales() {
        return experienciasLaborales;
    }

    public void setExperienciasLaborales(LinkedList<ExperienciaLaboral> experienciasLaborales) {
        this.experienciasLaborales = experienciasLaborales;
    }

    public LinkedList<EspecialidadOferente> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(LinkedList<EspecialidadOferente> especialidades) {
        this.especialidades = especialidades;
    }
       
    public File getArchivoCurriculo() {
        return archivoCurriculo;
    }

    public void setArchivoCurriculo(File archivoCurriculo) {
        this.archivoCurriculo = archivoCurriculo;
    }

    public LinkedList<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(LinkedList<Vacante> vacantes) {
        this.vacantes = vacantes;
    }
    
    public void agregarVacante(Vacante vacante){
        if(vacantes == null)
            vacantes = new LinkedList<>();
        vacantes.add(vacante);
    }

    @Override
    public String toString() {
        return "Oferente{" + "vacantes=" + vacantes + '}';
    }
    
    
    
}
