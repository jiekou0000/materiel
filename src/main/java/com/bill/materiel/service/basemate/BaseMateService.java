package com.bill.materiel.service.basemate;

import com.bill.materiel.dao.LightRepository;
import com.bill.materiel.domain.basemate.Light;
import com.bill.materiel.utils.message.Message;
import com.bill.materiel.utils.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * BaseMateService
 *
 * @author Bill
 * @date 2018/12/17 0017
 */
@Service
public class BaseMateService {
    @Autowired
    private LightRepository lightRepository;

    /**
     * light Etc Order
     */
    public Message lightEtcOrder(String phoneNum, Light req) {
        Light light = Light.builder()
                .phoneNum(phoneNum)
                .type(req.getType())
                .num(req.getNum())
                .startTime(req.getStartTime())
                .day(req.getDay())
                .createTime(new Date())
                .state(req.getState())
                .build();
        lightRepository.save(light);
        return new Message(MessageType.SUCCESS);
    }
}
