package org.openmrs.module.hrsreports.api.reporting.model;

import java.util.Date;

/**
 * A pojo that holds study variables from a result set
 */
public class StudyVariableResult {

    private Integer patient;
    private Integer visit;
    private Integer test;
    private Date requestDate;
    private Date dateCreated;
    private Date dateOfTest;
    private Double testResult;
    private Date nextVisitDate;

    public StudyVariableResult(){}

    public StudyVariableResult(Integer patient, Integer visit, Integer test, Date requestDate, Date dateCreated, Date dateOfTest, Double testResult) {
        this.patient = patient;
        this.visit = visit;
        this.test = test;
        this.requestDate = requestDate;
        this.dateCreated = dateCreated;
        this.dateOfTest = dateOfTest;
        this.testResult = testResult;
    }

    public StudyVariableResult(Integer patient, Integer visit, Integer test, Date requestDate, Date dateCreated, Date dateOfTest, Double testResult, Date nextVisitDate) {
        this.patient = patient;
        this.visit = visit;
        this.test = test;
        this.requestDate = requestDate;
        this.dateCreated = dateCreated;
        this.dateOfTest = dateOfTest;
        this.testResult = testResult;
        this.nextVisitDate = nextVisitDate;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateOfTest() {
        return dateOfTest;
    }

    public void setDateOfTest(Date dateOfTest) {
        this.dateOfTest = dateOfTest;
    }

    public Double getTestResult() {
        return testResult;
    }

    public void setTestResult(Double testResult) {
        this.testResult = testResult;
    }

    public Date getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(Date nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }
}
