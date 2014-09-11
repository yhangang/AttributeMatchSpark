/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audaque.instancematch.spark;

import com.audaque.instancematch.match.CalSetResemblance;
import com.audaque.instancematch.match.GenerateSignature;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

/**
 *
 * @author Administrator
 */
public class Run {

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        int hashNum = 1000;
        final int q = -1;
        String srcFile = "hdfs://172.16.1.101:8020/user/ALGO/match/data/address.txt";
        String srcFile1 = "hdfs://172.16.1.101:8020/user/ALGO/match/data1/address2.txt";
        String seedFile = "hdfs://172.16.1.101:8020/user/ALGO/match/seed/seed0.txt";
        
//        String destFile = "hdfs://172.16.1.101:8020/user/ALGO/result";
        //初始化spark配置
        SparkConf conf = new SparkConf().setAppName("Schema Matching");
//        conf.setMaster("spark://hadoop01.audaque:7060");
//        conf.setMaster("local[4]");
        conf.set("spark.executor.memory", "1g");
        conf.set("spark.cores.max", "40");
        JavaSparkContext sc = new JavaSparkContext(conf);

        long startTime1 = System.currentTimeMillis(); //获取开始时间
        List<Tuple2<String, String>> baseSigList = GenerateSignature.generateSignature(srcFile, seedFile, q, hashNum, sc);
        List<Tuple2<String, String>> patternSigList = GenerateSignature.generateSignature(srcFile1, seedFile, q, hashNum, sc);
        double[] rou = CalSetResemblance.getSetResemblance(baseSigList, patternSigList);
        long endTime1 = System.currentTimeMillis(); //获取结束时间
        System.out.println("与知识库地址列 相似度：A=B:" + rou[0] + "  A-B:" + rou[1] + "  B-A:" + rou[2]);
        System.out.println("耗时： " + (endTime1 - startTime1) / 1000.0 + "s");

    }

}
