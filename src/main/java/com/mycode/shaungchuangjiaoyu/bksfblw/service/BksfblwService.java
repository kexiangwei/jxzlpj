package com.mycode.shaungchuangjiaoyu.bksfblw.service;

import com.mycode.shaungchuangjiaoyu.bksfblw.domian.Bksfblw;

import java.util.Map;

/**
 * 双创教育-本科生发表论文
 */
public interface BksfblwService {

    Map<String, Object> getPageList(Bksfblw bksfblw);

    boolean insert(Bksfblw bksfblw);

    boolean update(Bksfblw bksfblw);

    boolean delete(String code);

}
