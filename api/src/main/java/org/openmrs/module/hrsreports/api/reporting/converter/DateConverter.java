package org.openmrs.module.hrsreports.api.reporting.converter;

import org.openmrs.module.reporting.data.converter.DataConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converts date element to custom date format
 */
public class DateConverter implements DataConverter {

    @Override
    public Object convert(Object original) {

        Date d = (Date) original;

        if (d == null)
            return " ";

        return formatDate(d);
    }

    @Override
    public Class<?> getInputDataType() {
        return Date.class;
    }

    @Override
    public Class<?> getDataType() {
        return String.class;
    }

    private String formatDate(Date date) {
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return date == null?"":dateFormatter.format(date);
    }
}