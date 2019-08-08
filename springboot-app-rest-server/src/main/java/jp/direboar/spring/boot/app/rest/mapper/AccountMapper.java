package jp.direboar.spring.boot.app.rest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.direboar.spring.boot.app.rest.entity.AccountEntity;

@Mapper
public interface AccountMapper {

    AccountEntity findById(@Param("id") String id);

    void insert(AccountEntity accountEntity);

}
