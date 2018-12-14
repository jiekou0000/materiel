package com.bill.materiel.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bill.materiel.utils.message.Message;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Bill
 */
public class JsonUtils {

    public static void write(ServletResponse response, Message message)
            throws IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(message));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是不是ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    /**
     * 判断一个字符串是不是json格式
     *
     * @param jsonString
     * @return
     */
    public static JSONObject isGoodJson(String jsonString) {
        try {
            if (StringUtils.isEmpty(jsonString)) {
                return null;
            }
            return (JSONObject) JSONObject.parse(jsonString);
        } catch (Exception e) {
            return null;
        }
    }


}
