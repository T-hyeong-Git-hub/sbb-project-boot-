package com.mysite.sbb.user;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String gender;

    private LocalDateTime createDate;

    private String isdel;

    /**
     * data insert 직전에 실행되는 어노테이션
     */
    @PrePersist
    public void creatTime(){
        this.createDate=LocalDateTime.now();
    }





}
