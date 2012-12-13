package com.ec.deploy.model.core;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: jhaswell Date: 12/4/12 Time: 3:51 PM To
 * change this template use File | Settings | File Templates.
 */
public interface UniquelyIndentifiable<I extends Serializable>
{
    I getUniqueIdentifier();
}
