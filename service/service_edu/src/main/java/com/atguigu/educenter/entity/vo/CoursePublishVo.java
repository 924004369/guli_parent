package com.atguigu.educenter.entity.vo;

import lombok.Data;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/12 13:13
 */
@Data
public class CoursePublishVo {
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}
