package Dominio;

import java.util.Date;

public class ExperienciaLaboral {
    private int idExperiencia;
    private String empresa;
    private String puesto;
    private Date fechaIngreso;
    private Date fechaTermina;
    private String descripcionFunciones;

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(int idExperiencia, String empresa, String puesto, Date fechaIngreso, Date fechaTermina, String descripcionFunciones) {
        this.idExperiencia = idExperiencia;
        this.empresa = empresa;
        this.puesto = puesto;
        this.fechaIngreso = fechaIngreso;
        this.fechaTermina = fechaTermina;
        this.descripcionFunciones = descripcionFunciones;
    }

    public int getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(int idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaTermina() {
        return fechaTermina;
    }

    public void setFechaTermina(Date fechaTermina) {
        this.fechaTermina = fechaTermina;
    }

    public String getDescripcionFunciones() {
        return descripcionFunciones;
    }

    public void setDescripcionFunciones(String descripcionFunciones) {
        this.descripcionFunciones = descripcionFunciones;
    }

    @Override
    public String toString() {
        return "ExperienciaLaboral: "  + idExperiencia + ", empresa=" + empresa + ", puesto=" + puesto + ", fechaIngreso=" + fechaIngreso + ", fechaTermina=" + fechaTermina + ", descripcionFunciones=" + descripcionFunciones;
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
        final ExperienciaLaboral other = (ExperienciaLaboral) obj;
        return this.idExperiencia == other.idExperiencia;
    }
    
    
}
