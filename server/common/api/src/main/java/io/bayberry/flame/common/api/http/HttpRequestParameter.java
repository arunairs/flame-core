package io.bayberry.flame.common.api.http;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpRequestParameter {

    private String name;
    private String value;
    private Boolean isRequired;
    private String type;
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpRequestParameter that = (HttpRequestParameter) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
