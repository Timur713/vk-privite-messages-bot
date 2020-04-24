package com.isanbirdin.vk_bot.util;

import java.util.Random;

public class RandomUtil {
    /*
    return random int in range
     */
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
