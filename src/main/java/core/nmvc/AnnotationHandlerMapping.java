package core.nmvc;

import com.google.common.collect.Maps;
import core.annotation.RequestMethod;
import was.http.HttpMethod;
import was.http.HttpRequest;

import java.util.Map;

public class AnnotationHandlerMapping {
    private Object[] basePackage;

    private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {

    }

    public HandlerExecution getHandler(HttpRequest request) {
        String requestUri = request.getPath();
        RequestMethod rm = RequestMethod.valueOf(request.getMethod().toString());
        return handlerExecutions.get(new HandlerKey(requestUri, rm));
    }
}
