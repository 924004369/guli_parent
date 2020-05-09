package com.atguigu.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/9 14:39
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {
    //一行一行的读取excel的内容
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println("***"+data);
    }
    //读取表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }
    //读取完成之后操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("success");
    }
}
