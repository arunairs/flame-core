package io.bayberry.repository.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project extends BaseEntity<Long> {
}
