#使用した技術要素
- java 1.8.0_172
- play framework 2.6

＃全体の設計・構成について
##ToDoリスト機能一覧
|画面|主な機能|
|----|-------|
|トップ画面|ToDoの表示、ToDoの追加、ToDoの状態変更|
|ToDo編集画面|ToDoの表示、編集|
|検索画面|ToDoの検索|
|共通ヘッダー||

##ディレクトリの構成

```
app
 ├-models
 |   └-Todo.java     //Todoモデル
 ├-views
 |   ├-index.scala.html         //トップ画面
 |   ├-main.scala.html          //共通ヘッダー
 |   ├-showedit.scala.html      //編集画面
 |   └-showsearch.scala.html    //検索画面
 └-controllers
        └-TodoController.java   //コントローラー
```

#開発環境のセットアップ手順
##javaのインストール
```
brew cask install java8
export JAVA_HOME=`/usr/libexec/java_home -v 1.8
```

##sbtのインストール
```
brew install sbt
```

##playのインストール
```
git clone https://github.com/YukiYanagikubo/ToDo.git
cd Todo
```

##playの起動
```
sbt run
```