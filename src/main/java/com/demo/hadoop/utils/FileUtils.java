package com.demo.hadoop.utils;

import java.io.File;

public class FileUtils {


    public static void outputFileDelete(String outDir){
        File outFile = new File(outDir);
        if(outFile.exists()){
            boolean deleteResult = outFile.delete();
            if (!deleteResult) {
                if(outFile.isDirectory()){
                    for(File file : outFile.listFiles()){
                        file.delete();
                    }
                    deleteResult = outFile.delete();
                }
            }
            System.out.println("文件删除成功?" + deleteResult);
        }
    }
}
