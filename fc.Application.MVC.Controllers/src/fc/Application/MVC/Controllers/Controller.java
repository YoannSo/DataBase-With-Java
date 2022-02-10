package fc.Application.MVC.Controllers;

import fc.Application.MVC.Model.Model;

public abstract class Controller
{
	protected Model m_Model;
	
	public void setModel(Model model)
	{
		m_Model = model;
	}
	
	public abstract ActionResult run(Object ... args);
	
	protected ActionResult View(Object viewModel)
	{
		return new ActionResult(this.getClass().getCanonicalName(), viewModel);
	}
}
