package cn.blinkmind.depot.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;

/**
 * @author jiaan.zhang@oracle.com
 * @date 29/11/2016 3:45 PM
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "snapshots")
@CompoundIndex(name = "unique_index", unique = true, def = "{'branchRef._id':1,'creatorRef._id':1}")
public class Snapshot extends EntityBean {
    private Ref<Long> branchRef;
    private Branch branch;
    private Archive archive;

    @JsonIgnore
    public Ref<Long> getBranchRef() {
        return branchRef;
    }

    @JsonIgnore
    private void setBranchRef(Ref<Long> branchRef) {
        this.branchRef = branchRef;
    }

    @Transient
    public Branch getBranch() {
        return branch;
    }

    @JsonIgnore
    public void setBranch(Branch branch) {
        this.branch = branch;
        if (this.branch != null)
            setBranchRef(new Ref<>(this.branch));
        else setBranchRef(null);
    }

    public Archive getArchive() {
        return archive;
    }

    @JsonIgnore
    public void setArchive(Archive archive) {
        this.archive = archive;
    }
}
