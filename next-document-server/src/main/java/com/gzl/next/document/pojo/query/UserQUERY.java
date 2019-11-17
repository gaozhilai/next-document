package com.gzl.next.document.pojo.query;

import com.gzl.next.document.validate.SelectGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Data
public class UserQUERY {
    @NotNull(groups = SelectGroup.class)
    private String loginName;
    @NotNull(groups = SelectGroup.class)
    private String password;
}
