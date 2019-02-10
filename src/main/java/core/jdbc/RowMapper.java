package core.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper <T> {
    T mapRow(ResultSet rs) throws DataAccessException, InvocationTargetException, IllegalAccessException, SQLException;
}
