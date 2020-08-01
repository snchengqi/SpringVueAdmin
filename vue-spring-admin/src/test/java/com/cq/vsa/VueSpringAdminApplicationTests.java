package com.cq.vsa;

import com.cq.vsa.dao.repository.test.TestBean;
import com.cq.vsa.dao.repository.test.TestBeanRepository;
import com.cq.vsa.jpa.annotation.QueryField;
import com.cq.vsa.jpa.domain.DataQuery;
import com.cq.vsa.jpa.domain.PageAndSortDataQuery;
import com.cq.vsa.jpa.enumobj.QueryType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VueSpringAdminApplicationTests {

    @Autowired
    private TestBeanRepository repository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testDynamicQuery() {
        TestDq dq = new TestDq("cheng", null, null, null, null);
        Specification<TestBean> spec = (Specification<TestBean>) (root, query, criteriaBuilder) -> {
            Path<String> path = root.get("name");
            return criteriaBuilder.like(path, "%you%");
        };
        Page<TestBean> page = repository.findAll(dq);
        System.out.println(page);
    }
}

class TestDq extends PageAndSortDataQuery.DefaultPageAndSortDataQuery {

    @QueryField(type = QueryType.FULL_LIKE)
    private String name;

    @QueryField(type = QueryType.GREATER_THAN_EQUAL, name = "createdTime")
    private Date createdStartTime;

    @QueryField(type = QueryType.LESS_THAN_EQUAL, name = "createdTime")
    private Date createdEndTime;

    @QueryField(type = QueryType.GREATER_THAN_EQUAL, name = "modifiedTime")
    private Date modifiedStartTime;

    @QueryField(type = QueryType.LESS_THAN_EQUAL, name = "modifiedTime")
    private Date modifiedEndTime;

    public TestDq() {}

    public TestDq(String name, Date createdStartTime, Date createdEndTime, Date modifiedStartTime, Date modifiedEndTime) {
        this.name = name;
        this.createdStartTime = createdStartTime;
        this.createdEndTime = createdEndTime;
        this.modifiedStartTime = modifiedStartTime;
        this.modifiedEndTime = modifiedEndTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedStartTime() {
        return createdStartTime;
    }

    public void setCreatedStartTime(Date createdStartTime) {
        this.createdStartTime = createdStartTime;
    }

    public Date getCreatedEndTime() {
        return createdEndTime;
    }

    public void setCreatedEndTime(Date createdEndTime) {
        this.createdEndTime = createdEndTime;
    }

    public Date getModifiedStartTime() {
        return modifiedStartTime;
    }

    public void setModifiedStartTime(Date modifiedStartTime) {
        this.modifiedStartTime = modifiedStartTime;
    }

    public Date getModifiedEndTime() {
        return modifiedEndTime;
    }

    public void setModifiedEndTime(Date modifiedEndTime) {
        this.modifiedEndTime = modifiedEndTime;
    }
}
