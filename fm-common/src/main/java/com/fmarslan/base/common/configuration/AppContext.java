package com.fmarslan.base.common.configuration;

import com.fmarslan.base.common.shared.ICurrentUserDetail;

public class AppContext {

	private AppContext() {
	}

	static ICurrentUserDetail currentUserHandler;
	
	public static void setCurrentUserHandler(ICurrentUserDetail currentUserHandler) {
		AppContext.currentUserHandler = currentUserHandler;
	}
	
	public static ICurrentUserDetail getCurrentUserHandler() {
		return currentUserHandler;
	}

}
