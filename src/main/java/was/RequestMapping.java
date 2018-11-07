package was;

import java.util.HashMap;
import java.util.Map;

import user.web.Controller;
import user.web.CreateUserController;
import user.web.ListUserController;
import user.web.LoginController;

public class RequestMapping {
    private static Map<String, Controller> controllers = new HashMap<String, Controller>();

    static {
        controllers.put("/user/create", new CreateUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/list", new ListUserController());
    }

    public static Controller getController(String requestUrl) {
        return controllers.get(requestUrl);
    }
}
