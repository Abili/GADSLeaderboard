package com.raisac.gadsleaderboard.models;

public class SubmitProject {
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String lintToProject;

    public SubmitProject() {
    }

    // Getters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getLintToProject() {
        return lintToProject;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setLintToProject(String lintToProject) {
        this.lintToProject = lintToProject;
    }

}
