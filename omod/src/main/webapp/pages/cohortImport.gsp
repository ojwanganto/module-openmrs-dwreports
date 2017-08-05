<%
    ui.decorateWith("kenyaemr", "standardPage", [ layout: "sidebar" ])
%>


<div class="ke-page-sidebar">

    ${ ui.includeFragment("hrsreports", "cohortFileUploadDirection") }
</div>

<div class="ke-page-content">
    ${ ui.includeFragment("hrsreports", "uploadFile") }
</div>
