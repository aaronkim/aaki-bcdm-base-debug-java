package bcdm.base.log;

import bcdm.base.log.core.LogImplement;
import bcdm.base.log.core.LogLevel;

/**
 * Created by aaron on 12/1/16.
 */

public final class BaseLog extends LogImplement {

	public interface Plugin extends LogImplement.Plugin {

	}

	public static void fatal(final String _message) {
		fatal(getLogCallerName(), _message);
	}

	public static void error(final String _message) {
		error(getLogCallerName(), _message);
	}

	public static void debug(final String _message) {
		debug(getLogCallerName(), _message);
	}

	public static void warning(final String _message) {
		warning(getLogCallerName(), _message);
	}

	public static void info(final String _message) {
		info(getLogCallerName(), _message);
	}

	public static void verbose(final String _message) {
		verbose(getLogCallerName(), _message);
	}


	public static void fatal(final String _tag, final String _message) {
		message(LogLevel.Fatal, _tag, _message);
	}

	public static void error(final String _tag, final String _message) {
		message(LogLevel.Error, _tag, _message);
	}

	public static void debug(final String _tag, final String _message) {
		message(LogLevel.Debug, _tag, _message);
	}

	public static void warning(final String _tag, final String _message) {
		message(LogLevel.Warning, _tag, _message);
	}

	public static void info(final String _tag, final String _message) {
		message(LogLevel.Info, _tag, _message);
	}

	public static void verbose(final String _tag, final String _message) {
		message(LogLevel.Verbose, _tag, _message);
	}
}

