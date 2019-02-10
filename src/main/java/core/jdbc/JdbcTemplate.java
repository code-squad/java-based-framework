package core.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface JdbcTemplate <T> {

    void execute(String query) throws DataAccessException;

    T queryForObject(String query, String key) throws DataAccessException;

    List<T> query(String query) throws DataAccessException;

}
