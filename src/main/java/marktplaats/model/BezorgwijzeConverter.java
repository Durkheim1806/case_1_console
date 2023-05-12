package marktplaats.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BezorgwijzeConverter implements AttributeConverter<Bezorgwijze, String> {
    @Override
    public String convertToDatabaseColumn(Bezorgwijze bezorgwijze) {
        return bezorgwijze.getShortName();
    }

    @Override
    public Bezorgwijze convertToEntityAttribute(String dbData) {
        return Bezorgwijze.fromShortName(dbData);
    }
}
