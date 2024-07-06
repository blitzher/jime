package util;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

public class JimeAction implements Action {
    private String action;
    private boolean enabled = true;

    public JimeAction(String action) {
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public Object getValue(String key) {
        return this.action;
    }

    @Override
    public void putValue(String key, Object value) {
        this.action = (String) value;
    }

    @Override
    public void setEnabled(boolean b) {
        this.enabled = b;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        throw new UnsupportedOperationException("Unimplemented method 'addPropertyChangeListener'");
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        throw new UnsupportedOperationException("Unimplemented method 'removePropertyChangeListener'");
    }
}
