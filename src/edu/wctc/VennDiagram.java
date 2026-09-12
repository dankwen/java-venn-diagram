package edu.wctc;

import java.util.HashSet;
import java.util.Set;

/**
 * A generic three-circle Venn diagram that supports set operations on labeled regions.
 *
 * @param <T> the generic element type stored in the diagram
 */
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

    /**
     * Returns the set associated with the requested label.
     *
     * @param label the label to match against the diagram circles
     * @return the matching circle set or an empty set if no match exists
     */
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

    /**
     * Adds an item to every circle whose label is supplied.
     *
     * @param item the item to insert
     * @param labels the labels for the circles that should receive the item
     */
    public void add(T item, String... labels) {
        for (String label : labels) {
            Set<T> targetSet = getCircleForLabel(label);
            if (targetSet != null) {
                targetSet.add(item);
            }
        }
    }

    /**
     * Returns the union of two circles as a new set.
     */
    public Set<T> unionOf(String first, String second) {
        Set<T> firstSet = getCircleForLabel(first);
        Set<T> secondSet = getCircleForLabel(second);
        Set<T> result = new HashSet<>(firstSet);
        result.addAll(secondSet);
        return result;
    }

    /**
     * Returns the intersection of two circles as a new set.
     */
    public Set<T> intersectionOf(String first, String second) {
        Set<T> firstSet = getCircleForLabel(first);
        Set<T> secondSet = getCircleForLabel(second);
        Set<T> result = new HashSet<>(firstSet);
        result.retainAll(secondSet);
        return result;
    }

    /**
     * Returns the relative complement of the second circle removed from the first.
     */
    public Set<T> complementOf(String first, String second) {
        Set<T> firstSet = getCircleForLabel(first);
        Set<T> secondSet = getCircleForLabel(second);
        Set<T> result = new HashSet<>(firstSet);
        result.removeAll(secondSet);
        return result;
    }

    /**
     * Returns the set of values shared by all three circles.
     */
    public Set<T> diagramCenter() {
        Set<T> result = new HashSet<>(getCircleForLabel(label1));
        result.retainAll(getCircleForLabel(label2));
        result.retainAll(getCircleForLabel(label3));
        return result;
    }
}
