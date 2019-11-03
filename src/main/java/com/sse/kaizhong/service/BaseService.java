package com.sse.kaizhong.service;

import java.util.List;

/**
 * @name: BaseService
 * @author: xiangyf
 * @create: 2019-11-03 16:58
 * @description:
 */
public interface BaseService {

    default List<Object> beforeExecute(List<Object> objects){return null;};

    default List<Object> execute(List<Object> objects){return null;};

    default List<Object> afterExecute(List<Object> objects){return null;};
}
