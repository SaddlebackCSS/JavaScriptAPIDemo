import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.script.*;

public class ScriptRunner extends JFrame{
    public static void main(String[] args){
        new ScriptRunner();
    }

    private final ScriptEngineManager manager;

    private JComboBox<String> languageSelector;
    private JButton executeButton;
    private JTextArea codeArea;

    public ScriptRunner(){
        super();
        manager = new ScriptEngineManager();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setLayout(new GridLayout(2, 1));
        setLayout(new BorderLayout());
        add(createTopPanel(), BorderLayout.PAGE_START);
        add(createEditorPanel(), BorderLayout.CENTER);

        setVisible(true);
        pack();
    }

    private JPanel createTopPanel(){
        createComboBox();
        createExecuteButton();

        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(languageSelector);
        topPanel.add(executeButton);
        return topPanel;
    }

    private JScrollPane createEditorPanel(){
        codeArea = new JTextArea(24, 40);
        final JScrollPane pane = new JScrollPane(codeArea);
        pane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        return pane;
    }

    private void createComboBox(){
        languageSelector = new JComboBox<>();
        //XXX create list of available languages
        for(ScriptEngineFactory factory : manager.getEngineFactories()){
            languageSelector.addItem(factory.getLanguageName());
        }
        languageSelector.setEditable(false);
    }

    private void createExecuteButton(){
        executeButton = new JButton("execute");
        executeButton.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(final ActionEvent event){
                        //XXX get selected language
                        String language = (String)languageSelector.getSelectedItem();
                        if(language == null){
                            showMessage(
                                "Please select a language", 
                                "Select Language",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        //XXX get engine for selected language
                        ScriptEngine engine = manager.getEngineByName(language);
                        if(engine == null){
                            showMessage(
                                "Unknown language " + language, 
                                "Unknown Language",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        //TODO add any script bindings here

                        //XXX get script text
                        String script = codeArea.getText();

                        //XXX execute script
                        try{
                            engine.eval(script);
                        }
                        catch(ScriptException e){
                            showMessage(
                                e.getMessage(), 
                                "Error during execution",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
    }

    private void showMessage(String message, String title, int messageType){
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

}

