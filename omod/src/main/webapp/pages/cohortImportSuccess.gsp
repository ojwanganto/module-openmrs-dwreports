<%
    ui.decorateWith("kenyaemr", "standardPage", [ layout: "sidebar" ])

    def menuItems = [
            [ label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to home", href: ui.pageLink("kenyaemr", "registration/registrationHome") ]
    ]
%>


<div class="ke-page-sidebar">
    ${ ui.includeFragment("kenyaemr", "patient/patientSearchForm", [ defaultWhich: "all" ]) }

    ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Tasks", items: menuItems ]) }
</div>

<div class="ke-page-content">
    <p><em>The file was uploaded successfully. Please proceed to reports app and run HRS Study Report</em></p>
</div>

<script type="text/javascript">
    jQuery(function() {
        jQuery('input[name="query"]').focus();
    });
</script>