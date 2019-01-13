package com.github.ailitech.rest.dal.util;

import com.alibaba.druid.pool.DruidDataSource;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DruidUtil {

    public static void configDruid(DruidDataSource ds,Object config){
        try{
            BeanInfo dsInfo=Introspector.getBeanInfo(ds.getClass());
            PropertyDescriptor[] dsProperties=dsInfo.getPropertyDescriptors();

            BeanInfo configInfo= Introspector.getBeanInfo(config.getClass());
            PropertyDescriptor[] configProperties=configInfo.getPropertyDescriptors();
            int configSize=configProperties.length;
            for(int i=0;i<configSize;i++){
                PropertyDescriptor configProp=configProperties[i];
                if(!configProp.getName().equalsIgnoreCase("class")){
                    Method propGetter=configProp.getReadMethod();
                    Object propValue=propGetter.invoke(config);
                    if(propValue!=null){
                        Arrays.stream(dsProperties).filter(p->p.getName().equalsIgnoreCase(configProp.getName()))
                                .findFirst().ifPresent(p->{
                            try{
                                Method dsPropSetter=p.getWriteMethod();
                                dsPropSetter.invoke(ds,propValue);
                            }catch (Exception ex){

                            }

                        });
                    }

                }
            }

        }catch (Exception ex){

        }

    }
}
