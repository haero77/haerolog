package com.haerolog.global.service;

import java.time.LocalDateTime;

public class SystemClockHolder implements ClockHolder {

	@Override
	public LocalDateTime now() {
		return LocalDateTime.now();
	}

}
