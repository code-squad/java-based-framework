package core.nmvc;

import core.annotation.Controller;
import core.annotation.RequestMapping;
import core.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import was.http.HttpRequest;
import was.http.HttpResponse;

@Controller
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @RequestMapping(value = "/users/findUserId", method = RequestMethod.GET)
    public String findUserId(HttpRequest request, HttpResponse response) {
        logger.debug("findUserId");
        return null;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String save(HttpRequest request, HttpResponse response) {
        logger.debug("save");
        return null;
    }
}
