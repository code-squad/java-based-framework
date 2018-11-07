package core.nmvc;

import org.junit.Before;
import org.junit.Test;
import was.http.HttpRequest;
import was.http.HttpResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class AnnotationHandlerMappingTest {
    private String testDirectory = "./src/test/resources/";

    private AnnotationHandlerMapping handlerMapping;

    @Before
    public void setup() {
        handlerMapping = new AnnotationHandlerMapping("core.nmvc");
        handlerMapping.initialize();
    }

    @Test
    public void getHandler() throws Exception {
        InputStream in = new FileInputStream(new File(testDirectory + "Http_NMVC.txt"));
        HttpRequest request = new HttpRequest(in);
        HttpResponse response = null;
        HandlerExecution execution = handlerMapping.getHandler(request);
        execution.handle(request, response);
    }
}
