# Camel Example EIP Project

http://wiki.buildria.com/camel/eip/start の説明で使用しているソースコードです。
[Maven](http://maven.apache.org/)が必要です。


## ディレクトリ構成

Javaコード、Spring XMLは以下のデイレクトリにあります。

* テストクラス 
  * src/test/test/com/buildria/camel/example/eip/  
* Spring XML
  * src/test/resource/com/buildria/camel/example/eip/  

また、AggregatorBasicTest.java が使用するSpring XMLは、AggregatorBasicTest-context.xmlです。

## 動かし方

AggregatorBasicTest.java を起動するのは、

  mvn test -Dcom.buildria.camel.exmaple.eip.aggregator.AggregatorBasicTest

を実行してください。

