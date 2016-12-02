package bcdm.base.log.core;

/**
 * Created by aaron on 12/1/16.
 */

class LogStack {
	private static String mThisClassName = null;

	protected LogStack() {
		mThisClassName = getClass().getName();
	}

	protected static StackTraceElement[] getCallStack() {
		final StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
		return callStack;
	}

	protected static int getLogCallerStackIndex(StackTraceElement callStack[]) {
		int result = 0;

		boolean isThisClass = false;

		for (StackTraceElement element : callStack) {
			if(element.getClassName().equals(mThisClassName)) {
				isThisClass = true;
			} else if(isThisClass) {
				break;
			}
			result ++;
		}

		if(result >= callStack.length) {
			result = -1;
		}

		return result;
	}

	protected static StackTraceElement getLogCallerStack() {
		StackTraceElement result = null;

		StackTraceElement[] callStack = getCallStack();
		int callerIndex = getLogCallerStackIndex(callStack);
		if(callerIndex >= 0) {
			result = callStack[callerIndex];
		}
		return result;
	}

	protected static String getLogCallerName() {
		String result = null;

		StackTraceElement callerElement = getLogCallerStack();
		if(callerElement != null) {
			result = callerElement.getClassName();
		} else {
			result = mThisClassName;
		}

		return result;
	}

	/**
	 * Return a String describing the calling method and location at a particular stack depth.
	 * @param callStack the Thread stack
	 * @param depth the depth of stack to return information for.
	 * @return the String describing the caller at that depth.
	 */
	private static String getCaller(StackTraceElement callStack[], int depth) {
		// callStack[4] is the caller of the method that called getCallers()
		int skip = 0;
		String thisClass = LogStack.class.getName();
		for (StackTraceElement element : callStack) {
			skip ++;
			if(skip < 3) {
				continue;
			} else if(element.getClassName().equals(thisClass)) {
				continue;
			} else {
				break;
			}
		}

		skip--;
		int step = skip + depth;

		if (step == callStack.length) {
			return "<bottom of call stack>";
		} else if (step > callStack.length) {
			return "";
		}
		StackTraceElement caller = callStack[step];
		return caller.getClassName() + "." + caller.getMethodName() + "():" + caller.getLineNumber();
	}

	/**
	 * Return a string consisting of methods and locations at multiple call stack levels.
	 * @param depth the number of levels to return, starting with the immediate caller.
	 * @return a string describing the call stack.
	 * {@hide}
	 */
	private static String getCallers(final int depth) {
//		final StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < depth; i++) {
//
//			sb.append(getCaller(callStack, i)).append("\n");
//		}
//		return sb.toString();
		return getCallers(0, depth);
	}

	/**
	 * Return a string consisting of methods and locations at multiple call stack levels.
	 * @param depth the number of levels to return, starting with the immediate caller.
	 * @return a string describing the call stack.
	 * {@hide}
	 */
	private static String getCallers(final int start, int depth) {
		final StackTraceElement[] callStack = Thread.currentThread().getStackTrace();

		StringBuffer sb = new StringBuffer();
		depth += start;
		for (int i = start; i < depth; i++) {
			String log = getCaller(callStack, i);
			if (log == null || log.isEmpty()) {
				continue;
			}

			if (i == 0) {
				log = "[callee] " + getCaller(callStack, i);
			} else {
				log = "(caller) " + getCaller(callStack, i);
			}

			sb.append(log).append("\n");
		}
		return sb.toString();
	}

	/**
	 * Like {@link #getCallers(int)}, but each location is append to the string
	 * as a new line with <var>linePrefix</var> in front of it.
	 * @param depth the number of levels to return, starting with the immediate caller.
	 * @param linePrefix prefix to put in front of each location.
	 * @return a string describing the call stack.
	 * {@hide}
	 */
	private static String getCallers(final int depth, String linePrefix) {
		final StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < depth; i++) {
			sb.append(linePrefix).append(getCaller(callStack, i)).append("\n");
		}
		return sb.toString();
	}

	/**
	 * @return a String describing the immediate caller of the calling method.
	 * {@hide}
	 */
	protected static String getCaller() {
		return getCaller(Thread.currentThread().getStackTrace(), 0);
	}
}

