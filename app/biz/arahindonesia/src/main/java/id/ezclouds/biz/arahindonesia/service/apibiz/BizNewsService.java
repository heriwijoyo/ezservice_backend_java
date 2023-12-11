/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.apibiz;

import id.ezclouds.biz.arahindonesia.model.news.SimpleNews;
import id.ezclouds.biz.arahindonesia.service.core.NewsService;
import id.ezclouds.core.shared.result.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizNewsService.java, v 0.1 2023‐12‐11 1:20 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizNewsService {

    @Autowired
    private NewsService newsService;

    public ListResult<SimpleNews> getActiveNews() {
        ListResult<SimpleNews> result = new ListResult<>();
        result.setItems(newsService.getActiveListNews());
        return result;
    }
}