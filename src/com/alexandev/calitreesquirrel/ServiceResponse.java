package com.alexandev.calitreesquirrel;


public class ServiceResponse {

//    boolean success;

    Long id;

    public ServiceResponse()
    {
    }

    public ServiceResponse( Long id )
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

}

