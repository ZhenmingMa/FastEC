package com.cby.fastec.generator;

import com.cby.orange.annotations.AppRegisterGenerator;
import com.cby.orange.wechat.templates.AppRegisterTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.cby.fastec",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
