package com.jiankun.blog.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.jiankun.blog.dao.IBlogDao;
import com.jiankun.blog.dao.impl.BlogDaoImpl;
import com.jiankun.blog.pojo.Blog;

import java.util.Map;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/20 10:42
 */
public class BlogExcelListener extends AnalysisEventListener<Blog> {
    private IBlogDao blogDao = new BlogDaoImpl();

    @Override
    public void invoke(Blog blog, AnalysisContext analysisContext) {
        System.out.println(blog);
        blogDao.addExcel(blog);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("doAfterAllAnalysed");
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头数据： " + headMap);
    }
}
