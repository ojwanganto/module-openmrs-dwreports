package org.openmrs.module.hrsreports.api.reporting.query.definition;

import org.openmrs.Visit;
import org.openmrs.module.reporting.definition.configuration.ConfigurationProperty;
import org.openmrs.module.reporting.definition.configuration.ConfigurationPropertyCachingStrategy;
import org.openmrs.module.reporting.evaluation.caching.Caching;
import org.openmrs.module.reporting.query.BaseQuery;
import org.openmrs.module.reporting.query.visit.definition.VisitQuery;

import java.util.Date;

/**
 * Visit Query for obtaining visits that are active.
 *
 * Date asOfDate: optional, defaults to now()
 */
@Caching(strategy=ConfigurationPropertyCachingStrategy.class)
public class StudyVisitQuery extends BaseQuery<Visit> implements VisitQuery {

    @ConfigurationProperty
    private Date asOfDate;

    public StudyVisitQuery() {
    }

    public Date getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Date asOfDate) {
        this.asOfDate = asOfDate;
    }
}
