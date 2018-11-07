package was.http;

import core.test.BaseTest;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class HttpRequestTest extends BaseTest {
    private String testDirectory = "./src/test/resources/";

    @Test
    public void request_GET() throws Exception {
        InputStream in = new FileInputStream(new File(testDirectory + "Http_GET.txt"));
        HttpRequest request = new HttpRequest(in);

        softly.assertThat(request.getMethod()).isEqualTo(HttpMethod.GET);
        softly.assertThat(request.getPath()).isEqualTo("/user/create");
        softly.assertThat(request.getHeader("Connection")).isEqualTo("keep-alive");
        softly.assertThat(request.getParameter("userId")).isEqualTo("javajigi");
    }

    @Test
    public void request_POST() throws Exception {
        InputStream in = new FileInputStream(new File(testDirectory + "Http_POST.txt"));
        HttpRequest request = new HttpRequest(in);

        softly.assertThat(request.getMethod()).isEqualTo(HttpMethod.POST);
        softly.assertThat(request.getPath()).isEqualTo("/user/create");
        softly.assertThat(request.getHeader("Connection")).isEqualTo("keep-alive");
        softly.assertThat(request.getParameter("userId")).isEqualTo("javajigi");
    }
    
    @Test
    public void request_POST2() throws Exception {
        InputStream in = new FileInputStream(new File(testDirectory + "Http_POST2.txt"));
        HttpRequest request = new HttpRequest(in);

        softly.assertThat(request.getMethod()).isEqualTo(HttpMethod.POST);
        softly.assertThat(request.getPath()).isEqualTo("/user/create");
        softly.assertThat(request.getHeader("Connection")).isEqualTo("keep-alive");
        softly.assertThat(request.getParameter("id")).isEqualTo("1");
        softly.assertThat(request.getParameter("userId")).isEqualTo("javajigi");
    }
}
