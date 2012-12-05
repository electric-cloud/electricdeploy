package com.ec.deploy.model.tenancy;

public class TenancyContextHolder
{


    private static final InheritableThreadLocal<Tenant> tenantHolder;

    static {
        tenantHolder = new InheritableThreadLocal<Tenant>();

    }

}
