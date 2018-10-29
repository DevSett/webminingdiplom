package automining.dao;

import automining.mapper.RigMapper;
import automining.model.Rig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class RigDaoImpl implements RigDao {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public void save(Rig rig) {

        String sql = "INSERT INTO rig " +
                "(id,userid,name,AVERAGESPEED,AVERAGETEMPERATURE,NUMBERVIDEOCARDS,LASTUPDATE) " +
                "VALUES " +
                "(?,?,?,?,?,?,?)";


        jdbcTemplate.update(sql,
                rig.getId(),
                rig.getUserId(),
                rig.getName(),
                rig.getAverageSpeed(),
                rig.getAverageTemperature(),
                rig.getNumberVideoCards(),
                rig.getLastUpdate());
    }

    @Override
    public Rig getById(int id) {
        String sql = "SELECT * FROM RIG WHERE id=?";

        try {
            return jdbcTemplate.queryForObject(sql, new RigMapper(), id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException){
            return new Rig();
        }

    }

    @Override
    public List<Rig> getByUserId(int id) {

        String sql = "SELECT * FROM Rig WHERE userid=?";

        try {
            return jdbcTemplate.query(sql, new RigMapper(), id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException){
            return new ArrayList<>();
        }
    }

    @Override
    public List<Rig> findAll() {
        String sql = "SELECT * FROM RIG";

        try {
            return jdbcTemplate.query(sql, new RigMapper());
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Rig rig) {

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM rig WHERE id=?";
        jdbcTemplate.update(sql, id);
    }



    @Override
    public int count() {
        String sql = "SELECT count(*) FROM rig";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
