package com.ec.deploy.model.auth;
import java.util.Comparator;

import org.springframework.security.core.GrantedAuthority;
public enum Role implements GrantedAuthority, Comparable<Role>
{

    USER("User", 1),
    GUEST("Guest", 0),
    TENANT_USER("Tenant User", 2),
    ADMINISTRATOR("Administrator", 4),
    TENANT_ADMINISTRATOR("Tenant Administrator", 3);


    public static final Comparator<Role> ROLE_COMPARATOR;

    static {
       ROLE_COMPARATOR = new RoleComparator();
    }


    private final String value;
    private final int priority;
    private Role(String value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    public int getPriority() {
        return priority;
    }


    private static class RoleComparator implements Comparator<Role> {
        @Override
        public int compare( Role lhs, Role rhs)
        {
            return lhs == null ? rhs == null ? 0 : 1 :
                lhs.priority < rhs.priority ? -1 : lhs.priority > rhs.priority ? 1 : 0;
        }
    }

}
