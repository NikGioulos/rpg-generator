package online.nikos.generator.rpg.ffd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StructDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Struct buildStruct(String lib, String file) {
        return Struct.builder()
                .fileName(file)
                .fields(getStructFields(file, lib))
                .build();
    }

    private List<Field> getStructFields(String struct, String schema) {
        String query = "SELECT SYSTEM_COLUMN_NAME "
                + ", COLUMN_TEXT  "
                + " FROM QSYS2.SYSCOLUMNS WHERE SYSTEM_TABLE_NAME = ? AND SYSTEM_TABLE_SCHEMA = ? ";

        return jdbcTemplate.query(query, new String[]{struct.toUpperCase(),schema.toUpperCase()}, new FieldRowMapper());
    }

}
