package Actions;

import Business.ServicioBusiness;
import Exception.DataException;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImagenServicioAction extends ActionSupport {

    byte[] imagenBytes;
    String idImagen;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public byte[] getImagenEnBytes() {
        try {
            return new ServicioBusiness().buscarServicio(Integer.parseInt(this.idImagen)).getFoto();
        } catch (SQLException | DataException ex) {
            Logger.getLogger(ImagenServicioAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getCustomContentType() {
        return "image/jpeg";
    }

    public String getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(String idImagen) {
        this.idImagen = idImagen;
    }

}
