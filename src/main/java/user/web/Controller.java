package user.web;

import was.http.HttpRequest;
import was.http.HttpResponse;

public interface Controller {
    void service(HttpRequest request, HttpResponse response);
}
