package util;

public class KeyValue<T1, T2> {
    private T1 key;
    private T2 value;

    public KeyValue(T1 key, T2 value) {
        this.key = key;
        this.value = value;
    }

    public T1 getKey() {
        return key;
    }

    public T2 getValue() {
        return value;
    }

}
