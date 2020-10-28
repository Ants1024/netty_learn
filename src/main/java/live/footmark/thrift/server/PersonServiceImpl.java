package live.footmark.thrift.server;

import io.netty.util.internal.StringUtil;
import live.footmark.thrift.DataExcption;
import live.footmark.thrift.Person;
import live.footmark.thrift.PersonService;
import org.apache.thrift.TException;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-27 21:36
 **/
public class PersonServiceImpl implements PersonService.Iface {

    private static Map<Integer, Person> personMap= new HashMap();


    @Override
    public Person findPersonInfo(int id) throws DataExcption, TException {
        Person person = getPerson(id);
        return person;
    }

    @Override
    public void updatePerson(int id, String name) throws DataExcption, TException {
        Person person = getPerson(id);
        if(!StringUtil.isNullOrEmpty(name)){
            person.setName(name);
        }
        System.out.println("修改成功");
    }

    @Override
    public void addPerson(Person person) throws TException {
        if(personMap.containsKey(person.getId())){
            throw new DataExcption().setMessage("Id已存在");
        }
        personMap.put(person.getId(),person);
        System.out.println("保存成功");
    }

    private Person getPerson(int id) throws DataExcption {
        Person person = personMap.get(id);
        if(person==null){
            throw new DataExcption().setMessage("无效Id");
        }
        return person;
    }

}
