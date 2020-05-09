package com.atguigu.serviceedu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/9 15:16
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjcetName;
    @ExcelProperty(index = 1)
    private String twoSubjcetName;
}
