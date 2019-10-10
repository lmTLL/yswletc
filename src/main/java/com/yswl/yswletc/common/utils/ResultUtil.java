package com.yswl.yswletc.common.utils;


import com.yswlkj.yswl.common.vo.ResultVo;
import com.yswlkj.yswl.common.vo.SystemCon;


public class ResultUtil {
    public static ResultVo exec(boolean istrue, String msg, Object data){
        ResultVo resultVo = new ResultVo();
        if(istrue){
            resultVo.setCode(SystemCon.OK);
        }else{
            resultVo.setCode(SystemCon.ERROR);
        }
        resultVo.setMsg(msg);
        resultVo.setData(data);
        return resultVo;
    }
}
