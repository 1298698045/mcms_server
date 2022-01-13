package net.mingsoft.cms.constant.e;

import net.mingsoft.base.constant.e.BaseEnum;

/**
 * @Description:
 * @Date: Create in 2020/06/23 14:18
 */
public enum CategoryTypeEnum implements BaseEnum {

    /**
     * 列表
     */
    LIST("1"),
    /**
     * 封面
     */
    COVER("2"),
    /**
     * 链接
     */
    LINK("3");


    CategoryTypeEnum(String type) {
        this.type = type;
    }

    private String type;

    public static CategoryTypeEnum get(String type) {
        for (CategoryTypeEnum e : CategoryTypeEnum.values()) {
            if (e.type.equals(type)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public int toInt() {
        return Integer.parseInt(type);
    }

    @Override
    public String toString() {
        return type;
    }
}
