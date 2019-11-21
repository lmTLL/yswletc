package com.yswl.yswletc.service;

import com.yswl.yswletc.common.vo.ResultVo;

/**
 * User: jang
 * Date: 2019/11/7
 * Time: 15:51
 * Description: No Description
 */
public interface MessageService {

    ResultVo pushMassTexting(String content);

    ResultVo achievementPass(Integer id, String comment);

    ResultVo achievementject(Integer id, String comment);

    ResultVo pushOrdersPass(Integer id);

    ResultVo pushOrdersReject(Integer id, String comment);
}
