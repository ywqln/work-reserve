package com.qln.workreserve.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.qln.workreserve.paper.dbo.Paper;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * 描述：Contoller基类
 * </p>
 *
 * @author QinLiNa
 * @data 2019/6/10
 */
public abstract class BaseController {

    public abstract void workFlow();

    private String jsonPath = "C:\\json/niug.json";


    protected String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY);
    }

    protected void print(String info) {
        System.out.println(info);
    }

    protected List<Paper> readJson2Paper() {
        String json = readJsonFile(jsonPath);
        List<Paper> papers = JSONObject.parseArray(json, Paper.class);
        return papers;
    }

    /**
     * 读取json文件，返回json串
     *
     * @param fileName
     * @return
     */
    protected String readJsonFile(String fileName) {
        return readFile(new File(fileName));
    }

    protected String readFile(File jsonFile){
        String jsonStr = StringUtils.EMPTY;
        try {
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void writeFile(String path, String content) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(path));
            out.write(content);
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
        }
    }

    public <T> T copyObject(Object data, Class<T> clazz) {
        String json = JSONObject.toJSONString(data);
        T t = JSONObject.parseObject(json, clazz);
        return t;
    }

    public <T> List<T> copyList(List data, Class<T> clazz) {
        String json = JSONObject.toJSONString(data);
        List<T> t = JSONObject.parseArray(json, clazz);
        return t;
    }
}
