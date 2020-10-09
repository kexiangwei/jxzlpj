package com.mycode.shaungchuangjiaoyu.bkssqzl.service;

import com.mycode.shaungchuangjiaoyu.bkssqzl.domian.Bkssqzl;

import java.util.Map;

/**
 * 双创教育-本科生申请专利
 */
public interface BkssqzlService {

    Map<String, Object> getPageList(Bkssqzl bkssqzl);

    boolean insert(Bkssqzl bkssqzl);

    boolean update(Bkssqzl bkssqzl);

    boolean delete(String code);

}
