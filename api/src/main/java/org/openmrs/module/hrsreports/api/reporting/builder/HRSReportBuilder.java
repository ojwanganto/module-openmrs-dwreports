/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.hrsreports.api.reporting.builder;

import org.openmrs.PatientIdentifierType;
import org.openmrs.module.hrsreports.api.reporting.definition.data.*;
import org.openmrs.module.hrsreports.api.reporting.query.definition.StudyVisitQuery;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyacore.report.data.patient.definition.CalculationDataDefinition;
import org.openmrs.module.kenyaemr.calculation.library.hiv.art.DateOfEnrollmentCalculation;
import org.openmrs.module.kenyaemr.calculation.library.hiv.art.InitialArtStartDateCalculation;
import org.openmrs.module.kenyaemr.metadata.HivMetadata;
import org.openmrs.module.kenyaemr.reporting.calculation.converter.DateArtStartDateConverter;
import org.openmrs.module.kenyaemr.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.BirthdateDataDefinition;
import org.openmrs.module.reporting.data.visit.definition.VisitIdDataDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.VisitDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Builds({"hrsreports.common.report.hrsstudyvariablereport"})
public class HRSReportBuilder extends AbstractReportBuilder {
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    @Override
    protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
        return Arrays.asList(
                new Parameter("startDate", "Start Date", Date.class)
        );
    }

    @Override
    protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor reportDescriptor, ReportDefinition reportDefinition) {
        return Arrays.asList(
                ReportUtils.map(datasetColumns(), "startDate=${startDate}")
        );
    }

    protected DataSetDefinition datasetColumns() {
        VisitDataSetDefinition dsd = new VisitDataSetDefinition();
        dsd.setName("VisitInformation");
        dsd.setDescription("Visit information");

        dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        String mapping = "startDate=${startDate}";

        PatientIdentifierType upn = MetadataUtils.existing(PatientIdentifierType.class, HivMetadata._PatientIdentifierType.UNIQUE_PATIENT_NUMBER);
        DataConverter identifierFormatter = new ObjectFormatter("{identifier}");
        DataDefinition identifierDef = new ConvertedPatientDataDefinition("identifier", new PatientIdentifierDataDefinition(upn.getName(), upn), identifierFormatter);

        dsd.addColumn("VISIT ID", new VisitIdDataDefinition(), null);	// Test a basic encounter data item
        dsd.addColumn("EMR ID", new PatientIdDataDefinition(), null); 			// Test a basic patient data item
        dsd.addColumn("Unique Patient Number", identifierDef, null); 		// Test a basic person data item
        dsd.addColumn("Date Enrolled in Care", new CalculationDataDefinition("DOE", new DateOfEnrollmentCalculation()), "", new CalculationResultConverter());
        dsd.addColumn("Date Created", new DateCreatedDataDefinition(), null);
        dsd.addColumn("CD4", new VisitCD4DataDefinition(), null);
        dsd.addColumn("Viral Load", new ViralLoadDataDefinition(), null);
        dsd.addColumn("Next Visit Date", new NextVisitDateDataDefinition(), null);
        dsd.addColumn("Art Start Date", new CalculationDataDefinition("Art Start Date", new InitialArtStartDateCalculation()), "", new DateArtStartDateConverter());

        dsd.addRowFilter(new StudyVisitQuery(), "");
        return dsd;

    }
}