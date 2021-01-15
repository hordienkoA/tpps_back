package ua.cn.stu.tpps.buyfly.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;


/**
 * Converts {@link java.time.LocalDate} to {@link java.sql.Date} to persist to database.
 */
// By setting autoApply=true, the converter will be applied to all attributes of the EntityType
// and no changes on the entity are required.
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}