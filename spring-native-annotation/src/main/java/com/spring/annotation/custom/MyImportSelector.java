package com.spring.annotation.custom;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author cyl
 * @date 2022-08-15 11:38
 * @description 自定义逻辑导入所需要的组件
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 不要返回空值
        return new String[]{"com.spring.annotation.bean.Green", "com.spring.annotation.bean.Orange"};
    }
}
