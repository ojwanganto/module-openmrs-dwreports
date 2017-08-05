<div>
    <p><b>Configuration for HRS Report</b></p>
    <div>
        <p>
            <b>Configuration file</b> is a CSV (Comma separated list) file with start date, end date and a list of Unique Patient Identifiers <br/>
            The file must be named <b><i>testCohort.csv</i></b><br/>
            The first element, start date, must be in the format yyyy-MM-dd i.e <b><i>2012-05-01</i></b> <br/>
            The second element, end date, must be in the format yyyy-MM-dd i.e <b><i>2015-05-01</i></b> <br/>
            <b>Where:</b> <br/>
            <b>yyyy</b> - is a four (4) digit value for year like 2012 <br/>
            <b>MM</b> - is two digit value for month like 06 for June <br/>
            <b>dd</b> - is a two digit value for day like 01 <br/>
            No other format is acceptable <br/>
            The report only accepts unique patient numbers and no other identifier is acceptable

        </p>
        <p>HRS Report depends on configuration file for its cohort and date range configurations else patient list defaults to all patients who have had CD4 and/or Viral Load tests in the past one year.<br/>
            This is not what we want hence need to upload a cohort file.
        </p>
        <p>
            To override existing cohort file, upload a copy containing configuration details i.e start date, end date date and list of patient identifiers
        </p>

        <p>Procedure for uploading/Updating Cohort file</p>
        <ul>
            <li>Select Configuration file using "Choose File" button</li>
            <li>Upload the file to the system</li>
            <li>Navigate to <b>Reports</b> app on <b>Home</b> page and click <b>"HRS Study Report"</b></li>
        </ul>
    </div>
</div>
