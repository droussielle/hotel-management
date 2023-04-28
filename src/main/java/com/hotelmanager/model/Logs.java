package com.hotelmanager.model;

import java.util.Date;

public class Logs
{
    private int id;
    private String details;
    private Date time;
    private String fileName;

    public Logs(int id, String details, int time, String fileName)
    {
        this.id = id;
        this.details = details;
        this.time = new Date(time * 1000L);
        this.fileName = fileName;
    }

    public int getId()
    {
        return this.id;
    }

    public String getDetails()
    {
        return this.details;
    }

    public Date getTime()
    {
        return this.time;
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public void printLog()
    {
        System.out.println(getId() + "\t" + getDetails() + "\t" + getTime() + "\t" + getFileName());
    }

}
