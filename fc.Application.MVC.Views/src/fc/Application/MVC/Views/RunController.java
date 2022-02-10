package fc.Application.MVC.Views;

import org.eclipse.swt.widgets.Shell;

public class RunController
{
	public Object m_ViewModel;
	public Class<?> m_Class;
	public Object[] m_Args;

	public void runController(Shell shellToClose, Class<?> c, Object ... args)
	{
		if (shellToClose != null)
			shellToClose.close(); // or dispose()
		m_Class = c;
		m_Args = args;
	}
}
