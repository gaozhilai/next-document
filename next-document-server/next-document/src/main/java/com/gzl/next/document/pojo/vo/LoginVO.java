package com.gzl.next.document.pojo.vo;

import com.gzl.next.document.pojo.dto.RolePermissionDTO;
import lombok.Builder;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Data
@Builder
public class LoginVO {
    private String token;
    private RolePermissionDTO roleAndPermission;
}
