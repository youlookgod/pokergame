package org.example.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

/**
 * @Description guava的joiner；引入guava打出来的包太大，去掉guava的jar包只有几十k
 * @Author aiq
 * @Date 2024/3/16 19:39
 * @Version 1.0
 */

public class Joiner {
    private final String separator;

    public static Joiner on(String separator) {
        return new Joiner(separator);
    }

    private Joiner(String separator) {
        Objects.requireNonNull(separator);
        this.separator = separator;
    }

    public final String join(Iterable<?> parts) {
        return join(parts.iterator());
    }

    public final String join(Iterator<?> parts) {
        return appendTo(new StringBuilder(), parts).toString();
    }

    public final StringBuilder appendTo(StringBuilder builder, Iterator<?> parts) {
        try {
            appendTo((Appendable) builder, parts);
        } catch (IOException impossible) {
            throw new AssertionError(impossible);
        }
        return builder;
    }

    public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
        Objects.requireNonNull(appendable);
        if (parts.hasNext()) {
            appendable.append(toString(parts.next()));
            while (parts.hasNext()) {
                appendable.append(separator);
                appendable.append(toString(parts.next()));
            }
        }
        return appendable;
    }

    CharSequence toString(Object part) {
        Objects.requireNonNull(part);
        return (part instanceof CharSequence) ? (CharSequence) part : part.toString();
    }
}
