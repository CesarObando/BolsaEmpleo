/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author JonathanA
 */
public class DownloadEjemploAction extends ActionSupport {
    
    private InputStream fileInputStream;
    
    public DownloadEjemploAction() {
    }
    
    public String execute() throws Exception {
        String ruta = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
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
