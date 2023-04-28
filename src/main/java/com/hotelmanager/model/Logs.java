package com.hotelmanager.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Logs
{
    private int id;
    private String details;
    private LocalDateTime time;
    private String fileName;

    public Logs(int id, String details, long time, String fileName)
    {
        this.id = id;
        this.details = details;
        this.time = LocalDateTime.ofEpochSecond(time, 0, OffsetDateTime.now().getOffset());
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

    public String getTime()
    {
        return this.time.toString();
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public void printLog()
    {
        System.out.println(getId() + "\t" + getDetails() + "\t" + getTime() + "\t" + getFileName());
    }

    public String getTimeString()
    {
        return this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public void setTime(LocalDateTime time)
    {
        this.time = time;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
}
