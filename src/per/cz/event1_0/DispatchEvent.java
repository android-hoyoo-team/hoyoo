package per.cz.event1_0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * 全局事件触发器
 * @author HJL
 *
 */
public class DispatchEvent
{
	private static Hashtable<String,List<IMethod>> mathodMap = new Hashtable<String, List<IMethod>>();
	private DispatchEvent()
	{}
	/**
	 * 触发全局的事件
	 * @param event
	 */
	public synchronized static  void dispatchEvent(DEvent event)
	{
//		Collections.synchronizedList(new LinkedList()); 
		List<IMethod> list = mathodMap.get(event.getType());
		if(list==null)
			return ;
		for (IMethod iMethod : list) {
			if(iMethod !=null)
			{
				iMethod.excute(event);
			}
		}
			//进行遍历找到注册此事件的目标(递归),其中涉及冒泡等操作 .
	}
	/**
	 * 对事件添加监听
	 * @param type 事件的类型 
	 * @param method 处理事件的方法
	 */
	public synchronized static void addEventListener(String type,IMethod method)
	{
		List<IMethod> list = mathodMap.get(type);
		if(list==null)
		{
			list = Collections.synchronizedList(new LinkedList()); 
			mathodMap.put(type, list);
		}
		list.add(method);
//		mathodMap.put(type, method);
	}
	/**
	 * 移除某个类型的事件
	 * @param type
	 * @return
	 */
	public synchronized static List<IMethod> removeListener(String type)
	{
		List<IMethod> list = mathodMap.get(type);
		if(list==null)
		{
			return null;
		}
		return mathodMap.remove(type);
	}
	/**
	 * 根据 监听的事件方法来移除事件的监听
	 * @param method
	 * @return
	 */
	public synchronized static List<IMethod> removeListener(IMethod method)
	{
		Enumeration<String> keys = mathodMap.keys();
		List<IMethod> resList=new ArrayList<IMethod>();
		while (keys.hasMoreElements())
		{
			String type = keys.nextElement();
			List<IMethod> list = mathodMap.get(type);
			for (IMethod iMethod : list) {
				if(iMethod.equals(method))
				{
					if(list.remove(iMethod))
						resList.add(iMethod);
				}
			}
		}
		
		return resList.size()>0?resList:null;
	}
}
