package cn.blinkmind.depot.server.repository.json;

import cn.blinkmind.depot.server.repository.entity.Branch;
import cn.blinkmind.depot.server.repository.entity.Snapshot;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class SnapshotDeserializer extends JsonDeserializer<Snapshot> {
    @Override
    public Snapshot deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Snapshot snapshot = new Snapshot();
        ObjectCodec codec = p.getCodec();
        JsonNode rootNode = codec.readTree(p);
        JsonNode branchNode = rootNode.get("branch");
        if (branchNode != null) {
            JsonNode branchIdNode = branchNode.get("id");
            if (branchIdNode != null) {
                Branch branch = new Branch();
                branch.setId(branchIdNode.asLong());
                snapshot.setBranch(branch);
            }
        }
        return snapshot;
    }
}
