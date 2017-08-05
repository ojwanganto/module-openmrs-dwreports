package org.openmrs.module.hrsreports.api.reporting.definition.data.evaluator;

import org.openmrs.annotation.Handler;
import org.openmrs.module.hrsreports.api.reporting.definition.data.ViralLoadDataDefinition;
import org.openmrs.module.hrsreports.api.util.CSVFileReader;
import org.openmrs.module.hrsreports.api.util.HRSUtil;
import org.openmrs.module.reporting.data.visit.EvaluatedVisitData;
import org.openmrs.module.reporting.data.visit.definition.VisitDataDefinition;
import org.openmrs.module.reporting.data.visit.evaluator.VisitDataEvaluator;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;
import org.openmrs.module.reporting.evaluation.querybuilder.SqlQueryBuilder;
import org.openmrs.module.reporting.evaluation.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Evaluates a VisitIdDataDefinition to produce a VisitData
 */
@Handler(supports=ViralLoadDataDefinition.class, order=50)
public class ViralLoadDataEvaluator implements VisitDataEvaluator {

    @Autowired
    private EvaluationService evaluationService;

    public EvaluatedVisitData evaluate(VisitDataDefinition definition, EvaluationContext context) throws EvaluationException {
        EvaluatedVisitData c = new EvaluatedVisitData(definition, context);

        String qry = "select v.visit_id, o.value_numeric"
                        + " from visit v "
                        + " inner join encounter e on e.visit_id = v.visit_id "
                        + " inner join obs o on o.encounter_id = e.encounter_id and o.voided=0 "
                        + " where o.concept_id in(856) ";
                        //+ " and v.date_started > :startDate  ";

        //we want to restrict visits to those for patients in question
        qry = qry + " and v.visit_id in (";
        qry = qry + HRSUtil.getInitialCohortQuery();
        qry = qry + ") ";

        SqlQueryBuilder queryBuilder = new SqlQueryBuilder();
        queryBuilder.append(qry);
        queryBuilder.addParameter("effectiveDate", HRSUtil.getReportEffectiveDate());
        queryBuilder.addParameter("endDate", HRSUtil.getReportEndDate());
        queryBuilder.addParameter("patientIds", HRSUtil.getReportCohort());
        Map<Integer, Object> data = evaluationService.evaluateToMap(queryBuilder, Integer.class, Object.class, context);
        c.setData(data);
        System.out.println("Completed processing Viral Load");
        return c;
    }
}
