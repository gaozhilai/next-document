package com.gzl.next.document.mapper;

import com.gzl.next.document.pojo.entity.ConfigModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/12/29 15:05
 * No Description
 */
@Repository
public interface ConfigModelMapper {
    /**
     * 批量插入模块配置
     * @param configModels 配置信息
     * @return 成功新增数据条数
     */
    int insertBatchConfigModel(@Param("configModels") List<ConfigModel> configModels);
}
