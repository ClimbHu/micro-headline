package com.headline.controller;

import com.headline.common.Result;
import com.headline.pojo.NewsType;
import com.headline.pojo.vo.HeadlineDetailVo;
import com.headline.pojo.vo.HeadlineQueryVo;
import com.headline.service.NewsHeadlineService;
import com.headline.service.NewsTypeService;
import com.headline.service.impl.NewsHeadlineServiceImpl;
import com.headline.service.impl.NewsTypeServiceImpl;
import com.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门户 控制器
 * 那些不需要登录，不需要做增删改查的门户页的请求都放在这里
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController {

    private NewsTypeService typeService = new NewsTypeServiceImpl();

    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();

    /**
     * 根据新闻id查询头条详情业务接口实现
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取新闻id
        int hid = Integer.parseInt(req.getParameter("hid"));
        // 查询数据
        HeadlineDetailVo headline = headlineService.showHeadlineDetail(hid);
        Map data = new HashMap();
        data.put("headline", headline);
        // 返回前端
        WebUtil.writeJson(resp, Result.ok(data));
    }

    /**
     * 分页带条件查询所有头条
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接受请求中的参数
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
        // 将参数传递给服务层 进行分页查询
        /**
         * pageInfo: [
         *      {
         *              	"hid":"1",                     // 新闻id
         *     				"title":"尚硅谷宣布 ... ...",   // 新闻标题
         *     				"type":"1",                    // 新闻所属类别编号
         *     				"pageViews":"40",              // 新闻浏览量
         *     				"pastHours":"3" ,              // 发布时间已过小时数
         *     				"publisher":"1"                // 发布用户ID
         *      }
         * ]
         * pageNum: 1
         * pageSize: 1
         * totalPage: 1
         * totalSize: 1
         */
        Map pageInfo = headlineService.findNewsPage(headlineQueryVo);
        Map data = new HashMap();
        data.put("pageInfo", pageInfo);

        // 将分页查询的结果转换成json相应给客户端
        WebUtil.writeJson(resp, Result.ok(data));
    }

    /**
     * 查询所有头条类型的业务接口实现
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有的新闻类型，装入Result对象响应给客户端
        List<NewsType> typeList = typeService.findAll();
        WebUtil.writeJson(resp, Result.ok(typeList));
    }
}
