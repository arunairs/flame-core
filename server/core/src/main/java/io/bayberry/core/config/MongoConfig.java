package io.bayberry.core.config;

import io.bayberry.core.repository.entity.converter.ApiReadConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfig {

    @Autowired
    private MongoDbFactory mongoFactory;

    @Autowired
    private MongoMappingContext mongoMappingContext;

    @Bean
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new ApiReadConverter());
        return new CustomConversions(converterList);
    }

    @Bean
    public MappingMongoConverter mongoConverter(CustomConversions customConversions) throws Exception {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoFactory);
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        mongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        mongoConverter.setCustomConversions(customConversions);
        return mongoConverter;
    }
}
