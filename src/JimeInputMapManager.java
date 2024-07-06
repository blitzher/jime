import java.awt.event.*;
import java.nio.file.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import util.*;

public class JimeInputMapManager {
    private final Path keyMapsPath = Path.of("./keymaps.json");
    private JSONParser parser;
    private InputMap inputMapRef;
    private ActionMap actionMapRef;

    private JimeFrame frameRef;
    private JimeEditor editorRef;

    public JimeInputMapManager() {
        parser = new JSONParser();

    }

    public void Bind(JimeFrame frame) {
        frameRef = frame;
        editorRef = frame.getEditor();
        inputMapRef = editorRef.getTextArea().getInputMap(JComponent.WHEN_FOCUSED);

        this.AddDefaultKeybindingsTo(inputMapRef);
        // this.Load();

        actionMapRef = editorRef.getTextArea().getActionMap();
        // TODO: Bind actions to relevant components, i.e. editor, file explorer, etc.

        this.Save();
        this.AddActionsTo(actionMapRef);

    }

    private void AddDefaultKeybindingsTo(InputMap inputMap) {
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.ALT_DOWN_MASK),
                "jime.editor.moveLineUp");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.ALT_DOWN_MASK),
                "jime.editor.moveLineDown");
    }

    private void AddActionsTo(ActionMap actionMap) {
        actionMap.put("jime.editor.moveLineUp", new JimeAction("jime.editor.moveLineUp") {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Move line up");
                frameRef.getEditor().moveLine(editorRef.getCaretLineNumber(), -1);

            }
        });
        actionMap.put("jime.editor.moveLineDown", new JimeAction("jime.editor.moveLineDown") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Move line down");
                frameRef.getEditor().moveLine(editorRef.getCaretLineNumber(), +1);
            }
        });
    }

    public void Load() {
        if (Files.exists(keyMapsPath)) {
            try {
                String keyMapsJson = FileUtils.ReadFile(keyMapsPath);
                JSONArray keyMapsArray = (JSONArray) parser.parse(keyMapsJson);
                for (Object keyMap : keyMapsArray) {
                    JSONObject keyMapObj = (JSONObject) keyMap;
                    String key = (String) keyMapObj.get("key");
                    String action = (String) keyMapObj.get("action");
                    KeyStroke keyStroke = KeyStroke.getKeyStroke(key);
                    inputMapRef.put(keyStroke, action);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    // JSONArray and JSONObject are unparameterized, so warning is suppressed
    @SuppressWarnings("unchecked")
    public void Save() {
        JSONArray keyMapsArray = new JSONArray();
        for (final KeyStroke stroke : inputMapRef.allKeys()) {
            String key = stroke.toString();
            String action = inputMapRef.get(stroke).toString();
            JSONObject obj = new JSONObject();
            obj.put("key", key);
            obj.put("action", action);
            keyMapsArray.add(obj);
        }
        FileUtils.WriteFile(keyMapsPath, keyMapsArray.toJSONString());
    }
}
