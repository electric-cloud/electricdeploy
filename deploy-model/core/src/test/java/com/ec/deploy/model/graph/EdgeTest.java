package com.ec.deploy.model.graph;

import com.ec.deploy.model.core.PersistentEntity;
import com.ec.deploy.model.core.PersistentEntityTestCase;

public class EdgeTest extends PersistentEntityTestCase<Edge>
{
    private Vertex source;
    private Vertex target;

    private static final String sourceName;
    private static final String targetName;
    private static final String sourceDescription;
    private static final String targetDescription;

    static {
        sourceName = "source vertex";
        targetName = "target vertex";

        sourceDescription = "just a source vertex";
        targetDescription = "just a target vertex";
    }
    @Override
    public void setUp() {
        source = createVertex(sourceName, sourceDescription);
        target = createVertex(targetName, targetDescription);
    }

    @Override
    protected Edge createValidPersistentEntity()
    {
        final Edge edge = new Edge();
        edge.setSource(source);
        edge.setTarget(target);
        edge.setName(createValidName());
        edge.setDescription(createValidDescription());
        return edge;
    }

    private Vertex createVertex(String name, String description) {
        final Vertex vertex = new Vertex();
        vertex.setName(name);
        vertex.setDescription(description);
        return vertex;
    }
}
