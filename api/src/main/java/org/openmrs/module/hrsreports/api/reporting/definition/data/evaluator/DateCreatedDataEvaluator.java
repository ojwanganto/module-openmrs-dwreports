package org.openmrs.module.hrsreports.api.reporting.definition.data.evaluator;

import org.openmrs.annotation.Handler;
import org.openmrs.module.hrsreports.api.reporting.definition.data.DateCreatedDataDefinition;
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
@Handler(supports=DateCreatedDataDefinition.class, order=50)
public class DateCreatedDataEvaluator implements VisitDataEvaluator {

    @Autowired
    private EvaluationService evaluationService;

    public EvaluatedVisitData evaluate(VisitDataDefinition definition, EvaluationContext context) throws EvaluationException {
        EvaluatedVisitData c = new EvaluatedVisitData(definition, context);

        String qry = "SELECT " +
                " v.visit_id, " +
                " o.date_created " +
                " FROM visit v " +
                " INNER JOIN encounter e ON e.visit_id=v.visit_id " +
                " INNER JOIN obs o on o.encounter_id=e.encounter_id " +
                " where o.concept_id in(5497,730,856) ";

        //we want to restrict visits to those for patients in question
        qry = qry + " and v.visit_id in (";
        qry = qry + HRSUtil.getInitialCohortQuery();
        qry = qry + ") ";

        SqlQueryBuilder queryBuilder = new SqlQueryBuilder();
        queryBuilder.append(qry);
        queryBuilder.addParameter("effectiveDate", HRSUtil.getReportEffectiveDate());
        queryBuilder.addParameter("endDate", HRSUtil.getReportEndDate());
        queryBuilder.addParameter("patientIds", HRSUtil.getReportCohort());
        System.out.println("Completed processing Date record created");
        Map<Integer, Object> data = evaluationService.evaluateToMap(queryBuilder, Integer.class, Object.class, context);
        c.setData(data);
        return c;
    }
}
