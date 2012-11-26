package com.ec.deploy.model.graph;

import org.junit.Before;
import org.junit.Test;

import com.ec.deploy.model.core.PersistentEntityTestCase;

public class VertexTest extends PersistentEntityTestCase<Vertex>

{

    private static final String defaultVertexName;
    private static final String defaultVertexDescription;

    static {
        defaultVertexName = "vertex";
        defaultVertexDescription = "just a vertex";
    }

    @Test(expected = UnsupportedOperationException.class)
    public void ensureGetSuccessorsThrowsUnmodifiableCollectionException() {
        createValidPersistentEntity().getSuccessors().add(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void ensureGetPredecessorsThrowsUnmodifiableCollectionException() {
        createValidPersistentEntity().getPredecessors().add(null);
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
