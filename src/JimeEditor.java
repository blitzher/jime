import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.nio.file.Path;

import javax.swing.*;

import util.*;

public class JimeEditor {

    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JPanel panel;
    private Path currentFilePath;

    public JimeEditor() {
        super();

        panel = new JPanel();

        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setText(assets.lipsum.Lipsum);
        // Set monospace font

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = gbc.weighty = 1.0;

        // Create the scroll pane view of the text area
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();

        // Use the utility class to render line numbers
        TextLineNumber tln = new TextLineNumber(textArea);
        scrollPane.setRowHeaderView(tln);

        System.out.println("\nJimeEditor: " + panel.getPreferredSize());

        panel.add(scrollPane, gbc);
        panel.add(verticalScrollBar, new GridBagConstraints() {
            {
                gridx = 1;
                gridy = 0;
                fill = GridBagConstraints.VERTICAL;
                weighty = 1.0;
            }
        });
    }

    public void LoadFile(Path path) {
        textArea.setText(util.FileUtils.ReadFile(path));
        textArea.setCaretPosition(0);
        currentFilePath = path;
        // TODO: Cache caret positions of previously opened files
    }

    public java.awt.Component getComponent() {
        return panel;
    }

    public Path getCurrentFilePath() {
        return currentFilePath;
    }

    public String getContent() {
        return textArea.getText();
    }
}
