package online.nikos.generator.rpg.ffd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/v1/generator", produces = "application/json")
public class GeneratorController {

    @Autowired
    private GeneratorService service;

    @GetMapping(value = "/getter/{lib}/{file}/{source}", produces = "text/plain")
    public ResponseEntity<String> generateGetter(@PathVariable("lib") String lib,
                                                 @PathVariable("file") String file,
                                                 @PathVariable("source") String source) {
        String generatedSource = service.generateSource(lib,file,source);
        return new ResponseEntity(generatedSource, HttpStatus.OK);
    }

}
