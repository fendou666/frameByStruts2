package com.study.struts2.convert;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.study.struts2.javabean.Telephone;

public class TelConvert extends StrutsTypeConverter {
	
	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		Object ret = null;
		if(arg1!=null){
			System.out.println("转换对象前传递过来的字符串是" + arg1);
			if(arg2 == Telephone.class){
				if(null !=arg1[0]){
					Telephone tel = null;
					if(arg1[0].indexOf("-")!=-1){
						String[] tmp = arg1[0].split("-");
						tel = new Telephone(tmp[0], tmp[1]);
					}else{
						String first 	= arg1[0].substring(0, arg1[0].length()-8);
						String seconde 	= arg1[0].substring(arg1[0].length()-8);
						tel = new Telephone(first, seconde);
					}
					ret = tel;
					System.out.println(tel.toString());
				}
			}
		}
		
		return ret;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		String ret = null;
		if(arg1!=null){
			if(arg1 instanceof Telephone){
				ret = ((Telephone) arg1).getFirst() + "-" 
							+ ((Telephone) arg1).getSecond();
			}
		}
		return ret;
	}
	// =========这是测试========
	public static void main(String[] args) {
		//new TelConvert().convertFromString(null, new String[] {"029-88888888"}, Telephone.class);
		//new TelConvert().convertFromString(null, new String[] {"02988888888"}, Telephone.class);
	}
}
