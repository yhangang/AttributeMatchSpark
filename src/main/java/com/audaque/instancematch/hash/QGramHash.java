/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audaque.instancematch.hash;

/**
 *
 * @author Administrator
 */
public class QGramHash {

    /**
     * 1 Robert Sedgwicks哈希算法
     *
     * @param str
     * @param seed
     * @param q
     * @return
     */
    public static int RSHash(String str, int seed, int q) {
        int minHash = Integer.MAX_VALUE;
        //字符长度小于Q，直接返回该串哈希值
        if (str.length() - q <= 0) {
            return Hash.RSHash(str, seed);
        }

        for (int i = 0; i < str.length() - q + 1; i++) {
            int hash = Hash.RSHash(str.substring(i, i + q), seed);
            if (hash < minHash) {
                minHash = hash;
            }
        }
        return minHash;
    }

    /**
     * 2
     * Justin Sobel哈希算法
     *
     * @param str
     * @param seed
     * @param q
     * @return
     */
    public static int JSHash(String str, int seed, int q) {
        int minHash = Integer.MAX_VALUE;
        //字符长度小于Q，直接返回该串哈希值
        if (str.length() - q <= 0) {
            return Hash.JSHash(str, seed);
        }

        for (int i = 0; i < str.length() - q + 1; i++) {
            int hash = Hash.JSHash(str.substring(i, i + q), seed);
            if (hash < minHash) {
                minHash = hash;
            }
        }
        return minHash;
    }

    /**
     * 3
     * ELF哈希函数
     *
     * @param str
     * @param seed
     * @param q
     * @return
     */
    public static int ELFHash(String str, int seed, int q) {
        int minHash = Integer.MAX_VALUE;
        //字符长度小于Q，直接返回该串哈希值
        if (str.length() - q <= 0) {
            return Hash.ELFHash(str, seed);
        }

        for (int i = 0; i < str.length() - q + 1; i++) {
            int hash = Hash.ELFHash(str.substring(i, i + q), seed);
            if (hash < minHash) {
                minHash = hash;
            }
        }
        return minHash;
    }

    /**
     * 4
     * BKDR哈希函数
     *
     * @param str
     * @param seed
     * @param q
     * @return
     */
    public static int BKDRHash(String str, int seed, int q) {
        int minHash = Integer.MAX_VALUE;
        //字符长度小于Q，直接返回该串哈希值
        if (str.length() - q <= 0) {
            return Hash.BKDRHash(str, seed);
        }

        for (int i = 0; i < str.length() - q + 1; i++) {
            int hash = Hash.BKDRHash(str.substring(i, i + q), seed);
            if (hash < minHash) {
                minHash = hash;
            }
        }
        return minHash;
    }

    /**
     * 5
     * SDBM哈希函数
     *
     * @param str
     * @param seed
     * @param q
     * @return
     */
    public static int SDBMHash(String str, int seed, int q) {
        int minHash = Integer.MAX_VALUE;
        //字符长度小于Q，直接返回该串哈希值
        if (str.length() - q <= 0) {
            return Hash.SDBMHash(str, seed);
        }

        for (int i = 0; i < str.length() - q + 1; i++) {
            int hash = Hash.SDBMHash(str.substring(i, i + q), seed);
            if (hash < minHash) {
                minHash = hash;
            }
        }
        return minHash;
    }

    /**
     * 6
     * 返回字符串str的Q-GRAM切分最小哈希值
     *
     * @param str
     * @param seed
     * @param q
     * @return
     */
    public static int DJBHash(String str, int seed, int q) {
        int minHash = Integer.MAX_VALUE;
        //字符长度小于Q，直接返回该串哈希值
        if (str.length() - q <= 0) {
            return Hash.DJBHash(str, seed);
        }

        for (int i = 0; i < str.length() - q + 1; i++) {
            int hash = Hash.DJBHash(str.substring(i, i + q), seed);
            if (hash < minHash) {
                minHash = hash;
            }
        }
        return minHash;
    }

    /**
     * 7
     * 返回字符串str的Q-GRAM切分最小哈希值
     *
     * @param str
     * @param seed
     * @param q
     * @return
     */
    public static int DEKHash(String str, int seed, int q) {
        int minHash = Integer.MAX_VALUE;
        //字符长度小于Q，直接返回该串哈希值
        if (str.length() - q <= 0) {
            return Hash.DEKHash(str, seed);
        }

        for (int i = 0; i < str.length() - q + 1; i++) {
            int hash = Hash.DEKHash(str.substring(i, i + q), seed);
            if (hash < minHash) {
                minHash = hash;
            }
        }
        return minHash;
    }

    /**
     * 8
     * Arash Partow哈希函数
     *
     * @param str
     * @param seed
     * @param q
     * @return
     */
    public static int APHash(String str, int seed, int q) {
        int minHash = Integer.MAX_VALUE;
        //字符长度小于Q，直接返回该串哈希值
        if (str.length() - q <= 0) {
            return Hash.APHash(str, seed);
        }

        for (int i = 0; i < str.length() - q + 1; i++) {
            int hash = Hash.APHash(str.substring(i, i + q), seed);
            if (hash < minHash) {
                minHash = hash;
            }
        }
        return minHash;
    }

    /**
     * Q-GRAM算法，返回字符串的Q-GRAM切分 目前由于执行效率原因没有用上
     *
     * @param str
     * @param q
     * @return
     */
    public static String[] qGram(String str, int q) {
        //字符长度小于Q，直接返回该串
        if (str.length() - q <= 0) {
            String[] segment = {str};
            return segment;
        }
        String[] segment = new String[str.length() - q + 1];
        for (int i = 0; i < str.length() - q + 1; i++) {
            segment[i] = str.substring(i, i + q);
        }
        return segment;
    }

}
