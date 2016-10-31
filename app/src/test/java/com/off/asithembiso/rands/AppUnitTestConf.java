package com.off.asithembiso.rands;

/**
 * Created by asithembiso on 2016/10/31.
 */
import com.off.asithembiso.rands.factory.CustomerFactoryTest;
import com.off.asithembiso.rands.factory.EmployeeFactoryTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        CustomerFactoryTest.class,
        EmployeeFactoryTest.class})

public class AppUnitTestConf {}
