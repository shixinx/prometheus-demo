package com.prometheus;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @ApiModelProperty
    private String username;

    @ApiModelProperty
    private Integer age;

    public User() {
    }

    // 带参数的构造函数
    public User(@JsonProperty("name") String name, @JsonProperty("age") int age) {
        this.username = name;
        this.age = age;
    }


}
