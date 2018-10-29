package automining.mapper;

import automining.model.Rig;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RigMapper implements RowMapper<Rig> {
    @Override
    public Rig mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rig statistic = new Rig();
        statistic.setId(rs.getInt("id"));
        statistic.setAverageSpeed(rs.getDouble("averagespeed"));
        statistic.setAverageTemperature(rs.getInt("averagetemperature"));
        statistic.setLastUpdate(rs.getString("lastupdate"));
        statistic.setUserId(rs.getInt("userid"));
        statistic.setNumberVideoCards(rs.getInt("numbervideocards"));
        statistic.setName(rs.getString("name"));
        return statistic;
    }
}
