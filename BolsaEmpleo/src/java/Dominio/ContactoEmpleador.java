package Dominio;

public class ContactoEmpleador {
    private String cedulaJuridica;
    private String empresa;
    private String desingnacion;
    private String telefono;
    private int extension;
    private String fax;
    private String email;

    public ContactoEmpleador() {
    }

    public ContactoEmpleador(String cedulaJuridica,  String empresa, String desingnacion, String telefono, int extension, String fax, String email) {
        this.empresa = empresa;
        this.cedulaJuridica = cedulaJuridica;
        this.desingnacion = desingnacion;
        this.telefono = telefono;
        this.extension = extension;
        this.fax = fax;
        this.email = email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCedulaJuridica() {
        return cedulaJuridica;
    }

    public void setCedulaJuridica(String cedulaJuridica) {
        this.cedulaJuridica = cedulaJuridica;
    }

    public String getDesingnacion() {
        return desingnacion;
    }

    public void setDesingnacion(String desingnacion) {
        this.desingnacion = desingnacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactoEmpleador{" +", empresa=" + empresa + ", cedulaJuridica=" + cedulaJuridica + ", desingnacion=" + desingnacion + ", telefono=" + telefono + ", extension=" + extension + ", fax=" + fax + ", email=" + email + '}';
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
        final ContactoEmpleador other = (ContactoEmpleador) obj;
        return this.cedulaJuridica == other.cedulaJuridica;
    }
    
    
}
