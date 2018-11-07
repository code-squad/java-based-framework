package was.http;

import static org.junit.Assert.*;

import core.test.BaseTest;
import org.junit.Test;

public class RequestParamsTest extends BaseTest {
	@Test
	public void add() throws Exception {
		RequestParams params = new RequestParams();
		params.addQueryString("id=1");
		params.addBody("userId=javajigi&password=password");

		softly.assertThat(params.getParameter("id")).isEqualTo("1");
		softly.assertThat(params.getParameter("userId")).isEqualTo("javajigi");
		softly.assertThat(params.getParameter("password")).isEqualTo("password");
	}
}
