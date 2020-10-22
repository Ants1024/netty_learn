package live.footmark.proto;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-21 15:57
 **/
public class ProtoDemo {

    public static void main (String[] args) throws Exception{
        //使用构造器创建对象资源
        AddressBookProto.Person person = AddressBookProto.Person.newBuilder().
                setEmail("wwww.xxxx@qq.con").setId(66).setName("你好").build();
        //序列化为字节数组用于网络传输
        byte[] message = person.toByteArray();
        //反序列化为对象
        AddressBookProto.Person person2 = AddressBookProto.Person.parseFrom(message);

        System.out.println(person2.getId());
        System.out.println(person2.getName());
        System.out.println(person2.getEmail());
    }
}
