namespace java live.footmark.thrift

typedef i32 int

typedef i64 long

typedef string String

struct Person {
    1: optional int id,
    2: optional String name,
    3: optional int age
}

exception DataExcption{
    // required 属性的参数是必须传值，否则会抛出 Socket is closed by peer.
    1: required String message,
    2: optional String callStack,
    3: optional String date
}

service PersonService {

     Person findPersonInfo(1: required int id)throws(1: DataExcption e) ,
     //参数如果是可选 类型需要省略optional关键字否则会编译失败
     void updatePerson(1: required int id,2:  String name)throws(1:DataExcption e),

     void addPerson(1: required Person person)
}


