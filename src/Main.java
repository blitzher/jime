import javax.swing.UIManager;

import java.util.Vector;

import javax.swing.JFrame;

public class Main {

    public final static int WIDTH = 400;
    public final static int HEIGHT = 400;
    private static Vector<JimeEditor> editors = new Vector<JimeEditor>();

    public static void main(String[] args) {

        try {
            // Use the native top bar for Mac OS
            if (System.getProperty("os.name").startsWith("Mac"))
                System.setProperty("apple.laf.useScreenMenuBar", "true");

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        newEditor();
    }

    private static JimeEditor newEditor() {
        JimeEditor editor = new JimeEditor(WIDTH, HEIGHT);

        // Print the number of editors
        System.out.println(String.format("Number of editors: %d", editors.size()));

        editor.setVisible(true);
        editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        editor.menuBar.emitter.on(JimeEnum.NewFile, () -> {
            newEditor();
        });
        editors.add(editor);
        return editor;
    }
}