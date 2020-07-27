package online.nikos.generator.rpg.ffd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Struct {

    private String fileName;
    private String recordName;
    private List<Field> fields;
}
