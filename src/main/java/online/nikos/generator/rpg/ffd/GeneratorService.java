package online.nikos.generator.rpg.ffd;

import online.nikos.generator.rpg.filereader.SourceGenerator;
import online.nikos.generator.rpg.filereader.SourceMemberConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneratorService {

    @Autowired
    private StructDao dao;

    public String generateSource(String lib, String file, String sourceType) {
        List<String> sources = sourceType.equals("ALL") ?
                Arrays.asList("M","P","S","B")
                : Arrays.asList(sourceType.toUpperCase());

        Struct struct = dao.buildStruct(lib,file);

        SourceGenerator sg = new SourceGenerator();

        return sources.stream()
                .map(s->sg.generate(struct,getTemplateName(s)))
                .collect(Collectors.joining("////////---////////\n"));
    }

    private String getTemplateName(String sourceType){
        return SourceMemberConfig.getTemplateName(sourceType);
    }

}
