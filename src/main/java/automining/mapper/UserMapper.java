package automining.mapper;

import automining.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setEmail(resultSet.getString("email"));
        user.setKeyTelegram(resultSet.getString("keytelegram"));
        user.setKeyUser(resultSet.getString("keyuser"));
        user.setRight(resultSet.getInt("right"));
        user.setPassword(resultSet.getString("password"));
        user.setAccount(resultSet.getString("account"));

        return user;
    }
}
