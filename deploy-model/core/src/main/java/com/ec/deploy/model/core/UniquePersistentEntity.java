package com.ec.deploy.model.core;

import java.io.Serializable;
import java.util.UUID;

public abstract class UniquePersistentEntity<E extends UniquePersistentEntity<E>>
    extends PersistentEntity<E, UUID>
    implements UniquelyIndentifiable<UUID>
{

    public UniquePersistentEntity(UUID uuid) {
        this.id = uuid;
    }

    public UniquePersistentEntity() {
        this(UUID.randomUUID());
    }

    @Override
    public E clone()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UUID getUniqueIdentifier()
    {
        return id;
    }
}
