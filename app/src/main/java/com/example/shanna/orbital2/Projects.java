package com.example.shanna.orbital2;

public class Projects {


    private String Title;
    private String ProjectSummary;
    private String Owner; //ID of project owner
    private String Pay;
    private String Duration;
    private String BufferWait;
    private String MaxChanges;
    private String DateOfRequest;
    private String Partner; //ID of project requester

    public Projects(){

    }

    public Projects(String title, String projectSummary, String owner,
                    String pay, String duration, String bufferWait,
                    String maxChanges, String dateOfRequest,
                    String partner){
        this.Title = title;
        this.ProjectSummary = projectSummary;
        this.Owner=owner;
        this.Pay = pay;
        this.Duration = duration;
        this.BufferWait = bufferWait;
        this.MaxChanges = maxChanges;
        this.DateOfRequest = dateOfRequest;
        this.Partner = partner;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getProjectSummary() {
        return ProjectSummary;
    }

    public void setProjectSummary(String projectSummary) {
        ProjectSummary = projectSummary;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getPay() {
        return Pay;
    }

    public void setPay(String pay) {
        Pay = pay;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getBufferWait() {
        return BufferWait;
    }

    public void setBufferWait(String bufferWait) {
        BufferWait = bufferWait;
    }

    public String getMaxChanges() {
        return MaxChanges;
    }

    public void setMaxChanges(String maxChanges) {
        MaxChanges = maxChanges;
    }

    public String getDateOfRequest() {
        return DateOfRequest;
    }

    public void setDateOfRequest(String dateOfRequest) {
        DateOfRequest = dateOfRequest;
    }

    public String getPartner() {
        return Partner;
    }

    public void setPartner(String partner) {
        Partner = partner;
    }
}

