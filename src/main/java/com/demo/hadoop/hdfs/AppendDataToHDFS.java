package com.demo.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

public class AppendDataToHDFS {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("dfs.replication","1");
        String localSrc = "E:\\ghost\\mrtest\\files\\localfile.txt";
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(localSrc));

            //拿到HDFS文件系统中的URI
            String dest = "hdfs://localhost:19000/hdfs/create/demo.txt";
            FileSystem fs = FileSystem.get(URI.create(dest), conf);
            FSDataOutputStream fos = fs.append(new Path(dest));
            IOUtils.copyBytes(in, fos, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
