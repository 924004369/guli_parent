package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/9 13:24
 */
@Data
public class DemoData {
    //设置表头名称
    @ExcelProperty(value = "学生编号",index=0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名",index=1)
    private String sname;
}
