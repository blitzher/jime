import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.EventEmitter;
import util.JimeEnum;

public class JimeMenuBar extends MenuBar implements ActionListener {

    EventEmitter emitter = new EventEmitter();

    public JimeMenuBar() {
        super();

        Menu fileMenu = new Menu("File");
        MenuItem menuItemNew = new MenuItem("New");
        MenuItem menuItemOpen = new MenuItem("Open");
        MenuItem menuItemSave = new MenuItem("Save");
        // JMenuItem menuItemPrint = new JMenuItem("Print");

        menuItemNew.addActionListener(this);
        menuItemOpen.addActionListener(this);
        menuItemSave.addActionListener(this);

        fileMenu.add(menuItemNew);
        fileMenu.add(menuItemOpen);
        fileMenu.add(menuItemSave);

        add(fileMenu);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(String.format("ActionEvent: %s", e.getActionCommand()));

        if (e.getActionCommand().equals("New")) {
            emitter.emit(JimeEnum.NewFile);
        } else if (e.getActionCommand().equals("Open")) {
            emitter.emit(JimeEnum.OpenFile);
        } else if (e.getActionCommand().equals("Save")) {
            emitter.emit(JimeEnum.SaveFile);
        }

    }

}
