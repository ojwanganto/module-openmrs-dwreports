package org.openmrs.module.hrsreports.api.reporting.definition.data;

import org.openmrs.module.reporting.data.BaseDataDefinition;
import org.openmrs.module.reporting.data.visit.definition.VisitDataDefinition;
import org.openmrs.module.reporting.definition.configuration.ConfigurationPropertyCachingStrategy;
import org.openmrs.module.reporting.evaluation.caching.Caching;

/**
 * Visit ID Column
 */
@Caching(strategy=ConfigurationPropertyCachingStrategy.class)
public class NextVisitDateDataDefinition extends BaseDataDefinition implements VisitDataDefinition {

    public static final long serialVersionUID = 1L;

    /**
     * Default Constructor
     */
    public NextVisitDateDataDefinition() {
        super();
    }

    /**
     * Constructor to populate name only
     */
    public NextVisitDateDataDefinition(String name) {
        super(name);
    }

    //***** INSTANCE METHODS *****

    /**
     * @see org.openmrs.module.reporting.data.DataDefinition#getDataType()
     */
    public Class<?> getDataType() {
        return Double.class;
    }
}
