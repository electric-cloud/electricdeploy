package com.ec.deploy.model.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import com.ec.deploy.model.core.PersistentEntity;

public class Vertex
    extends PersistentEntity<Vertex>
{

    private Set<Edge> successors;
    private Set<Edge> predecessors;

    public Vertex() {
        successors = new LinkedHashSet<>();
        predecessors = new LinkedHashSet<>();
    }

    public Vertex(
        Collection<? extends Edge> successors,
        Collection<? extends Edge> predecessors
    ) {
        this.successors = new LinkedHashSet<>(successors);
        this.predecessors = new LinkedHashSet<>(predecessors);
    }

    public Set<Edge> getSuccessors() {
        return Collections.unmodifiableSet(successors);
    }

    public Set<Edge> getPredecessors() {
        return Collections.unmodifiableSet(predecessors);
    }

    public boolean addSuccessor(final Edge edge) {
        assert edge != null : "Edge must not be null!";
        return successors.add(edge);
    }

    public boolean addPredecessor(final Edge edge) {
        assert edge != null : "Edge must not be null!";
        return predecessors.add(edge);
    }


    @Override
    public Vertex clone()
    {
        final Vertex clone = new Vertex();
        clone.setName(getName());
        clone.setDescription(getDescription());
        return clone;
    }
}
