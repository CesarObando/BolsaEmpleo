package Dominio;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;

public class Vacante {

    private int idPuesto;
    private String descripcion;
    private String requerimientos;
    private int numeroVacantes;
    private int diasLaborar;
    private Timestamp horaEntrada;
    private Timestamp horaSalida;
    private double sueldo;
    private String provincia;
    private String ciudad;
    private Date fechaCreacion;
    private boolean tienePostulantes;
    private CategoriaVacante categoria;
    private LinkedList<Oferente> oferentes;

    public Vacante() {
        categoria = new CategoriaVacante();
    }

    public boolean isTienePostulantes() {
        return tienePostulantes;
    }

    public void setTienePostulantes(boolean tienePostulantes) {
        this.tienePostulantes = tienePostulantes;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(String requerimientos) {
        this.requerimientos = requerimientos;
    }

    public int getNumeroVacantes() {
        return numeroVacantes;
    }

    public void setNumeroVacantes(int numeroVacantes) {
        this.numeroVacantes = numeroVacantes;
    }

    public int getDiasLaborar() {
        return diasLaborar;
    }

    public void setDiasLaborar(int diasLaborar) {
        this.diasLaborar = diasLaborar;
    }

    public Timestamp getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Timestamp horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Timestamp getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Timestamp horaSalida) {
        this.horaSalida = horaSalida;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public CategoriaVacante getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaVacante categoria) {
        this.categoria = categoria;
    }

    public LinkedList<Oferente> getOferentes() {
        return oferentes;
    }

    public void setOferentes(LinkedList<Oferente> oferentes) {
        this.oferentes = oferentes;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vacante other = (Vacante) obj;
        return this.idPuesto == other.idPuesto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.idPuesto;
        return hash;
    }

    @Override
    public String toString() {
        return "Vacante{" + "idPuesto=" + idPuesto + ", descripcion=" + descripcion + ", requerimientos=" + requerimientos + ", numeroVacantes=" + numeroVacantes + ", diasLaborar=" + diasLaborar + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", sueldo=" + sueldo + ", provincia=" + provincia + ", ciudad=" + ciudad + ", fechaCreacion=" + fechaCreacion + '}';
    }
    public void agrgarOferente(Oferente oferente){
        if(oferentes == null){
            oferentes = new LinkedList<>();
        }
        oferentes.add(oferente);
    }
}
