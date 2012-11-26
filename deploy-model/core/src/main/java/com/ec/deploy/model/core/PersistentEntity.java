package com.ec.deploy.model.core;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public abstract class PersistentEntity<E extends PersistentEntity<E>>
    implements Identifiable<Long>,
    NamedEntity<Long>,
    Copyable<E>
{


    @NotNull
    private long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Size(min = 3, max = 200)
    private String description;

    @Override
    public Long getId()
    {
        return id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }
    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(o.getClass().equals(getClass())) {
            final PersistentEntity<?> other = (PersistentEntity<?>) o;
            return ((PersistentEntity<?>) o).getId().equals(getId());
        }
        return false;
    }


    public abstract E clone();

    @Override
    public int hashCode() {
        return getId() == null ? 0 : (int) (getId() * 31);
    }

    @Override
    public String toString() {
        return String.format(
                "PersistentEntity[actual type:'%s']{\n%s}",
                getClass(), getPropertyString());
    }

    private String getPropertyString() {
        final StringBuffer builder = new StringBuffer();
        for(Map.Entry<String, String> entry : getPropertyValues().entrySet()) {
            builder.append(entry.getKey())
                   .append(" : ").append(entry.getValue())
                   .append("\n");
        }
        return builder.toString();
    }


    protected Map<String, String> getPropertyValues() {
        final Map<String, String> result =
            new LinkedHashMap<String, String>();
        result.put("id", String.valueOf(id));
        result.put("name", "name");
        result.put("description", "description");
        return result;
    }

}
