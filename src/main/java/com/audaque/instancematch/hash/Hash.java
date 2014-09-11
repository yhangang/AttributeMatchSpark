/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audaque.instancematch.hash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Administrator
 */
public class Hash {

    /**
     * 1 Robert Sedgwicks哈希算法 28s
     *
     * @param str
     * @param seed
     * @return
     */
    public static int RSHash(String str, int seed) {
        int a = seed;// 随机调换的种子
        int b = 378551;
        int hash = 0;
        for (int j = 0; j < str.length(); j++) {
            hash = hash * a + str.charAt(j);
            a = a * b;
        }
        return hash;
    }

    /**
     * 2 Justin Sobel哈希算法 34s
     *
     * @param str
     * @param seed
     * @return
     */
    public static int JSHash(String str, int seed) {
        int hash = seed; // 随机调换的种子
        for (int j = 0; j < str.length(); j++) {
            hash ^= ((hash << 5) + (str.charAt(j) * 131313131) + (hash >> 2));
        }
        return hash;
    }

    /**
     * 3 ELF哈希函数 88s
     *
     * @param str
     * @param seed
     * @return
     */
    public static int ELFHash(String str, int seed) {
        int hash = seed; // 随机调换的种子
        long x = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash << 4) + str.charAt(i);
            if ((x = hash & 0xF0000000L) != 0) {
                hash ^= (x >> 24);
            }
            hash &= ~x;
        }
        return hash;
    }

    /**
     * 4 BKDR哈希函数 28s
     *
     * @param str
     * @param seed
     * @return
     */
    public static int BKDRHash(String str, int seed) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * seed) + str.charAt(i);
        }
        return hash;
    }

    /**
     * 5 SDBM哈希函数 30s
     *
     * @param str
     * @param seed
     * @return
     */
    public static int SDBMHash(String str, int seed) {
        int hash = seed; // 随机调换的种子
        for (int i = 0; i < str.length(); i++) {
            hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }
        return hash;
    }

    /**
     * 6 Daniel J.Bernstein哈希算法 24s
     *
     * @param str
     * @param seed
     * @return
     */
    public static int DJBHash(String str, int seed) {
        int hash = seed; // 随机调换的种子;
        for (int j = 0; j < str.length(); j++) {
            hash = ((hash << 5) + hash) + str.charAt(j);
        }
        return hash;
    }

    /**
     * 7 DEK哈希函数 24s
     *
     * @param str
     * @param seed
     * @return
     */
    public static int DEKHash(String str, int seed) {
        int hash = seed; // 随机调换的种子;
        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
        }
        return hash;
    }

    /**
     * 8 Arash Partow哈希函数 36s
     *
     * @param str
     * @param seed
     * @return
     */
    public static int APHash(String str, int seed) {
        int hash = seed; // 随机调换的种子;
        for (int i = 0; i < str.length(); i++) {
            if ((i & 1) == 0) {
                hash ^= ((hash << 7) ^ str.charAt(i) * (hash >> 3));
            } else {
                hash ^= (~((hash << 11) + str.charAt(i) ^ (hash >> 5)));
            }
        }
        return hash;
    }

    /**
     * 加载哈希种子
     *
     * @param filePath
     * @param HashNum
     * @return
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     */
    public static int[] loadSeeds(String filePath, int HashNum) throws UnsupportedEncodingException, IOException
             {
        int[] seeds = new int[HashNum];
        File file = new File(filePath);
        InputStreamReader isr = new InputStreamReader(
                new FileInputStream(file), "GBK");
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        int lineNumber = 0;

        while ((line = br.readLine()) != null && lineNumber < HashNum) {
            seeds[lineNumber] = Integer.valueOf(line);
            lineNumber++;
        }
        br.close();
        return seeds;
    }

}
