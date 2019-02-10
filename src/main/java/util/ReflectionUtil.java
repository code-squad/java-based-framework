package util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReflectionUtil {
    public static List<Method> obtainMethod(String startWith, Object object) {
        return Stream.of(object.getClass().getDeclaredMethods())
                .filter(m -> m.getName().startsWith(startWith))
                .collect(Collectors.toList());
    }
}
