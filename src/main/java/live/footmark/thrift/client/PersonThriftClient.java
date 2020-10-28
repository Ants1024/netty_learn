package live.footmark.thrift.client;

import live.footmark.thrift.DataExcption;
import live.footmark.thrift.Person;
import live.footmark.thrift.PersonService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-27 22:03
 **/
public class PersonThriftClient {
    public static void main(String[] args) {
        TFramedTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 900);
        try {
            TCompactProtocol protocol = new TCompactProtocol(transport);
            PersonService.Client client = new PersonService.Client(protocol);
            transport.open();
            //client.findPersonInfo(1);
            Person person = new Person();
            person.setId(1);
            person.setName("小黑");
            person.setAge(13);
            client.addPerson(person);
            Person personInfo = client.findPersonInfo(1);
            System.out.println(personInfo.toString());
        }catch (DataExcption dataExcption){
            System.out.println(dataExcption.getMessage());
        }catch (TException e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }


    }
}
