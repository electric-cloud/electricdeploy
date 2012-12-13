package com.ec.deploy.model.tenancy;

import java.util.logging.Logger;

import javax.annotation.Resource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;

/**
 * Created with IntelliJ IDEA. User: jhaswell Date: 12/9/12 Time: 6:31 PM To
 * change this template use File | Settings | File Templates.
 */
public class TenantConfigurerListener
    extends SessionEventAdapter
{


    static final Logger logger = Logger.getLogger(
        TenantConfigurerListener.class.getName());
    @Resource(name = "tenancyContextProvider")
    TenancyContextProvider tenancyContextProvider;


    @Override
    public void postAcquireClientSession(SessionEvent event)
    {
        event.getSession().setProperty(
            PersistenceUnitProperties.MULTITENANT_PROPERTY_DEFAULT,
            tenancyContextProvider.get().getId());

    }
}
