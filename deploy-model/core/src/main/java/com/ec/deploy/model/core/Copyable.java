package com.ec.deploy.model.core;

/**
 * Created with IntelliJ IDEA. User: jhaswell Date: 11/25/12 Time: 5:32 PM To
 * change this template use File | Settings | File Templates.
 */
public interface Copyable<E extends Copyable<E>>
{

    public E clone();
}
