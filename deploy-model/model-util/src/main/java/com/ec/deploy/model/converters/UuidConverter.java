package com.ec.deploy.model.converters;

import java.util.UUID;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

public class UuidConverter implements Converter
{
    @Override
    public Object convertObjectValueToDataValue(
        Object objectValue,
        Session session)
    {
        return objectValue.toString();
    }

    @Override
    public Object convertDataValueToObjectValue(
        Object dataValue,
        Session session)
    {
        return UUID.fromString(String.valueOf(dataValue));
    }

    @Override
    public boolean isMutable()
    {
        return false;
    }

    @Override
    public void initialize(
        DatabaseMapping mapping,
        Session session)
    {
        mapping.setIsJPAId();
    }
}
