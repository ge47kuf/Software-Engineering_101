package eist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AccessControlList {
    private Map<String, Set<String>> accessControlMap;


    public AccessControlList() {
        accessControlMap = new HashMap<>();
    }

    //TODO: fill accessControlMap instance with the given user and permission information.
    public void grantAccess(String user, String permission) {
        if (user.equals("SalesManager") || user.equals("SalesIntern") || user.equals("MarketingManager")) {
            Set<String> p = accessControlMap.get(user);
            if (p == null) {
                p = new HashSet<>(Set.of(permission));
                accessControlMap.put(user, p);
            } else {
                p.add(permission);
            }
        }
    }

    //TODO: check if accessControlMap instance has given user and permission. Return result in a boolean.
    public boolean hasAccess(String user, String permission) {
        if (accessControlMap.containsKey(user)) {
            Set<String> p = accessControlMap.get(user);
            return p.contains(permission);
        }
        return false;
    }

    @Override
    public String toString() {
        return accessControlMap.toString();
    }

    public Map<String, Set<String>> getAccessControlMap() {
        return accessControlMap;
    }

    public void setAccessControlMap(Map<String, Set<String>> accessControlMap) {
        this.accessControlMap = accessControlMap;
    }
}