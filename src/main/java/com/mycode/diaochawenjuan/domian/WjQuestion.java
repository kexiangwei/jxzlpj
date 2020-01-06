package com.mycode.diaochawenjuan.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * 调查问卷-问卷设置-题库
 * @auther kexiangwei
 * @date 2019/10/8
 */

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class WjQuestion {

    @JsonIgnore
    private Integer pageIndex=1
            ,pageSize=10;
    @NonNull
    private String wjCode;
    private String wjName;
    private String qCode
            ,qContent
            ,optA,optB,optC,optD;

}
