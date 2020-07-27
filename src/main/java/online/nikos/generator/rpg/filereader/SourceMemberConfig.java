package online.nikos.generator.rpg.filereader;

public class SourceMemberConfig {

    private static final String templatePath = "templates/velocity/";
    private static final String genCodePath = "src/main/java/online/nikos/generator/rpg/gen/";

    public static String getTemplateName(String sourceType){
        String template = null;
        if(sourceType.equals("M")){
            template = templatePath + "getter_module.vm";
        }else if(sourceType.equals("P")){
            template = templatePath + "getter_prototype.vm";
        }else if(sourceType.equals("S")){
            template = templatePath + "getter_srvpgm.vm";
        }else if(sourceType.equals("B")){
            template = templatePath + "getter_bindingLanguage.vm";
        }else if(sourceType.equals("CLN")){
            template = templatePath + "getter_client.vm";
        }else {
            throw new RuntimeException("invalid sourceType");
        }
        return template;
    }

    public static String getMemberName(String sourceType, String filename){
        String suffix = null;
        if(sourceType.equals("M")){
           suffix = "M.rpgle";
        }else if(sourceType.equals("P")){
            suffix = "P.rpgleinc";
        }else if(sourceType.equals("S")){
            suffix = "S.txt";
        }else if(sourceType.equals("B")){
            suffix = "B.txt";
        }else if(sourceType.equals("CLN")){
            suffix = "CLN.rpgle";
        }else {
            throw new RuntimeException("invalid sourceType");
        }
        return genCodePath + filename + suffix;
    }

}
