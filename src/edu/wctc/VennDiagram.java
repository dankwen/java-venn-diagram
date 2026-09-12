package edu.wctc;

import java.util.HashSet;
import java.util.Set;

public class VennDiagram<T> {
    private final String label1;
    private final String label2;
    private final String label3;
    private final Set<T> circle1 = new HashSet<>();
    private final Set<T> circle2 = new HashSet<>();
    private final Set<T> circle3 = new HashSet<>();

    public VennDiagram(String label1, String label2, String label3) {
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
    }

    private Set<T> getCircleForLabel(String label) {
        if (label1 != null && label1.equalsIgnoreCase(label)) {
            return circle1;
        }
        if (label2 != null && label2.equalsIgnoreCase(label)) {
            return circle2;
        }
        if (label3 != null && label3.equalsIgnoreCase(label)) {
            return circle3;
        }
        return new HashSet<>();
    }

    public void add(T item, String... labels) {
        for (String label : labels) {
            Set<T> targetSet = getCircleForLabel(label);
            if (targetSet != null) {
                targetSet.add(item);
            }
        }
    }

    public Set<T> unionOf(String first, String second) {
        Set<T> firstSet = getCircleForLabel(first);
        Set<T> secondSet = getCircleForLabel(second);
        Set<T> result = new HashSet<>(firstSet);
        result.addAll(secondSet);
        return result;
    }

    public Set<T> intersectionOf(String first, String second) {
        Set<T> firstSet = getCircleForLabel(first);
        Set<T> secondSet = getCircleForLabel(second);
        Set<T> result = new HashSet<>(firstSet);
        result.retainAll(secondSet);
        return result;
    }

    public Set<T> complementOf(String first, String second) {
        Set<T> firstSet = getCircleForLabel(first);
        Set<T> secondSet = getCircleForLabel(second);
        Set<T> result = new HashSet<>(firstSet);
        result.removeAll(secondSet);
        return result;
    }

    public Set<T> diagramCenter() {
        Set<T> result = new HashSet<>(getCircleForLabel(label1));
        result.retainAll(getCircleForLabel(label2));
        result.retainAll(getCircleForLabel(label3));
        return result;
    }
}
