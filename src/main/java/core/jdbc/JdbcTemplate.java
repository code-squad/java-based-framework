package core.jdbc;

import core.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// connection을 close하면 자동으로 이 것과 관련된 연결인 PreparedStatement, ResultSet이 닫힌다.

public class JdbcTemplate {

    public void update(String sql, Object... values) {
        update(sql, createPrepareStatementSetter(values));
    }

    public void update(String sql, PreparedStatementSetter preparedStatementSetter) {
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            preparedStatementSetter.setValues(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... values) {
        return query(sql, rowMapper, createPrepareStatementSetter(values));
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, PreparedStatementSetter preparedStatementSetter) {
        List<T> queryResult = new ArrayList<>();

        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            preparedStatementSetter.setValues(pstmt);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                queryResult.add(rowMapper.mapRow(rs));
            }
            return queryResult;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... values) {
        return queryForObject(sql, rowMapper, createPrepareStatementSetter(values));
    }

    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, PreparedStatementSetter preparedStatementSetter) {
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            preparedStatementSetter.setValues(pstmt);

            ResultSet rs = pstmt.executeQuery();

            T obj = null;
            if (rs.next()) {
                obj = rowMapper.mapRow(rs);
            }

            return obj;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    private PreparedStatementSetter createPrepareStatementSetter(Object... values) {
        PreparedStatementSetter preparedStatementSetter = pstmt -> {
            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i + 1, values[i]);
            }
        };

        return preparedStatementSetter;
    }

}
