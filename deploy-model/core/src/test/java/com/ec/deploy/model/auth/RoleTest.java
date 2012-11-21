package com.ec.deploy.model.auth;

import java.util.Collection;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoleTest
{

    @Test
    public void ensureAdministratorIsRankedHigherThanGuest() {
        assertEquals(Role.ROLE_COMPARATOR.compare(Role.ADMINISTRATOR, Role.GUEST), 1);
    }
}
