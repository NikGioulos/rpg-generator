package online.nikos.generator.rpg.filereader;

import online.nikos.generator.rpg.ffd.Struct;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

public class SourceGenerator {

    public String generate(Struct params, String sourceCodeTemplate) {

        VelocityEngine ve = initEngine();
        VelocityContext ctx = initContext(params);

        Template t = ve.getTemplate(sourceCodeTemplate);

        StringWriter sw = new StringWriter();

        t.merge(ctx, sw);

        return sw.toString();
    }

    private VelocityEngine initEngine() {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        return ve;
    }

    private VelocityContext initContext(Struct params){
        VelocityContext ctx = new VelocityContext();
        ctx.put("D","$");
        if(params != null) {
            ctx.put("ds", params);
        }
        return ctx;
    }

}
