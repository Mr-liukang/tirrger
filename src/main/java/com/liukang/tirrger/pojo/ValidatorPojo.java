package com.liukang.tirrger.pojo;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.util.Date;

public class ValidatorPojo {
    @NotNull(message = "id不能为空")
    private Long id;
    @Future(message = "需要将来的一个时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date date;
    @NotNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "1000.00")
    private Double doubleValue;
    @NotNull
    @Min(value = 1,message = "最小值为1")
    @Max(value = 100,message = "最大值为100")
    private Integer integer;
    @Range(min = 1,max = 888,message = "范围为1到888")
    private Long range;
    @Email(message = "邮件格式错误")
    private String email;
    @Size(min = 20,max = 30,message = "字符长度为20~30之间")
    private String size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
