/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audaque.instancematch.match;

import com.audaque.instancematch.hash.Hash;
import com.audaque.instancematch.hash.QGramHash;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import scala.Tuple2;

/**
 *
 * @author Administrator
 */
public class GenerateSignature {
    /**
     * 根据参数q,hashNum生成签名，放入tuple2中，key表示种子编号，value为哈希值，返回JavaPairRDD
     * @param srcFile 源文件路径
     * @param seedFile
     * @param q q-gram的q
     * @param hashNum 哈希函数个数
     * @param sc
     * @return
     * @throws IOException 
     */
    public static List<Tuple2<String, String>> generateSignature(String srcFile, String seedFile,final int q, int hashNum, JavaSparkContext sc) throws IOException {
//        final int[] seeds = Hash.loadSeeds("res/seed0.txt", hashNum);// 加载哈希种子

        JavaRDD<String> srcRDD = sc.textFile(srcFile,40);
        JavaRDD<String> seedRDD = sc.textFile(seedFile);
        final List<String> seedList = seedRDD.collect();

        JavaPairRDD<String, String> seeds_hashRDD = srcRDD.flatMapToPair(new PairFlatMapFunction<String, String, String>() {
            @Override
            public Iterable<Tuple2<String, String>> call(String line) throws Exception {
                List<Tuple2<String, String>> list = new ArrayList<Tuple2<String, String>>();
                for (int i = 0; i < seedList.size(); i++) {
                    int hash;
                    if (q < 0) {
                        hash = Hash.RSHash(line, Integer.valueOf(seedList.get(i)));
                    } else {
                        hash = QGramHash.RSHash(line, Integer.valueOf(seedList.get(i)), q);
                    }
                    list.add(new Tuple2<String, String>(seedList.get(i),String.valueOf(hash)));
                }
                return list;
            }
        });

        JavaPairRDD<String, String> seed_hashRDD = seeds_hashRDD.reduceByKey(new Function2<String, String, String>() {
            @Override
            public String call(String v1, String v2) throws Exception {
                if (Integer.valueOf(v1)<Integer.valueOf(v2)) {
                    return v1;
                } else {
                    return v2;
                }
            }
        });
//        seeds_hashRDD.sortByKey().saveAsTextFile("hdfs://172.16.1.101:8020/user/ALGO/result");
        return seed_hashRDD.sortByKey().collect();
    }
}
