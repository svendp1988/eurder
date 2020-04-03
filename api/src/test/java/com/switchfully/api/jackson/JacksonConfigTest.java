package com.switchfully.api.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonConfigTest {
    @Test
    void testObjectMapper() {
        JacksonConfig jacksonConfig = new JacksonConfig();
        ObjectMapper objectMapper = jacksonConfig.objectMapper();
        assertThat(objectMapper.getSerializationConfig().getDefaultVisibilityChecker().withIsGetterVisibility(JsonAutoDetect.Visibility.NONE));
        assertThat(objectMapper.getSerializationConfig().getDefaultVisibilityChecker().withSetterVisibility(JsonAutoDetect.Visibility.NONE));
        assertThat(objectMapper.getSerializationConfig().getDefaultVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        assertThat(objectMapper.getSerializationConfig().getDefaultVisibilityChecker().withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
    }
}