package io.bayberry.core.repository.entity.converter;

import com.mongodb.DBObject;
import io.bayberry.core.repository.entity.Api;
import io.bayberry.core.repository.entity.HttpApi;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class ApiReadConverter implements Converter<DBObject, Api> {

    @Override
    public Api convert(DBObject source) {
        return new HttpApi();
    }
}
