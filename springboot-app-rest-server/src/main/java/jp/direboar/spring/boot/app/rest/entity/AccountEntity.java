package jp.direboar.spring.boot.app.rest.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AccountEntity {

    /**
     * ID.
     */
    private String id;

    /**
     * 名前.
     */
    private String name;

    /**
     * プロフィール.
     */
    private String profile;

    /**
     * 誕生日.
     */
    private LocalDate birthday;

    /**
     * メールアドレス.
     */
    private String mailAddress;

    /**
     * 登録日.
     */
    private LocalDateTime createdBy;

    /**
     * 更新日.
     */
    private LocalDateTime updatedBy;

}
