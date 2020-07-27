package online.nikos.generator.rpg.filereader;

import online.nikos.generator.rpg.ffd.Struct;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SourceMemberCreator {

    private SourceGenerator generator;

    public SourceMemberCreator(SourceGenerator generator) {
        this.generator = generator;
    }

    public void createSourceMember(Struct params, String sourceType) throws IOException {

        List<String> sources = sourceType.equals("ALL") ?
                Arrays.asList("M","P","S","B")
                : Arrays.asList(sourceType.toUpperCase());

        for(String s:sources){
            generateSourceCode(params,
                    SourceMemberConfig.getTemplateName(s),
                    SourceMemberConfig.getMemberName(s, params.getFileName()));
        }
    }

    private void generateSourceCode(Struct params,
                                    String sourceCodeTemplate, String generatedFilename) throws IOException {

        String source = generator.generate(params, sourceCodeTemplate);

        if(generatedFilename != null) {
            FileWriter fw = new FileWriter(generatedFilename);
            fw.write(source);
            fw.close();
        }
    }

}
