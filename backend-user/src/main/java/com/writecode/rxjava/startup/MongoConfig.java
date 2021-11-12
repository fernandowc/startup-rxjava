package com.writecode.rxjava.startup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

//esta clase sirve para que cuando insertes elementos ya no te ponga el documento _class en mongo
@Configuration
public class MongoConfig implements InitializingBean {

    @Autowired
    @Lazy //inicializacion ardia o perezozo, se va a levantar en el momento que se llame
    private MappingMongoConverter mappingMongoConverter;

    @Override
    public void afterPropertiesSet() throws Exception {
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }
}
