package packagetracking.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Autowired
    public void configureJackson(ObjectMapper objectMapper) {
        // Ignore Hibernate-specific properties
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Disable FAIL_ON_EMPTY_BEANS
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}

