package jp.direboar.spring.boot.app.rest.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * アカウント情報.
 * 
 * @author direboar
 */
// lombok
// getter/setter/equals/toString/hashCodeの自動生成。
@Data
// 全項目を取るコンストラクタを生成する。
@AllArgsConstructor
// デフォルトコンストラクタを生成する。
@NoArgsConstructor
public class Account {

    // 必須、７桁、英数字
    /**
     * ID.
     */
    // lombokの@NonNullを指定すると、MockMVCのテストで対象の項目にNullをセットする振る舞いで想定外にエラーとなるため適用しない。
    // @NonNull
    // BeanValidation
    @NotNull
    @Length(max = 7)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String id;

    // 必須、２０桁
    // TODO 全角チェック
    /**
     * 名前.
     */
    // @NonNull
    @NotNull
    @Length(max = 20)
    private String name;

    // 任意、１４０文字、全角
    /**
     * プロフィール.
     */
    @Length(max = 140)
    private String profile;

    // 必須、日付
    /**
     * 誕生日.
     */
    // @NonNull
    @NotNull
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}")
    private String birthday;

    // 必須、メールアドレス ２５６文字
    /**
     * メールアドレス.
     */
    // @NonNull
    @NotNull
    @Length(max = 256)
    @Email
    private String mailAddress;

    /**
     * 登録日.
     */
    private String createdBy;

}
