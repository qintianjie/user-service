package com.colorcc.user.bean.dto;

import com.colorcc.user.bean.Bean;

public interface Dto<T, V extends Bean> {

	public V transferTypetoBean(T t, Object ... objects);

	public T transferBeanToType(V v, Object ...objects );

}
