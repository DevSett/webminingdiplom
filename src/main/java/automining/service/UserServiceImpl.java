package automining.service;

import automining.dao.UserDao;
import automining.model.User;
import automining.security.Cryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public boolean validateUser(User user) {
        User userDb = getByLogin(user.getLogin());

        if (userDb != null &&
                userDb.getPassword() != null &&
                userDb.getPassword().equals(Cryption.crypt(user.getPassword()))) return true;
        else {
            return false;
        }
    }

    @Override
    public Cookie checkCookie(Cookie[] cookies) {
        if (cookies == null) return null;

        String username = null;
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    return cookie;
                }
            }
        return null;
    }

    @Override
    public boolean validateCreateUser(User user) {
        if (getByLogin(user.getLogin()).getLogin() != null) {
            return false;
        }

        User userWithGeneratedKeys = generateKeys(user);
        if (user.getRight() != 666) {
            ServerServiceImpl.addUser(userWithGeneratedKeys);
            ServerServiceImpl.addMoney(userWithGeneratedKeys, "0");
        }
        save(userWithGeneratedKeys);
        return true;
    }

    @Override
    public boolean validateDeleteUser(String login) {
        if (getByLogin(login).getLogin() == null) return false;

        userDao.delete(getByLogin(login).getId());
        return true;
    }

    private User generateKeys(User user) {
        user.setKeyUser(getSaltString(50, null));
        user.setKeyTelegram(getSaltString(10, "1234567890"));
        user.setId(userDao.countUser() + 1);
        return user;
    }

    private String getSaltString(int length, String salts) {
        String SALTCHARS = salts == null ? "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890" : salts;
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
