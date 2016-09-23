package lol.events;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

/**
 * 
 * 2016年8月5日 下午4:42:38
 */
public class BlackListNotifier implements ApplicationListener<BlackListEvent> {

    private String notificationAddress;

    public void setNotificationAddress(String notificationAddress) {
        this.notificationAddress = notificationAddress;
    }
    @EventListener
    public void onApplicationEvent(BlackListEvent event) {
        // notify appropriate parties via notificationAddress...
    	System.out.println("我要报警了"+this.notificationAddress);
    }

}
