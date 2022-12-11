package com.fullstack.fullstackproject.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ProductTranslationDeserializer extends JsonDeserializer<ProductTranslation> {

    @Override
    public ProductTranslation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        return new ProductTranslation(node.get("translatedName").textValue(), node.get("translatedDescription").textValue());
    }
}
