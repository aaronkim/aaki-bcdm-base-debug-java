package bcdm.base.log.core;

/**
 * Created by skkim on 12/2/16.
 */

public enum LogLevel {
	None,
	Fatal,
	Error,
	Debug,
	Warning,
	Info,
	Verbose;

//	private LogLevel mLogLevel = null;
//
//	protected void setLevel(LogLevel _level) {
//		mLogLevel = _level;
//	}
//
//	protected boolean isOnLogging() {
//		if(mLogLevel == null) {
//			return false;
//		}
//		return this.ordinal() >= mLogLevel.ordinal();
//	}
}
