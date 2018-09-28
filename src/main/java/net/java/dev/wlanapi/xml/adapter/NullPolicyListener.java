package net.java.dev.wlanapi.xml.adapter;

import javax.xml.bind.Marshaller;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @Descirption 处理空值
 * @Author WuXiang
 * @Date 2018/9/27/
 */
public class NullPolicyListener extends Marshaller.Listener  {

    private String BLANK = "";
    @Override
    public void beforeMarshal(Object source) {
        super.beforeMarshal(source);

            Field[] declaredFields = source.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                try {
                    if(declaredField.get(source) ==null){
                        if( declaredField.getType() == String.class){
                            declaredField.set(source,BLANK);
                        }else{
                            Constructor<?> constructor = declaredField.getType().getConstructor();
                            declaredField.set(source,constructor.newInstance());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }

    }
}
