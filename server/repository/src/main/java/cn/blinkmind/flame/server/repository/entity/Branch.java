package cn.blinkmind.flame.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;

@org.springframework.data.mongodb.core.mapping.Document(collection = "branches")
@CompoundIndex(name = "unique_index", unique = true, def = "{'name':1,'documentRef._id':1}")
public class Branch extends BasicEntity<Long> implements Commit<Archive> {

    private String name;
    private Ref<Long> documentRef;
    private Document document;
    private Headers headers = new Headers();
    private Archive archive;
    private Ref<Long> originRef;
    private Branch origin;

    @Id
    @Override
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    public Long getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Ref<Long> getDocumentRef() {
        return documentRef;
    }

    @JsonIgnore
    private void setDocumentRef(Ref<Long> documentRef) {
        this.documentRef = documentRef;
    }

    @Transient
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
        if (this.document != null)
            setDocumentRef(new Ref<>(this.document));
        else setDocumentRef(null);
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Headers getHeaders() {
        return headers;
    }

    private void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    @JsonIgnore
    public Ref<Long> getOriginRef() {
        return originRef;
    }

    private void setOriginRef(Ref<Long> originRef) {
        this.originRef = originRef;
    }

    @Transient
    public Branch getOrigin() {
        return origin;
    }

    public void setOrigin(Branch origin) {
        this.origin = origin;
        if (this.origin != null)
            setOriginRef(new Ref<>(this.origin));
        else setOriginRef(null);
    }

    @Override
    @JsonIgnore
    public Archive getPayload() {
        return this.archive;
    }
}
