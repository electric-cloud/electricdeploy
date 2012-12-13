package com.ec.deploy.model.tenancy;

/**
 * Created with IntelliJ IDEA. User: jhaswell Date: 12/5/12 Time: 10:57 AM To
 * change this template use File | Settings | File Templates.
 */
public interface TenancyContextProvider
{
    Tenant get();

    void set(Tenant tenant);

}
