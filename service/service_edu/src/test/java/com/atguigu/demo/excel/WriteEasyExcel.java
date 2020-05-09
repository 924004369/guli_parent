package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/9 14:25
 */
public class WriteEasyExcel {
    public static void main(String[] args) {

        String filename="D:\\write.xlsx";

        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
    }


    private static List<DemoData> getData(){
        List<DemoData> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            DemoData demoData=new DemoData();
            demoData.setSno(i);
            demoData.setSname("lucy"+i);
            list.add(demoData);
        }
        return list;
    }
}
