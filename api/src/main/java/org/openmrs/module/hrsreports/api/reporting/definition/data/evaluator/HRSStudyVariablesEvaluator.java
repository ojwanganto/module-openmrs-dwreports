package org.openmrs.module.hrsreports.api.reporting.definition.data.evaluator;

import org.openmrs.api.context.Context;
import org.openmrs.module.kenyaemr.api.KenyaEmrService;
import org.openmrs.module.reporting.data.person.EvaluatedPersonData;
import org.openmrs.module.reporting.data.person.definition.PersonDataDefinition;
import org.openmrs.module.reporting.data.person.evaluator.PersonDataEvaluator;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Evaluator for HRSStudyVariables
 */

public class HRSStudyVariablesEvaluator implements PersonDataEvaluator {

    @Override
    public EvaluatedPersonData evaluate(final PersonDataDefinition definition, final EvaluationContext context) throws EvaluationException {
        EvaluatedPersonData data = new EvaluatedPersonData(definition, context);

        if (context.getBaseCohort().isEmpty())
            return data;

       /* Concepts:
        CD4 count: 5497
        CD4 perc : 730
        VL	 : 856
        RTC	 : 5096

        select e.patient_id,
        o.concept_id,
        IF(o.concept_id=5096,
        o.value_datetime,
        o.value_numeric) result,
        e.encounter_datetime,
        o.date_created
        from obs o
        inner join encounter e on e.encounter_id= o.encounter_id and e.voided=0
        where e.encounter_type = 8 and o.concept_id in(5497, 730, 856, 5096) and o.date_created > :effectiveDate;
        */
        String qry = "select e.patient_id, o.concept_id, IF(o.concept_id=5096, o.value_datetime,o.value_numeric) result, e.encounter_datetime, o.date_created " +
                " from obs o " +
                " inner join encounter e on e.encounter_id= o.encounter_id and e.voided=0 " +
                " where e.patient_id in (:patientIds) o.concept_id in(5497, 730, 856, 5096) and o.date_created > :effectiveDate  ";

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("patientIds", context.getBaseCohort());
        m.put("effectiveDate", context.getParameterValue("endDate"));

        TreeMap<Double, Integer> dataMapFromSQL = (TreeMap<Double, Integer>) makeDataMapFromSQL(qry, m);
/*
        for (Integer memberId : context.getBaseCohort().getMemberIds()) {

            Set<Date> startDates = safeFind(mappedStartDates, memberId);
            data.addData(memberId, startDates);
        }*/

        return data;
    }

    protected Map<Double, Integer> makeDataMapFromSQL(String sql, Map<String, Object> substitutions) {
        List<Object> data = Context.getService(KenyaEmrService.class).executeSqlQuery(sql, substitutions);

        return makePatientDataMap(data);
    }

    protected Map<Double, Integer> makePatientDataMap(List<Object> data) {
        Map<Double, Integer> dataTreeMap = new TreeMap<Double, Integer>();
        for (Object o : data) {
            Object[] parts = (Object[]) o;
            if (parts.length == 2) {
                Double rand = (Double) parts[0];
                Integer pid = (Integer) parts[1];
                dataTreeMap.put(rand, pid);
            }
        }

        return dataTreeMap;
    }
}
