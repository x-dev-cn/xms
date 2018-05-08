package cn.xdev.core.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by felix on 2016-01-26-0026.
 */
public class BaseModel implements Serializable {
    public boolean equals(Object obj) {
        if (obj instanceof BaseModel) {
            return true;// ((BaseModel) obj).getID() == ID;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String result = "";
        // 获得对象的类型
        Class<?> classType = this.getClass();

        try {
            // 通过默认构造方法创建一个新的对象
            // @SuppressWarnings("unused")
            // Object objectCopy = classType.getConstructor(new
            // Class[]{}).newInstance(new Object[]{});

            // 获得对象的所有属性
            Field fields[] = classType.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];

                String fieldName = field.getName();

                String firstLetter = fieldName.substring(0, 1).toUpperCase();

                // 获取属性对应的getXXX()方法的名字
                String getMethodName = "get" + firstLetter
                        + fieldName.substring(1);
                // 获得和属性对应的getXXX()方法
                Method getMethod = classType.getMethod(getMethodName,
                        new Class[]{});

                Object value = getMethod.invoke(this, new Object[]{});

                if (getMethod.getReturnType().equals(Date.class)) {
                    SimpleDateFormat dateformat1 = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    value = dateformat1.format(value);
                }

                if (i == 0)
                    result += "{\"" + fieldName.toLowerCase() + "\":\"" + value
                            + "\"";
                else
                    result += ",\"" + fieldName.toLowerCase() + "\":\"" + value
                            + "\"";
            }

            result += "}";

        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    public void convertModel(BaseModel otherModel) {
        if (otherModel != null) {
            try {
                Class<?> otherClassObject = Class.forName(otherModel.getClass()
                        .getName());
                Field[] otherField = otherClassObject.getDeclaredFields();
                if (otherField != null && otherField.length > 0) {
                    Class<?> classObject = Class.forName(this.getClass()
                            .getName());

                    for (Field otherFieldItem : otherField) {

                        String otherFieldName = otherFieldItem.getName();
                        String getMethodName = this.createMethod(2,
                                otherFieldName);
                        String setMethodName = this.createMethod(1,
                                otherFieldName);

                        Method getMethod = otherClassObject.getMethod(
                                getMethodName, new Class[]{});

                        Class<?> returnType = getMethod.getReturnType();

                        Method setMethod = classObject.getMethod(setMethodName,
                                returnType);

                        Object fieldValue = getMethod.invoke(otherModel,
                                new Object[]{});

                        if (fieldValue == null) {
                            continue;
                        }

                        if (returnType.equals(Integer.class)) {
                            setMethod.invoke(this,
                                    Integer.valueOf(fieldValue.toString()));
                            continue;
                        }

                        if (returnType.equals(int.class)) {
                            setMethod.invoke(this,
                                    Integer.valueOf(fieldValue.toString()));
                            continue;
                        }

                        if (returnType.equals(Long.class)) {
                            setMethod.invoke(this,
                                    Long.valueOf(fieldValue.toString()));
                            continue;
                        }

                        if (returnType.equals(long.class)) {
                            setMethod.invoke(this,
                                    Long.valueOf(fieldValue.toString()));
                            continue;
                        }

                        if (returnType.equals(Boolean.class)) {
                            setMethod.invoke(this,
                                    Boolean.valueOf(fieldValue.toString()));
                            continue;
                        }

                        if (returnType.equals(boolean.class)) {
                            setMethod.invoke(this,
                                    Boolean.valueOf(fieldValue.toString()));
                            continue;
                        }

                        if (returnType.equals(Byte.class)) {
                            setMethod.invoke(this,
                                    Byte.valueOf(fieldValue.toString()));
                            continue;
                        }

                        if (returnType.equals(byte.class)) {
                            setMethod.invoke(this,
                                    Byte.valueOf(fieldValue.toString()));
                            continue;
                        }

                        if (returnType.equals(Double.class)) {
                            setMethod.invoke(this,
                                    Double.valueOf(fieldValue.toString()));
                            continue;
                        }

                        if (returnType.equals(double.class)) {
                            setMethod.invoke(this,
                                    Double.valueOf(fieldValue.toString()));
                            continue;
                        }

                        if (returnType.equals(Float.class)) {
                            setMethod.invoke(this,
                                    Float.valueOf(fieldValue.toString()));
                            continue;
                        }

                        if (returnType.equals(float.class)) {
                            setMethod.invoke(this,
                                    Float.valueOf(fieldValue.toString()));
                            continue;
                        }

                        if (returnType.equals(Date.class)) {
                            setMethod.invoke(this, (Date) fieldValue);
                            continue;
                        }

                        if (returnType.equals(String.class)) {
                            setMethod.invoke(this, fieldValue.toString());
                            continue;
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("对象为空,请检查");

        }

    }

    private String createMethod(int type, String methodName) {

        methodName = methodName.substring(0, 1).toUpperCase()
                + methodName.substring(1);

        // set方法
        if (type == 1) {
            methodName = "set" + methodName;
            // get方法
        } else if (type == 2) {
            methodName = "get" + methodName;
        }

        return methodName;
    }

    private void setModelValue(Class<?> returnType, Method setMethod, Object fieldValue) {
        try {
            if (returnType.equals(Integer.class)) {
                setMethod.invoke(this,
                        Integer.valueOf(fieldValue.toString()));
            }

            if (returnType.equals(int.class)) {
                setMethod.invoke(this,
                        Integer.valueOf(fieldValue.toString()));
            }

            if (returnType.equals(Long.class)) {
                setMethod.invoke(this,
                        Long.valueOf(fieldValue.toString()));
            }

            if (returnType.equals(long.class)) {
                setMethod.invoke(this,
                        Long.valueOf(fieldValue.toString()));
            }

            if (returnType.equals(Boolean.class)) {
                setMethod.invoke(this,
                        Boolean.valueOf(fieldValue.toString()));
            }

            if (returnType.equals(boolean.class)) {
                setMethod.invoke(this,
                        Boolean.valueOf(fieldValue.toString()));
            }

            if (returnType.equals(Byte.class)) {
                setMethod.invoke(this,
                        Byte.valueOf(fieldValue.toString()));
            }

            if (returnType.equals(byte.class)) {
                setMethod.invoke(this,
                        Byte.valueOf(fieldValue.toString()));
            }

            if (returnType.equals(Double.class)) {
                setMethod.invoke(this,
                        Double.valueOf(fieldValue.toString()));
            }

            if (returnType.equals(double.class)) {
                setMethod.invoke(this,
                        Double.valueOf(fieldValue.toString()));
            }

            if (returnType.equals(Float.class)) {
                setMethod.invoke(this,
                        Float.valueOf(fieldValue.toString()));
            }

            if (returnType.equals(float.class)) {
                setMethod.invoke(this,
                        Float.valueOf(fieldValue.toString()));
            }

            if (returnType.equals(Date.class)) {
                setMethod.invoke(this, (Date) fieldValue);
            }

            if (returnType.equals(String.class)) {
                setMethod.invoke(this, fieldValue.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setModelField(String fieldName, Object fieldValue) {

        String getMethodName = this.createMethod(2, fieldName);
        String setMethodName = this.createMethod(1, fieldName);

        try {
            Class<?> classObject = Class.forName(this.getClass().getName());
            Method getMethod = classObject.getMethod(getMethodName, new Class[]{});
            Class<?> returnType = getMethod.getReturnType();
            Method setMethod = classObject.getMethod(setMethodName, returnType);

            setModelValue(returnType, setMethod, fieldValue);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getModelField(String fieldName) {
        Object fieldValue = null;
        String getMethodName = this.createMethod(2, fieldName);

        try {
            Class<?> classObject = Class.forName(this.getClass().getName());
            Method getMethod = classObject.getMethod(getMethodName, new Class[]{});
            fieldValue = getMethod.invoke(this, new Object[]{});

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fieldValue;
    }
}
