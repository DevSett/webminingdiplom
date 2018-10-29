package automining.dao;

import automining.mapper.CardMapper;
import automining.mapper.RigMapper;
import automining.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CardDaoImpl implements CardDao {
    @Autowired
    public JdbcTemplate jdbcTemplate;


    @Override
    public void save(Card card) {

        String sql = "INSERT INTO card " +
                "(id,rigid,speed,TEMPERATURE,FANSPEED) " +
                "VALUES " +
                "(?,?,?,?,?)";


        jdbcTemplate.update(sql,
                card.getId(),
                card.getRigId(),
                card.getSpeed(),
                card.getTemperature(),
                card.getFanSpeed());
    }

    @Override
    public Card getById(int id) {
        String sql = "SELECT * FROM CARD WHERE id=?";

        try {
            return jdbcTemplate.queryForObject(sql, new CardMapper(), id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return new Card();
        }
    }

    @Override
    public List<Card> getByRigId(int id) {
        String sql = "SELECT * FROM Card WHERE rigid=?";

        try {
            return jdbcTemplate.query(sql, new CardMapper(), id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Card> findAll() {
        String sql = "SELECT * FROM CARD";

        try {
            return jdbcTemplate.query(sql, new CardMapper());
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return new ArrayList<>();
        }
    }



    @Override
    public void update(Card card) {

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM card WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public int count() {
        String sql = "SELECT count(*) FROM CARD";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
