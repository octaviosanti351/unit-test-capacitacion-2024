package com.testing.course;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
//@SelectPackages("com.testing.course")
@SelectClasses({ParametrizedTests.class, CartTest.class})
public class PaymentTestSuite {
}
