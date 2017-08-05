package org.openmrs.module.hrsreports.page.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.UiUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller class for cohort import page
 */
@AppPage("hrsreports.reports")
public class CohortImportPageController {

    String successPage = "cohortImportSuccess";
    protected final Log log = LogFactory.getLog(getClass());

    public void get() {
        System.out.println("This is for get request:::::::::::::::::::::::::::::::::::::::::::::::::::::::");
    }

  /*  public void post(*//*@RequestParam("cohortFile") MultipartFile cohortFile, UiUtils ui*//*){
        System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRR");
        log.info("This request is reaching the controller---------------------------------------------------------------------");
        //return successPage;// "redirect:" + ui.pageLink("hrsreports", successPage, null);

    }*/

    public String post(UiUtils ui){
        System.out.println("This is for post request:::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        return "redirect:" + ui.pageLink("hrsreports", successPage, null);

    }
}
