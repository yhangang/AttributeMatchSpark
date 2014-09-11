/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audaque.instancematch.spark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 *
 * @author Administrator
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String uri = "hdfs://172.16.1.101:8020/user/ALGO/match/data1/address2.txt";
        Configuration conf = new Configuration();
//        conf.set("hadoop.job.ugi", "ALGO,audaque.algo");
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FSDataInputStream in = fs.open(new Path(uri));
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

//        IOUtils.copyBytes(in, System.out,4096,false);
//        System.out.println();
    }

}
