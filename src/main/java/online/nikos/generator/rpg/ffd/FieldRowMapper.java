package online.nikos.generator.rpg.ffd;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FieldRowMapper implements RowMapper<Field> {

    @Override
    public Field mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Field(
                trimmed( resultSet.getString("SYSTEM_COLUMN_NAME") ),
                trimmed( resultSet.getString("COLUMN_TEXT") )
        );
    }

    private String trimmed(String dbValue) {
        return (dbValue!=null)?dbValue.trim():null;
    }
}
