/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audaque.instancematch.match;

import com.audaque.instancematch.hash.Hash;
import com.audaque.instancematch.hash.QGramHash;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import scala.Tuple2;

/**
 *
 * @author Administrator
 */
public class GenerateSignature2 {

    public static List<Tuple2<String, String>> generateSignature(final String srcFile, String seedFile, final int q, int hashNum, JavaSparkContext sc) {
        JavaRDD<String> seedRDD = sc.textFile(seedFile, 40);

        JavaPairRDD<String, String> seeds_hashRDD = seedRDD.mapPartitionsToPair(new PairFlatMapFunction<Iterator<String>, String, String>() {
            @Override
            public Iterable<Tuple2<String, String>> call(Iterator<String> seed) throws Exception {
                List<Integer> seedList = new ArrayList<Integer>();
                while (seed.hasNext()) {
                    seedList.add(Integer.valueOf(seed.next()));
                }
                int[] minHash = new int[seedList.size()];
                for (int i = 0; i < minHash.length; i++) {
                    minHash[i] = Integer.MAX_VALUE;
                }
                //流式读取hdfs文本文件
                Configuration conf = new Configuration();
                FileSystem fs = FileSystem.get(URI.create(srcFile), conf);
                FSDataInputStream in = fs.open(new Path(srcFile));
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    for (int i = 0; i < seedList.size(); i++) {
                        int hash;
                        if (q < 0) {
                            hash = Hash.RSHash(line, seedList.get(i));
                        } else {
                            hash = QGramHash.RSHash(line, seedList.get(i), q);
                        }
                        if (hash < minHash[i]) {
                            minHash[i] = hash;
                        }
                    }

                }
                List<Tuple2<String, String>> tList = new ArrayList<Tuple2<String, String>>();
                for(int i = 0; i < seedList.size(); i++){
                    tList.add(new Tuple2<String, String>(String.valueOf(seedList.get(i)), String.valueOf(minHash[i])));
                }
                
                return tList;
            }
        });

        return seeds_hashRDD.sortByKey().collect();
    }

}
