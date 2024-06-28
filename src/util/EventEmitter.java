package util;

import java.util.Hashtable;
import java.util.Vector;

public class EventEmitter {

    public Hashtable<JimeEnum, Object[]> eventArgs = new Hashtable<>();

    private Hashtable<JimeEnum, Vector<Runnable>> listeners = new Hashtable<>();

    public EventEmitter() {
    }

    public void on(JimeEnum event, Runnable callback) {
        if (event == null) {
            return;
        }
        if (listeners.get(event) == null) {
            Vector<Runnable> callbacks = new Vector<>();
            callbacks.add(callback);
            listeners.put(event, callbacks);
        } else {
            listeners.get(event).add(callback);
        }
    }

    public void emit(JimeEnum event, Object... args) {
        System.out.println(String.format("Event: %s, Args: %s", event, args));
        if (event == null) {
            return;
        }
        if (listeners.get(event) == null) {
            return;
        }

        // Print the event and arguments

        eventArgs.put(event, args);
        for (Runnable callback : listeners.get(event)) {
            callback.run();
        }
    }
}
