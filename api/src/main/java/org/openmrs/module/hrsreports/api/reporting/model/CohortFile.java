package org.openmrs.module.hrsreports.api.reporting.model;

import java.util.Date;
import java.util.Set;

/**
 * A class that holds data in cohort csv file
 */
public class CohortFile {
    private Date effectiveDate;
    private Date endDate;
    private Set<String> patientIds;

    public CohortFile() {
    }

    public CohortFile(Date effectiveDate, Date endDate, Set<String> patientIds) {
        this.effectiveDate = effectiveDate;
        this.endDate = endDate;
        this.patientIds = patientIds;
    }

    public CohortFile(Date effectiveDate, Set<String> patientIds) {
        this.effectiveDate = effectiveDate;
        this.patientIds = patientIds;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<String> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(Set<String> patientIds) {
        this.patientIds = patientIds;
    }
}
