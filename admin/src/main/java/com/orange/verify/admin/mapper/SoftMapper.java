package com.orange.verify.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orange.verify.api.bean.Soft;
import com.orange.verify.api.vo.SoftVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SoftMapper extends BaseMapper<Soft> {

    @Select("<script>" +
            "SELECT " +
            "s.name, " +
            "s.id, " +
            "s.service_status, " +
            "(SELECT count(*) FROM t_account a where a.soft_id = s.id ) as account_total, " +
            "(SELECT sv.number FROM t_soft_versions sv WHERE sv.soft_id = s.id) as versions_num " +
            "FROM " +
            "t_soft s " +
            "where s.del_flag = 0 " +
            "<if test=\"soft.name != null and soft.name != ''\"> and s.name like concat('%',#{soft.name},'%') </if>" +
            "</script>")
    List<SoftVo> page(@Param("soft") Soft soft,Page page);

}