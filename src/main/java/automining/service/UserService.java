package automining.service;

import automining.model.User;

import javax.servlet.http.Cookie;
import java.util.List;

public interface UserService {

    void save(User user);

    User getById(int id);

    List<User> findAll();

    void update(User user);

    void delete(int id);

    User getByLogin(String login);

    boolean validateUser(User user);

    Cookie checkCookie(Cookie[] cookies);

    boolean validateCreateUser(User user);

    boolean validateDeleteUser(String login);
}
