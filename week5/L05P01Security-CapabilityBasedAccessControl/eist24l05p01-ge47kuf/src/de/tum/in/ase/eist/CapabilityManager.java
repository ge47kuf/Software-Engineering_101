package de.tum.in.ase.eist;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CapabilityManager {

    /*
    TODO 2.1
        Add a new variable capabilityMap as depicted in the UML diagram.
        Don't forget to initialize the variable in the constructor.
     */
    private final HashMap<Application, Set<Capability>> capabilityMap;
    public CapabilityManager(HashMap<Application, Set<Capability>> capabilityMap) {
        this.capabilityMap = capabilityMap;
    }

    /*
    TODO 2.3.1
        Adjust the implementation of the method grantCapability(Application, Capability),
        that it adds a capability of an application, to the capabilityMap, if it does not exist yet.
     */
    public void grantCapability(Application application, Capability capability) {
        Set<Capability> capabilitySet = capabilityMap.get(application);
        if (capabilitySet == null) {
            capabilitySet = new HashSet<>();
            capabilityMap.put(application, capabilitySet);
        }
        capabilityMap.get(application).add(capability);
    }

    /*
    TODO 2.3.2
        Adjust the implementation of the method revokeCapability(Application, Capability),
        that it removes a capability of an application of the capabilityMap, if it exists.
     */
    public void revokeCapability(Application application, Capability capability) {
        Set<Capability> capabilitySet = capabilityMap.get(application);
        if (capabilitySet != null) {
            capabilitySet.remove(capability);
        }
    }

    /*
    TODO 2.2
        Adjust the method hasCapability(Application, File, Permission), which is
        supposed to check, if a requested capability exits in the capabilityMap.
        A capability is the permission of an application for a certain file.
        Hint: Check the Capability class for useful methods.
     */
    public boolean hasCapability(Application application, File file, Permission permission) {
        Set<Capability> appCap = capabilityMap.get(application);
        if (appCap != null) {
            Set<Permission> permissionSet = new HashSet<>();
            permissionSet.add(permission);
            Capability capability = new Capability(file, permissionSet);

            return appCap.contains(capability);
        }
        return false;
    }

    /*
    TODO 2.3.3
        Adjust the implementation of the method delegateCapability(Application,
        Application, Capability), that it revokes a certain capability from the
        "old" application and grants it for the "new" application.
     */
    public void  delegateCapability(Application oldApplication, Application newApplication,
                                    Capability capability) {
        revokeCapability(oldApplication, capability);
        grantCapability(newApplication, capability);
    }
}
