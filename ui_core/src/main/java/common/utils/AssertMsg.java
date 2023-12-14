package common.utils;

public class AssertMsg {

    public static final String ASSERT_MESSAGE_PREFIX = "Checking: ";

    private static final String ASSERT_MESSAGE_ACTUAL = "' , actual: '";
    private static final String ASSERT_MESSAGE_END = "'.";

    private AssertMsg() {}

    public static String objectsEqual(Object actualValue, Object expectedValue, Object fieldName) {
        if (fieldName == null) {
            return "Expecting " + expectedValue + ASSERT_MESSAGE_ACTUAL + actualValue + ASSERT_MESSAGE_END;
        } else {
            return "Expecting  '" + fieldName + ASSERT_MESSAGE_ACTUAL + actualValue + ASSERT_MESSAGE_END;
        }
    }

    public static String notEquals(Object actualValue, Object expectedValue, Object fieldName) {
        if (fieldName == null) {
            return "Expecting value should NOT to be '" + expectedValue
                    + ASSERT_MESSAGE_ACTUAL + actualValue + ASSERT_MESSAGE_END;
        } else {
            return "Expecting '" + fieldName + "' should NOT to be '" + expectedValue
                    + ASSERT_MESSAGE_ACTUAL + actualValue + ASSERT_MESSAGE_END;
        }
    }

    public static String assertTrue(Object actualValue) {
        return "Expecting 'true', actual: " + actualValue + ASSERT_MESSAGE_END;
    }

    public static String assertFalse(Object actualValue) {
        return "Expecting 'false', actual: " + actualValue + ASSERT_MESSAGE_END;
    }
}
