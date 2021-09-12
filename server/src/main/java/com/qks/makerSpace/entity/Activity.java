package com.qks.makerSpace.entity;

public class Activity {

    private String activityId;
    private Integer underProjects;
    private Integer nationalProject;
    private Integer expenditure;
    private Integer radExpenditure;
    private Integer productExpenditure;
    private Integer govermentGrant;
    private Integer selfRaised;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Integer getUnderProjects() {
        return underProjects;
    }

    public void setUnderProjects(Integer underProjects) {
        this.underProjects = underProjects;
    }

    public Integer getNationalProject() {
        return nationalProject;
    }

    public void setNationalProject(Integer nationalProject) {
        this.nationalProject = nationalProject;
    }

    public Integer getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Integer expenditure) {
        this.expenditure = expenditure;
    }

    public Integer getRadExpenditure() {
        return radExpenditure;
    }

    public void setRadExpenditure(Integer radExpenditure) {
        this.radExpenditure = radExpenditure;
    }

    public Integer getProductExpenditure() {
        return productExpenditure;
    }

    public void setProductExpenditure(Integer productExpenditure) {
        this.productExpenditure = productExpenditure;
    }

    public Integer getGovermentGrant() {
        return govermentGrant;
    }

    public void setGovermentGrant(Integer govermentGrant) {
        this.govermentGrant = govermentGrant;
    }

    public Integer getSelfRaised() {
        return selfRaised;
    }

    public void setSelfRaised(Integer selfRaised) {
        this.selfRaised = selfRaised;
    }
}
