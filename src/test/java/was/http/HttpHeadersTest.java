package was.http;

import core.test.BaseTest;
import org.junit.Test;

public class HttpHeadersTest extends BaseTest {
	@Test
	public void add() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Connection: keep-alive");
		softly.assertThat(headers.getHeader("Connection")).isEqualTo("keep-alive");
	}
}
