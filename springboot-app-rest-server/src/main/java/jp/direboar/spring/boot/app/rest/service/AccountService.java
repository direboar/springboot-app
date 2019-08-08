package jp.direboar.spring.boot.app.rest.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.direboar.spring.boot.app.rest.data.Account;
import jp.direboar.spring.boot.app.rest.entity.AccountEntity;
import jp.direboar.spring.boot.app.rest.mapper.AccountMapper;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Transactional
    public Account searchAccount(String id) {
        AccountEntity accountEntity = this.accountMapper.findById(id);
        if (accountEntity == null) {
            // FIXME 例外体系の整理。
            throw new RuntimeException("account not found.");
        }

        // FIXME コピー処理の改善。ライブラリを使用する。
        Account account = new Account(accountEntity.getAccountId(), accountEntity.getName(),
                        accountEntity.getProfile(),
                        accountEntity.getBirthday().format(DateTimeFormatter.ISO_DATE),
                        accountEntity.getMailAddress(), accountEntity.getCreatedBy().format(
                                        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        return account;
    }

    public void insertAccount(Account account) {
        AccountEntity accountEntity = new AccountEntity(account.getId(), account.getName(),
                        LocalDate.parse(account.getBirthday(), DateTimeFormatter.ISO_DATE),
                        account.getMailAddress());
        accountEntity.setProfile(account.getProfile());

        accountMapper.insert(accountEntity);

    }

}
