package com.jiangli.api.mapper;

import com.jiangli.api.model.ShareSSR;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareSSRMapper {
    List<ShareSSR> queryAll();
}
