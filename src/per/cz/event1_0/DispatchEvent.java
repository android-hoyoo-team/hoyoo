package per.cz.event1_0;

import java.util.Hashtable;
public class DispatchEvent
{
	private static Hashtable<String,IMethod> mathodMap = new Hashtable<String, IMethod>();
	private DispatchEvent()
	{}
	public static void dispatchEvent(DEvent event)
	{
		IMethod method = mathodMap.get(event.getType());
		if(method !=null)
		{
			method.excute(event);
		}
			//进行遍历找到注册此事件的目标(递归),其中涉及冒泡等操作 .
	}
	public static void addEventListener(String type,IMethod method)
	{
		mathodMap.put(type, method);
	}
	public static IMethod removeListener(String type)
	{
		return mathodMap.remove(type);
	}
}
