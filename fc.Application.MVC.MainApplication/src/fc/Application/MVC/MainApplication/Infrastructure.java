package fc.Application.MVC.MainApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import fc.Application.MVC.Controllers.ActionResult;
import fc.Application.MVC.Controllers.Controller;
import fc.Application.MVC.Controllers.StartController;
import fc.Application.MVC.Model.Model;
import fc.Application.MVC.Views.RunController;

public class Infrastructure
{
	protected Model m_Model;
	protected Shell m_Shell;

	public static void main(String[] args)
	{
		new Infrastructure().run();
	}

	public void run()
	{		

		m_Model = Model.getModel();

		m_Shell = new Shell();
		m_Shell.setSize(450, 216);
		m_Shell.setText("Application L3 Genie Logiciel");
		m_Shell.open();
		m_Shell.setVisible(false);
		runController(StartController.class);
	}

	public void runController(Class<?> c, Object ... args)
	{
		RunController rc = new RunController();
		rc.m_Class = c;
		rc.m_Args = args;
		while (true)
		{
			runController(rc);
		}
	}

	public void runController(RunController rc)
	{
		Controller controller = createController(rc.m_Class);
		controller.setModel(m_Model);

		ActionResult result = controller.run(rc.m_Args);

		try
		{
			String viewName = result.m_Name.replaceAll("Controller", "View");
			Class<?> viewClass = Class.forName(viewName);
			Constructor<?> ctor = viewClass.getConstructor(Shell.class, int.class);
			Object dialog = ctor.newInstance(m_Shell, SWT.NONE);
			Field field = dialog.getClass().getDeclaredField("m_Infrastructure");
			rc.m_ViewModel = result.m_ViewModel;
			field.set(dialog, rc);
			Method method = dialog.getClass().getMethod("open");
			method.invoke(dialog);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Controller createController(Class c)
	{
		try
		{			

			Controller controller = (Controller)c.getConstructor().newInstance();
			return controller;
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
