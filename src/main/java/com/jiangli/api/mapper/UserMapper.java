package com.jiangli.api.mapper;

import com.jiangli.api.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User queryByUserId(@Param("userId") Long userId);

}
