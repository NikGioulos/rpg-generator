package online.nikos.generator.rpg.generator;

import online.nikos.generator.rpg.ffd.Field;
import online.nikos.generator.rpg.filereader.SourceGenerator;
import online.nikos.generator.rpg.filereader.SourceMemberCreator;
import online.nikos.generator.rpg.ffd.Struct;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SourceMemberCreatorTest {

    @Autowired
    private SourceMemberCreator smg;

    @Before
    public void setUp() throws Exception {
        smg = new SourceMemberCreator(new SourceGenerator());
    }

    @Test
    public void test_createModule() throws IOException {
        smg.createSourceMember(testStruct(),"M");
    }

    @Test
    public void test_createPrototype() throws IOException {
        smg.createSourceMember(testStruct(),"P");
    }

    @Test
    public void test_createSrvPgm() throws IOException {
        smg.createSourceMember(testStruct(),"S");
    }

    @Test
    public void test_createBindingLanguage() throws IOException {
        smg.createSourceMember(testStruct(),"B");
    }

    @Test
    public void test_createSimpleClient() throws IOException {
        smg.createSourceMember(testStruct(),"CLN");
    }

    @Test
    public void test_allSources(){
        String source = "ALL";
        List<String> sources = source.equals("ALL") ? Arrays.asList("M","P","S") : Arrays.asList(source);
        Assert.assertEquals(3,sources.size());
        Assert.assertEquals("M",sources.get(0));
        Assert.assertEquals("P",sources.get(1));
        Assert.assertEquals("S",sources.get(2));
    }

    private Struct testStruct(){
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("CHAR1","text char 1"));
        fields.add(new Field("CHAR2","text char 2"));
        fields.add(new Field("PACKED1","text packed 1"));
        fields.add(new Field("PACKED2","text packed 2"));
        fields.add(new Field("ZONED1","text zoned 1"));
        fields.add(new Field("ZONED2","text zoned 2"));

        return new Struct("FILE001", "FILE001R", fields);
    }


}
