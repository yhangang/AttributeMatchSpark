/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.audaque.instancematch.match;

import java.util.List;
import scala.Tuple2;

/**
 *
 * @author Administrator
 */
public class CalSetResemblance {
    /**
     * 由签名文件比较域的相似度
     * @param baseSigList
     * @param patternSigList
     * @return 
     */
     public static double[] getSetResemblance(List<Tuple2<String, String>> baseSigList, List<Tuple2<String, String>> patternSigList) {
        double[] rou = new double[3];// 等于，小于，大于
        int equality = 0;
        int lower = 0;
        int higher = 0;
        // 两个签名应该等长
        if (baseSigList.size() != patternSigList.size()) {
            System.out.println("签名文件不等长，请核查");
            return rou;
        }

        // 数组第三个值代表哈希值
        for (int i = 0; i < baseSigList.size(); i++) {
            if (baseSigList.get(i)._2().equals(patternSigList.get(i)._2())) {
                equality++;
            } else if (Integer.valueOf(baseSigList.get(i)._2()) < Integer.valueOf(patternSigList.get(i)._2())) {
                lower++;
            } else {
                higher++;
            }
        }
        rou[0] = (double) equality / baseSigList.size();
        rou[1] = (double) lower / baseSigList.size();
        rou[2] = (double) higher / baseSigList.size();
        return rou;
    }
    
}
