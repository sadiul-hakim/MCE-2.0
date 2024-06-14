package org.learn.cms.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import org.learn.cms.pojo.SpecialContact;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@Converter
public class SpecialContactConverter implements AttributeConverter<List<SpecialContact>, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<SpecialContact> attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<SpecialContact> convertToEntityAttribute(String dbData) {
        try {

            List<SpecialContact> contactList = new ArrayList<>();
            JsonNode node = mapper.readTree(dbData);
            for (JsonNode n : node) {
                SpecialContact.builder()
                        .name(n.get("name").asText())
                        .contact(n.get("contact").asText())
                        .build();
            }

            return contactList;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return Collections.emptyList();
        }
    }
}
