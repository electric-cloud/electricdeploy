package com.ec.deploy.model.core;

import java.io.Serializable;

public interface UniquePersistentEntity<T extends Serializable>
{
    public T getUniqueIndentifier();
}
