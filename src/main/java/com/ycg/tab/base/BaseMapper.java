package com.ycg.tab.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author 杨剑
 * @date 2019-09-25
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
