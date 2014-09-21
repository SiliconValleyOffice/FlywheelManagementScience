package com.flywheelms.library.gcg.enumerator;

import com.flywheelms.library.gcg.GcgApplication;
import com.flywheelms.library.gcg.interfaces.GcgPerspective;

import java.util.ArrayList;

/**
 * Just for documentation.
 * Use this as an example for other enum base classes.
 */
public class GcgEnum {

    protected static final ArrayList<GcgEnum> VALUES = new ArrayList<GcgEnum>();

    public static ArrayList<GcgEnum> values() {
        return GcgEnum.VALUES;
    }

    public static GcgEnum getObjectForName(String aName) {
        GcgEnum theGcgEnum = null;
        for(GcgEnum theInstance : GcgEnum.VALUES) {
            if(theInstance.getName().equals(aName)) {
                theGcgEnum = theInstance;
                break;
            }
        }
        return theGcgEnum;
    }

    private final int nameResourceId;
    private String name;

    protected GcgEnum(int aNameResourceId, GcgPerspective[] anGcgPerspectiveArray) {
        this.nameResourceId = aNameResourceId;
        this.name = GcgApplication.getAppResources().getString(this.nameResourceId);
    }

    public int getNameResourceId() {
        return this.nameResourceId;
    }

    public String getName() {
        if(this.name == null) {
            this.name = GcgApplication.getAppResources().getString(this.nameResourceId);
        }
        return this.name;
    }
}
