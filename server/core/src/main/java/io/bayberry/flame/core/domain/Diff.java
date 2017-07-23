package io.bayberry.flame.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

@Getter
public class Diff<E> {
    @JsonProperty("added")
    private Set<E> addedCollection = Sets.newLinkedHashSet();

    @JsonProperty("modified")
    private Set<E> modifiedCollection = Sets.newLinkedHashSet();

    @JsonProperty("removed")
    private Set<E> removedCollection = Sets.newLinkedHashSet();

    @JsonProperty("reordered")
    private Set<E> reorderedCollection = Sets.newLinkedHashSet();

    public void add(DiffType diffType, E diff) {
        if (diffType == null) throw new NullPointerException();
        switch (diffType) {
            case ADDED:
                getAddedCollection().add(diff);
                break;
            case MODIFIED:
                getModifiedCollection().add(diff);
                break;
            case REMOVED:
                getRemovedCollection().add(diff);
                break;
            case REORDERED:
                getReorderedCollection().add(diff);
                break;
            default:
                return;
        }
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return addedCollection.isEmpty()
                && modifiedCollection.isEmpty()
                && removedCollection.isEmpty()
                && reorderedCollection.isEmpty();
    }

    public static boolean isEmpty(Diff diff) {
        return diff.isEmpty();
    }

    public static boolean isNotEmpty(Diff diff) {
        return diff.isNotEmpty();
    }
}
