package aaki.component.log.test;

import aaki.component.data.value.DataType;
import aaki.component.data.value.ValueBoolean;
import aaki.component.data.value.ValueDouble;
import aaki.component.data.value.ValueImplement;
import aaki.component.data.value.ValueInteger;
import aaki.component.data.value.ValueLong;
import aaki.component.data.value.ValueString;
import jdk.nashorn.internal.parser.JSONParser;

/**
 * Created by skkim on 10/13/16.
 */
public class LogTestMain {
	public static void main(String[ ] args) {
		System.out.println();
		System.out.println("===================== Data Test Start =====================");

		System.out.println("--------------------- testNewData() Start ---------------------");
//		testNewData();
		System.out.println("---------------------  testNewData() End  ---------------------");
		System.out.println();
		System.out.println("--------------------- testNullData() Start ---------------------");
//		testNullData();
		System.out.println("---------------------  testNullData() End  ---------------------");

		System.out.println("=====================  Data Test End  ====================");
	}


	private static void logData(DataType _value, int id) {
		if(_value == null) return;
		System.out.println();
		System.out.println("TEST(" + id + ")::getName() = " + _value.getName());
		System.out.println("TEST(" + id + ")::getUUID() = " + _value.getUUID());
		System.out.println("TEST(" + id + ")::getDataType() = " + _value.getDataType());
		System.out.println("TEST(" + id + ")::getData() = " + _value.getData());
	}

	private static void logData(ValueImplement _value, int id) {
		System.out.println();
		System.out.println("TEST(" + id + ")::getName() = " + _value.getName());
		System.out.println("TEST(" + id + ")::getDataType() = " + _value.getDataType());
		System.out.println("TEST(" + id + ")::getData() = " + _value.getData());
	}


	private void testJson() {

		JSONParser parser;

	}
}
