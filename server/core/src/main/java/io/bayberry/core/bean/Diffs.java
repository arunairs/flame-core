package io.bayberry.core.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

@Getter
public class Diffs<E> {
    @JsonProperty("added")
    private Set<E> addedCollection = Sets.newLinkedHashSet();

    @JsonProperty("modified")
    private Set<E> modifiedCollection = Sets.newLinkedHashSet();

    @JsonProperty("removed")
    private Set<E> removedCollection = Sets.newLinkedHashSet();

    @JsonProperty("reordered")
    private Set<E> reorderedCollection = Sets.newLinkedHashSet();

    public void add(DiffResult diffResult, E diff) {
        if (diffResult == null) throw new NullPointerException();
        switch (diffResult) {
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

    public static boolean isEmpty(Diffs diffs) {
        return diffs.isEmpty();
    }

    public static boolean isNotEmpty(Diffs diffs) {
        return diffs.isNotEmpty();
    }
}
