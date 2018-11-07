package user.web;

import was.http.HttpRequest;
import was.http.HttpResponse;
import user.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import user.dao.DataBase;

public class CreateUserController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        User user = new User(request.getParameter("userId"), request.getParameter("password"),
                request.getParameter("name"), request.getParameter("email"));
        log.debug("user : {}", user);
        DataBase.addUser(user);
        response.sendRedirect("/index.html");
    }
}