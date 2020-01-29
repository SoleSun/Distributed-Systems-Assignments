package com.s13998159.CPEN431_2020_A3;

import ca.NetSysLab.ProtocolBuffers.KeyValueResponse;

public class ErrCode {
    static byte[] overload_message =
            KeyValueResponse.KVResponse.newBuilder()
            .setErrCode(ErrCode.Overload)
            .build().toByteArray();

    static byte[] general_fail_message =
            KeyValueResponse.KVResponse.newBuilder()
            .setErrCode(ErrCode.GeneralFail)
            .build().toByteArray();

    static byte[] nospace_message =
            KeyValueResponse.KVResponse.newBuilder()
            .setErrCode(ErrCode.NoSpace)
            .build().toByteArray();

    /* Operation is successful. */
    static final int Success  = 0;

    /* Non-existent key requested in a get or delete operation */
    static final int NoKey    = 1;

    /* Returned when there is no space left to store data for an additional PUT.
     * Operations that do not consume new space (e.g., GET, REMOVE) would
     * generally not (or ‘never’ if you can guarantee it) return this error
     * code.
     */
    static final int NoSpace  = 2;

    /*
     * The system is operational but decides to refuse the operation due to
     * temporary overload that consumes internal resources (e.g., full internal
     * 8 buffers, too many in-flight requests).  Otherwise said, this error code
     * indicates that if nothing happens and no new requests are accepted,
     * the system will likely to return to a functioning state. At the
     * application level, a well behaved client will wait for the
     * overloadWaitTime (in milliseconds) and either continue or retry the
     * same operation.
     */
    static final int Overload = 3;

    /* a catch-all for all other situations where your KVStore has determined
     * that something is wrong and it can not recover from the failure.
     */
    static final int GeneralFail  = 4;

    /* Command code is not supported */
    static final int NoCmd    = 5;

    /* Key length is invalid */
    static final int InvalKey = 6;

    /* Value length is invalid */
    static final int InvalVal = 7;
}
