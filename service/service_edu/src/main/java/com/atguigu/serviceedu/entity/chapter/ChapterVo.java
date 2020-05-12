package com.atguigu.serviceedu.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/11 16:09
 */
@Data
public class ChapterVo {
    private String id;
    private String title;

    private List<VideoVo> children=new ArrayList<>();
}
