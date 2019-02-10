package core.jdbc;

import org.slf4j.Logger;
import util.ReflectionUtil;
import util.StringConverter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

public class CrudTemplate <T> implements JdbcTemplate, RowMapper, PreparedStatementSetter {

    private static final Logger logger = getLogger(CrudTemplate.class);

    private T object;

    public CrudTemplate() {

    }

    public CrudTemplate<T> setObject(T object) {
        this.object = object;
        return this;
    }

    /* insert, update */
    @Override
    public void execute(String query) throws DataAccessException {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            setValues(pstmt, query);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /* select all */
    @Override
    public List<T> query(String query) throws DataAccessException {
        List<T> results = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){
            results.add(mapRow(pstmt.executeQuery()));
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
        return results;
    }

    @Override
    public T queryForObject(String query, String key) throws DataAccessException {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){
            setValue(pstmt, 1, key);
            return mapRow(pstmt.executeQuery());
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /* insert, update */
    @Override
    public void setValues(PreparedStatement pstmt, String query)
            throws InvocationTargetException, IllegalAccessException, SQLException, NoSuchMethodException {
        List<String> params = obtainMappingValue(query);
        for(int i = 1; i <= params.size(); i++) {
            Method method = this.object.getClass().getDeclaredMethod(StringConverter.createMethod("get", params.get(i - 1)), null);
            setValue(pstmt, i, (String)method.invoke(this.object));
        }
    }

    /* insert, update, select */
    @Override
    public void setValue(PreparedStatement pstmt, int seq, String value) throws SQLException {
        pstmt.setString(seq, value);
    }

    /* select all, select */
    @Override
    public T mapRow(ResultSet rs) throws InvocationTargetException, IllegalAccessException, SQLException {
        List<Method> methods = ReflectionUtil.obtainMethod("set", object);
        while(rs.next()) {
            for (Method method : methods) {
                method.invoke(object, rs.getString(StringConverter.extractMethodName(method.getName())));
            }
        }

        return object;
    }

    /* select update */
    public List<String> obtainMappingValue(String query) {
        /* UPDATE 경우에만 가능 */
        if(query.startsWith("UPDATE")) {
            List<String> params = new ArrayList<>();
            String regex = "[a-zA-Z]+ = ?";
            Matcher m = Pattern.compile(regex).matcher(query);
            while(m.find()) {
                System.out.println(m.group()+"&&&&&");
                params.add(m.group().split(" ")[0]);
            }
            return params;
        }

        return Stream.of(query.split("VALUES")[0].split("\\(")[1].split("\\)")[0].split(","))
                .map(p -> p.trim()).collect(Collectors.toList());
    }
}
