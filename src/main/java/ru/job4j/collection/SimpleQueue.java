package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int topIn;
    private int topOut;

    public T poll() {
        while (topIn > 0) {
            out.push(in.pop());
            topIn--;
            topOut++;
        }
        if (topOut == 0) {
            throw new NoSuchElementException();
        }
        topOut--;
        return out.pop();
    }

    public void push(T value) {
        while (topOut > 0) {
            in.push(out.pop());
            topOut--;
            topIn++;
        }
        in.push(value);
        topIn++;
    }
}
