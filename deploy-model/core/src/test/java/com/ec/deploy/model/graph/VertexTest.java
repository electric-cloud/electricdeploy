package com.ec.deploy.model.graph;

import org.junit.Before;

import com.ec.deploy.model.core.PersistentEntityTestCase;

public class VertexTest extends PersistentEntityTestCase<Vertex>

{

    private static final String defaultVertexName;
    private static final String defaultVertexDescription;

    static {
        defaultVertexName = "vertex";
        defaultVertexDescription = "just a vertex";
    }

    @Override
    protected Vertex createValidPersistentEntity()
    {
        final Vertex vertex = new Vertex();
        vertex.setName(defaultVertexName);
        vertex.setDescription(defaultVertexDescription);
        return vertex;
    }
}
