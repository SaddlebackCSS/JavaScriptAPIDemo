
import javax.script.*;

public class BindingsDemo {

    public static class HTMLDocument {

        private int n = 0;
        public void write(String text) {
            System.out.println(text);
        }
        
        public void increment(){
            System.out.println("N is now " + n);
            n++;
        }
    }

    public static void main(String[] args) throws ScriptException, IOException {
        singleBindingsDemo();
        System.out.println("");
        multiBindingsDemo();
    }

    private static void singleBindingsDemo() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine js = manager.getEngineByName("js");

        HTMLDocument doc = new HTMLDocument();
        js.put("document", doc);
        js.eval("document.write(\"Hello from JavaScript\");");
    }

    private static void multiBindingsDemo() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        HTMLDocument doc = new HTMLDocument();
        manager.put("document", doc);

        ScriptEngine js = manager.getEngineByName("js");
        ScriptEngine py = manager.getEngineByName("python");
        ScriptEngine rb = manager.getEngineByName("ruby");
        js.eval("document.write(\"Hello from JavaScript\");document.increment();");
        py.eval("document.write(\"Hello from Python\");document.increment();");
        rb.eval("$document.write(\"Hello from Ruby\");$document.increment();");
    }
}
