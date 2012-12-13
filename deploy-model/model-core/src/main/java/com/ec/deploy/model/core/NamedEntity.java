package com.ec.deploy.model.core;

import java.io.Serializable;

public interface NamedEntity<T extends Serializable>
    extends Identifiable<T>
{
    String getName();
    String getDescription();

    void setName(String name);

    void setDescription(String description);
}
