package com.ec.deploy.model.graph;

import com.ec.deploy.model.core.PersistentEntity;

public class Edge extends Element<Edge>
{

    private Vertex source;
    private Vertex target;

    public Edge(Vertex source, Vertex target) {
        this.source = source;
        this.target = target;
    }

    public Edge() {

    }
    public Vertex getSource()
    {
        return source;
    }

    public void setSource(Vertex source)
    {
        this.source = source;
    }

    public Vertex getTarget()
    {
        return target;
    }

    public void setTarget(Vertex target)
    {
        this.target = target;
    }


    @Override
    public String toString() {
        return String.format(
                "Edge[id:%s, name:%s]{%s -> %s}",
                getId(), getName(),
                getSource() == null ? "" : getSource().getName(),
                getTarget() == null ? "" : getTarget().getName());
    }



    @Override
    public Edge clone()
    {
        final Edge result = new Edge();
        result.setName(getName());
        result.setSource(source);
        result.setTarget(target);
        result.setDescription(getDescription());
        return result;
    }
}
