package io.bayberry.common.protocol.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SingleFieldHttpRequestParameter {

    private String name;
    private String value;
    private Boolean isRequired;
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleFieldHttpRequestParameter that = (SingleFieldHttpRequestParameter) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
