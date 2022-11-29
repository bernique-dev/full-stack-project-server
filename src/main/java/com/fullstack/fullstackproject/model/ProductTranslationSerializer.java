package com.fullstack.fullstackproject.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ProductTranslationSerializer extends JsonSerializer<ProductTranslation> {
    @Override
    public void serialize(ProductTranslation productTranslation, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("product_name", productTranslation.translatedName);
        jsonGenerator.writeStringField("product_description", productTranslation.translatedDescription);
        jsonGenerator.writeEndObject();
    }
}
