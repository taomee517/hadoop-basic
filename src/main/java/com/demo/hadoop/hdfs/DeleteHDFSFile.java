package com.demo.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;

public class DeleteHDFSFile {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        //拿到HDFS文件系统中的URI
        String uri = "hdfs://localhost:19000/hdfs/create/demo.txt";
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        //第二个参数true代表是否递归删除整个文件（夹）
        fs.delete(new Path(uri),true);

    }
}
