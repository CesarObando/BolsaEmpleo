package Actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
public class PrincipalAction extends ActionSupport implements Preparable {

    public PrincipalAction() {
    }

    public String execute() throws Exception {
        return SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
    }

}
