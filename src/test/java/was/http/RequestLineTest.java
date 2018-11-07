package was.http;

import core.test.BaseTest;
import org.junit.Test;

public class RequestLineTest extends BaseTest {

    @Test
    public void create_method_get() {
        RequestLine line = new RequestLine("GET /index.html HTTP/1.1");
        softly.assertThat(line.getMethod()).isEqualTo(HttpMethod.GET);
        softly.assertThat(line.getPath()).isEqualTo("/index.html");
    }
    
    @Test
    public void create_method_post() {
        RequestLine line = new RequestLine("POST /index.html HTTP/1.1");
        softly.assertThat(line.getMethod()).isEqualTo(HttpMethod.POST);
        softly.assertThat(line.getPath()).isEqualTo("/index.html");
    }

    @Test
    public void create_path_and_params() {
        RequestLine line = new RequestLine("GET /user/create?userId=javajigi&password=pass HTTP/1.1");
        softly.assertThat(line.getMethod()).isEqualTo(HttpMethod.GET);
        softly.assertThat(line.getPath()).isEqualTo("/user/create");
        softly.assertThat(line.getQueryString()).isEqualTo("userId=javajigi&password=pass");
    }
}
