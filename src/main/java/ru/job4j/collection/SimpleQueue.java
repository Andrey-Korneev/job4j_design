package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int topIn;
    private int topOut;

    public T poll() {
        if (topOut == 0) {
            if (topIn == 0) {
                throw new NoSuchElementException();
            }
            while (topIn > 0) {
                out.push(in.pop());
                topOut++;
                topIn--;
            }
        }
        topOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        topIn++;
    }
}
