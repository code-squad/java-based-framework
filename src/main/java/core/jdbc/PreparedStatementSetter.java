package core.jdbc;

import util.StringConverter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface PreparedStatementSetter {
    void setValues(PreparedStatement pstmt, String query) throws InvocationTargetException, IllegalAccessException, SQLException, NoSuchMethodException;

    void setValue(PreparedStatement pstmt, int seq, String value) throws SQLException ;
}
