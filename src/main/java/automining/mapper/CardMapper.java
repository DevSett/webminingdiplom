package automining.mapper;

import automining.model.Card;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardMapper implements RowMapper<Card> {
    @Override
    public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
        Card card = new Card();
        card.setFanSpeed(rs.getInt("fanspeed"));
        card.setId(rs.getInt("id"));
        card.setSpeed(rs.getDouble("speed"));
        card.setRigId(rs.getInt("rigid"));
        card.setTemperature(rs.getInt("temperature"));
        return card;
    }
}
