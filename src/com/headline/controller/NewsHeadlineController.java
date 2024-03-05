package com.headline.controller;

import com.headline.common.Result;
import com.headline.dao.NewsHeadLineDao;
import com.headline.pojo.NewsHeadline;
import com.headline.service.NewsHeadlineService;
import com.headline.service.impl.NewsHeadlineServiceImpl;
import com.headline.util.JwtHelper;
import com.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController {

    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();

    /**
     * 删除头条业务接口
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取新闻id
        int hid = Integer.parseInt(req.getParameter("hid"));
        // 删除数据
        headlineService.removeByHid(hid);
        // 返回前端
        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * 更新新闻信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsHeadline headline = WebUtil.readJson(req, NewsHeadline.class);
        headlineService.update(headline);
        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * 修改头条回显业务接口
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接受参数
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        // 调用服务层查询数据
        NewsHeadline headline = headlineService.findByHid(hid);
        Map data = new HashMap();
        data.put("headline", headline);
        // 响应前端
        WebUtil.writeJson(resp, Result.ok(data));
    }


    /**
     * 发布头条接口
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接受参数
        String token = req.getHeader("token");
        // 通过token获取发布者ID
        Long userId = JwtHelper.getUserId(token);
        NewsHeadline headline = WebUtil.readJson(req, NewsHeadline.class);
        headline.setPublisher(userId.intValue());
        // 将数据存入数据库
        headlineService.addNewsHeadline(headline);
        WebUtil.writeJson(resp, Result.ok(null));
    }
}