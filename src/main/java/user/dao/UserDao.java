package user.dao;

import core.jdbc.CrudTemplate;
import user.domain.User;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UserDao {

    private CrudTemplate<User> crudTemplate;

    public UserDao() {
        crudTemplate = new CrudTemplate<>();
    }

    public void insert(User user) throws IllegalAccessException, SQLException, InvocationTargetException, NoSuchMethodException {
        String query = "INSERT INTO USERS (name, email, userId, password) VALUES (?, ?, ?, ?)";
        crudTemplate.setObject(user).execute(query);
    }

    public void update(User user) throws IllegalAccessException, SQLException, InvocationTargetException, NoSuchMethodException {
        String query = "UPDATE USERS SET name = ?, email = ?, password = ?WHERE userId = ?";
        crudTemplate.setObject(user).execute(query);
    }
}

