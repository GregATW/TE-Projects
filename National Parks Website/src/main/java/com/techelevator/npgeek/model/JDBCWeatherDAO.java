package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCWeatherDAO implements WeatherDAO {
	private JdbcTemplate jdbcTemplate;

	public JDBCWeatherDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Weather> getWeatherByParkCode(String parkCode) {
		String sql = "SELECT * FROM weather WHERE parkcode = ? ORDER BY fivedayforecastvalue ";
		List<Weather> fiveDayWeather = new ArrayList<Weather>();
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkCode);
		while (result.next()) {
			Weather weatherForecast = mapRowToWeather(result);
			fiveDayWeather.add(weatherForecast);
		}
		return fiveDayWeather;
	}

	private Weather mapRowToWeather(SqlRowSet result) {
		Weather weather = new Weather();
		weather.setParkCode(result.getString("parkcode"));
		weather.setFiveDayForecastValue(result.getInt("fivedayforecastvalue"));
		weather.setLow(result.getInt("low"));
		weather.setHigh(result.getInt("high"));
		weather.setForecast(result.getString("forecast"));

		return weather;
	}
}
