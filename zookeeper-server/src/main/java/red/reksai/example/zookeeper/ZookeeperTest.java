package red.reksai.example.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 原生zookeeper
 * vm options : -Dlog4j.configuration=file:/Users/gnehcgnaw/Environments/apache-zookeeper-3.5.5/conf/log4j.properties -Dzookeeper.admin.enableServer=false
 * program arguments : /Users/gnehcgnaw/Environments/apache-zookeeper-3.5.5/conf/zoo.cfg
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/8/29 22:53
 */
public class ZookeeperTest {
    public static void main(String[] args) {
        String connectString = "127.0.0.1:2181/app/a";
        int sessionTimeout = 5000;
        try {
            ZooKeeper zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("连接成功："+event);
                }
            });

            /**
             * 如果没有指定ACL：（ZooDefs.Ids.OPEN_ACL_UNSAFE），会出现
             * org.apache.zookeeper.KeeperException$MarshallingErrorException: KeeperErrorCode = MarshallingError for /testnode
             * 	at org.apache.zookeeper.KeeperException.create(KeeperException.java:104)
             * 	at org.apache.zookeeper.KeeperException.create(KeeperException.java:54)
             * 	at org.apache.zookeeper.ZooKeeper.create(ZooKeeper.java:1538)
             * 	at red.reksai.example.zookeeper.ZookeeperClientTest.main(ZookeeperClientTest.java:22)
             */
            String responseMessage = zooKeeper.create("/testnode", "123456".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
            System.out.println(responseMessage);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
