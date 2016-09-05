package test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import db.model.AccountInfo;

public class TestObject {
	public static void main(String[] args) {
		try {
			Class clazz = AccountInfo.class;// 根据类名获得其对应的Class对象 写上你想要的类名就是了
											// 注意是全名 如果有包的话要加上
											// 比如java.Lang.String
			Field[] fields = clazz.getDeclaredFields();// 根据Class对象获得属性 私有的也可以获得
			for (Field f : fields) {
//				System.out.println(f.getName());// 打印每个属性的类型名字

				System.out.println(f.getName() + "--" + getFieldValueByName(f.getName(), new AccountInfo()));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据属性名获取属性值
	 */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}

}
