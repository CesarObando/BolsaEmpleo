package Actions;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class CustomImageResultServicio implements Result {

    @Override
    public void execute(ActionInvocation ai) throws Exception {
        ImagenServicioAction action = (ImagenServicioAction) ai.getAction();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType(action.getCustomContentType());
        response.getOutputStream().write(action.getImagenEnBytes());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

}
