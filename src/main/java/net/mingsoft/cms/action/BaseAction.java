package net.mingsoft.cms.action;

import java.util.MissingResourceException;

/**
 * @Date: 2019/8/9 20:47
 */
public class BaseAction extends net.mingsoft.mdiy.action.BaseAction{
    @Override
    protected String getResString(String key) {
        // TODO Auto-generated method stub
        String str = "";
        try {
            str = super.getResString(key);
        } catch (MissingResourceException e) {
            str = getLocaleString(key,net.mingsoft.cms.constant.Const.RESOURCES);
        }

        return str;
    }
}
