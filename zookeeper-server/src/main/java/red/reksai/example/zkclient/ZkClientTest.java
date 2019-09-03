package red.reksai.example.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.apache.log4j.PropertyConfigurator;
import org.apache.zookeeper.CreateMode;

/**
 *
 * ZkClient测试
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/8/30 11:24
 */
public class ZkClientTest {

    public static void main(String[] args) {
        PropertyConfigurator.configure("/Users/gnehcgnaw/Environments/apache-zookeeper-3.5.5/conf/log4j.properties");
        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
        String s = zkClient.create("/testnode3", "123", CreateMode.PERSISTENT);

    }
}
