package util;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class StringConverterTest {

    @Test
    public void extractSetterNameTest() {
        String str = "setUserId";
        assertThat(StringConverter.extractMethodName(str)).isEqualTo("userId");
    }

    @Test
    public void createMethodTest() {
        assertThat(StringConverter.createMethod("get", "userId")).isEqualTo("getUserId");
    }

    @Test
    public void 정규식() {
        String regex = "[a-zA-Z]+ = ?";
        String query = "UPDATE USERS SET name = ?, email = ?, password WHERE userId = ?";
        Pattern pattern = Pattern.compile(regex);

        Matcher m = pattern.matcher(query);

        while(m.find()) {
            System.out.println(m.group().split(" ")[0]);
        }

    }
}
