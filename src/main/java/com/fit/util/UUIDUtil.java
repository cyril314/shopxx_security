package com.fit.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @AUTO
 * @FILE UUIDUtil.java
 * @DATE 2018-1-6 下午10:54:39
 * @Author AIM
 */
public abstract class UUIDUtil {

    private static boolean IS_THREADLOCALRANDOM_AVAILABLE = false;
    private static Random random;
    private static final long leastSigBits;
    private static final ReentrantLock lock = new ReentrantLock();
    private static long lastTime;

    static {
        try {
            IS_THREADLOCALRANDOM_AVAILABLE = null != UUIDUtil.class.getClassLoader().loadClass("java.util.concurrent.ThreadLocalRandom");
        } catch (ClassNotFoundException e) {
        }

        byte[] seed = new SecureRandom().generateSeed(8);
        leastSigBits = new BigInteger(seed).longValue();
        if (!IS_THREADLOCALRANDOM_AVAILABLE) {
            random = new Random(leastSigBits);
        }
    }

    /**
     * Create a new random UUID.
     *
     * @return the new UUID
     */
    public static UUID random() {
        byte[] randomBytes = new byte[16];
        if (IS_THREADLOCALRANDOM_AVAILABLE) {
            java.util.concurrent.ThreadLocalRandom.current().nextBytes(randomBytes);
        } else {
            random.nextBytes(randomBytes);
        }

        long mostSigBits = 0;
        for (int i = 0; i < 8; i++) {
            mostSigBits = (mostSigBits << 8) | (randomBytes[i] & 0xff);
        }
        long leastSigBits = 0;
        for (int i = 8; i < 16; i++) {
            leastSigBits = (leastSigBits << 8) | (randomBytes[i] & 0xff);
        }

        return new UUID(mostSigBits, leastSigBits);
    }

    /**
     * Create a new time-based UUID.
     *
     * @return the new UUID
     */
    public static UUID create() {
        long timeMillis = (System.currentTimeMillis() * 10000) + 0x01B21DD213814000L;

        lock.lock();
        try {
            if (timeMillis > lastTime) {
                lastTime = timeMillis;
            } else {
                timeMillis = ++lastTime;
            }
        } finally {
            lock.unlock();
        }

        // time low
        long mostSigBits = timeMillis << 32;

        // time mid
        mostSigBits |= (timeMillis & 0xFFFF00000000L) >> 16;

        // time hi and version
        mostSigBits |= 0x1000 | ((timeMillis >> 48) & 0x0FFF); // version 1

        return new UUID(mostSigBits, leastSigBits);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
