import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

import util.FileTreeModel;

public class JimeFileExplorer extends JTree {

    private Path root;

    public JimeFileExplorer(Path _root) {
        super();

        root = _root;
        setModel(new FileTreeModel(root.toFile()));
        setCellRenderer(new TreeCellRenderer() {
            @Override
            public java.awt.Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                    boolean expanded, boolean leaf, int row, boolean hasFocus) {

                File f = (File) value;
                JLabel label = new JLabel(f.getName());
                label.setIcon(f.isDirectory() ? null : null);

                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        System.out.println("Clicked on " + f.getName());
                    }
                });

                return label;
            }

        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println("Clicked on " + getSelectionPath().getLastPathComponent());
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
            }
        });

        setPreferredSize(
                new Dimension(500, 600));

    }

}
