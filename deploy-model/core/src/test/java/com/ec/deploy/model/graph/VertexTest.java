package com.ec.deploy.model.graph;

import org.junit.Before;
import org.junit.Test;

import com.ec.deploy.model.core.PersistentEntityTestCase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
    @Test(expected = AssertionError.class)
    public void ensureAddingNullSuccessorResultsInException() {
        createValidPersistentEntity().addSuccessor(null);
    }

    @Test(expected = AssertionError.class)
    public void ensureAddingNullPredecessorResultsInAssertionError() {
        createValidPersistentEntity().addPredecessor(null);
    }

    @Test
    public void ensureTwoVerticesWithNullNameAreEquivalent() {
        Vertex lhs = new Vertex();
        lhs.setName(null);

        Vertex rhs = new Vertex();
        rhs.setName(null);

        assertThat(lhs, is(equalTo(rhs)));
    }
    
    @Test
    public void ensureTwoVerticesWithDifferent() {

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
