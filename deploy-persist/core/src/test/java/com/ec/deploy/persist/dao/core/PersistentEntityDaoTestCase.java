package com.ec.deploy.persist.dao.core;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    locations = {
        "classpath:/spring/persist-context.xml"
    })
@Transactional
@TransactionConfiguration
public abstract class PersistentEntityDaoTestCase
{
}
