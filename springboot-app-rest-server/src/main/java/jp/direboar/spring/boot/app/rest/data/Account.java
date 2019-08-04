package jp.direboar.spring.boot.app.rest.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * アカウント情報.
 * 
 * @author direboar
 */
// lombok
// getter/setter/equals/toString/hashCodeの自動生成。
@Data
// NonNull項目を取るコンストラクタを生成する。
@RequiredArgsConstructor
// デフォルトコンストラクタを生成する。
@NoArgsConstructor
public class Account {

    // 必須、７桁、英数字
    /**
     * ID.
     */
    // lombok 非Null項目を宣言する。Nullチェックが自動的に実装される。
    @NonNull
    private String id;

    // 必須、２０桁、全角
    /**
     * 名前.
     */
    @NonNull
    private String name;

    // 任意、１４０文字、全角
    /**
     * プロフィール.
     */
    private String profile;

    // 必須、日付
    /**
     * 誕生日.
     */
    @NonNull
    private LocalDate birthday;

    // 必須、メールアドレス
    /**
     * メールアドレス.
     */
    @NonNull
    private String mailAddress;

    /**
     * 登録日.
     */
    private LocalDateTime createdBy;

}
