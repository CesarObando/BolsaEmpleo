package Dominio;

import java.util.Date;

public class EspecialidadOferente {

    private int idEspecialidadOferente;
    private Date fechaInicio;
    private Date fechaFin;
    private String nombreTitulo;
    private InstitucionEstudio institucionEstudio;
    private NivelEstudio nivelEstudio;
    private AreaEspecialidad areaEspecialidad;

    public EspecialidadOferente() {
        institucionEstudio = new InstitucionEstudio();
        nivelEstudio = new NivelEstudio();
        areaEspecialidad = new AreaEspecialidad();
    }

    public EspecialidadOferente(int idEspecialidadOferente, Date fechaInicio, Date fechaFin, String nombreTitulo, InstitucionEstudio institucionEstudio, NivelEstudio nivelEstudio, AreaEspecialidad areaEspecialidad) {
        this.idEspecialidadOferente = idEspecialidadOferente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombreTitulo = nombreTitulo;
        this.institucionEstudio = institucionEstudio;
        this.nivelEstudio = nivelEstudio;
        this.areaEspecialidad = areaEspecialidad;
    }

    public int getIdEspecialidadOferente() {
        return idEspecialidadOferente;
    }

    public void setIdEspecialidadOferente(int idEspecialidadOferente) {
        this.idEspecialidadOferente = idEspecialidadOferente;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombreTitulo() {
        return nombreTitulo;
    }

    public void setNombreTitulo(String nombreTitulo) {
        this.nombreTitulo = nombreTitulo;
    }

    public InstitucionEstudio getInstitucionEstudio() {
        return institucionEstudio;
    }

    public void setInstitucionEstudio(InstitucionEstudio institucionEstudio) {
        this.institucionEstudio = institucionEstudio;
    }

    public NivelEstudio getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(NivelEstudio nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public AreaEspecialidad getAreaEspecialidad() {
        return areaEspecialidad;
    }

    public void setAreaEspecialidad(AreaEspecialidad areaEspecialidad) {
        this.areaEspecialidad = areaEspecialidad;
    }

    @Override
    public String toString() {
        return "EspecialidadOferente{" + "idEspecialidadOferente=" + idEspecialidadOferente + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", nombreTitulo=" + nombreTitulo + ", institucionEstudio=" + institucionEstudio + ", nivelEstudio=" + nivelEstudio + ", areaEspecialidad=" + areaEspecialidad + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EspecialidadOferente other = (EspecialidadOferente) obj;
        return this.idEspecialidadOferente == other.idEspecialidadOferente;
    }

    
}
