package org.openmrs.module.hrsreports.api.reporting.converter;

import org.openmrs.calculation.result.CalculationResult;
import org.openmrs.module.kenyaemr.reporting.RDQAReportUtils;
import org.openmrs.module.reporting.data.converter.DataConverter;

import java.util.Date;

/**
 * Converter to get obsDatetime from an observation
 */
public class GenericDateConverter implements DataConverter {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
	@Override
	public Object convert(Object original) {
		if (original == null)
			return "";

		Object value = ((CalculationResult) original).getValue();

		if (value == null)
			return "";

		return RDQAReportUtils.formatdates((Date) value, DATE_FORMAT);
	}

	@Override
	public Class<?> getInputDataType() {
		return CalculationResult.class;
	}

	@Override
	public Class<?> getDataType() {
		return String.class;
	}
}
