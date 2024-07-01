import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import util.*;

public class JimeEditor extends JPanel {

    private JTextArea textArea;

    public JimeEditor() {
        super();

        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setText(assets.lipsum.Lipsum);
        // Set monospace font

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = gbc.weighty = 1.0;

        // Create the scroll pane view of the text area
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();

        // Use the utility class to render line numbers
        TextLineNumber tln = new TextLineNumber(textArea);
        scrollPane.setRowHeaderView(tln);

        System.out.println("\nJimeEditor: " + getPreferredSize());

        add(scrollPane, gbc);
        add(verticalScrollBar, new GridBagConstraints() {
            {
                gridx = 1;
                gridy = 0;
                fill = GridBagConstraints.VERTICAL;
                weighty = 1.0;
            }
        });
        // add(textArea, gbc);
    }
}
