package com.demo.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

import static com.demo.hadoop.config.HadoopEnv.HDFS_PREFIX;

public class AppendDataToHDFS {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("dfs.replication","1");
        conf.set("fs.defaultFS", HDFS_PREFIX);
//        String localSrc = "E:\\ghost\\mrtest\\files\\localfile.txt";
        InputStream in = null;
        try {
//            in = new BufferedInputStream(new FileInputStream(localSrc));
            in = new ByteArrayInputStream("Hello,World! From hadoop-basic".getBytes());

            //拿到HDFS文件系统中的URI
            String dest = "/hdfs/create/demo.txt";
            FileSystem fs = FileSystem.get(URI.create(dest), conf);
            FSDataOutputStream fos = fs.append(new Path(dest));
            IOUtils.copyBytes(in, fos, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
