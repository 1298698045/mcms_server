package net.mingsoft.custom.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.mingsoft.base.dao.IBaseDao;
import net.mingsoft.custom.entity.ExpertEntity;
import net.mingsoft.custom.entity.VExpertEntity;

import java.util.List;

/**
 * 专家管理持久层
 * @author t-think
 * 创建日期：2021-6-27 12:29:09<br/>
 * 历史修订：<br/>
 */
public interface IExpertDao extends IBaseDao<ExpertEntity> {
    List<VExpertEntity> listExperts(VExpertEntity expertEntity);
    VExpertEntity getExpert(ExpertEntity expertEntity);
}
