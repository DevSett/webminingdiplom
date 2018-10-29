package automining.service;

import automining.model.Rig;
import automining.model.User;

import java.util.List;

public interface RigService {
    void save(Rig rig);

    Rig getById(int id);

    List<Rig> getByUserId(int id);

    List<Rig> findAll();

    void update(Rig rig);

    void delete(int id);

    void updateInfo(String info, User user);
}
