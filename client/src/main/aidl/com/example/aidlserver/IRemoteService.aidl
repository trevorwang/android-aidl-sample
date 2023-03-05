// IRemoteService.aidl
package com.example.aidlserver;

// Declare any non-default types here with import statements

parcelable ServiceData;
interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    ServiceData getData();

    void updateDataIn(in ServiceData data);
    void updateDataOut(out ServiceData data);
    void updateDataInOut(inout ServiceData data);
}