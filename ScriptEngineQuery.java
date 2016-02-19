import javax.script.*;

public class ScriptEngineQuery{
    public static void main(String[] args){

        ScriptEngineManager manager = new ScriptEngineManager();

        for(ScriptEngineFactory factory : manager.getEngineFactories()){

            System.out.println("Engine: " + factory.getEngineName());
            System.out.println(
                    "Language: " 
                    + factory.getLanguageName()
                    + " (" + factory.getLanguageVersion() + ")" );

            System.out.println("Alternate Language Names:");
            for(String name : factory.getNames()){
                System.out.println("\t" + name);
                //manager.getEngineByName(name);
            }

            System.out.println("Recoginzed MIME Types:");
            for(String type : factory.getMimeTypes()){
                System.out.println("\t" + type);
                //manager.getEngineByMimeType(type);
            }

            System.out.println("Recognized Filename Extensions:");
            for(String ext : factory.getExtensions()){
                System.out.println("\t" + ext);
                //manager.getEngineByExtension(ext);
            }

            ScriptEngine engine = factory.getScriptEngine();

            if (engine instanceof Compilable){
                System.out.println(
                        "Engine can compile scripts for greater efficiency");
                //Compilable cEngine = (Compilable) engine;
            }

            if (engine instanceof Invocable){
                System.out.println(
                    "Engine allows Java to call functions defined in this language");
                //Invocable iEngine = (Invocable) engine;
            }

            System.out.println();
        }
    }
}

