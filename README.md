
#### 採用技術
| 技術要素 | 採用技術 |
|:---|:---|
|Java    |Java11(Adapt Open JDK)    |
 | ビルドツール |Gradle5.5.1   |
|DB    |インメモリDB(先々はDocker+Postgres)   |
 |動作環境    |ローカル（先々はHeroku等）   |

lombokは適用したい。

#### プロジェクト構成
* マルチプロジェクト
* Web flont => Rest Backend
* 可能ならバッチ（コマンドライン or SpringBatch)
* 可能なら非同期処理（技術選定から)

#### 静的コード解析
* chckstyle 8.18(STSに導入したバージョンに合わせた)
* spotbugs
* pmd 6.17.0(STSに導入したバージョンに合わせた)
* jacoco 0.8.2  (JDK11に対応するため　https://github.com/vaskoz/core-java9-impatient/issues/11）

#### 単体テスト
* jUnit5
* mockito/PowerMock
* dbUnit
* Spring TestFramework
* SpringMVC (コントローラ部分)

#### ITA
* jUnit5
* dbUnit
* Spring TestFramework(RestTestTemplate + SpringBootTest(Web Envirinment))

#### その他
* Swagger2(手作成 or SpringFox)
* postman
  
#### Eclipse Plugin
https://marketplace.eclipse.org/
Market placeから検索

* フォルダを開く機能を追加
Windows Explorer

* マークダウンエディタ
FluenteMark + GitHub Flavored Markdown viewer plugin
https://raw.github.com/satyagraha/gfm_viewer/master/p2-composite/

* chckstyleプラグイン
* eclipse-pmd プラグイン　https://dl.bintray.com/pmd/pmd-eclipse-plugin/updates/

