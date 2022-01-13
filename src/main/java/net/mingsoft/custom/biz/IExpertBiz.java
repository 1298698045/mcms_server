package net.mingsoft.custom.biz;

import net.mingsoft.base.biz.IBaseBiz;
import net.mingsoft.custom.entity.ExpertEntity;
import net.mingsoft.custom.entity.VExpertEntity;

import java.util.List;

/**
 * 专家管理业务
 * @author t-think
 * 创建日期：2021-6-27 12:29:09<br/>
 * 历史修订：<br/>
 */
public interface IExpertBiz extends IBaseBiz<ExpertEntity> {
    List<VExpertEntity> listExperts(VExpertEntity expertEntity);
    VExpertEntity getExpert(ExpertEntity expertEntity);
}
