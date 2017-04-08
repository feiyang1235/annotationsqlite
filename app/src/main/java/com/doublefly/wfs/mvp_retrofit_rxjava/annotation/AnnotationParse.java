package com.doublefly.wfs.mvp_retrofit_rxjava.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by WFS on 2017/4/8.
 */
public class AnnotationParse {
    private static AnnotationParse instance;
    private Object f;

    private AnnotationParse(Object f) {
        this.f = f;
    }

    public static AnnotationParse getInstance(Object f) {
        if (instance == null) instance = new AnnotationParse(f);
        return instance;
    }

    public String parse() {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ");
        String tableName = null;
        Class<? extends Object> filter = f.getClass();

        //是否存在我们需要找的注解
        boolean isExist = filter.isAnnotationPresent(Table.class);
        if(!isExist){
            return null;
        }else{
            //拿到注解的实例
            Table t = (Table) filter.getAnnotation(Table.class);
            //拿到表名
            tableName = t.value();
        }

        sb.append(tableName).append(" where 1=1");

        //取出所有的字段
        Field[] fields = filter.getDeclaredFields();
        for (Field field : fields) {
            //处理每个字段对应的sql
            //先拿到字段名和字段值再拼装
            boolean ismExist = field.isAnnotationPresent(Columns.class);
            if(!ismExist){
                continue;
            }

            //拿到字段注解的实例
            Columns col = (Columns) field.getAnnotation(Columns.class);
            //拿到sql的列名
            String columnValue = col.value();

            //拿到字段名
            String fieldName = field.getName();
            //拼接出每个字段的get方法(例如: getUsername)
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            String fieldValue = null;

            try {
                //通过反射获得get方法
                Method getMethod = filter.getMethod(getMethodName);
                //通过反射执行get方法拿到字段的值
                fieldValue = (String) getMethod.invoke(f);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(fieldValue != null){
                //拼装sql
                sb.append(" and ").append(columnValue).append("='").append(fieldValue).append("'");
            }

        }

        return sb.toString();
    }

}
