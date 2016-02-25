package org.openmrs.util;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by winglam on 2/16/16.
 */
public class CustomOpenmrsUtilTest {
    @Test public void addListToMapEmpty_shouldContainOneKeyAndOneListWithOneValue() {
        Integer key = 1;
        Integer val = 2;
        Map<Integer, List<Integer>> map = new HashMap<>();
        OpenmrsUtil.addToListMap(map, key, val);

        assertTrue(map.containsKey(key));
        assertEquals(1, map.size());
        assertNotNull(map.get(key));
        assertEquals(val, map.get(key).get(0));
    }

    @Test public void addListToMapExisting_shouldContainOneKeyAndOneListWithTwoValue() {
        Integer key = 1;
        Integer val = 2;
        Integer newVal = 3;
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> currentVals = new ArrayList<>();
        currentVals.add(val);
        map.put(key, currentVals);

        OpenmrsUtil.addToListMap(map, key, newVal);

        assertTrue(map.containsKey(key));
        assertEquals(1, map.size());
        assertNotNull(map.get(key));
        assertEquals(val, map.get(key).get(0));
        assertEquals(newVal, map.get(key).get(1));
    }

    @Test public void addSetToMapEmpty_shouldContainOneKeyAndOneSetWithOneValue() {
        Integer key = 1;
        Integer val = 2;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        OpenmrsUtil.addToSetMap(map, key, val);

        assertTrue(map.containsKey(key));
        assertEquals(1, map.size());
        assertNotNull(map.get(key));
        assertEquals(1, map.get(key).size());
        assertTrue(map.get(key).contains(val));
    }

    @Test public void addSetToMapEmpty_shouldContainOneKeyAndOneSetWithTwoValue() {
        Integer key = 1;
        Integer val = 2;
        Integer newVal = 3;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> currentVals = new HashSet<>();
        currentVals.add(val);
        map.put(key, currentVals);

        OpenmrsUtil.addToSetMap(map, key, newVal);

        assertTrue(map.containsKey(key));
        assertEquals(1, map.size());
        assertNotNull(map.get(key));
        assertEquals(2, map.get(key).size());
        assertTrue(map.get(key).contains(val));
        assertTrue(map.get(key).contains(newVal));
    }

    @Test public void applyLogLevel_shouldChangeLogLevelToTrace() {
        testApplyLogLevelHelper(OpenmrsConstants.LOG_LEVEL_TRACE);
    }

    @Test public void applyLogLevel_shouldChangeLogLevelToNull() {
        String logClass = "";
        String level = OpenmrsConstants.LOG_LEVEL_TRACE;
        OpenmrsUtil.applyLogLevel(logClass, level);
        Logger logger = Logger.getLogger(logClass);
        assertEquals(null, logger.getLevel());
    }

    @Test public void applyLogLevel_shouldChangeLogLevelToDefaultWarn() {
        String logClass = OpenmrsConstants.LOG_CLASS_DEFAULT;
        OpenmrsUtil.applyLogLevel(logClass, "");
        Logger logger = Logger.getLogger(logClass);
        assertEquals(OpenmrsConstants.LOG_LEVEL_WARN.toLowerCase().toLowerCase(),
                     logger.getLevel().toString().toLowerCase());
    }

    @Test public void applyLogLevel_shouldChangeLogLevelToDebug() {
        testApplyLogLevelHelper(OpenmrsConstants.LOG_LEVEL_DEBUG);
    }

    @Test public void applyLogLevel_shouldChangeLogLevelToInfo() {
        testApplyLogLevelHelper(OpenmrsConstants.LOG_LEVEL_INFO);
    }

    @Test public void applyLogLevel_shouldChangeLogLevelToWarn() {
        testApplyLogLevelHelper(OpenmrsConstants.LOG_LEVEL_WARN);
    }

    @Test public void applyLogLevel_shouldChangeLogLevelToError() {
        testApplyLogLevelHelper(OpenmrsConstants.LOG_LEVEL_ERROR);
    }

    @Test public void applyLogLevel_shouldChangeLogLevelToFatal() {
        testApplyLogLevelHelper(OpenmrsConstants.LOG_LEVEL_FATAL);
    }

    private void testApplyLogLevelHelper(String level) {
        String logClass = OpenmrsConstants.LOG_CLASS_DEFAULT;
        OpenmrsUtil.applyLogLevel(logClass, level);
        Logger logger = Logger.getLogger(logClass);
        assertEquals(level.toLowerCase(), logger.getLevel().toString().toLowerCase());
    }

    @Test public void parse_shouldParseStringToBooleanTrue() {
        String string = "true";
        Class clazz = Boolean.class;
        assertTrue((Boolean) OpenmrsUtil.parse(string, clazz));
    }

    @Test public void parse_shouldParseStringToIntegerOne() {
        String string = "1";
        Class clazz = Integer.class;
        assertEquals(1, OpenmrsUtil.parse(string, clazz));
    }

    @Test public void parse_shouldParseStringToStringHello() {
        String string = "hello";
        Class clazz = String.class;
        assertEquals("hello", OpenmrsUtil.parse(string, clazz));
    }
}
