package anogueira.offline.geolocator.cache.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import anogueira.offline.geolocator.Coordinate;
import anogueira.offline.geolocator.cache.PositionInfo;

/**
 * An implementation of the PositionInfoDAO interface using the
 * Spring Framework JDBC abstraction
 *   
 * @author Andre Nogueira
 */
class JdbcTemplatePositionDAO implements PositionInfoDAO {

	private final String INSERT = "INSERT INTO PositionInfo VALUES (?,?,?,?)";
	private final String FIND   = "SELECT * FROM PositionInfo WHERE region LIKE ? AND location LIKE ? ";

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplatePositionDAO(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public PositionInfo find(String region, String location) {
		try{
			return jdbcTemplate.queryForObject(FIND, new PositionRowMapper(),
					region, location);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}	
	}

	@Override
	public void insert(PositionInfo position) {
		jdbcTemplate.update(INSERT, position.getRegion(),position.getLocation(),
				position.getCoordinate().getLatitude(),position.getCoordinate().getLongitude());
	}


	/**
	 * Represents a row in the PositionInfo table
	 * @author Andre Nogueira
	 */
	private class PositionRowMapper implements RowMapper<PositionInfo>{
		public PositionInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			PositionInfo position = new PositionInfo(
					rs.getString("region"),
					rs.getString("location"),
					new Coordinate(rs.getDouble("latitude"),rs.getDouble("longitude")));
			return position;
		}
	}
}
