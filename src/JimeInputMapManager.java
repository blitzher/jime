import java.awt.event.*;
import java.nio.file.*;
import javax.swing.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import util.*;

public class JimeInputMapManager implements KeyListener {
    private final Path keyMapsPath = Path.of("./keymaps.json");
    private JSONParser parser;
    private InputMap inputMapRef;
    private ActionMap actionMapRef;

    public JimeInputMapManager() {
        parser = new JSONParser();

    }

    public void Bind(JimeFrame frame) {
        inputMapRef = frame.getJFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.Load();
        this.AddDefaultKeybindings(inputMapRef);

        actionMapRef = frame.getJFrame().getRootPane().getActionMap();
        // TODO: Bind actions to relevant components, i.e. editor, file explorer, etc.

        this.Save();
        this.AddActionsTo(actionMapRef);

    }

    private void AddDefaultKeybindings(InputMap inputMap) {
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.ALT_DOWN_MASK),
                "jime.editor.moveLineUp");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.ALT_DOWN_MASK),
                "jime.editor.moveLineDown");
    }

    private void AddActionsTo(ActionMap actionMap) {
        actionMap.put("jime.editor.moveLineUp", new JimeAction("jime.editor.moveLineUp") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Move line up");
            }
        });
        actionMap.put("jime.editor.moveLineDown", new JimeAction("jime.editor.moveLineDown") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Move line down");
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

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Key typed: " + e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        System.out.println("Key pressed: " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Key released: " + e.getKeyCode());
    }
}
