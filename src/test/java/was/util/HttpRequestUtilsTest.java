package was.util;

import core.test.BaseTest;
import org.junit.Test;
import was.util.HttpRequestUtils.Pair;

import java.util.Map;

public class HttpRequestUtilsTest extends BaseTest {
    @Test
    public void parseQueryString() {
        String queryString = "userId=javajigi";
        Map<String, String> parameters = HttpRequestUtils.parseQueryString(queryString);
        softly.assertThat(parameters.get("userId")).isEqualTo("javajigi");
        softly.assertThat(parameters.get("password")).isNull();

        queryString = "userId=javajigi&password=password2";
        parameters = HttpRequestUtils.parseQueryString(queryString);
        softly.assertThat(parameters.get("userId")).isEqualTo("javajigi");
        softly.assertThat(parameters.get("password")).isEqualTo("password2");
    }

    @Test
    public void parseQueryString_null() {
        Map<String, String> parameters = HttpRequestUtils.parseQueryString(null);
        softly.assertThat(parameters).isEmpty();

        parameters = HttpRequestUtils.parseQueryString("");
        softly.assertThat(parameters).isEmpty();

        parameters = HttpRequestUtils.parseQueryString(" ");
        softly.assertThat(parameters).isEmpty();
    }

    @Test
    public void parseQueryString_invalid() {
        String queryString = "userId=javajigi&password";
        Map<String, String> parameters = HttpRequestUtils.parseQueryString(queryString);
        softly.assertThat(parameters.get("userId")).isEqualTo("javajigi");
        softly.assertThat(parameters.get("password")).isNull();
    }

    @Test
    public void parseCookies() {
        String cookies = "logined=true; JSessionId=1234";
        Map<String, String> parameters = HttpRequestUtils.parseCookies(cookies);
        softly.assertThat(parameters.get("logined")).isEqualTo("true");
        softly.assertThat(parameters.get("JSessionId")).isEqualTo("1234");
        softly.assertThat(parameters.get("session")).isNull();
    }

    @Test
    public void getKeyValue() throws Exception {
        Pair pair = HttpRequestUtils.getKeyValue("userId=javajigi", "=");
        softly.assertThat(pair).isEqualTo(new Pair("userId", "javajigi"));
    }

    @Test
    public void getKeyValue_invalid() throws Exception {
        Pair pair = HttpRequestUtils.getKeyValue("userId", "=");
        softly.assertThat(pair).isNull();
    }

    @Test
    public void parseHeader() throws Exception {
        String header = "Content-Length: 59";
        Pair pair = HttpRequestUtils.parseHeader(header);
        softly.assertThat(pair).isEqualTo(new Pair("Content-Length", "59"));
    }
}
