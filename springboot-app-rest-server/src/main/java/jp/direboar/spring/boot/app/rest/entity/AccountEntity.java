package jp.direboar.spring.boot.app.rest.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * アカウントエンティティ情報.
 * 
 * @author direboar
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    /**
     * ID.
     */
    @NonNull
    private String id;

    /**
     * 名前.
     */
    @NonNull
    private String name;

    /**
     * プロフィール.
     */
    private String profile;

    /**
     * 誕生日.
     */
    @NonNull
    private LocalDate birthday;

    /**
     * メールアドレス.
     */
    @NonNull
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
