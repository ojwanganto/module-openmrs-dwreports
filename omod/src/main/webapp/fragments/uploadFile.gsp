<fieldset>
     <legend>Cohort File Upload</legend>
    <p><B>Cohort file status: </B></p>
    <div style="color: red;font-family: verdana, arial, helvetica; font-size: 16px;border: blue">
            ${fileExist}
        </div>
        <hr color="green">
        <form method="post" enctype="multipart/form-data" action="${ ui.actionLink("hrsreports", "uploadFile", "uploadFile") }">
            <span>
                <input type="file" name="cohortFile" accept=".csv"/>
            </span>
            <span>
                <button type="submit">
                    <img src="${ ui.resourceLink("kenyaui", "images/glyphs/ok.png") }" /> Upload File
                </button>
            </span>
        </form>
</fieldset>