package automining.dao;

import automining.mapper.UserMapper;
import automining.model.User;
import automining.security.Cryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;


    @Override
    public void save(User user) {

        String sql = "INSERT INTO users " +
                "(id,login,password,keyuser,keytelegram,email,right,account) " +
                "VALUES " +
                "(?,?,?,?,?,?,?,?)";


        jdbcTemplate.update(sql,
                user.getId(),
                user.getLogin(),
                Cryption.crypt(user.getPassword()),
                user.getKeyUser(),
                user.getKeyTelegram(),
                user.getEmail(),
                user.getRight(),
                user.getAccount());

    }

    @Override
    public User getById(int id) {
        String sql = "SELECT * FROM users WHERE id=?";

        User user = new User();

        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return user;
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users ";

        try {
            return jdbcTemplate.query(sql, new UserMapper());
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return new ArrayList<>();
        }
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public User getByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login=?";

        User user = new User();

        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), login);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return user;
        }

    }

    @Override
    public int countUser() {
        String sql = "SELECT count(*) FROM users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
