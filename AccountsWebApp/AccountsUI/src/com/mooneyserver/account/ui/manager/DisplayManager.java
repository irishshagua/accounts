package com.mooneyserver.account.ui.manager;

import java.util.Stack;

import com.mooneyserver.account.AccountsApplication;
import com.mooneyserver.account.ui.iface.IAccountsView;
import com.mooneyserver.account.ui.view.main.AbstractBaseView;

public class DisplayManager {
	
	private Stack<IAccountsView> windowStack;
	
	// Display Manager should be session static
	public DisplayManager() {	
		windowStack = new Stack<>();
	}
	
	public void loadNewView(IAccountsView view) {
		windowStack.push(view);
		setCurrentView(windowStack.peek());
	}
	
	public void destroyCurrentView() {
		AccountsApplication.getInstance().breadcrumb
			.removeScreenFromBreadcrumb(windowStack.pop().getDisplayName());
		setCurrentView(windowStack.peek());
	}
	
	// To be called for logout scenario
	public void closeAllViews() {
		while (windowStack.size() > 1) {
			AccountsApplication.getInstance().breadcrumb
				.removeScreenFromBreadcrumb(windowStack.pop().getDisplayName());
		}
		setCurrentView(windowStack.peek());
	}
	
	private void setCurrentView(IAccountsView view) {
		AccountsApplication.setCurrentView(view);
	}
	
	
	public void refreshViews() {
		for (IAccountsView view : windowStack) {
			view.buildStringsFromLocale();
			view.requestRepaint();
		}
		
		if (windowStack.size() > 0)
			((AbstractBaseView) windowStack.peek()).
				reloadMainFrameComponentStrings();
	}
	
	public int getViewCount() {
		return windowStack.size();
	}
}