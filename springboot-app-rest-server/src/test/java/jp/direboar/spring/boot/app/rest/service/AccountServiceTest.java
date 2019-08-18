package jp.direboar.spring.boot.app.rest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.dbunit.PrepAndExpectedTestCase;
import org.dbunit.VerifyTableDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;

import jp.direboar.spring.boot.app.rest.data.Account;
import jp.direboar.spring.boot.app.testutil.DbUnitConfiguration;

/**
 * サービスのテスト。dbUnitを使用したテスト例。
 * 
 * @author direboar
 */
// dbUnitのライブラリ選定について
// spring-test-dbunit配下の理由から不採用。
// 1.jUnit5をサポートしていない
// 2.現在アクティブなプロジェクトではない
//
// 以下のStackOverflowでも、jUnit5が提供しているSpringとの連携方法を採用したほうが良い、という意見もあり、
// こちらに従うのが良いと思う。
// https://stackoverflow.com/questions/49028640/problems-using-dbunit-with-spring-without-spring-test-dbunit

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Import(DbUnitConfiguration.class)
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PrepAndExpectedTestCase prepAndExpectedTestCase;

    @Test
    void testSearchAccountNotFound() throws Exception {

        // サンプル：http://dbunit.sourceforge.net/testcases/PrepAndExpectedTestCase.html

        // テストデータのセットアップのみ行う場合。
        // 第一引数：検証対象のテーブル（検証対象外のカラム指定が可能）
        // 第二引数：セットアップするテストファイル
        // 第三引数：アサーションするテストファイル
        // 第四引数：テスト実行するラムダ式
        prepAndExpectedTestCase.runTest(new VerifyTableDefinition[] {}, new String[] {"setup.xml"},
            new String[] {}, () -> {

                // 検索条件に一致するデータがない場合
                assertThatThrownBy(() -> {
                    accountService.searchAccount("HOGE");
                }).isInstanceOfAny(RuntimeException.class);

                return null;
            });

    }

    @Test
    void testSearchAccount_Found() throws Exception {
        prepAndExpectedTestCase.runTest(new VerifyTableDefinition[] {}, new String[] {"setup.xml"},
            new String[] {}, () -> {

                // 検索条件に一致するデータがある場合
                Account account = accountService.searchAccount("1111111");
                assertThat(account).isNotNull();


                // TODO bean比較ツール導入
                Account expected = new Account();
                expected.setId("1111111");
                expected.setName("アカウント1");
                expected.setProfile("プロフィール1");
                expected.setBirthday("2019-01-01");
                expected.setMailAddress("1111@test.com");
                expected.setCreatedBy("2019/08/18 23:54:17");

                assertThat(account).isEqualTo(expected);

                return null;
            });
    }

    @Test
    void testInsert() throws Exception {
        prepAndExpectedTestCase.runTest(

            // テストデータのセットアップと検証を行う場合。
            // "CREATEDBY", "UPDATEDBY"は検証対象外。
            new VerifyTableDefinition[] {
                    new VerifyTableDefinition("ACCOUNT", new String[] {"CREATEDBY", "UPDATEDBY"})},

            new String[] {"setup.xml"}, new String[] {"expected.xml"}, () -> {

                Account account = new Account();
                account.setId("1234");
                account.setName("みのくば");
                account.setProfile("みのくばのプロファイル");
                account.setBirthday("2010-10-10");
                account.setMailAddress("minokuba@dummy.co.jp");

                this.accountService.insertAccount(account);

                return null;
            });

        // FIXME テスト結果の自動ダンプがほしい。
        // FlatXmlDataSet.write(databaseTester.getConnection().createDataSet(new String[]
        // {"ACCOUNT"}),
        // new FileOutputStream("./src/test/resources/org/dbunit/util/fileloader/setup.xml"));

    }

}
