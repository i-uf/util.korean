# util.korean v1.1.0 (Korean Library)


[![Maven](https://img.shields.io/badge/Maven-v1.1.0-blue)](#installation)

## Features
- Divide
  - `Korean.boonhae("안녕")` -> `ㅇㅏㄴㄴㅕㅇ`
- Merge
  - `Korean.hapche("ㅇㅏㄴㄴㅕㅇ")` -> `안녕`
- Invert(Qwerty <-> Korean 2-set)
  - `Korean.banjeon("안녕")` -> `dkssud`
- Search
  - `Korean.basicSearch(new String[] { "김치", "기차", "기러기" }, "기", dest)` -> `{ "기차", "기러기" }`

## Installation

[![MavenCentral](https://img.shields.io/badge/Central-v1.1.0-green)](https://central.sonatype.com/artifact/io.github.i-uf/util.korean)

Maven
```maven
<dependency>
    <groupId>io.github.i-uf</groupId>
    <artifactId>util.korean</artifactId>
    <version>1.1.0</version>
</dependency>

```

Gradle
```gradle
implementation("io.github.i-uf:util.korean:1.1.0")
```
