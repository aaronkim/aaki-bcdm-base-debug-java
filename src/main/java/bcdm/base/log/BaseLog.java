package bcdm.base.log;

import java.util.HashMap;

/**
 * Created by skkim on 8/15/17.
 */

public class BaseLog {

	private final static BaseLog myInstance = new BaseLog();
	private static CONFIG mCONFIG;
	private final HashMap<Class, Boolean> mClsMap = new HashMap<>();

	private BaseLog() {
		mCONFIG = new CONFIG();
	}

	public static BaseLog getInstance() {
		return myInstance;
	}

	public void regist(Class _cls, boolean _enable) {
		synchronized (mClsMap) {
			mClsMap.put(_cls, _enable);
		}
	}

	private static class CONFIG {

		private boolean mEnableLogging = false;
		private BaseLogLevel mLogLevelLimit = BaseLogLevel.NONE;
		private BaseLogStub mLogStub = null;

		protected void make() {
			mCONFIG = this;
		}

		public CONFIG set(boolean _enableLogging) {
			mEnableLogging = _enableLogging;
			return this;
		}

		public CONFIG set(BaseLogLevel _Log_levelLimit) {
			mLogLevelLimit = _Log_levelLimit;
			return this;
		}

		public CONFIG set(BaseLogStub _logStub) {
			mLogStub = _logStub;
			return this;
		}
	}

	public static class Setting extends CONFIG {
		public void make() {
			super.make();
		}
	}

	private static boolean isLoggable(BaseLogLevel _logLevel) {
		if( mCONFIG == null ||
				!mCONFIG.mEnableLogging ||
				mCONFIG.mLogLevelLimit.ordinal() < _logLevel.ordinal()) {
			return false;
		}

		return true;
	}

	private static void printLog(BaseLogLevel _logLevel, String _TAG, String _msg) {
		if( !isLoggable(_logLevel)) {
			return;
		}

		if(mCONFIG.mLogStub != null) {
			mCONFIG.mLogStub.log_stub(_logLevel, _TAG, _msg);
		} else {

			String log = "[" + _logLevel.name() + "] ";

			if(_TAG != null) {
				log += _TAG;
			}

			if(_msg != null) {
				if(_TAG != null) {
					log += " :: ";
				}
				log += _msg;
			}

			if(_logLevel == BaseLogLevel.FATAL) {
				printException(log);
			} else {
				System.out.println(log);
			}
		}
	}

	private static void printException(String _msg) {
		(new Exception(_msg)).printStackTrace();
	}

	public static void fatal(String _TAG, String _msg, boolean _condition) {
		if(!_condition) {
			printLog(BaseLogLevel.FATAL, _TAG, _msg);
		}
	}

	public static void error(String _TAG, String _msg) {
		printLog(BaseLogLevel.ERROR, _TAG, _msg);
	}

	public static void warn(String _TAG, String _msg) {
		printLog(BaseLogLevel.WARN, _TAG, _msg);
	}

	public static void info(String _TAG, String _msg) {
		printLog(BaseLogLevel.INFO, _TAG, _msg);
	}

	public static void debug(String _TAG, String _msg) {
		printLog(BaseLogLevel.DEBUG, _TAG, _msg);
	}

	public static void verbose(String _TAG, String _msg) {
		printLog(BaseLogLevel.VERBOSE, _TAG, _msg);
	}
}
