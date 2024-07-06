import java.awt.FileDialog;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

import util.FileUtils;

public class JimeMenuBar implements ActionListener {

    private Runnable onNewFile = null;
    private Runnable onOpenFile = null;
    private Runnable onSaveFile = null;

    private JimeFrame frameRef;
    private MenuBar menuBar;

    public void setOnNewFile(Runnable delegate) {
        onNewFile = delegate;
    }

    public void setOnOpenFile(Runnable delegate) {
        onOpenFile = delegate;
    }

    @Deprecated
    public void setOnSaveFile(Runnable delegate) {
        onSaveFile = delegate;
    }

    public JimeMenuBar() {
        menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem menuItemNew = new MenuItem("New");
        MenuItem menuItemOpen = new MenuItem("Open");
        MenuItem menuItemSave = new MenuItem("Save");
        // JMenuItem menuItemPrint = new JMenuItem("Print");

        menuItemNew.setShortcut(new MenuShortcut('N'));
        menuItemOpen.setShortcut(new MenuShortcut('O'));
        menuItemSave.setShortcut(new MenuShortcut('S'));

        menuItemNew.addActionListener(this);
        menuItemOpen.addActionListener(this);
        menuItemSave.addActionListener(this);

        fileMenu.add(menuItemNew);
        fileMenu.add(menuItemOpen);
        fileMenu.add(menuItemSave);

        menuBar.add(fileMenu);
    }

    public void Bind(JimeFrame frame) {
        frameRef = frame;
        frame.getJFrame().setMenuBar(menuBar);

    }

    public void actionPerformed(ActionEvent e) {
        // System.out.println(String.format("ActionEvent: %s", e.getActionCommand()));

        if (e.getActionCommand().equals("New")) {
            JimeEditor editor = frameRef.getEditor();
            editor.getTextArea().setText("");
            editor.setCurrentFilePath(null);
            if (this.onNewFile != null)
                this.onNewFile.run();
        } else if (e.getActionCommand().equals("Open")) {
            // Open file dialog
            FileDialog fd = new FileDialog(frameRef.getJFrame(), "Choose a file", FileDialog.LOAD);
            fd.setDirectory(".");
            fd.setVisible(true);
            String filename = fd.getFile();
            if (filename == null)
                return;
            String directory = fd.getDirectory();
            frameRef.setRootPath(Path.of(directory));

            if (this.onOpenFile != null)
                this.onOpenFile.run();
        } else if (e.getActionCommand().equals("Save")) {
            JimeEditor editor = frameRef.getEditor();
            if (editor.getCurrentFilePath() == null) {
                FileDialog fd = new FileDialog(frameRef.getJFrame(), "Save file", FileDialog.SAVE);
                fd.setDirectory(".");
                fd.setVisible(true);
                String filename = fd.getFile();
                if (filename == null)
                    return;
                String directory = fd.getDirectory();
                editor.setCurrentFilePath(Path.of(directory, filename));
            }
            FileUtils.WriteFile(editor.getCurrentFilePath(), editor.getContent());
            if (this.onSaveFile != null)
                this.onSaveFile.run();
        }

    }

}
