package io.bayberry.flame.core.repository.entity.converter;

import com.mongodb.DBObject;
import io.bayberry.flame.common.util.JsonUtils;
import io.bayberry.flame.core.repository.entity.Api;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class ApiReadingConverter implements Converter<DBObject, Api> {

    @Override
    public Api convert(DBObject source) {
        return JsonUtils.convert(source, Api.class);
    }
}
