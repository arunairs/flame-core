package cn.blinkmind.depot.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Transient;

/**
 * @author jiaan.zhang@outlook.com
 * @date 28/11/2016 2:42 PM
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "releases")
public class Release extends EntityBean {

    private Version version;
    private Archive archive;
    private Ref<Long> branchRef;
    private Branch branch;

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    @JsonIgnore
    public Ref<Long> getBranchRef() {
        return branchRef;
    }

    public void setBranchRef(Ref<Long> branchRef) {
        this.branchRef = branchRef;
    }

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnoreProperties(value = {"createdDate", "archive", "version"})
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
        if (this.branch != null)
            setBranchRef(new Ref<>(this.branch));
        else setBranchRef(null);
    }
}
