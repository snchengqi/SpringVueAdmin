package com.cq.vsa.dao.repository.test;

import com.cq.vsa.jpa.domain.OrgBelong;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: chengqi
 * @Date: Created in 8:59 2019/4/22
 */
@Entity
@Table(name = "test")
public class TestBean extends OrgBelong<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreatedDate
    private Date createdTime;

    @LastModifiedDate
    private Date modifiedTime;
}
