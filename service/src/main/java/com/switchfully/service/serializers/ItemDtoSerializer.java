package com.switchfully.service.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.switchfully.service.item.dto.ItemDto;

import java.io.IOException;
import java.io.StringWriter;

public class ItemDtoSerializer extends JsonSerializer<ItemDto> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(ItemDto value,
                          JsonGenerator gen,
                          SerializerProvider serializers)
            throws IOException {

        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, value);
        gen.writeFieldName(writer.toString());
    }
}
