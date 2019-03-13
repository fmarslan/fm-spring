package com.fmarslan.base.common.shared;

import java.util.List;

public interface ICurrentUserDetail {

	List<String> getCurrentUserAuths();
	String getCurrentUserName();

}
