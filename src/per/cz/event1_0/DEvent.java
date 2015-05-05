package per.cz.event1_0;

/**
 * 事件类，用于接收事件
 * @author HJL
 *
 * @param <T>
 */
public class DEvent<T>
{
	private final String type;
	private final T target;

	public DEvent(String type,T obj)
	{
		this.type=type;
		this.target=obj;
	}
	
	public String getType()
	{
		return type;
	}
	public T getTarget()
	{
		return target;
	}
}
