package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/9 14:45
 */
public class EasyExcelWrite {
    public static void main(String[] args) {
        String filename="D:\\write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
}
