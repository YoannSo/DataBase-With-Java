package fc.Application.MVC.Controllers;

public class ActionResult
{
	public String m_Name;
	public Object m_ViewModel;
	
	public ActionResult(String name, Object viewModel)
	{
		m_Name = name;
		m_ViewModel = viewModel;
	}
}
