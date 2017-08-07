package javaInterfaceTest;

public class Test {

	
	public String getInterfaceValue(TestParam testParam, TestParam2 testParam2){
		// 将第一个接口的返回值给第二个接口，第二个接口处理后进行返回
		return testParam2.getLastInterfaceParam(testParam.getStr());
	}
	
	public String testInterfaceParam(){
		String msg = "我是准备传递给interface的值";
		
		return getInterfaceValue(new TestParam() {
			
			@Override
			public String getStr() {
				return msg;
			}
		}, new TestParam2() {
			
			@Override
			public String getLastInterfaceParam(String ifcMsg) {
				return ifcMsg + "我是第二个接口添加的值";
			}
		});
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		System.out.println(t.testInterfaceParam());
	}
}
