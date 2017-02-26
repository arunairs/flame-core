package cn.blinkmind.flame.repository.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project extends BaseModel<Long> {
}
