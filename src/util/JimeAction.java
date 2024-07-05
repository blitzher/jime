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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public Object getValue(String key) {
        // TODO Auto-generated method stub
        return this.action;
    }

    @Override
    public void putValue(String key, Object value) {
        // TODO Auto-generated method stub
        this.action = (String) value;
    }

    @Override
    public void setEnabled(boolean b) {
        // TODO Auto-generated method stub
        this.enabled = b;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return this.enabled;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPropertyChangeListener'");
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removePropertyChangeListener'");
    }
}
