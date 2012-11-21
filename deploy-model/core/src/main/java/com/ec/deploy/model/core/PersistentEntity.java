package com.ec.deploy.model.core;

import javax.validation.constraints.NotNull;

public class PersistentEntity
    implements Identifiable<Long>
{
    @NotNull
    private long id;

    @Override
    public Long getId()
    {
        return id;
    }
}
