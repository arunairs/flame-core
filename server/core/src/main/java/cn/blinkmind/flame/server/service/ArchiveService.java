package cn.blinkmind.flame.server.service;

import cn.blinkmind.flame.server.bean.Diffs;
import cn.blinkmind.flame.server.repository.entity.Archive;
import cn.blinkmind.flame.server.repository.entity.Resource;
import org.apache.commons.lang3.StringUtils;
import org.javers.common.collections.Lists;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ObjectRemoved;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.changetype.container.ListChange;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArchiveService {

    public Diffs<String> diff(Archive base, Archive head) {
        Diffs<String> diffs = new Diffs<>();
        Diff diff = JaversBuilder.javers().build().compare(base, head);
        diffs.getRemovedCollection().addAll(this.resolveRemovedObjects(diff));
        diffs.getAddedCollection().addAll(this.resolveAddedObjects(diff));
        diffs.getModifiedCollection().addAll(this.resolveModifiedObjects(diff));
        return diffs;
    }

    private Collection<String> resolveAddedObjects(final Diff diff) {
        return diff.getChangesByType(NewObject.class)
                .stream()
                .filter(change -> change.getAffectedObject().get() instanceof Resource)
                .map(change -> StringUtils.substringAfter(change.getAffectedGlobalId().value(), "#"))
                .collect(Collectors.toList());
    }

    private Collection<String> resolveModifiedObjects(final Diff diff) {
        return diff.getChangesByType(ValueChange.class)
                .stream()
                .map(ValueChange::getPropertyName)
                .collect(Collectors.toList());
    }

    private Collection<String> resolveRemovedObjects(final Diff diff) {
        return diff.getChangesByType(ObjectRemoved.class)
                .stream()
                .filter(change -> change.getAffectedObject().get() instanceof Resource)
                .map(change -> StringUtils.substringAfter(change.getAffectedGlobalId().value(), "#"))
                .collect(Collectors.toList());
    }
}
