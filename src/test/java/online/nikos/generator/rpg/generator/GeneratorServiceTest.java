package online.nikos.generator.rpg.generator;

import online.nikos.generator.rpg.ffd.Field;
import online.nikos.generator.rpg.ffd.GeneratorService;
import online.nikos.generator.rpg.ffd.Struct;
import online.nikos.generator.rpg.ffd.StructDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GeneratorServiceTest {

    private String library;
    private String filename;

    @Mock
    StructDao dao;

    @InjectMocks
    private GeneratorService service;

    @Before
    public void setUp() throws Exception {
        library = "QUSRSYS";
        filename = "QAEASTUI";
        when(dao.buildStruct(anyString(),anyString())).thenReturn(mockStruct(filename));
    }

    @After
    public void tearDown() {
        reset(dao);
    }

    @Test
    public void test_module(){
        String sourceType = "M"; //module
        String generatedSource = service.generateSource(library, filename, sourceType);

        Assert.assertTrue(generatedSource.contains("P find_"+filename));
        Assert.assertTrue(generatedSource.contains("P get_"));
    }

    @Test
    public void test_prototype(){
        String sourceType = "P"; //prototype
        String generatedSource = service.generateSource(library, filename, sourceType);

        Assert.assertTrue(generatedSource.contains("D find_"+filename));
        Assert.assertTrue(generatedSource.contains("D get_"));
    }

    private Struct mockStruct(String file){
        return Struct.builder()
                .fileName(file)
                .fields(mockFields())
                .build();
    }
    private List<Field> mockFields(){
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("STDID","student id"));
        fields.add(new Field("STDFRTNM","student first name"));
        fields.add(new Field("STDLSTNM","student last name"));
        fields.add(new Field("STDCRSID","student course id"));
        fields.add(new Field("STDAUDID","std auditor id"));
        fields.add(new Field("RSV","reserved"));
        return fields;
    }


}
