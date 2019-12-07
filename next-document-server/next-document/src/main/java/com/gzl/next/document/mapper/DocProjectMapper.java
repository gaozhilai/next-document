package com.gzl.next.document.mapper;

import com.gzl.next.document.pojo.entity.DocProject;
import com.gzl.next.document.pojo.query.ProjectQUERY;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 项目DAO操作
 */
@Mapper
public interface DocProjectMapper {
    /**
     * 查询项目列表
     * @param id 项目id精确查询
     * @param projectName 项目名称模糊查询
     * @param description 项目描述模糊查询
     * @return 项目列表
     */
    List<DocProject> getProjectList(@Param("id") Long id,
                                    @Param("projectName") String projectName,
                                    @Param("description") String description);

    /**
     * 创建项目
     * @param projectName 项目名称
     * @param description 项目描述
     * @param createdBy 创建者id
     * @param updatedBy 更新者id
     * @return
     */
    int createProject(@Param("projectName") String projectName,
                      @Param("description") String description,
                      @Param("createdBy") Long createdBy,
                      @Param("updatedBy") Long updatedBy);
}
