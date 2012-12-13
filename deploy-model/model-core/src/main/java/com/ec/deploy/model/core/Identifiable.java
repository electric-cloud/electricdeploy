package com.ec.deploy.model.core;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public interface Identifiable<T extends Serializable>
{
    @NotNull
    T getId();
}
