package com.starter.medicalcommon.vo.request;

import lombok.Data;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 20:45
 **/
@Data
public class DeviceRequest {
    private String id;
    private String userId;
    private String deviceId;
    private String category;
    private String type;
    private Integer state;
}
