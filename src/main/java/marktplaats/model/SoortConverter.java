package marktplaats.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SoortConverter implements AttributeConverter<Soort, String> {

    @Override
    public String convertToDatabaseColumn(Soort soort) {
        return soort.getShortName();
    }

    @Override
    public Soort convertToEntityAttribute(String dbData) {
        return Soort.fromShortName(dbData);
    }
}
