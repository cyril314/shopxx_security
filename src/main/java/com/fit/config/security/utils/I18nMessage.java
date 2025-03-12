package com.fit.config.security.utils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

/**
 * @AUTO 国际化消息
 * @Author AIM
 * @DATE 2025/1/15
 */
@Component("i18nMessage")
public class I18nMessage implements TemplateMethodModel {

    @Lazy
    @Autowired
    private MessageSource messageSource;

    // 注册项类型：
    public enum AttributeType {
        text, number, alphaint, email, select, checkbox, name, gender, date, area, address, zipCode, mobile, phone, qq, msn, wangwang, skype
    }

    // 配送类型：先付款后发货、货到付款
    public enum DeliveryMethod {
        deliveryAgainstPayment, cashOnDelivery
    }

    // 导航位置:顶部、中间、底部
    public enum Position {
        top, middle, bottom
    }

    // 排序方式
    public enum OrderType {
        asc, desc
    }

    // 财付通交易类型（即时交易、担保交易-实物、担保交易-虚拟）
    public enum TenpayType {
        direct, partnerMaterial, partnerVirtual
    }

    // 订单状态（未处理、已处理、已完成、已作废）
    public enum OrderStatus {
        unprocessed, processed, completed, invalid
    }

    // 支付结果（成功、失败）
    public enum PaymentResult {
        success, failure
    }

    // 支付类型（在线充值、预存款支付、在线支付、线下支付）
    public enum PaymentType {
        recharge, deposit, online, offline
    }

    // 付款状态（未支付、部分支付、已支付、部分退款、全额退款）
    public enum PaymentStatus {
        unpaid, partPayment, paid, partRefund, refunded
    }

    // 退款类型（预存款支付、在线支付、线下支付）
    public enum RefundType {
        deposit, online, offline
    }

    // 配送状态（未发货、部分发货、已发货、部分退货、已退货）
    public enum ShippingStatus {
        unshipped, partShipped, shipped, partReshiped, reshiped
    }

    public enum WatermarkPosition {
        no, topLeft, topRight, center, bottomLeft, bottomRight
    }

    // 重量单位（克、千克、吨）
    public enum WeightUnit {
        g, kg, t
    }

    public String getEnumMessage(String prefix, Integer index) {
        String msg_code = "";
        switch (prefix) {
            case "AttributeType.":
                msg_code = prefix + AttributeType.values()[index].name();
                break;
            case "DeliveryMethod.":
                msg_code = prefix + DeliveryMethod.values()[index].name();
                break;
            case "OrderStatus.":
                msg_code = prefix + OrderStatus.values()[index].name();
                break;
            case "PaymentType.":
                msg_code = prefix + PaymentType.values()[index].name();
                break;
            case "PaymentStatus.":
                msg_code = prefix + PaymentStatus.values()[index].name();
                break;
            case "Position.":
                msg_code = prefix + Position.values()[index].name();
                break;
            case "RefundType.":
                msg_code = prefix + RefundType.values()[index].name();
                break;
            case "ShippingStatus.":
                msg_code = prefix + ShippingStatus.values()[index].name();
                break;
            case "WatermarkPosition.":
                msg_code = prefix + WatermarkPosition.values()[index].name();
                break;
            case "WeightUnit.":
                msg_code = prefix + WeightUnit.values()[index].name();
                break;
        }
        return this.getMessage(msg_code);
    }

    /**
     * 获取国际化消息
     *
     * @param code 代码
     * @param args 参数
     * @return 国际化消息
     */
    public String getMessage(String code, Object... args) {
        // 从 MessageSource 中获取国际化消息
        return messageSource.getMessage(code, args, Locale.getDefault());
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        if (list != null && list.size() > 0) {
            String message = null;
            String code = list.get(0).toString();
            if (list.size() > 1) {
                message = getEnumMessage(code, Integer.parseInt(list.get(1).toString()));
            } else {
                message = getMessage(code);
            }
            return new SimpleScalar(message);
        }
        return null;
    }
}