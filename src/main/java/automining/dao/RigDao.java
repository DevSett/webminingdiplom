package automining.dao;

import automining.model.Rig;
import automining.model.User;

import java.util.List;

public interface RigDao {

    void save(Rig rig);

    Rig getById(int id);

    List<Rig> getByUserId(int id);

    List<Rig> findAll();

    void update(Rig rig);

    void delete(int id);


    int count();
}
