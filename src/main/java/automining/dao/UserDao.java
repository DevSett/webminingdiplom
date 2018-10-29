package automining.dao;

import automining.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {

    void save(User user);

    User getById(int id);

    List<User> findAll();

    void update(User user);

    void delete(int id);

    User getByLogin(String login);

    int countUser();
}
