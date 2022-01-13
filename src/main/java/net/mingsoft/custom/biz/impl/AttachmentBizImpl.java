/**
The MIT License (MIT) * Copyright (c) 2019 铭飞科技

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.mingsoft.custom.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.mingsoft.base.biz.impl.BaseBizImpl;
import net.mingsoft.base.dao.IBaseDao;
import net.mingsoft.custom.entity.AttachmentEntity;
import net.mingsoft.custom.biz.IAttachmentBiz;
import net.mingsoft.custom.dao.IAttachmentDao;

/**
 * 附件管理管理持久化层
 * @author t-think
 * 创建日期：2021-8-20 16:48:45<br/>
 * 历史修订：<br/>
 */
 @Service("customattachmentBizImpl")
public class AttachmentBizImpl extends BaseBizImpl<IAttachmentDao,AttachmentEntity> implements IAttachmentBiz {


	@Autowired
	private IAttachmentDao attachmentDao;


	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return attachmentDao;
	}
}
