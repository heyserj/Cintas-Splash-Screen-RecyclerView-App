package com.example.splashscreenapp;

public class MyListData{
    private String description;
    private int infoImvId;
    private int editImvId;
    private int deleteImvId;
    public MyListData(String description, int infoImvId, int editImvId, int deleteImvId) {
        this.description = description;
        this.infoImvId = infoImvId;
        this.editImvId = editImvId;
        this.deleteImvId = deleteImvId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getInfoImvId() {
        return infoImvId;
    }
    public void setInfoImvId(int imgId) {
        this.infoImvId = imgId;
    }
    public int getEditImvId() {
        return editImvId;
    }
    public void setEditImvId(int imgId) {
        this.editImvId = imgId;
    }
    public int getDeleteImvId() {
        return deleteImvId;
    }
    public void setDeleteImvId(int imgId) {
        this.deleteImvId = imgId;
    }
}