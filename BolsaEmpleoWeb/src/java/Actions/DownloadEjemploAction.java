package Actions;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.struts2.ServletActionContext;

public class DownloadEjemploAction extends ActionSupport {
    
    private InputStream fileInputStream;
    
    public DownloadEjemploAction() {
    }
    
    public String execute() throws Exception {
        //Obtiene la ruta de la aplicacion
        String ruta = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
        //Agrega la ruta del archivo
        fileInputStream = new FileInputStream(new File(ruta+"/recursos/curriculo/Ejemplo.doc"));
	    return SUCCESS;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }
    
}
