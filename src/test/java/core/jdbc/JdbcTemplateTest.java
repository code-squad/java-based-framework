package core.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import support.UserFixture;
import user.dao.UserDao;
import user.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class JdbcTemplateTest {

    private static final Logger logger = getLogger(JdbcTemplateTest.class);

    private CrudTemplate<User> template;

    @Before
    public void setUp() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("db.sql"));
        DatabasePopulatorUtils.execute(databasePopulator, ConnectionManager.getDataSource());

        template = new CrudTemplate<User>();
    }

    @Test
    public void insertTest() throws IllegalAccessException, SQLException, InvocationTargetException, NoSuchMethodException {
        /* if 동시에 실행할 때, Connection is null */
        UserDao userDao = new UserDao();
        userDao.insert(UserFixture.DOBY);
    }

    @Test
    public void updateTest() throws IllegalAccessException, SQLException, InvocationTargetException, NoSuchMethodException {
        /* Connection is null */
        UserDao userDao = new UserDao();
        userDao.insert(UserFixture.DOBY);

        UserFixture.DOBY.setName("DOBYISFREE");
        userDao.update(UserFixture.DOBY);
    }

    @Test
    public void queryTest() throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException {
        String query = "SELECT * FROM USERS";
        List<User> users = template.setObject(new User()).query(query);
        assertThat(users.get(0).getName()).isEqualTo("자바지기");

        logger.debug(users.toString());
    }

    @Test
    public void queryForObjectTest() throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        /* org.h2.jdbc.JdbcSQLException: No data is available */
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId=?";
        User user = template.setObject(new User()).queryForObject(sql, "admin");
        assertThat(user.getName()).isEqualTo("자바지기");
        logger.debug("User : {}", user.toString());
    }

}
