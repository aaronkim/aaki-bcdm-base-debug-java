package bcdm.base.log.core;

import java.util.HashMap;

/**
 * Created by aaron on 12/1/16.
 */

public class LogImplement extends LogStack {

	protected LogImplement() {
		super();
		setLogLevel(LogLevel.None);
	}

	private static HashMap<String, LogLevel> mLogPackageMap = new HashMap<>();

	public static void setLogLevel(LogLevel _logLevel) {
		mLogPackageMap.put(getLogCallerName(), _logLevel);
	}

	protected static LogLevel getLogLevel() {
		LogLevel result = mLogPackageMap.get(getLogCallerName());
		if(result == null) {
			result = LogLevel.None;
		}
		return result;
	}

	protected static boolean isLogable(LogLevel _logLevel) {
		boolean result = false;
		LogLevel logLevel = getLogLevel();

		return result;
	}


	/* Log Listener */
	private static HashMap<LogLevel, Plugin> mLogLevelListenerHashMap = new HashMap<>();

	protected interface Plugin {
		void message(final String _tag, final String _message);
	}

	public static void setPlugin(final LogLevel _logLevel, Plugin _plugin) {
		mLogLevelListenerHashMap.put(_logLevel, _plugin);
	}

	private static boolean callListener(final LogLevel _logLevel, final String _tag, final String _message) {
		Plugin plugin = mLogLevelListenerHashMap.get(_logLevel);
		if(plugin != null) {
			plugin.message(_tag, _message);
		}
		return false;
	}

	protected static void message(final LogLevel _logLevel, final String _tag, final String _message) {
		if(!callListener(_logLevel, _tag, _message)) {
			System.out.printf("[%s] %s : %s", _logLevel.name().toUpperCase(), _tag, _message);
		}
	}
}

