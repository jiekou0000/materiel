package com.bill.materiel.consts;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 订单枚举
 *
 * @author Bill
 */
public class OrderEnum {
    /**
     * 订单类目
     */
    public enum ORDER_CATEGORY {
        LIGHT("10", "灯光");
        @Getter
        private String key;
        @Getter
        private String value;

        ORDER_CATEGORY(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static String getValueByKey(String key) {
            if (StringUtils.isEmpty(key)) {
                return "";
            }
            for (OrderEnum.ORDER_CATEGORY e : OrderEnum.ORDER_CATEGORY.values()) {
                if (key.equals(e.getKey())) {
                    return e.getValue();
                }
            }
            return "";
        }
    }

    /**
     * 订单类型
     */
    public enum ORDER_TYPE {
        ETC("1001", "帕灯"),
        DOWN("1002", "筒灯");
        @Getter
        private String key;
        @Getter
        private String value;

        ORDER_TYPE(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static String getValueByKey(String key) {
            if (StringUtils.isEmpty(key)) {
                return "";
            }
            for (OrderEnum.ORDER_TYPE e : OrderEnum.ORDER_TYPE.values()) {
                if (key.equals(e.getKey())) {
                    return e.getValue();
                }
            }
            return "";
        }
    }

    /**
     * 订单状态
     */
    public enum ORDER_STATE {
        PEND("0", "待定"),
        SURE("1", "确定"),
        CANCEL("2", "撤单");
        @Getter
        private String key;
        @Getter
        private String value;

        ORDER_STATE(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static String getValueByKey(String key) {
            if (StringUtils.isEmpty(key)) {
                return "";
            }
            for (OrderEnum.ORDER_STATE e : OrderEnum.ORDER_STATE.values()) {
                if (key.equals(e.getKey())) {
                    return e.getValue();
                }
            }
            return "";
        }
    }
}
