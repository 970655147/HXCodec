package com.hx.codec.utils;

import com.hx.codec.constants.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * FileUtils
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/4/16 13:59
 */
public class FileUtils {

    // disable constructor
    private FileUtils() {
        throw new RuntimeException(" can't be instantiate ");
    }

    /**
     * 读取文件的各个行
     *
     * @return java.util.List<java.lang.String>
     * @author Jerry.X.He
     * @date 2021/4/16 17:21
     */
    public static List<String> readLines(File file, String charset) {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> readLines(String filePath, String charset) {
        return readLines(new File(filePath), charset);
    }

    public static List<String> readLines(File file) {
        return readLines(file, Constants.STRING_UTF8);
    }

    public static List<String> readLines(String filePath) {
        return readLines(filePath, Constants.STRING_UTF8);
    }

    /**
     * readContent
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021/5/6 14:40
     */
    public static String readContent(File file, String charset) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\r\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readContent(String filePath, String charset) {
        return readContent(new File(filePath), charset);
    }

    public static String readContent(File file) {
        return readContent(file, Constants.STRING_UTF8);
    }

    public static String readContent(String filePath) {
        return readContent(filePath, Constants.STRING_UTF8);
    }

    /**
     * traverse
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/2 9:48
     */
    public static void traverse(String filePath, FileFilter fileFilter, Consumer<File> applyFunc) {
        traverse(new File(filePath), fileFilter, applyFunc);
    }

    public static void traverse(File file, FileFilter fileFilter, Consumer<File> applyFunc) {
        if (file.isFile()) {
            if (fileFilter.accept(file)) {
                applyFunc.accept(file);
            }
            return;
        }

        for (File childFile : file.listFiles()) {
            if (file.isFile()) {
                if (fileFilter.accept(file)) {
                    applyFunc.accept(file);
                }
                continue;
            }

            traverse(childFile, fileFilter, applyFunc);
        }
    }

}

