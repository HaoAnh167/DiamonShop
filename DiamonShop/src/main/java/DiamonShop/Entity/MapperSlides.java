package DiamonShop.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperSlides implements RowMapper<Slides> {

	public Slides mapRow(ResultSet rs, int rowNum) throws SQLException {
		Slides slides = new Slides();
		slides.setId(rs.getInt("id"));
		slides.setImage(rs.getString("image"));
		slides.setTitle(rs.getString("title"));
		slides.setDescription(rs.getString("description"));
		return slides;
	}
	
}
