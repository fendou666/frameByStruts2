package javaInterfaceTest;

public class Test {

	
	public String getInterfaceValue(TestParam testParam, TestParam2 testParam2){
		// ����һ���ӿڵķ���ֵ���ڶ����ӿڣ��ڶ����ӿڴ������з���
		return testParam2.getLastInterfaceParam(testParam.getStr());
	}
	
	public String testInterfaceParam(){
		String msg = "����׼�����ݸ�interface��ֵ";
		
		return getInterfaceValue(new TestParam() {
			
			@Override
			public String getStr() {
				return msg;
			}
		}, new TestParam2() {
			
			@Override
			public String getLastInterfaceParam(String ifcMsg) {
				return ifcMsg + "���ǵڶ����ӿ���ӵ�ֵ";
			}
		});
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		System.out.println(t.testInterfaceParam());
	}
}
