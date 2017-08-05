package org.openmrs.module.hrsreports.api.reporting.query.definition.evaluator;

import org.openmrs.annotation.Handler;
import org.openmrs.module.hrsreports.api.reporting.query.definition.StudyVisitQuery;
import org.openmrs.module.hrsreports.api.util.HRSUtil;
import org.openmrs.module.reporting.common.ObjectUtil;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;
import org.openmrs.module.reporting.evaluation.querybuilder.SqlQueryBuilder;
import org.openmrs.module.reporting.evaluation.service.EvaluationService;
import org.openmrs.module.reporting.query.visit.VisitQueryResult;
import org.openmrs.module.reporting.query.visit.definition.VisitQuery;
import org.openmrs.module.reporting.query.visit.evaluator.VisitQueryEvaluator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * The logic that evaluates an {@link org.openmrs.module.hrsreports.api.reporting.query.definition.StudyVisitQuery} and produces a {@link org.openmrs.module.reporting.query.visit.VisitQueryResult}
 */
@Handler(supports=StudyVisitQuery.class)
public class StudyVisitQueryEvaluator implements VisitQueryEvaluator {

    @Autowired
    EvaluationService evaluationService;

    public VisitQueryResult evaluate(VisitQuery definition, EvaluationContext context) throws EvaluationException {
        context = ObjectUtil.nvl(context, new EvaluationContext());
        VisitQueryResult queryResult = new VisitQueryResult(definition, context);

        String qry = HRSUtil.getInitialCohortQuery();
        SqlQueryBuilder builder = new SqlQueryBuilder();
        builder.append(qry);
        builder.addParameter("effectiveDate", HRSUtil.getReportEffectiveDate());
        builder.addParameter("endDate", HRSUtil.getReportEndDate());
        builder.addParameter("patientIds", HRSUtil.getReportCohort().size()==0? new HashSet<Long>(0):HRSUtil.getReportCohort());

        List<Integer> results = evaluationService.evaluateToList(builder, Integer.class, context);
        queryResult.getMemberIds().addAll(results);
        System.out.println("Effective date: ==============================" + HRSUtil.getReportEffectiveDate());
        System.out.println("End Date: ==============================" + HRSUtil.getReportEndDate());
        System.out.println("Completed processing visit query: Total visits: " + results.size());
        return queryResult;
    }

}
