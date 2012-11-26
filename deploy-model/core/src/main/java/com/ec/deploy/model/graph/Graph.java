package com.ec.deploy.model.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ec.deploy.model.core.TenantRestrictedEntity;

public class Graph extends TenantRestrictedEntity<Graph>
{

    private Set<Edge> edges;
    private Set<Vertex> vertices;

    public Graph() {
        edges = new HashSet<>();
        vertices = new HashSet<>();
    }


    public boolean addVertex(Vertex vertex) {
        return vertices.add(vertex);
    }

    public boolean addEdge(Edge edge) {
        return edges.add(edge) &&
            addVertex(edge.getSource()) &&
            addVertex(edge.getTarget());
    }

    public boolean removeVertex(Vertex vertex) {
        return vertices.remove(vertex);
    }

    public boolean removeEdge(Edge edge) {
        return edges.remove(edge) &&
            removeVertex(edge.getSource()) &&
            removeVertex(edge.getTarget());
    }

    @Override
    public Graph clone()
    {
        return null;
    }
}
