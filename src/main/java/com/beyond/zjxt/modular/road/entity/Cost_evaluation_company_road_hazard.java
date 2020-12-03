package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 第三方巡检机构负责的病害表
 * </p>
 *
 * @author beyond
 * @since 2019-12-03
 */
@Data
public class Cost_evaluation_company_road_hazard implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 第三方造价评估公司id(关联着sys_user表)
     */
    private Long third_party_id;

    /**
     * 负责的病害id
     */
    private Long application_id;

    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;
    private int status;
}
