package com.ec.deploy.model.graph;

import com.ec.deploy.model.core.PersistentEntity;

public class Edge extends PersistentEntity
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





}
