package com.ikamobile.pa.common.jms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface Producer<T> {
	void send(@NotNull @Valid T message);
}
