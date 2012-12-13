package com.ec.deploy.model.tenancy;

public class TenancyContextHolder implements TenancyContextProvider
{

    private static final ThreadLocal<Tenant> tenantHolder;

    static {
        tenantHolder = new InheritableThreadLocal<>();
    }

    private TenancyContextHolder() {}


    @Override
    public Tenant get()
    {
        return tenantHolder.get();
    }

    @Override
    public void set(Tenant tenant)
    {
        tenantHolder.set(tenant);
    }


    public static TenancyContextHolder getInstance() {
        return TenancyContextHolderHolder.INSTANCE;
    }

    private static final class TenancyContextHolderHolder {
        static final TenancyContextHolder INSTANCE = new TenancyContextHolder();
    }

}
