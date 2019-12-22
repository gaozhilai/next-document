package com.gzl.next.document.mapper;

import com.gzl.next.document.pojo.entity.DocProject;
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
     * @param privateProject 1私人项目, 0公共项目
     * @return
     */
    int createProject(@Param("projectName") String projectName,
                      @Param("description") String description,
                      @Param("createdBy") Long createdBy,
                      @Param("updatedBy") Long updatedBy,
                      @Param("privateProject") Boolean privateProject);

    /**
     * 根据id查询项目详情
     * @param id 项目id
     * @return 项目详情
     */
    DocProject getProjectById(@Param("id") Long id);

    /**
     * 更新项目信息
     * @param id 项目id
     * @param projectName 项目名称
     * @param description 项目描述
     * @param updatedBy 更新者id
     * @return 返回1为成功
     */
    int updateProjectById(@Param("id") Long id, @Param("projectName") String projectName,
                          @Param("description") String description, @Param("updatedBy") Long updatedBy);
}
