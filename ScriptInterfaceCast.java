import javax.script.*;

public class ScriptInterfaceCast{
    public static void main(String[] args) throws Exception{
        ScriptEngineManager manager = new ScriptEngineManager();

        ScriptEngine js = manager.getEngineByName("javascript");
        String script
            = "function JSRunnable(){\n"
            + "  this.run = function(){\n"
            + "    java.lang.System.out.println(\"Hello from JS Runnable\");\n"
            + "  }\n"
            + "}\n"
            + "myJSRunnable = new JSRunnable();\n"
            + "\n";

        js.eval(script);
        Runnable myJSRunnable = ((Invocable)js).getInterface(
                js.get("myJSRunnable"),
                Runnable.class);
        myJSRunnable.run();
    }
}

